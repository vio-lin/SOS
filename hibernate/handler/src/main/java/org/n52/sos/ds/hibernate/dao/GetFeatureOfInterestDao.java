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
package org.n52.sos.ds.hibernate.dao;

import java.util.Locale;
import java.util.Map;

import javax.inject.Inject;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.n52.faroe.annotation.Setting;
import org.n52.iceland.ds.ConnectionProvider;
import org.n52.iceland.i18n.I18NSettings;
import org.n52.iceland.ogc.ows.OwsServiceMetadataRepository;
import org.n52.janmayen.http.HTTPStatus;
import org.n52.shetland.ogc.gml.AbstractFeature;
import org.n52.shetland.ogc.ows.exception.NoApplicableCodeException;
import org.n52.shetland.ogc.ows.exception.OwsExceptionReport;
import org.n52.shetland.ogc.sos.request.GetFeatureOfInterestRequest;
import org.n52.sos.ds.FeatureQueryHandler;
import org.n52.sos.ds.FeatureQueryHandlerQueryObject;
import org.n52.sos.ds.hibernate.HibernateSessionHolder;

public class GetFeatureOfInterestDao implements org.n52.sos.ds.dao.GetFeatureOfInterestDao {

    private HibernateSessionHolder sessionHolder;

    private OwsServiceMetadataRepository serviceMetadataRepository;

    private FeatureQueryHandler featureQueryHandler;

    private Locale defaultLanguage;

    @Inject
    public void setServiceMetadataRepository(OwsServiceMetadataRepository repo) {
        this.serviceMetadataRepository = repo;
    }

    @Inject
    public void setConnectionProvider(ConnectionProvider connectionProvider) {
        this.sessionHolder = new HibernateSessionHolder(connectionProvider);
    }

    @Inject
    public void setFeatureQueryHandler(FeatureQueryHandler featureQueryHandler) {
        this.featureQueryHandler = featureQueryHandler;
    }

    @Setting(I18NSettings.I18N_DEFAULT_LANGUAGE)
    public void setDefaultLanguage(String defaultLanguage) {
        this.defaultLanguage = new Locale(defaultLanguage);
    }

    @Override
    public Map<String, AbstractFeature> getFeatureOfInterest(GetFeatureOfInterestRequest request) throws OwsExceptionReport {
        Session session = null;
        try {
            session = sessionHolder.getSession();
            FeatureQueryHandlerQueryObject queryObject = new FeatureQueryHandlerQueryObject(session)
                    .setFeatures(request.getFeatureIdentifiers()).setVersion(request.getVersion())
                    .setI18N(getRequestedLocale(request));
            return featureQueryHandler.getFeatures(queryObject);
        } catch (HibernateException he) {
            throw new NoApplicableCodeException().causedBy(he).withMessage("Error while querying observation data!")
                    .setStatus(HTTPStatus.INTERNAL_SERVER_ERROR);
        } finally {
            sessionHolder.returnSession(session);
        }
    }

    @Override
    public Locale getDefaultLanguage() {
        return defaultLanguage;
    }

}