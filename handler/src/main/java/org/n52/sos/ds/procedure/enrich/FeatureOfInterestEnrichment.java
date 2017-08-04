/*
 * Copyright (C) 2012-2017 52°North Initiative for Geospatial Open Source
 * Software GmbH
 *
 * This program is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 as published
 * by the Free Software Foundation.
 *
 * If the program is linked with libraries which are licensed under one of
 * the following licenses, the combination of the program with the linked
 * library is not considered a "derivative work" of the program:
 *
 *     - Apache License, version 2.0
 *     - Apache Software License, version 1.0
 *     - GNU Lesser General Public License, version 3
 *     - Mozilla Public License, versions 1.0, 1.1 and 2.0
 *     - Common Development and Distribution License (CDDL), version 1.0
 *
 * Therefore the distribution of the program linked with libraries licensed
 * under the aforementioned licenses, is permitted by the copyright holders
 * if the distribution is compliant with both the GNU General Public
 * License version 2 and the aforementioned licenses.
 *
 * This program is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General
 * Public License for more details.
 */
package org.n52.sos.ds.procedure.enrich;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.n52.io.request.IoParameters;
import org.n52.io.request.RequestSimpleParameterSet;
import org.n52.proxy.db.dao.ProxyFeatureDao;
import org.n52.series.db.DataAccessException;
import org.n52.series.db.beans.FeatureEntity;
import org.n52.series.db.dao.DbQuery;
import org.n52.shetland.ogc.gml.AbstractFeature;
import org.n52.shetland.ogc.gml.CodeWithAuthority;
import org.n52.shetland.ogc.om.features.samplingFeatures.InvalidSridException;
import org.n52.shetland.ogc.om.features.samplingFeatures.SamplingFeature;
import org.n52.shetland.ogc.ows.exception.NoApplicableCodeException;
import org.n52.shetland.ogc.ows.exception.OwsExceptionReport;
import org.n52.sos.ds.FeatureQueryHandlerQueryObject;
import org.n52.sos.ds.ProxyQueryHelper;
import org.n52.sos.util.GeometryHandler;
import org.n52.sos.util.SosHelper;

import com.google.common.base.Strings;
import com.google.common.collect.Sets;

/**
 * TODO JavaDoc
 *
 * @author <a href="mailto:c.autermann@52north.org">Christian Autermann</a>
 */
public class FeatureOfInterestEnrichment extends ProcedureDescriptionEnrichment implements ProxyQueryHelper {

    private GeometryHandler geometryHandler;

    @Override
    public void enrich() throws OwsExceptionReport {
        Collection<String> featureOfInterestIDs = getFeatureOfInterestIDs();
        getDescription().addFeaturesOfInterest(featureOfInterestIDs);
        getDescription().addFeaturesOfInterestMap(getAbstractFeaturesMap(featureOfInterestIDs));
    }

    @Override
    public boolean isApplicable() {
        return super.isApplicable() && procedureSettings().isEnrichWithFeatures();
    }

    /**
     * Get featureOfInterests for procedure and version
     *
     * @return Collection with featureOfInterests
     *
     * @throws OwsExceptionReport If an error occurs
     */
    private Collection<String> getFeatureOfInterestIDs()
            throws OwsExceptionReport {
        Set<String> features = Sets.newHashSet();
        // add cache map for proc/fois and get fois for proc
        for (String offering : getCache().getOfferingsForProcedure(getIdentifier())) {
            // don't include features for offerings which this procedure is a
            // hidden child of
            if (!getCache().getHiddenChildProceduresForOffering(offering).contains(getIdentifier())) {
                features.addAll(getCache().getFeaturesOfInterestForOffering(offering));
            }
        }
        return SosHelper.getFeatureIDs(features, getVersion());
    }

    private Map<String, AbstractFeature> getAbstractFeaturesMap(Collection<String> featureOfInterestIDs)
            throws OwsExceptionReport {
        FeatureQueryHandlerQueryObject object = new FeatureQueryHandlerQueryObject(getSession());
        object.setFeatures(featureOfInterestIDs);
        if (isSetLocale()) {
            object.setI18N(getLocale());
        }
        try {
            return createFeatures(new HashSet<>(
                    new ProxyFeatureDao(getSession()).getAllInstances(createDbQuery(featureOfInterestIDs))));
        } catch (InvalidSridException | DataAccessException e) {
            throw new NoApplicableCodeException().causedBy(e)
                    .withMessage("Error while querying data for GetFeatureOfInterest!");
        }
    }

    private DbQuery createDbQuery(Collection<String> featureOfInterestIDs) {
        RequestSimpleParameterSet rsps = new RequestSimpleParameterSet();
        if (featureOfInterestIDs != null && !featureOfInterestIDs.isEmpty()) {
            rsps.setParameter(IoParameters.FEATURES, IoParameters.getJsonNodeFrom(listToString(featureOfInterestIDs)));
        }
        rsps.setParameter(IoParameters.MATCH_DOMAIN_IDS, IoParameters.getJsonNodeFrom(true));
        return new DbQuery(IoParameters.createFromQuery(rsps));
    }

    private Map<String, AbstractFeature> createFeatures(Set<FeatureEntity> featureEntities) throws InvalidSridException, OwsExceptionReport {
        final Map<String, AbstractFeature> map = new HashMap<>(featureEntities.size());
        for (final FeatureEntity feature : featureEntities) {
            final AbstractFeature abstractFeature = createFeature(feature);
            map.put(abstractFeature.getIdentifier(), abstractFeature);
        }
        return map;
    }

    private AbstractFeature createFeature(FeatureEntity feature) throws InvalidSridException, OwsExceptionReport {
        final SamplingFeature sampFeat = new SamplingFeature(new CodeWithAuthority(feature.getDomainId()));
        if (feature.isSetName()) {
            sampFeat.addName(feature.getName());
        }
        if (!Strings.isNullOrEmpty(feature.getDescription())) {
            sampFeat.setDescription(feature.getDescription());
        }
        if (feature.isSetGeometry()) {
            if (geometryHandler != null) {
            sampFeat.setGeometry(geometryHandler.switchCoordinateAxisFromToDatasourceIfNeeded(feature.getGeometry()));
            } else {
                sampFeat.setGeometry(feature.getGeometry());
            }
        }
        final Set<FeatureEntity> parentFeatures = feature.getParents();
        if (parentFeatures != null && !parentFeatures.isEmpty()) {
            final List<AbstractFeature> sampledFeatures = new ArrayList<>(parentFeatures.size());
            for (final FeatureEntity parentFeature : parentFeatures) {
                sampledFeatures.add(createFeature(parentFeature));
            }
            sampFeat.setSampledFeatures(sampledFeatures);
        }
        return sampFeat;
    }

    public FeatureOfInterestEnrichment setGeometryHandler(GeometryHandler geometryHandler) {
        this.geometryHandler = geometryHandler;
        return this;
    }
}