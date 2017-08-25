package org.n52.sw.suite.db.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.n52.series.db.DataModelUtil;
import org.n52.series.db.beans.PhenomenonEntity;
import org.n52.shetland.ogc.om.AbstractPhenomenon;
import org.n52.shetland.ogc.om.OmCompositePhenomenon;
import org.n52.shetland.ogc.om.OmObservableProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PhenomenonDao
        extends
        org.n52.series.db.dao.PhenomenonDao implements Dao, TimeCreator {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(PhenomenonDao.class);
    private DaoFactory daoFactory;

    public PhenomenonDao(DaoFactory daoFactory, Session session) {
        super(session);
        this.daoFactory = daoFactory;
    }

    @Override
    public DaoFactory getDaoFactory() {
        return daoFactory;
    }

    @Override
    public Session getSession() {
        return session;
    }

    public Map<String, PhenomenonEntity> getOrInsertAsMap(Collection<? extends AbstractPhenomenon> observableProperties,
            boolean hiddenChild) {
        Map<String, PhenomenonEntity> existing = getExisting(observableProperties);
        insertNonExisting(observableProperties, hiddenChild, existing);
        insertHierachy(observableProperties, existing);
        return existing;
    }

    protected Map<String, PhenomenonEntity> getExistingObservableProperties(
            Collection<? extends AbstractPhenomenon> observableProperty) {
        List<String> identifiers = getIdentifiers(observableProperty);
        return getAsMap(identifiers);
    }

    protected List<String> getIdentifiers(Collection<? extends AbstractPhenomenon> observableProperty) {
        List<String> identifiers = new ArrayList<>(observableProperty.size());
        for (AbstractPhenomenon sosObservableProperty : observableProperty) {
            identifiers.add(sosObservableProperty.getIdentifier());
            if (sosObservableProperty instanceof OmCompositePhenomenon) {
                OmCompositePhenomenon parent = (OmCompositePhenomenon) sosObservableProperty;
                for (OmObservableProperty child : parent.getPhenomenonComponents()) {
                    identifiers.add(child.getIdentifier());
                }
            }
        }
        return identifiers;
    }

    protected Map<String, PhenomenonEntity> getAsMap(Collection<String> identifiers) {
        List<PhenomenonEntity> obsProps = get(identifiers);
        Map<String, PhenomenonEntity> existing = new HashMap<>(identifiers.size());
        for (PhenomenonEntity obsProp : obsProps) {
            existing.put(obsProp.getIdentifier(), obsProp);
        }
        return existing;
    }

    protected Map<String, PhenomenonEntity> getExisting(
            Collection<? extends AbstractPhenomenon> observableProperty) {
        List<String> identifiers = getIdentifiers(observableProperty);
        return getAsMap(identifiers);
    }

    protected void insertNonExisting(Collection<? extends AbstractPhenomenon> observableProperties, boolean hiddenChild,
            Map<String, PhenomenonEntity> existing)
            throws HibernateException {
        for (AbstractPhenomenon sosObsProp : observableProperties) {
            insertNonExisting(sosObsProp, hiddenChild, existing);
        }
    }

    protected void insertNonExisting(AbstractPhenomenon sosObsProp, boolean hiddenChild,
            Map<String, PhenomenonEntity> existing)
            throws HibernateException {
        if (!existing.containsKey(sosObsProp.getIdentifier())) {
            PhenomenonEntity obsProp = new PhenomenonEntity();
            // FIXME: how to do this???
//            addIdentifierNameDescription(sosObsProp, obsProp);
            obsProp.setHiddenChild(hiddenChild);
            getSession().save(obsProp);
            getSession().flush();
            getSession().refresh(obsProp);
            existing.put(obsProp.getIdentifier(), obsProp);
        }
        if (sosObsProp instanceof OmCompositePhenomenon) {
            insertNonExisting(((OmCompositePhenomenon) sosObsProp).getPhenomenonComponents(), true, existing);
        }
    }

    protected void insertHierachy(Collection<? extends AbstractPhenomenon> observableProperty,
            Map<String, PhenomenonEntity> existing) {
        for (AbstractPhenomenon sosObsProp : observableProperty) {
            if (sosObsProp instanceof OmCompositePhenomenon) {
                insertHierachy((OmCompositePhenomenon) sosObsProp, existing);
            }
        }
    }

    protected void insertHierachy(OmCompositePhenomenon parent, Map<String, PhenomenonEntity> existing)
            throws HibernateException {
        PhenomenonEntity parentObsProp = get(parent.getIdentifier(), existing);
        for (OmObservableProperty child : parent) {
            PhenomenonEntity childObsProp = get(child.getIdentifier(), existing);
            childObsProp.addParent(parentObsProp);
            getSession().update(childObsProp);
        }
        // do not save the parent, as it would result in a duplicate key
        // error...
        getSession().flush();
        getSession().refresh(parentObsProp);
    }

    private PhenomenonEntity get(String identifier, Map<String, PhenomenonEntity> observableProperties) {
        // TODO check if this is still required
        if (identifier == null) {
            return null;
        }
        PhenomenonEntity observableProperty = observableProperties.get(identifier);
        if (observableProperty != null) {
            return observableProperty;
        }
        observableProperty = get(identifier);
        observableProperties.put(identifier, observableProperty);
        return observableProperty;
    }

    /**
     * Get observable property by identifier
     *
     * @param identifier
     *            The observable property's identifier
     * @return Observable property object
     */
    public PhenomenonEntity get(String identifier) {
        Criteria criteria = getSession().createCriteria(PhenomenonEntity.class)
                .add(Restrictions.eq(PhenomenonEntity.PROPERTY_DOMAIN_ID, identifier));
        LOGGER.debug("QUERY getObservablePropertyForIdentifier(identifier): {}",
                DataModelUtil.getSqlString(criteria));
        return (PhenomenonEntity) criteria.uniqueResult();
    }

    /**
     * Get observable property objects for observable property identifiers
     *
     * @param identifiers
     *            Observable property identifiers
     * @return Observable property objects
     */
    @SuppressWarnings("unchecked")
    public List<PhenomenonEntity> get(Collection<String> identifiers) {
        Criteria criteria =
                getSession().createCriteria(PhenomenonEntity.class).add(
                        Restrictions.in(PhenomenonEntity.PROPERTY_DOMAIN_ID, identifiers));
        LOGGER.debug("QUERY getObservableProperties(identifiers): {}", DataModelUtil.getSqlString(criteria));
        return criteria.list();
    }

}
