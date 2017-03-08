/**
 * ﻿Copyright (C) 2013
 * by 52 North Initiative for Geospatial Open Source Software GmbH
 *
 * Contact: Andreas Wytzisk
 * 52 North Initiative for Geospatial Open Source Software GmbH
 * Martin-Luther-King-Weg 24
 * 48155 Muenster, Germany
 * info@52north.org
 *
 * This program is free software; you can redistribute and/or modify it under
 * the terms of the GNU General Public License version 2 as published by the
 * Free Software Foundation.
 *
 * This program is distributed WITHOUT ANY WARRANTY; even without the implied
 * WARRANTY OF MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with
 * this program (see gnu-gpl v2.txt). If not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA 02111-1307, USA or
 * visit the Free Software Foundation web page, http://www.fsf.org.
 */
package org.n52.sos.converter;

import java.util.Collections;
import java.util.List;

import org.hamcrest.CoreMatchers;
import org.hamcrest.core.Is;
import org.hamcrest.core.IsEqual;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.n52.schetland.uvf.UVFConstants;
import org.n52.sos.convert.RequestResponseModifierFacilitator;
import org.n52.sos.convert.RequestResponseModifierKeyType;
import org.n52.sos.exception.ConfigurationException;
import org.n52.sos.exception.ows.NoApplicableCodeException;
import org.n52.sos.ogc.ows.OWSConstants;
import org.n52.sos.ogc.ows.OwsExceptionReport;
import org.n52.sos.ogc.sos.Sos2Constants;
import org.n52.sos.ogc.sos.SosConstants;
import org.n52.sos.ogc.swe.simpleType.SweCount;
import org.n52.sos.ogc.swes.SwesExtensionImpl;
import org.n52.sos.ogc.swes.SwesExtensions;
import org.n52.sos.request.GetObservationRequest;
import org.n52.sos.request.RequestContext;
import org.n52.sos.util.CollectionHelper;
import org.n52.sos.util.http.MediaTypes;

/**
 * @author <a href="mailto:e.h.juerrens@52north.org">Eike Hinderk J&uuml;rrens</a>
 *
 */
public class UVFRequestModifierTest {

    @Rule
    public ExpectedException exp = ExpectedException.none();

    private UVFRequestModifier modifier;
    
    private GetObservationRequest request;

    @Before
    public void setUp() throws Exception {
        modifier = new UVFRequestModifier();
        modifier.setDefaultCRS(UVFConstants.ALLOWED_CRS.get(0));
        request = new GetObservationRequest();
        RequestContext requestContext = new RequestContext();
        requestContext.setAcceptType(Collections.singletonList(UVFConstants.CONTENT_TYPE_UVF));
        request.setRequestContext(requestContext);
        request.setFeatureIdentifiers(Collections.singletonList("test-feature-of-interest"));
        request.setProcedures(Collections.singletonList("test-procedure"));
        request.setObservedProperties(Collections.singletonList("test-observed-property"));
        SweCount crsExtension = (SweCount) new SweCount()
                .setValue(UVFConstants.ALLOWED_CRS.get(0))
                .setIdentifier(OWSConstants.AdditionalRequestParams.crs.name());
        request.addExtension(new SwesExtensionImpl<SweCount>().setValue(crsExtension));
    }

    @Test
    public void shouldModifyOnlySOS200GetObservationRequests() {
        Assert.assertThat(modifier.getRequestResponseModifierKeyTypes().size(), Is.is(1));
        final RequestResponseModifierKeyType key = modifier.getRequestResponseModifierKeyTypes().iterator().next();
        Assert.assertThat(key.getService(), Is.is(SosConstants.SOS));
        Assert.assertThat(key.getVersion(), Is.is(Sos2Constants.SERVICEVERSION));
        Assert.assertThat(key.getRequest(), Is.is(CoreMatchers.instanceOf(GetObservationRequest.class)));
    }
    
    @Test
    public void shouldThrowExceptionIfFormatUVFAndMoreThanOneFeatureOfInterestRequested() throws OwsExceptionReport {
        exp.expect(NoApplicableCodeException.class);
        exp.expectMessage("When requesting UVF format, the request MUST have "
                + "ONE procedure, ONE observedProperty, and ONE featureOfInterest.");

        request.setFeatureIdentifiers(CollectionHelper.list("foi-1", "foi-2"));
        modifier.modifyRequest(request);
    }

    @Test
    public void shouldThrowExceptionIfFormatUVFAndNoFeatureOfInterestRequested() throws OwsExceptionReport {
        exp.expect(NoApplicableCodeException.class);
        exp.expectMessage("When requesting UVF format, the request MUST have "
                + "ONE procedure, ONE observedProperty, and ONE featureOfInterest.");

        final List<String> emptyList = Collections.emptyList();
        request.setFeatureIdentifiers(emptyList);
        modifier.modifyRequest(request);
    }

    @Test
    public void shouldThrowExceptionIfFormatUVFAndMoreThanOneProcedureRequested() throws OwsExceptionReport {
        exp.expect(NoApplicableCodeException.class);
        exp.expectMessage("When requesting UVF format, the request MUST have "
                + "ONE procedure, ONE observedProperty, and ONE featureOfInterest.");

        request.setProcedures(CollectionHelper.list("procedure-1", "procedure-2"));
        modifier.modifyRequest(request);
    }

    @Test
    public void shouldThrowExceptionIfFormatUVFAndNoProcedureRequested() throws OwsExceptionReport {
        exp.expect(NoApplicableCodeException.class);
        exp.expectMessage("When requesting UVF format, the request MUST have "
                + "ONE procedure, ONE observedProperty, and ONE featureOfInterest.");

        final List<String> emptyList = Collections.emptyList();
        request.setProcedures(emptyList);
        modifier.modifyRequest(request);
    }

    @Test
    public void shouldThrowExceptionIfFormatUVFAndMoreThanOneObservedPropertyRequested() throws OwsExceptionReport {
        exp.expect(NoApplicableCodeException.class);
        exp.expectMessage("When requesting UVF format, the request MUST have "
                + "ONE procedure, ONE observedProperty, and ONE featureOfInterest.");

        request.setObservedProperties(CollectionHelper.list("obs-prop-1", "obs-prop-2"));
        modifier.modifyRequest(request);
    }

    @Test
    public void shouldThrowExceptionIfFormatUVFAndNoObservedPropertyRequested() throws OwsExceptionReport {
        exp.expect(NoApplicableCodeException.class);
        exp.expectMessage("When requesting UVF format, the request MUST have "
                + "ONE procedure, ONE observedProperty, and ONE featureOfInterest.");

        final List<String> emptyList = Collections.emptyList();
        request.setObservedProperties(emptyList);
        modifier.modifyRequest(request);
    }

    @Test
    public void shouldNotModifyRequestNotForUVF() throws OwsExceptionReport{
        request.getRequestContext().setContentType(MediaTypes.APPLICATION_JSON.toString());
        
        GetObservationRequest modifiedRequest = modifier.modifyRequest(request);
        
        Assert.assertThat(modifiedRequest, IsEqual.equalTo(modifiedRequest));
    }

    @Test
    public void shouldNotModifyValidRequest() throws OwsExceptionReport {
        GetObservationRequest modifiedRequest = modifier.modifyRequest(request);
        
        Assert.assertThat(modifiedRequest, IsEqual.equalTo(request));
    }

    @Test
    public void shouldReturnValidRequestResponseModifierFacilitator() {
        RequestResponseModifierFacilitator facilitator = modifier.getFacilitator();
        
        Assert.assertThat(facilitator.isAdderRemover(), Is.is(false));
        Assert.assertThat(facilitator.isMerger(), Is.is(false));
        Assert.assertThat(facilitator.isSplitter(), Is.is(false));
    }

    @Test
    public void shouldThrowConfigExcetionIfDefaultCrsEpsgIsBelowAllowedMinumum() {
        exp.expect(ConfigurationException.class);
        exp.expectMessage("Setting with key 'uvf.default.crs': '31465' outside allowed interval ]31466, 31469[.");

        modifier.setDefaultCRS(31465);
    }

    @Test
    public void shouldThrowConfigExcetionIfDefaultCrsEpsgIsAbovenAllowedMaximum() {
        exp.expect(ConfigurationException.class);
        exp.expectMessage("Setting with key 'uvf.default.crs': '31470' outside allowed interval ]31466, 31469[.");

        modifier.setDefaultCRS(31470);
    }

    @Test
    public void shouldAddExtensionWithDefaultCRSIfNotPresent() throws OwsExceptionReport {
        request.setExtensions(null);

        GetObservationRequest modifiedRequest = modifier.modifyRequest(request);
        final SwesExtensions extensions = modifiedRequest.getExtensions();

        Assert.assertThat(extensions.getExtensions().size(), Is.is(1));
        Assert.assertThat(extensions.containsExtension(OWSConstants.AdditionalRequestParams.crs), Is.is(true));
        Assert.assertThat(
                ((SweCount)extensions.getExtension(OWSConstants.AdditionalRequestParams.crs).getValue()).getValue(),
                Is.is(UVFConstants.ALLOWED_CRS.get(0)));
    }

    @Test
    public void shouldThrowExceptionWhenRequestedCRSIsOutsideAllowedValues() throws OwsExceptionReport {
        ((SweCount)request.getExtension(OWSConstants.AdditionalRequestParams.crs).getValue()).setValue(42);
        exp.expect(NoApplicableCodeException.class);
        exp.expectMessage("When requesting UVF format, the request MUST have a CRS of the German GK bands, e.g. "
                + "'[31466, 31467, 31468, 31469]'. Requested was: '42'.");

        modifier.modifyRequest(request);
    }
}
