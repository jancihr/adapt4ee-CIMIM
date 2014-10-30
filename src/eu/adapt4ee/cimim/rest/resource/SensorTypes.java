package eu.adapt4ee.cimim.rest.resource;

import java.io.InputStream;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;

import eu.adapt4ee._2012.schema.device.SensorType;
import eu.adapt4ee.cimim.rest.storage.StorageFactory;
import eu.adapt4ee.cimim.rest.storage.StorageFactoryImplementationXbase;

/**
 * @author jan hreno
 *
 */
@Path("/sensortypes")
public class SensorTypes {
	@Context
	UriInfo uriInfo;
	@Context
	Request request;
	StorageFactory sf = StorageFactoryImplementationXbase.getInstance();
	
	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.TEXT_XML})
	public SensorType getSensorTypes() throws Exception {
		return sf.getSensorTypes();
	}
	
	@PUT
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.TEXT_XML})
	public Response addSensorTypes(InputStream st) throws Exception {
		 sf.saveSensorTypes(st);
		 return Response.created(uriInfo.getAbsolutePath()).build();
	}
	
	@DELETE
	public void removeSensorTypes() throws Exception {
		 sf.removeSensorTypes();
	}

}
