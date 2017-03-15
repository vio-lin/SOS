# 52°North Sensor Observation Service (SOS)
| Master | Develop | OpenHUB |
| ------------- | ------------- | ------------- |
| [![Master Build Status](https://travis-ci.org/52North/SOS.png?branch=master)](https://travis-ci.org/52North/SOS) | [![Develop Build Status](https://travis-ci.org/52North/SOS.png?branch=develop)](https://travis-ci.org/52North/SOS) | [![OpenHUB](https://www.openhub.net/p/SensorObservationService/widgets/project_thin_badge.gif)](https://www.openhub.net/p/SensorObservationService) |

## Description

**Standardized, web-based upload and download of sensor data and sensor metadata**

*The 52°North Sensor Observation Service (SOS) provides an interoperable web-based interface for inserting and querying sensor data and sensor descriptions. It aggregates observations from live in-situ sensors as well as historical data sets (time series data).*

The 52°North SOS is a reference implementation of the
[OGC Sensor Observation Service specification (version 2.0)](https://portal.opengeospatial.org/files/?artifact_id=47599), an interoperable interface for publishing and querying sensor data and metadata. 
It was implemented during the [OGC Web Services Testbed,  Phase 9 (OWS-9)](http://www.ogcnetwork.net/ows-9) 
and tested to be compliant to this specification within the [OGC CITE testing](http://cite.opengeospatial.org/test_engine) in December of 2012.

The 52°North SOS enables the user to:

 - insert and retrieve georeferenced observation data
 - access georeferenced measurement data in a standardized format (ISO/OGC Observation and Measurement - O&M 2.0, OGC WaterML 2.0)
 - insert and retrieve sensor descriptions (encoded according to the OGC SensorML standard - SML 1.0.1, SML 2.0)
 - publish measurement data (near real-time, as well as archived data) 

An extension accomodates additional INSPIRE Directive requirements, thus ensuring interoperable exchange of any kind of observation data across political, administrative and organizational boundaries. Client applications, such as [Helgoland](http://www-neu.52north.org/software/software-projects/helgoland/) enable analysis and visualization of the measurement data provided via the SOS server.
 
### Features

  - [INSPIRE Download Service](http://inspire.ec.europa.eu/id/document/tg/download-sos) for measurement data
  - [Guidelines for the use of Observations & Measurements and Sensor Web Enablement-related standards in INSPIRE (D2.9)](http://inspire.ec.europa.eu/id/document/tg/d2.9-o%26m-swe)
  - [OGC Hydro Profile] (http://docs.opengeospatial.org/bp/14-004r1/14-004r1.html), including GetDataAvailability operation
  - [SensorML 1.0.1 and 2.0](http://www.opengeospatial.org/standards/sensorml)
  - [WaterML 2.0](http://www.opengeospatial.org/standards/waterml)
  - [AQD e-Reporting flows E](https://www.eionet.europa.eu/aqportal/requirements/dataflows)
  - Multiple DB support (by using the [Hibernate ORM framework](http://hibernate.org/orm/))
  - Bundle including [Sensor Web REST-API](https://github.com/52North/series-rest-api) and [helgoland](https://github.com/52North/helgoland/)
  - DeleteObservation operation, to delete observation by identifier (not part of the SOS 2.0 specification)
  - [Efficient XML Interchange (EXI) 1.0 format](http://52north.org/communities/sensorweb/sos/index.html#www.w3.orgTRexi)

## License

 The 52°North SOS is published under the [GNU General Public License, Version 2 (GPLv2)](http://www.gnu.org/licenses/gpl-2.0.html)

## Changelog

 The latest changes, additions, bugfixes, etc. can be found in the [RELEASE-NOTES](https://github.com/52North/SOS/blob/website-markdowns/RELEASE-NOTES)

## Quick Start

 Get started - the [installation guide](https://wiki.52north.org/bin/view/SensorWeb/SensorObservationServiceIVDocumentation#Installation) helps you install and configure the 52°North SOS.

## User Guide

 An OGC SOS [tutorial](http://www.ogcnetwork.net/SOS_2_0/tutorial) is available.

## Demo

    http://sensorweb.demo.52north.org/sensorwebtestbed/

## References

 - [IRCEL-CELINE](www.irceline.be/) (Belgium): *Current and archived air quality data for all of Belgium*
 - [Wupperverband](https://www.wupperverband.de/) (Germnay): *Regional water board providing a multitude of hydrological measurment data with the help of SOS standards*
 - [Swedish EPA](http://www.swedishepa.se/)/[IVL](http://www.ivl.se/)/[SMHI](http://www.smhi.se/) (Schweden): *Current and archived air quality data for all of Sweden, as well as delivery this data to the European Protection Agency*
 - [RIVM](http://www.rivm.nl/) (Netherlands): *Current and archived air quality data for all of the Netherlands*
 - [Lithuanian EPA](http://gamta.lt/cms/index?lang=en) (Lithuania): *Current and archived air quality data for all of Lithuania*
 - [European Environment Agency (EEA)](http://www.eea.europa.eu/): *Use of SOS interface to collect data from the member countries, as well as to publish the collective data*
 - [PEGELONLINE](https://www.pegelonline.wsv.de/) (Germany): *Interoperable publication of te federal waterways' hydrological measurment data.*

## Contact

 - Carsten Hollmann (c.hollmann@52north.org)
 - Christian Autermann (c.autermann@52north.org)
 - Eike Hinderk Jürrens [@EHJ-52n](e.h.juerrens@52north.org)
 
## Support

You can get support in the community mailing list and forums:

    http://52north.org/resources/mailing-lists-and-forums/

## Contributing

You are interesting in contributing the 52°North SOS and you want to pull your changes to the 52N repository to make it available to all?

In that case we need your official permission and for this purpose we have a so called contributors license agreement (CLA) in place. With this agreement you grant us the rights to use and publish your code under an open source license.

A link to the contributors license agreement and further explanations are available here: 

    http://52north.org/about/licensing/cla-guidelines

## Credits

### Contributors

| Name | Organisation |
| ------------- | :-------------: |
| [Carsten Hollmann](http://52north.org/about/52-north-team/25-carsten-hollmann) | [52&deg;North](http://52north.org) |
| [Eike Hinderk J&uuml;rrens](http://52north.org/about/52-north-team/14-eike-hinderk-juerrens) | [52&deg;North](http://52north.org) |
| [Christian Autermann](http://52north.org/about/52-north-team/30-autermann-christian) | [52&deg;North](http://52north.org) |
| [Christoph Stasch](http://52north.org/about/52-north-team/31-stasch-christoph) | [52&deg;North](http://52north.org) |
| Shane StClair | [Axiom Data Science](http://www.axiomdatascience.com) |
| Victor Gonz&aacute;lez | [geomati.co](http://geomati.co/en) |
| Oscar Fonts | [geomati.co](http://geomati.co/en) |
| Carlos Giraldo | [Instituto Tecnol&oacute;gico de Galicia (ITG)](http://www.itg.es/) |
| Alexander Kmoch | [Z_GIS](http://www.zgis.at),  [Universit&auml;t Salzburg</a> (Austria and Germany)](http://www.uni-salzburg.at) |
| Carl Schroedl | Center for Integrated Data Analytics ([CIDA](http://cida.usgs.gov)), [USGS](http://www.usgs.gov) |
| Jordan Walker | Center for Integrated Data Analytics ([CIDA](http://cida.usgs.gov)), [USGS](http://www.usgs.gov) |

### Contributing organizations

<table style="background-color:white;width:100%;">
  <tr>
    <td align="center" style="width:25%;"><a target="_blank" href="http://www.uni-muenster.de/Geoinformatics/en/index.html"><img alt="IfGI"  align="middle" width="200" src="https://raw.githubusercontent.com/52North/SOS/develop/spring/views/src/main/webapp/static/images/contribution/logo_ifgi.png"/></a></td>
    <td align="center" style="width:25%;"><a target="_blank" href="http://www.axiomdatascience.com"><img alt="Axiom Data Science"  align="middle" width="85" src="https://raw.githubusercontent.com/52North/SOS/develop/spring/views/src/main/webapp/static/images/contribution/axiom.png"/></a></td>
    <td align="center" style="width:25%;"><a target="_blank" href="http://geomati.co"><img alt="geomati.co"  align="middle" width="85" src="https://raw.githubusercontent.com/52North/SOS/develop/spring/views/src/main/webapp/static/images/contribution/logo_geomatico_256.png"/></a></td>
    <td align="center" style="width:25%;"><a target="_blank" href="http://www.itg.es/"><img alt="ITG"  align="middle" width="104" src="https://raw.githubusercontent.com/52North/SOS/develop/spring/views/src/main/webapp/static/images/contribution/Logo_ITG_vectorizado.png"/></a></td>
  </tr>
  <tr>
    <td align="center" style="width:25%;"><a target="_blank" href="http://www.zgis.at"><img alt="ZIGS"  align="middle" width="128" src="https://raw.githubusercontent.com/52North/SOS/develop/spring/views/src/main/webapp/static/images/contribution/UniSalzburgZGIS_1.jpg"/></a></td>
    <td align="center" style="width:25%;"><a href="http://52north.org/about/licensing/cla-guidelines">Your logo?!<br/>Get involved!</a></td>
    <td align="center" style="width:25%;"><img alt="Placeholder" align="middle" width="85" src="https://raw.githubusercontent.com/52North/SOS/develop/spring/views/src/main/webapp/static/images/contribution/placeholder.png"/></td>
    <td align="center" style="width:25%;"><img alt="Placeholder" align="middle" width="85" src="https://raw.githubusercontent.com/52North/SOS/develop/spring/views/src/main/webapp/static/images/contribution/placeholder.png"/></td>
  </tr>
</table>

### Funding organizations/projects

<table style="background-color:white;width:100%;">
  <tr>
    <td align="center" style="padding:3px;width:30%;"><a target="_blank" href="http://www.nexosproject.eu/"><img alt="NeXOS - Next generation, Cost-effective, Compact, Multifunctional Web Enabled Ocean Sensor Systems Empowering Marine, Maritime and Fisheries Management" align="middle" width="172" src="https://raw.githubusercontent.com/52North/SOS/develop/spring/views/src/main/webapp/static/images/funding/logo_nexos.png"/></a></td>
    <td style="padding:3px;width:70%;">The development of this version of the 52&deg;North SOS was supported by the <a target="_blank" href="http://cordis.europa.eu/fp7/home_en.html">European FP7</a> research project <a target="_blank" href="http://www.nexosproject.eu/">NeXOS</a> (co-funded by the European Commission under the grant agreement n&deg;614102)</td>
  </tr>
  <tr>
    <td align="center" style="padding:3px;width:30%;"><a target="_blank" href="http://www.eo2heaven.org/"><img alt="EO2HEAVEN - Earth Observation and ENVironmental Modeling for the Mitigation of HEAlth Risks" align="middle" width="172" src="https://raw.githubusercontent.com/52North/SOS/develop/spring/views/src/main/webapp/static/images/funding/logo_eo2heaven_200px.png"/></a></td>
    <td style="padding:3px;width:70%;">The development of this version of the 52&deg;North SOS was supported by the <a target="_blank" href="http://cordis.europa.eu/fp7/home_en.html">European FP7</a> research project <a target="_blank" href="http://www.eo2heaven.org/">EO2HEAVEN</a> (co-funded by the European Commission under the grant agreement n&deg;244100)</td>
  </tr>
  <tr>
    <td align="center" style="padding:3px;width:30%;"><a target="_blank" href="http://www.geowow.eu/"><img alt="GEOWOW - GEOSS interoperability for Weather, Ocean and Water" align="middle" width="172" src="https://raw.githubusercontent.com/52North/SOS/develop/spring/views/src/main/webapp/static/images/funding/logo_geowow.png"/></a></td>
    <td style="padding:3px;width:70%;">The development of this version of the 52&deg;North SOS was supported by the <a target="_blank" href="http://cordis.europa.eu/fp7/home_en.html">European FP7</a> research project <a href="http://www.geowow.eu/" title="GEOWOW">GEOWOW</a> (co-funded by the European Commission under the grant agreement n&deg;282915)</td>
  </tr>
  <tr>
    <td align="center" style="padding:3px;width:30%;"><a target="_blank" href="http://www.geoviqua.org/"><img alt="GeoViQua - QUAlity aware VIsualization for the Global Earth Observation System of Systems" align="middle" width="172" src="https://raw.githubusercontent.com/52North/SOS/develop/spring/views/src/main/webapp/static/images/funding/logo_geoviqua.png"/></a></td>
    <td style="padding:3px;width:70%;">The development of this version of the 52&deg;North SOS was supported by the <a target="_blank" href="http://cordis.europa.eu/fp7/home_en.html">European FP7</a> research project <a href="http://www.geoviqua.org/" title="GeoViQua">GeoViQua</a> (co-funded by the European Commission under the grant agreement n&deg;265178)</td>
  </tr>
  <tr>
    <td align="center" style="padding:3px;width:30%;">
        <a target="_blank" href="http://inspire.ec.europa.eu"><img alt="INSPIRE" align="middle" width="60" src="https://raw.githubusercontent.com/52North/SOS/develop/spring/views/src/main/webapp/static/images/funding/inspire-logo.jpg" /></a>
        <a target="_blank" href="http://ec.europa.eu/isa/"><img alt="ISA" align="middle" width="60" src="https://raw.githubusercontent.com/52North/SOS/develop/spring/views/src/main/webapp/static/images/funding/ISALogo.png" /></a>
        <a target="_blank" href="http://ec.europa.eu/isa/actions/01-trusted-information-exchange/1-17action_en.htm"><img alt="ARE3NA" align="middle" width="60" src="https://raw.githubusercontent.com/52North/SOS/develop/spring/views/src/main/webapp/static/images/funding/ARe3NA.png"/></a>
    </td>
    <td style="padding:3px;width:70%;">The enhancements to make the 52&deg;North SOS an <a target="_blank" href="http://inspire.ec.europa.eu/">INSPIRE</a> compliant Download Service were funded by the <a target="_blank" href="http://ec.europa.eu/dgs/jrc/">JRC</a> under the <a target="_blank" href="http://ec.europa.eu/isa/">ISA</a> Programme's Action 1.17: A Reusable INSPIRE Reference Platform (<a target="_blank" href="http://ec.europa.eu/isa/actions/01-trusted-information-exchange/1-17action_en.htm">ARE3NA</a>).</td>
  </tr>
  <tr>
    <td align="center" style="padding:3px;width:30%;"><a target="_blank" href="http://www.ioos.noaa.gov"><img alt="IOOS - Integrated Ocean Observing System" align="middle" width="156" src="https://raw.githubusercontent.com/52North/SOS/develop/spring/views/src/main/webapp/static/images/funding/logo_ioos.png"/></a></td>
    <td style="padding:3px;width:70%;">The <a target="_blank" href="http://www.ioos.noaa.gov">IOOS</a> project with the mission: <br/>Lead the integration of ocean, coastal, and Great Lakes observing capabilities, in collaboration with Federal and non-Federal partners, to maximize access to data and generation of information products, inform decision making, and promote economic, environmental, and social benefits to our Nation and the world. </td>
  </tr>
  <tr>
    <td align="center" style="padding:3px;width:30%;"><a target="_blank" href="http://www.brgm.fr/"><img alt="BRGM - Bureau de Recherches GÃ©ologiques et MiniÃ¨res" align="middle" width="172" src="https://raw.githubusercontent.com/52North/SOS/develop/spring/views/src/main/webapp/static/images/funding/173px-Logo_BRGM.svg.png"/></a></td>
    <td style="padding:3px;width:70%;"><a href="http://www.brgm.fr/" title="BRGM">BRGM</a>, the French geological survey, is France's reference public institution for Earth Science applications in the management of surface and subsurface resources and risks.</td>
  </tr>
  <tr>
    <td align="center" style="padding:3px;width:30%;"><a target="_blank" href="http://www.wupperverband.de"><img alt="Wupperverband" align="middle" width="196" src="https://raw.githubusercontent.com/52North/SOS/develop/spring/views/src/main/webapp/static/images/funding/logo_wv.jpg"/></a></td>
    <td style="padding:3px;width:70%;">The <a target="_blank" href="http://www.wupperverband.de/">Wupperverband</a> for water, humans and the environment (Germany)</td>
  </tr>
  <tr>
    <td align="center" style="padding:3px;width:30%;"><a target="_blank" href="http://www.irceline.be/en"><img alt="Belgian Interregional Environment Agency (IRCEL - CELINE)" align="middle" width="130" src="https://raw.githubusercontent.com/52North/SOS/develop/spring/views/src/main/webapp/static/images/funding/logo_irceline_no_text.png"/></a></td>
    <td style="padding:3px;width:70%;">The <a href="http://www.irceline.be/en" target="_blank" title="Belgian Interregional Environment Agency (IRCEL - CELINE)">Belgian Interregional Environment Agency (IRCEL - CELINE)</a> is primarily active with the domain of air quality (modelling, forecasts, informing the public on the state of their air quality, e-reporting to the EU under the air quality directives, participating in scientific research on air quality, etc.). IRCEL â CELINE is a permanent cooperation between three regional environment agencies: <a href="http://www.awac.be/" title="Agence wallonne de l&#39Air et du Climat (AWAC)">Agence wallonne de l&#39Air et du Climat (AWAC)</a>, <a href="http://www.ibgebim.be/" title="Bruxelles Environnement - Leefmilieu Brussel">Bruxelles Environnement - Leefmilieu Brussel</a> and <a href="http://www.vmm.be/" title="Vlaamse Milieumaatschappij (VMM)">Vlaamse Milieumaatschappij (VMM)</a>.</td>
  </tr>
  <tr>
    <td align="center" style="padding:3px;width:30%;"><a target="_blank" href="http://www.ivl.se/english"><img alt="IVL Swedish Environmental Research Institute" align="middle" width="196" src="https://raw.githubusercontent.com/52North/SOS/develop/spring/views/src/main/webapp/static/images/funding/ivl_eng_rgb_70mm.png"/></a></td>
    <td style="padding:3px;width:70%;">The <a target="_blank" href="http://www.ivl.se/english">IVL Swedish Environmental Research Institute</a> is an independent, non-profit research institute, owned by a foundation jointly established by the Swedish Government and Swedish industry.</td>
  </tr>
  <tr>
    <td align="center" style="padding:3px;width:30%;"><a target="_blank" href="http://www.dlr.de"><img alt="German Aerospace Centre" align="middle" width="172" src="https://raw.githubusercontent.com/52North/SOS/develop/spring/views/src/main/webapp/static/images/funding/DLR-logo.jpg"/></a></td>
    <td style="padding:3px;width:70%;">The <a target="_blank" href="http://www.dlr.de">German Aerospace Centre</a> (Deutsches Zentrum fuer Luft- und Raumfahrt, DLR) and part of their <a target="_blank" href="http://www.dlr.de/eoc/en/desktopdefault.aspx/tabid-5400/10196_read-21914/">Environmental and Crisis Information System</a> (Umwelt- und Kriseninformationssystem, UKis)</td>
  </tr>
  <tr>
    <td align="center" style="padding:3px;width:30%;"><a target="_blank" href="http://www.smart-project.info"><img alt="SMART Aquifer Characterisation Programme (SAC)" align="middle"  width="96" src="https://raw.githubusercontent.com/52North/SOS/develop/spring/views/src/main/webapp/static/images/funding/Logo_SMART_v2_rescale.png"/></a></td>
    <td style="padding:3px;width:70%;">The <a target="_blank" href="http://www.smart-project.info">SMART</a> Project, funded by the <a target="_blank" href="http://www.msi.govt.nz/">Ministry of Business, Innovation and Employment</a> (07/2011 &ndash; 06/2017): <br/> Experts in the Smart Project will develop, apply, and validate pioneering new techniques for understanding New Zealand&lsquo;s groundwater resources. Satellite and airborne remote sensing techniques and Sensor Observation Services including seismic signals from earthquakes are used for rapid and costeffective characterisation and mapping of New Zealand&lsquo;s aquifer systems. Together with a stakeholder network the research team will use new methods to overcome the current time- and resourceconsuming challenges of in-time data acquisition. Special spatial skills in hydrogeology, geology, satellite remote sensing, geophysics, seismology, uncertainty mathematics and spatial information technology will be developed to assist with improvement of New Zealand&lsquo;s freshwater management.</td>
  </tr>
  <tr>
    <td align="center" style="padding:3px;width:30%;"><a target="_blank" href="http://www.dlz-it.de/cln_008/DE/Home/home_node.html"><img alt="DLZ-IT" align="middle" width="140" src="https://raw.githubusercontent.com/52North/SOS/develop/spring/views/src/main/webapp/static/images/funding/dlz-it-logo.jpg"/></a></td>
    <td style="padding:3px;width:70%;">The <a target="_blank" href="http://www.dlz-it.de/">DLZ-IT</a> BMVBS Information Technology Services Centre of Federal Ministry of Transport, Building and Urban Development (Germany)</td>
  </tr>
  <tr>
    <td align="center" style="padding:3px;width:30%;"></td>
    <td style="padding:3px;width:70%;">The OGC Web Services, <a target="_blank" href="http://www.opengeospatial.org/projects/initiatives/ows-9">Phase 9 (OWS-9)</a> Testbed</td>
  </tr>
  <tr>
    <td align="center" style="padding:3px;width:30%;"></td>
    <td style="padding:3px;width:70%;">The OGC Web Services, <a target="_blank" href="http://www.opengeospatial.org/projects/initiatives/ows-10">Phase 10 (OWS-10)</a> Testbed</td>
  </tr>
  <tr>
    <td align="center" style="padding:3px;width:30%;"></td>
    <td style="padding:3px;width:70%;">The <a target="_blank" href="http://www.rijkswaterstaat.nl/en/">Rijkswaterstaat</a> - Dutch Ministry of Infrastructure and the Environment (The Netherlands)</td>
  </tr>
</table>


## Branches

This project follows the  [Gitflow branching model](http://nvie.com/posts/a-successful-git-branching-model/). "master" reflects the latest stable release.
Ongoing development is done in branch [develop](../../tree/develop) and dedicated feature branches (feature-*).

## Code Compilation

This project is managed with Maven3. Simply run `mvn clean install`
to create a deployable .WAR file.

## Distributions

Here you can find some information that relates to the distributions of the 52°North SOS.

### Download

The latest release of 52°North SOS can be downloaded from this website:

    http://52north.org/downloads/category/3-sos

### Contents
  * `/src` :                 The source files of 52°North SOS modules
  * `/bin` :                 Executable binary of 52°North SOS webapp module
  * `LICENSE` :              The license of 52°North SOS
  * `NOTICE` :               Third Party libraries and their licenses
  * `README` :               This file
  * `RELEASE-NOTES` :        The release notes of the 52°North SOS

No printer friendly documentation exist for this release. Instead, refer to the [wiki documentation](https://wiki.52north.org/bin/view/SensorWeb/SensorObservationServiceIVDocumentation).

52°North Inititative for Geospatial Open Source Software GmbH, Germany
