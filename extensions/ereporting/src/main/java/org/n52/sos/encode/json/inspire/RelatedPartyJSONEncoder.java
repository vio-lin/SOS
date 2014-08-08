/**
 * Copyright (C) 2012-2014 52°North Initiative for Geospatial Open Source
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
package org.n52.sos.encode.json.inspire;

import org.n52.sos.util.AQDJSONConstants;
import org.n52.sos.encode.json.JSONEncoder;
import org.n52.sos.inspire.aqd.RelatedParty;
import org.n52.sos.ogc.ows.OwsExceptionReport;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * TODO JavaDoc
 *
 * @author Christian Autermann
 */
public class RelatedPartyJSONEncoder extends JSONEncoder<RelatedParty> {

    public RelatedPartyJSONEncoder() {
        super(RelatedParty.class);
    }

    @Override
    public JsonNode encodeJSON(RelatedParty t)
            throws OwsExceptionReport {
        ObjectNode j = nodeFactory().objectNode();
        j.put(AQDJSONConstants.CONTACT, encodeObjectToJson(t.getContact()));
        j.put(AQDJSONConstants.INDIVIDUAL_NAME, encodeObjectToJson(t.getIndividualName()));
        j.put(AQDJSONConstants.ORGANISATION_NAME, encodeObjectToJson(t.getOrganisationName()));
        j.put(AQDJSONConstants.POSITION_NAME, encodeObjectToJson(t.getPositionName()));
        j.put(AQDJSONConstants.ROLES, encodeObjectToJson(t.getRoles()));
        return j;
    }

}
