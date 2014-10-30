package eu.adapt4ee.cimim.rest.resource;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.StreamingOutput;
import javax.ws.rs.core.UriInfo;
import javax.xml.bind.JAXBElement;

import eu.adapt4ee._2012.schema.cim.EventTraceImport;
import eu.adapt4ee._2012.schema.cim.EventsImport;
import eu.adapt4ee._2012.schema.cim.MeasurementFile;
import eu.adapt4ee._2012.schema.cim.ObjectFactory;
import eu.adapt4ee._2012.schema.cim.SimulationFile;
import eu.adapt4ee.cimim.rest.storage.StorageFactory;
import eu.adapt4ee.cimim.rest.storage.StorageFactoryImplementationXbase;

/**
 * @author jan hreno
 *
 */
@Path("/simulationfile")
public class Simulation {

	@Context
	UriInfo uriInfo;
	@Context
	Request request;
	StorageFactory sf = StorageFactoryImplementationXbase.getInstance();

	@DELETE
	@Path("/{param}")
	public void removeSimulationFile(@PathParam("param") String simID) {
		sf.removeSimulationFile(simID);
	}

	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.TEXT_XML })
	public StreamingOutput getSimulationFiles() {

		return new StreamingOutput() {
			public void write(OutputStream output) throws IOException,
					WebApplicationException {
				try {
					sf.getSimulationFiles(output);
				} catch (Exception e) {
					throw new WebApplicationException(e);
				}
			}
		};

	}

	@GET
	@Path("/{param}")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public StreamingOutput getSimulationFile(
			@PathParam("param") String simulationID) {
		final String ms = simulationID;
		return new StreamingOutput() {
			public void write(OutputStream output) throws IOException,
					WebApplicationException {
				try {
					sf.getSimulationFileByID(ms, output);
				} catch (Exception e) {
					throw new WebApplicationException(e);
				}
			}
		};
	}
	
	@GET
	@Path("/{param}/case/{param2}/mintime")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public StreamingOutput getSimulationFileMinTime(
			@PathParam("param") final String simulationID,
			@PathParam("param2") final String simulationCaseID) {
		return new StreamingOutput() {
			public void write(OutputStream output) throws IOException,
					WebApplicationException {
				try {
					sf.getSimulationFileByIDMintime(simulationID, simulationCaseID, output);
				} catch (Exception e) {
					throw new WebApplicationException(e);
				}
			}
		};
	}
	
	@GET
	@Path("/{param}/case/{param2}/count")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public StreamingOutput getSimulationFileCount(
			@PathParam("param") final String simulationID,
			@PathParam("param2") final String simulationCaseID) {
		return new StreamingOutput() {
			public void write(OutputStream output) throws IOException,
					WebApplicationException {
				try {
					sf.getSimulationFileByIDCount(simulationID, simulationCaseID, output);
				} catch (Exception e) {
					throw new WebApplicationException(e);
				}
			}
		};
	}
	
	
	
	
	@GET
	@Path("/{param}/case/{param2}/maxtime")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public StreamingOutput getSimulationFileMaxTime(
			@PathParam("param") final String simulationID,
			@PathParam("param2") final String simulationCaseID) {
		return new StreamingOutput() {
			public void write(OutputStream output) throws IOException,
					WebApplicationException {
				try {
					sf.getSimulationFileByIDMaxtime(simulationID, simulationCaseID, output);
				} catch (Exception e) {
					throw new WebApplicationException(e);
				}
			}
		};
	}
	
	@GET
	@Path("/{param}/case/{param2}/eventtraces/from/{from}/to/{to}")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public StreamingOutput getSimulationFile(
			@PathParam("param") final String simulationID,
			@PathParam("param2") final String simulationCaseID,
			@PathParam("from") final String from,
			@PathParam("to") final String to) {
		return new StreamingOutput() {
			public void write(OutputStream output) throws IOException,
					WebApplicationException {
				try {
					sf.getEventTracesByIDLimited(simulationID, simulationCaseID, from, to, output);
				} catch (Exception e) {
					throw new WebApplicationException(e);
				}
			}
		};
	}
	

	@PUT
	// add simulation file
	@Consumes({ MediaType.APPLICATION_XML, MediaType.TEXT_XML })
	@Produces({ MediaType.TEXT_PLAIN })
	public String putMeasurementFile(JAXBElement<SimulationFile> jaxbEntity) {

		SimulationFile ms = jaxbEntity.getValue();
		return sf.saveSimulationFileNoStream(ms);

	}

	@PUT
	@Path("/streamimport")
	// add measurement file
	@Consumes({ MediaType.APPLICATION_XML, MediaType.TEXT_XML })
	@Produces({ MediaType.TEXT_PLAIN })
	public String putSimulationFileStream(
			InputStream inputXML) {
		sf.saveSimulationFile(inputXML, null);
		return "File Uploaded";
	}
	
	@PUT
	@Path("/streamimport/{id}")
	// add measurement file
	@Consumes({ MediaType.APPLICATION_XML, MediaType.TEXT_XML })
	@Produces({ MediaType.TEXT_PLAIN })
	public String putSimulationFileStream(@PathParam("id") final String simulationID,
			InputStream inputXML) {
		sf.saveSimulationFile(inputXML, null);
		return "File Uploaded";
	}

	@PUT
	// add events to the simulation file
	
	@Path("/{simulationfileID}/{simulationCaseID}/event/import")
	@Consumes({ MediaType.APPLICATION_XML, MediaType.TEXT_XML })
	public String putEventsTracesFileStream(
			JAXBElement<EventTraceImport> jaxbEntity,
			@PathParam("simulationfileID") String simulationID,
			@PathParam("simulationCaseID") String simulationCaseID) {
		EventTraceImport ms = jaxbEntity.getValue();
		// System.out.println("tried to write events import");
		sf.addEventTracesMeasurementFile(ms, simulationID,
				simulationCaseID);

		return "Events added";
	}

}
