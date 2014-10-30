package eu.adapt4ee.cimim.rest.resource;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.net.URL;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.StreamingOutput;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.Provider;
import javax.ws.rs.ext.Providers;
import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.stream.XMLStreamException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.apache.cxf.jaxrs.ext.multipart.Multipart;
import org.apache.tomcat.util.http.fileupload.FileUpload;

import eu.adapt4ee._2012.schema.cim.BPMImport;
import eu.adapt4ee._2012.schema.cim.BuildingExport;
import eu.adapt4ee._2012.schema.cim.BuildingImport;
import eu.adapt4ee._2012.schema.cim.DeviceDiscovered;
import eu.adapt4ee._2012.schema.cim.EventsImport;
import eu.adapt4ee._2012.schema.cim.EventsStats;
import eu.adapt4ee._2012.schema.cim.ListOfEquipments;
import eu.adapt4ee._2012.schema.cim.ListOfHVACs;
import eu.adapt4ee._2012.schema.cim.ListOfLightings;
import eu.adapt4ee._2012.schema.cim.ListOfProcesses;
import eu.adapt4ee._2012.schema.cim.ListOfRoles;
import eu.adapt4ee._2012.schema.cim.ListOfSensors;
import eu.adapt4ee._2012.schema.cim.ListOfZones;
import eu.adapt4ee._2012.schema.cim.MeasurementFile;
import eu.adapt4ee._2012.schema.cim.MeasurementsImport;
import eu.adapt4ee.cimim.rest.storage.StorageFactory;
import eu.adapt4ee.cimim.rest.storage.StorageFactoryImplementationXbase;
import eu.adapt4ee.cimim.rest.storage.StorageFactoryImplementationXbaseServer;

/**
 * @author jan hreno
 *
 */
@Path("/measurementfile")
public class Measurement {

	@Context
	UriInfo uriInfo;
	@Context
	Request request;
	StorageFactory sf = StorageFactoryImplementationXbase.getInstance();

	// StorageFactory sf =
	// StorageFactoryImplementationXbaseServer.getInstance();

	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.TEXT_XML })
	public StreamingOutput getMeasurementFiles() {

		return new StreamingOutput() {
			public void write(OutputStream output) throws IOException,
					WebApplicationException {
				try {
					sf.getMeasurementFiles(output);
				} catch (Exception e) {
					throw new WebApplicationException(e);
				}
			}
		};

	}

	// @GET
	// @Path("/{param}/xml")
	// @Produces({ MediaType.APPLICATION_XML,
	// MediaType.TEXT_XML })
	// public MeasurementFile getMeasurementFileXML(
	// @PathParam("param") String measurementID) {
	// return sf.getMeasurementFileByID(measurementID);
	// }
	//
	// @GET
	// @Path("/{param}/json")
	// @Produces({ MediaType.APPLICATION_JSON,
	// MediaType.TEXT_XML })
	// public MeasurementFile getMeasurementFileJSON(
	// @PathParam("param") String measurementID) {
	// return sf.getMeasurementFileByID(measurementID);
	// }

	@GET
	@Path("/{param}/xml")
	@Produces({ MediaType.APPLICATION_XML, MediaType.TEXT_XML })
	public StreamingOutput getMeasurementFileXML(
			@PathParam("param") String measurementID) {
		final String ms = measurementID;
		return new StreamingOutput() {
			public void write(OutputStream output) throws IOException,
					WebApplicationException {
				try {
					sf.getMeasurementFileByID(ms, output);
				} catch (Exception e) {
					throw new WebApplicationException(e);
				}
			}
		};

	}

	@GET
	@Path("/{param}")
	@Produces({ MediaType.APPLICATION_XML, MediaType.TEXT_XML })
	public StreamingOutput getMeasurementFile(
			@PathParam("param") String measurementID) {
		final String ms = measurementID;
		return new StreamingOutput() {
			public void write(OutputStream output) throws IOException,
					WebApplicationException {
				try {
					sf.getMeasurementFileByID(ms, output);
				} catch (Exception e) {
					throw new WebApplicationException(e);
				}
			}
		};
	}
	
	/*@GET
	@Path("/{param}/event/mintime")
	@Produces({ MediaType.APPLICATION_XML, MediaType.TEXT_XML })
	public String getMeasurementFileMinTime(
			@PathParam("param") String measurementID) {
		//TODO
		return "<mintime>notimplemented</mintime>";
	}
	
	@GET
	@Path("/{param}/event/maxtime")
	@Produces({ MediaType.APPLICATION_XML, MediaType.TEXT_XML })
	public String getMeasurementFileMaxTime(
			@PathParam("param") String measurementID) {
		//TODO
		return "<maxtime>notimplemented</maxtime>";
	}*/
	
	@GET
	@Path("/{param}/events/stats")
	@Produces({ MediaType.APPLICATION_XML, MediaType.TEXT_XML })
	public StreamingOutput getMeasurementFileEventStats(
			@PathParam("param") final String measurementID) {
		
		
		return new StreamingOutput() {
			public void write(OutputStream output) throws IOException,
					WebApplicationException {
				try {
					sf.getMeasurementEventsStats(measurementID, true, output);
				} catch (Exception e) {
					throw new WebApplicationException(e);
				}
			}
		};
				
	}
	
	@GET
	@Path("/{param}/events/stats/counts")
	@Produces({ MediaType.APPLICATION_XML, MediaType.TEXT_XML })
	public StreamingOutput getMeasurementFileEventStatsCounts(
			@PathParam("param") final String measurementID) {
		
		return new StreamingOutput() {
			public void write(OutputStream output) throws IOException,
					WebApplicationException {
				try {
					sf.getMeasurementEventsStats(measurementID, false, output);
				} catch (Exception e) {
					throw new WebApplicationException(e);
				}
			}
		};	
	}
	
	
	@GET
	@Path("/{param}/transformed")
	@Produces({ MediaType.APPLICATION_XML, MediaType.TEXT_XML })
	public StreamingOutput getMeasurementFileTransformed(
			@PathParam("param") String measurementID) {
		final String ms = measurementID;
		return new StreamingOutput() {
			public void write(OutputStream output) throws IOException,
					WebApplicationException {
				try {
					sf.getMeasurementFileByIDTransformed(ms, output, false);
				} catch (Exception e) {
					throw new WebApplicationException(e);
				}
			}
		};
	}
	
	
	@GET
	@Path("/{param}/transformed/withspaces")
	@Produces({ MediaType.APPLICATION_XML, MediaType.TEXT_XML })
	public StreamingOutput getMeasurementFileTransformedWithSpaces(
			@PathParam("param") String measurementID) {
		final String ms = measurementID;
		return new StreamingOutput() {
			public void write(OutputStream output) throws IOException,
					WebApplicationException {
				try {
					sf.getMeasurementFileByIDTransformed(ms, output, true);
				} catch (Exception e) {
					throw new WebApplicationException(e);
				}
			}
		};
	}
	
	@GET
	@Path("/{param}/nobigdata")
	@Produces({ MediaType.APPLICATION_XML, MediaType.TEXT_XML })
	public StreamingOutput getMeasurementFileWithoutBigData(
			@PathParam("param") String measurementID) {
		final String ms = measurementID;
		return new StreamingOutput() {
			public void write(OutputStream output) throws IOException,
					WebApplicationException {
				try {
					sf.getMeasurementFileByIDWithoutBigData(ms, output);
				} catch (Exception e) {
					throw new WebApplicationException(e);
				}
			}
		};
	}
	
	
	@GET
	@Path("/{param}/filteredbydate/{from}/{to}")
	@Produces({ MediaType.APPLICATION_XML, MediaType.TEXT_XML })
	public StreamingOutput getMeasurementFileWithoutBigData(
			@PathParam("param") final String measurementID,@PathParam("from") final String from,@PathParam("to") final String to) {
		return new StreamingOutput() {
			public void write(OutputStream output) throws IOException,
					WebApplicationException {
				try {
					sf.getMeasurementFileByIDFilteredByTime(measurementID, from, to, output);
				} catch (Exception e) {
					throw new WebApplicationException(e);
				}
			}
		};
	}
	

	@GET
	@Produces({ MediaType.APPLICATION_OCTET_STREAM })
	@Path("/{param}/transformedmeasurementfile.zip")
	public StreamingOutput getMeasurementFileTransformedZipped(
			@PathParam("param") String measurementID,
			@Context HttpServletRequest request) throws IOException {
		final String ms = measurementID;
		return new StreamingOutput() {
			public void write(OutputStream output) throws IOException,
					WebApplicationException {
				try {
					ZipOutputStream zip = new ZipOutputStream(output);
					zip.putNextEntry(new ZipEntry("measurementfile.xml"));
					sf.getMeasurementFileByIDTransformed(ms, zip, false);
					zip.closeEntry();
					zip.close();
				} catch (Exception e) {
					throw new WebApplicationException(e);
				}
			}
		};
	}

	@GET
	@Produces({ MediaType.APPLICATION_OCTET_STREAM })
	@Path("/{param}/measurementfile.zip")
	public StreamingOutput getMeasurementFileZip(
			@PathParam("param") String measurementID,
			@Context HttpServletRequest request) throws IOException {
		final String ms = measurementID;
		return new StreamingOutput() {
			public void write(OutputStream output) throws IOException,
					WebApplicationException {
				try {
					ZipOutputStream zip = new ZipOutputStream(output);
					zip.putNextEntry(new ZipEntry("measurementfile.xml"));
					sf.getMeasurementFileByID(ms, zip);
					zip.closeEntry();
					zip.close();
				} catch (Exception e) {
					throw new WebApplicationException(e);
				}
			}
		};
	}
	
	
	@GET
	@Produces({ MediaType.APPLICATION_OCTET_STREAM })
	@Path("/{param}/filteredbydate/{starttime}/{endtime}/measurementfiletimefiltered.zip")
	public StreamingOutput getMeasurementFileFilteredByTimeZip(
			@PathParam("param") final String measurementID, @PathParam("starttime") final String startTime, @PathParam("endtime") final String endTime,
			@Context HttpServletRequest request) throws IOException {
		
		return new StreamingOutput() {
			public void write(OutputStream output) throws IOException,
					WebApplicationException {
				try {
					ZipOutputStream zip = new ZipOutputStream(output);
					zip.putNextEntry(new ZipEntry("measurementfile.xml"));
					sf.getMeasurementFileByIDFilteredByTime(measurementID, startTime, endTime, zip);
					zip.closeEntry();
					zip.close();
				} catch (Exception e) {
					throw new WebApplicationException(e);
				}
			}
		};
	}

	@DELETE
	@Path("/{param}")
	public void removeMeasurementFile(@PathParam("param") String measurementID) {
		sf.removeMeasurementFile(measurementID);
	}

	@PUT
	@Path("/streamimport")
	// add measurement file
	@Consumes({ MediaType.APPLICATION_XML, MediaType.TEXT_XML })
	@Produces({ MediaType.TEXT_PLAIN })
	public String putMeasurementFileStream(InputStream inputXML) {
		sf.saveMeasurementFile(inputXML,null);
		return "File Uploaded";
	}
	
	@PUT
	@Path("/streamimport/{id}")
	// add measurement file
	@Consumes({ MediaType.APPLICATION_XML, MediaType.TEXT_XML })
	@Produces({ MediaType.TEXT_PLAIN })
	public String putMeasurementFileStream(@PathParam("id") final String measurementID, 
			InputStream inputXML) {
		sf.saveMeasurementFile(inputXML, measurementID);
		return "File Uploaded";
	}
	
	/*
	
	@POST
	@Path("/streamzipimport/{measurementid}")
	// add measurement file
	@Consumes({ MediaType.MULTIPART_FORM_DATA})
	@Produces({ MediaType.TEXT_PLAIN })
	public String putMeasurementFileStreamZip(
			@PathParam("measurementid") String measurementID, 
			@FormParam("file") InputStream uploadedInputStream) 
	{
		sf.saveMeasurementFileZip(new ZipInputStream (uploadedInputStream), measurementID);
		return "File Uploaded";
	}
	*/
	
	@PUT
	@Path("/streamimport/lowmem")
	// add measurement file
	@Consumes({ MediaType.APPLICATION_XML, MediaType.TEXT_XML })
	@Produces({ MediaType.TEXT_PLAIN })
	public String putMeasurementStreamLowMem(InputStream inputXML) throws XMLStreamException {
		
		return sf.saveMeasurementFileLowMem(inputXML);
	}

	@PUT
	// add measurement file
	@Consumes({ MediaType.APPLICATION_XML, MediaType.TEXT_XML })
	@Produces({ MediaType.TEXT_PLAIN })
	public String putMeasurementFile(JAXBElement<MeasurementFile> jaxbEntity) {

		MeasurementFile ms = jaxbEntity.getValue();
		return sf.saveMeasurementFileNoStream(ms);

	}

	/**
	 * @param jaxbEntity
	 * @return
	 */
	@PUT
	// add events to the measurement file
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON,
			MediaType.TEXT_XML })
	@Path("/{param}/importevents")
	public Response putEventImport(JAXBElement<EventsImport> jaxbEntity,
			@PathParam("param") String measurementID) {
		EventsImport c = jaxbEntity.getValue();

		// System.out.println("tried to write events import");
		sf.addEvents(c, measurementID);
		return Response.created(uriInfo.getAbsolutePath()).build();
	}

	// @GET
	// // add events to the measurement file
	// @Produces({ MediaType.APPLICATION_JSON,
	// MediaType.TEXT_XML })
	// @Path("/{param}/events/json")
	// public ListOfEvents getEventsJSON(@PathParam("param") String
	// measurementID) {
	// return sf.getEventsFromMeasurementFile(measurementID);
	// }
	//
	// @GET
	// // add events to the measurement file
	// @Produces({ MediaType.APPLICATION_XML,
	// MediaType.TEXT_XML })
	// @Path("/{param}/events/xml")
	// public ListOfEvents getEventsXML(@PathParam("param") String
	// measurementID) {
	// return sf.getEventsFromMeasurementFile(measurementID);
	// }

	@GET
	// add events to the measurement file
	@Produces({ MediaType.APPLICATION_XML, MediaType.TEXT_XML })
	@Path("/{param}/events")
	public StreamingOutput getEvents(@PathParam("param") String measurementID) {
		final String ms = measurementID;
		return new StreamingOutput() {
			public void write(OutputStream output) throws IOException,
					WebApplicationException {
				try {
					sf.getEventsFromMeasurementFile(ms, output);
				} catch (Exception e) {
					throw new WebApplicationException(e);
				}
			}
		};
	}

	@GET
	// add events to the measurement file
	@Produces({ MediaType.APPLICATION_XML, MediaType.TEXT_XML })
	@Path("/{param}/events/xml")
	public StreamingOutput getEventsXML(@PathParam("param") String measurementID) {
		final String ms = measurementID;
		return new StreamingOutput() {
			public void write(OutputStream output) throws IOException,
					WebApplicationException {
				try {
					sf.getEventsFromMeasurementFile(ms, output);
				} catch (Exception e) {
					throw new WebApplicationException(e);
				}
			}
		};
	}

	@GET
	// add events to the measurement file
	@Produces({ MediaType.APPLICATION_OCTET_STREAM })
	@Path("/{param}/events.zip")
	public StreamingOutput getEventsZip(@PathParam("param") String measurementID)
			throws IOException {
		final String ms = measurementID;
		return new StreamingOutput() {
			public void write(OutputStream output) throws IOException,
					WebApplicationException {
				try {
					ZipOutputStream zip = new ZipOutputStream(output);
					zip.putNextEntry(new ZipEntry("events.xml"));
					sf.getEventsFromMeasurementFile(ms, zip);
					zip.closeEntry();
					zip.close();
				} catch (Exception e) {
					throw new WebApplicationException(e);
				}
			}
		};
	}
	
	@GET
	// add events to the measurement file
	@Produces({ MediaType.APPLICATION_XML, MediaType.TEXT_XML })
	@Path("/{param}/events/type/{type}")
	public StreamingOutput getEventsOfType(@PathParam("param") String measurementID, @PathParam("type") final String type) {
		final String ms = measurementID;
		return new StreamingOutput() {
			public void write(OutputStream output) throws IOException,
					WebApplicationException {
				try {
					sf.getEventsOftype(ms, type, output);
				} catch (Exception e) {
					throw new WebApplicationException(e);
				}
			}
		};
	}
	

	@DELETE
	@Path("/{param}/events")
	public void deleteEvents(@PathParam("param") String measurementID) {
		sf.removeEvents(measurementID);
	}

	/**
	 * @param jaxbEntity
	 * @return
	 */
	@PUT
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON,
			MediaType.TEXT_XML })
	@Path("/{param}/importmeasurements")
	public Response putMeasurementsImport(
			JAXBElement<MeasurementsImport> jaxbEntity,
			@PathParam("param") String measurementID) {
		MeasurementsImport m = jaxbEntity.getValue();

		// System.out.println("tried to write events import");
		sf.addMeasurements(m, measurementID);
		return Response.created(uriInfo.getAbsolutePath()).build();
	}

	// @GET
	// // add events to the measurement file
	// @Produces({ MediaType.APPLICATION_XML,
	// MediaType.TEXT_XML })
	// @Path("/{param}/measurements/xml")
	// public MeasurementsImport getMeasurementsXML(
	// @PathParam("param") String measurementID) {
	// return sf.getMeasurementsInMeasurementFile(measurementID);
	// }
	//
	// @GET
	// // add events to the measurement file
	// @Produces({ MediaType.APPLICATION_JSON,
	// MediaType.TEXT_XML })
	// @Path("/{param}/measurements/json")
	// public MeasurementsImport getMeasurementsJSON(
	// @PathParam("param") String measurementID) {
	// return sf.getMeasurementsInMeasurementFile(measurementID);
	// }

	@GET
	// add events to the measurement file
	@Produces({ MediaType.APPLICATION_XML, MediaType.TEXT_XML })
	@Path("/{param}/measurements/xml")
	public StreamingOutput getMeasurementsXML(
			@PathParam("param") String measurementID) {
		final String ms = measurementID;
		return new StreamingOutput() {
			public void write(final OutputStream output) throws IOException,
					WebApplicationException {
				try {
					sf.getMeasurementsInMeasurementFile(ms, output);
				} catch (Exception e) {
					throw new WebApplicationException(e);
				}
			}
		};
	}

	@GET
	// add events to the measurement file
	@Produces({ MediaType.APPLICATION_XML, MediaType.TEXT_XML })
	@Path("/{param}/measurements")
	public StreamingOutput getMeasurements(
			@PathParam("param") String measurementID) {
		final String ms = measurementID;
		return new StreamingOutput() {
			public void write(OutputStream output) throws IOException,
					WebApplicationException {
				try {
					sf.getMeasurementsInMeasurementFile(ms, output);
				} catch (Exception e) {
					throw new WebApplicationException(e);
				}
			}
		};

	}

	@GET
	// add events to the measurement file
	@Produces({ MediaType.APPLICATION_OCTET_STREAM })
	@Path("/{param}/measurements.zip")
	public StreamingOutput getMeasurementsZip(
			@PathParam("param") String measurementID) throws IOException {
		final String ms = measurementID;
		return new StreamingOutput() {
			public void write(OutputStream output) throws IOException,
					WebApplicationException {
				try {
					ZipOutputStream zip = new ZipOutputStream(output);
					zip.putNextEntry(new ZipEntry("measurements.xml"));
					sf.getMeasurementsInMeasurementFile(ms, zip);
					zip.closeEntry();
					zip.close();
				} catch (Exception e) {
					throw new WebApplicationException(e);
				}
			}
		};
	}

	@DELETE
	@Path("/{param}/measurements")
	public void deleteMeasurements(@PathParam("param") String measurementID) {
		sf.removeMeasurements(measurementID);
	}

	/**
	 * @param jaxbEntity
	 * @return
	 */

	@PUT
	// add events to the measurement file
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON,
			MediaType.TEXT_XML })
	@Path("/{param}/importprocess")
	public Response putBPMImport(JAXBElement<BPMImport> jaxbEntity,
			@PathParam("param") String measurementID) {
		BPMImport b = jaxbEntity.getValue();

		// System.out.println("tried to write activities import");
		sf.addProcess(b, measurementID);
		return Response.created(uriInfo.getAbsolutePath()).build();
	}

	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON,
			MediaType.TEXT_XML })
	@Path("/{param}/processes")
	public ListOfProcesses getProcesses(@PathParam("param") String measurementID) {
		return sf.getProcessesInMeasurementFile(measurementID);
	}

	@DELETE
	@Path("/{param}/processes")
	public void deleteProcesses(@PathParam("param") String measurementID) {
		sf.removeProcesses(measurementID);
	}

	/**
	 * @param jaxbEntity
	 * @return
	 */
	@PUT
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON,
			MediaType.TEXT_XML })
	@Path("/{param}/sensordiscovered")
	public Response putDeviceImport(JAXBElement<DeviceDiscovered> jaxbEntity,
			@PathParam("param") String measurementID) {
		DeviceDiscovered d = jaxbEntity.getValue();

		sf.addDevice(d, measurementID);
		return Response.created(uriInfo.getAbsolutePath()).build();
	}

	/**
	 * @param jaxbEntity
	 * @return
	 */
	@PUT
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON,
			MediaType.TEXT_XML })
	@Path("/{param}/sensorsdiscovered")
	public Response putDevicesImport(JAXBElement<ListOfSensors> jaxbEntity,
			@PathParam("param") String measurementID) {
		ListOfSensors los = jaxbEntity.getValue();

		sf.addDevices(los, measurementID);
		return Response.created(uriInfo.getAbsolutePath()).build();
	}

	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON,
			MediaType.TEXT_XML })
	@Path("/{param}/sensors")
	public ListOfSensors getSensors(@PathParam("param") String measurementID) {
		return sf.getSensorsByMeasurementFileID(measurementID);
	}

	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON,
			MediaType.TEXT_XML })
	@Path("/{param}/sensors/{sensorType}")
	public ListOfSensors getSensorsOfType(
			@PathParam("param") String measurementID,
			@PathParam("sensorType") String sensorType) {
		return sf
				.getSensorsOfTypeByMeasurementFileID(measurementID, sensorType);
	}

	@DELETE
	@Path("/{param}/sensors")
	public void removeSensors(@PathParam("param") String measurementID) {
		sf.removeSensors(measurementID);
	}

	@PUT
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON,
			MediaType.TEXT_XML })
	@Path("/{param}/importbuilding")
	public Response putBuilding(JAXBElement<BuildingImport> jaxbEntity,
			@PathParam("param") String measurementID) {
		BuildingImport b = jaxbEntity.getValue();

		sf.saveBuilding(b, measurementID);
		return Response.created(uriInfo.getAbsolutePath()).build();
	}

	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON,
			MediaType.TEXT_XML })
	@Path("/{param}/building")
	public BuildingExport getBuilding(@PathParam("param") String measurementID) {
		return sf.getBuildingFromMeasurementFile(measurementID);
	}

	// @GET
	// @Produces({ MediaType.TEXT_PLAIN })
	// @Path("/{param}/gbXML")
	// public String getGbXML(@PathParam("param") String measurementID)
	// throws TransformerException {
	// Element node = (Element) (sf.getMeasurementFileByID(measurementID)
	// .getGbXML());
	//
	// TransformerFactory transFactory = TransformerFactory.newInstance();
	// Transformer transformer = transFactory.newTransformer();
	// StringWriter buffer = new StringWriter();
	// transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
	// transformer.transform(new DOMSource(node), new StreamResult(buffer));
	// String str = buffer.toString();
	// return str;
	//
	// }

	@GET
	@Produces({ MediaType.TEXT_PLAIN })
	@Path("/{param}/gbXML/convert")
	public String getGbXML(@PathParam("param") String measurementID) {
		String gbXML = sf.getGBXML(measurementID);
		return gbXML;
	}
	
	

	@GET
	@Produces({ MediaType.TEXT_PLAIN })
	@Path("/{param}/gbXML")
	public StreamingOutput getGbXMLNotFormatted(
			@PathParam("param") String measurementID) {

		final String ms = measurementID;
		return new StreamingOutput() {
			public void write(OutputStream output) throws IOException,
					WebApplicationException {
				try {
					sf.getGbXMLNotFormatted(ms, output);
				} catch (Exception e) {
					throw new WebApplicationException(e);
				}
			}
		};
	}
	
	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON,
		MediaType.TEXT_XML })
	@Path("/{param}/gbXML/XML")
	public StreamingOutput getGbXMLNotFormattedXML(
			@PathParam("param") String measurementID) {

		final String ms = measurementID;
		return new StreamingOutput() {
			public void write(OutputStream output) throws IOException,
					WebApplicationException {
				try {
					sf.getGbXMLNotFormatted(ms, output);
				} catch (Exception e) {
					throw new WebApplicationException(e);
				}
			}
		};
	}

	@DELETE
	@Path("/{param}/building")
	public void deleteBuilding(@PathParam("param") String measurementID) {
		sf.removeBuilding(measurementID);
	}

	// @GET
	// @Path("/{param}/template")
	// @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	// public TTrainedTemplate getTrainedTepmlate(
	// @PathParam("param") String measurementID) {
	// return
	// sf.getTrainedTemplate(measurementID);
	// }

	/**
	 * @param jaxbEntity
	 * @return
	 */
	@PUT
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON,
			MediaType.TEXT_XML })
	@Path("/{param}/zones")
	public Response putZones(JAXBElement<ListOfZones> jaxbEntity,
			@PathParam("param") String measurementID) {
		ListOfZones loz = jaxbEntity.getValue();

		sf.addZones(loz, measurementID);
		return Response.created(uriInfo.getAbsolutePath()).build();
	}

	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON,
			MediaType.TEXT_XML })
	@Path("/{param}/zones")
	public ListOfZones getZones(@PathParam("param") String measurementID) {
		return sf.getZones(measurementID);
	}

	@DELETE
	@Path("/{param}/zones")
	public void removeZones(@PathParam("param") String measurementID) {
		sf.removeZones(measurementID);
	}

	/**
	 * @param jaxbEntity
	 * @return
	 */
	@PUT
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON,
			MediaType.TEXT_XML })
	@Path("/{param}/addroles")
	public Response addRoles(JAXBElement<ListOfRoles> jaxbEntity,
			@PathParam("param") String measurementID) {
		ListOfRoles lor = jaxbEntity.getValue();

		sf.addRoles(lor, measurementID);
		return Response.created(uriInfo.getAbsolutePath()).build();
	}

	@PUT
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON,
			MediaType.TEXT_XML })
	@Path("/{param}/replaceroles")
	public Response replaceRoles(JAXBElement<ListOfRoles> jaxbEntity,
			@PathParam("param") String measurementID) {
		ListOfRoles lor = jaxbEntity.getValue();

		sf.replaceRoles(lor, measurementID);
		return Response.created(uriInfo.getAbsolutePath()).build();
	}

	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON,
			MediaType.TEXT_XML })
	@Path("/{param}/getroles")
	public ListOfRoles getRoles(@PathParam("param") String measurementID) {
		return sf.getRoles(measurementID);
	}

	@DELETE
	@Path("/{param}/deleteroles")
	public void removeRoles(@PathParam("param") String measurementID) {
		sf.removeRoles(measurementID);
	}

	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON,
			MediaType.TEXT_XML })
	@Path("/{param}/equipments")
	public ListOfEquipments getEquipments(
			@PathParam("param") String measurementID) {
		return sf.getEquipmentsInMeasurementFile(measurementID);
	}

	@DELETE
	@Path("/{param}/equipments")
	public void deleteEquipments(@PathParam("param") String measurementID) {
		sf.removeEquipments(measurementID);
	}

	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON,
			MediaType.TEXT_XML })
	@Path("/{param}/space/{space}/getequipments")
	public ListOfEquipments getEquipmentsInSpace(
			@PathParam("param") String measurementID,
			@PathParam("space") String spaceID) {
		return sf.getEquipments(measurementID, spaceID);
	}

	@DELETE
	@Path("/{param}/space/{space}/deleteequipments")
	public void deleteEquipmentInSpace(
			@PathParam("param") String measurementID,
			@PathParam("space") String spaceID) {
		sf.removeEquipments(measurementID, spaceID);
	}

	@PUT
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON,
			MediaType.TEXT_XML })
	@Path("/{param}/space/{space}/addequipments")
	public Response addEquipmentIntoSpace(
			JAXBElement<ListOfEquipments> jaxbEntity,
			@PathParam("param") String measurementID,
			@PathParam("space") String spaceID) {

		ListOfEquipments los = jaxbEntity.getValue();
		sf.addEquipments(los, measurementID, spaceID);
		return Response.created(uriInfo.getAbsolutePath()).build();
	}

	@PUT
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON,
			MediaType.TEXT_XML })
	@Path("/{param}/space/{space}/replaceequipments")
	public Response replaceEquipmentIntoSpace(
			JAXBElement<ListOfEquipments> jaxbEntity,
			@PathParam("param") String measurementID,
			@PathParam("space") String spaceID) {
		ListOfEquipments los = jaxbEntity.getValue();
		sf.replaceEquipments(los, measurementID, spaceID);
		return Response.created(uriInfo.getAbsolutePath()).build();
	}

	// ################# HVAC

	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON,
			MediaType.TEXT_XML })
	@Path("/{param}/space/{space}/getHVACs")
	public ListOfHVACs getHVACsInSpace(
			@PathParam("param") String measurementID,
			@PathParam("space") String spaceID) {
		return sf.getHVACs(measurementID, spaceID);
	}

	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON,
			MediaType.TEXT_XML })
	@Path("/{param}/getHVACs")
	public ListOfHVACs getHVACsInSpace(@PathParam("param") String measurementID) {
		return sf.getHVACs(measurementID);
	}

	@DELETE
	@Path("/{param}/deleteHVACs")
	public void deleteHVACInSpace(@PathParam("param") String measurementID) {
		sf.removeHVACs(measurementID);
	}

	@PUT
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON,
			MediaType.TEXT_XML })
	@Path("/{param}/addHVACs")
	public Response addHVACIntoSpace(JAXBElement<ListOfHVACs> jaxbEntity,
			@PathParam("param") String measurementID) {

		ListOfHVACs los = jaxbEntity.getValue();
		sf.addHVACs(los, measurementID);
		return Response.created(uriInfo.getAbsolutePath()).build();
	}

	@PUT
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON,
			MediaType.TEXT_XML })
	@Path("/{param}/replaceHVACs")
	public Response replaceHVACIntoSpace(JAXBElement<ListOfHVACs> jaxbEntity,
			@PathParam("param") String measurementID) {
		ListOfHVACs loh = jaxbEntity.getValue();
		sf.replaceHVACs(loh, measurementID);
		return Response.created(uriInfo.getAbsolutePath()).build();
	}

	// ################# Lighting

	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON,
			MediaType.TEXT_XML })
	@Path("/{param}/space/{space}/getlightings")
	public ListOfLightings getLightingsInSpace(
			@PathParam("param") String measurementID,
			@PathParam("space") String spaceID) {
		return sf.getLightings(measurementID, spaceID);
	}

	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON,
			MediaType.TEXT_XML })
	@Path("/{param}/getlightings")
	public ListOfLightings getLightingsInSpace(
			@PathParam("param") String measurementID) {
		return sf.getLightings(measurementID);
	}

	@DELETE
	@Path("/{param}/space/{space}/deletelightings")
	public void deleteLightingInSpace(@PathParam("param") String measurementID,
			@PathParam("space") String spaceID) {
		sf.removeLightings(measurementID, spaceID);
	}

	@PUT
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON,
			MediaType.TEXT_XML })
	@Path("/{param}/space/{space}/addlightings")
	public Response addLightingIntoSpace(
			JAXBElement<ListOfLightings> jaxbEntity,
			@PathParam("param") String measurementID,
			@PathParam("space") String spaceID) {

		ListOfLightings los = jaxbEntity.getValue();
		sf.addLightings(los, measurementID, spaceID);
		return Response.created(uriInfo.getAbsolutePath()).build();
	}

	@PUT
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON,
			MediaType.TEXT_XML })
	@Path("/{param}/space/{space}/replacelightings")
	public Response replaceLightingIntoSpace(
			JAXBElement<ListOfLightings> jaxbEntity,
			@PathParam("param") String measurementID,
			@PathParam("space") String spaceID) {
		ListOfLightings los = jaxbEntity.getValue();
		sf.replaceLightings(los, measurementID, spaceID);
		return Response.created(uriInfo.getAbsolutePath()).build();
	}
}

/**
 * @author jan hreno
 *
 */
@Provider
@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON,
		MediaType.TEXT_XML })
class ValidatingReader implements MessageBodyReader<MeasurementFile> {

	@Context
	protected Providers providers;
	private Schema schema;

	public ValidatingReader() {
		try {
			SchemaFactory sf = SchemaFactory
					.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
			URL schemaURL = null;
			schema = sf.newSchema(schemaURL);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public boolean isReadable(Class<?> arg0, Type arg1, Annotation[] arg2,
			MediaType arg3) {
		return arg0 == MeasurementFile.class;
	}

	public MeasurementFile readFrom(Class<MeasurementFile> arg0, Type arg1,
			Annotation[] arg2, MediaType arg3,
			MultivaluedMap<String, String> arg4, InputStream arg5)
			throws IOException, WebApplicationException {
		try {
			JAXBContext jaxbContext = null;
			ContextResolver<JAXBContext> resolver = providers
					.getContextResolver(JAXBContext.class, arg3);
			if (null != resolver) {
				jaxbContext = resolver.getContext(arg0);
			}
			if (null == jaxbContext) {
				jaxbContext = JAXBContext.newInstance(arg0);
			}
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
			unmarshaller.setSchema(schema);
			return (MeasurementFile) unmarshaller.unmarshal(arg5);
		} catch (JAXBException e) {
			throw new RuntimeException(e);
		}
	}

}
