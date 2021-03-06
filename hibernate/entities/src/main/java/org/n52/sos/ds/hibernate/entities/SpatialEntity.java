/*
 * Copyright (C) 2012-2018 52°North Initiative for Geospatial Open Source
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
package org.n52.sos.ds.hibernate.entities;

import org.n52.sos.ds.hibernate.entities.HibernateRelations.HasCoordinate;
import org.n52.sos.ds.hibernate.entities.HibernateRelations.HasGeometry;
import org.n52.sos.ds.hibernate.entities.HibernateRelations.HasIdentifier;

import org.locationtech.jts.geom.Geometry;

/**
 * @author <a href="mailto:e.h.juerrens@52north.org">Eike Hinderk J&uuml;rrens</a>
 *
 * @since 4.0.0
 */
public abstract class SpatialEntity extends DescriptionXmlEntity implements HasGeometry, HasCoordinate, HasIdentifier {

    private static final long serialVersionUID = 2051886640846877840L;
    private Geometry geom;
    private Object longitude;
    private Object latitude;
    private Object altitude;
    private int srid;

    @Override
    public Geometry getGeom() {
        return geom;
    }

    @Override
    public void setGeom(Geometry geom) {
        this.geom = geom;
    }

    @Override
    public Object getLongitude() {
        return longitude;
    }

    @Override
    public SpatialEntity setLongitude(final Object longitude) {
        this.longitude = longitude;
        return this;
    }

    @Override
    public Object getLatitude() {
        return latitude;
    }

    @Override
    public SpatialEntity setLatitude(final Object latitude) {
        this.latitude = latitude;
        return this;
    }

    @Override
    public Object getAltitude() {
        return altitude;
    }

    @Override
    public SpatialEntity setAltitude(final Object altitude) {
        this.altitude = altitude;
        return this;
    }

    @Override
    public int getSrid() {
        return srid;
    }

    @Override
    public void setSrid(int srid) {
        this.srid = srid;
    }

    @Override
    public boolean isSpatial() {
        return HasGeometry.super.isSpatial() || HasCoordinate.super.isSpatial();
    }

}
