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
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.StreamingOutput;
import javax.ws.rs.core.UriInfo;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;

import eu.adapt4ee._2012.schema.cim.Extensions;
import eu.adapt4ee._2012.schema.cim.Properties;
import eu.adapt4ee._2012.schema.device.SensorType;
import eu.adapt4ee.cimim.rest.storage.StorageFactory;
import eu.adapt4ee.cimim.rest.storage.StorageFactoryImplementationXbase;

/**
 * @author jan hreno
 *
 */
@Path("/extensions")
public class ExtensionsResorce {
	@Context
	UriInfo uriInfo;
	@Context
	Request request;
	StorageFactory sf = StorageFactoryImplementationXbase.getInstance();
	
	//get the list of existing extensions sets
	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.TEXT_XML })
	public StreamingOutput getSimulationFiles() {

		return new StreamingOutput() {
			public void write(OutputStream output) throws IOException,
					WebApplicationException {
				try {
					sf.getExtensionFiles(output);
				} catch (Exception e) {
					throw new WebApplicationException(e);
				}
			}
		};

	}
	
	
	@PUT
	@Path("/{extid}")
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.TEXT_XML})
	public Response replaceExtension(@PathParam("extid") final String extid, Extensions ext) throws Exception {
		 sf.replaceExtensions(extid, ext);
		 return Response.created(uriInfo.getAbsolutePath()).build();
	}
	
	
	@PUT
	@Path("/{extid}/add")
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.TEXT_XML})
	public Response addExtension(@PathParam("extid") final String extid, Extensions ext) throws Exception {
		 sf.addExtensions(extid, ext);
		 return Response.created(uriInfo.getAbsolutePath()).build();
	}
	
	
	@PUT
	@Path("/{extid}/properties")
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.TEXT_XML})
	public Response addProperties(@PathParam("extid") final String extid, 
			@QueryParam("referenceID") final String referenceID,
			@QueryParam("rootElementName") final String rootElementName,
			@QueryParam("scope") final String scope,
			@QueryParam("application") final String application,
			@QueryParam("propertySetName") final String propertySetName,
			@QueryParam("propertyName") final String propertyName,
			@QueryParam("version") final String version, Properties props) throws Exception {
		 sf.addProperties( props,  extid,  rootElementName,
					 referenceID,  propertySetName,  propertyName,
					 scope,  application,  version);
		 return Response.created(uriInfo.getAbsolutePath()).build();
	}
	
	
	
	
	@GET
	@Path("/{extid}")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public StreamingOutput getExtensionsByRefIDRootElm(
			@PathParam("extid") final String extid, 
			@QueryParam("referenceID") final String referenceID,
			@QueryParam("rootElementName") final String rootElementName,
			@QueryParam("propertySetName") final String propertySetName,
			@QueryParam("propertyName") final String propertyName,
			@QueryParam("scope") final String scope,
			@QueryParam("application") final String application,
			@QueryParam("version") final String version) throws Exception {
		
		return new StreamingOutput() {
			public void write(OutputStream output) throws IOException,
					WebApplicationException {
				try {
					sf.getExtensions(extid, rootElementName, referenceID, propertySetName, propertyName, scope, application, version, output);
				} catch (Exception e) {
					throw new WebApplicationException(e);
				}
			}
		};
	}
	
	
	@DELETE
	@Path("{extid}")
	public void deleteExtensionsByRootElmName(
			@PathParam("extid") final String extid,
			@QueryParam("rootElementName") final String rootElementName,
			@QueryParam("referenceID") final String referenceID,
			@QueryParam("propertySetName") final String propertySetName,
			@QueryParam("propertyName") final String propertyName,
			@QueryParam("scope") final String scope,
			@QueryParam("application") final String application,
			@QueryParam("version") final String version) throws Exception {

		sf.removeExtensions(extid, rootElementName, referenceID, propertySetName, propertyName, scope, application, version);
	}
	
	
	
	
	
	
	@GET
	@Path("/{extid}/properties")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public StreamingOutput getPropSets(
			@PathParam("extid") final String extid, 
			@QueryParam("referenceID") final String referenceID,
			@QueryParam("rootElementName") final String rootElementName,
			@QueryParam("propertySetName") final String propertySetName,
			@QueryParam("propertyName") final String propertyName,
			@QueryParam("scope") final String scope,
			@QueryParam("application") final String application,
			@QueryParam("version") final String version) throws Exception {
		
		return new StreamingOutput() {
			public void write(OutputStream output) throws IOException,
					WebApplicationException {
				try {
					sf.getPropertySets(extid, rootElementName, referenceID, propertySetName, propertyName, scope, application, version, output);
				} catch (Exception e) {
					throw new WebApplicationException(e);
				}
			}
		};
	}
	
	@DELETE
	@Path("{extid}/properties")
	public void deletePropSets(
			@PathParam("extid") final String extid,
			@QueryParam("rootElementName") final String rootElementName,
			@QueryParam("referenceID") final String referenceID,
			@QueryParam("propertySetName") final String propertySetName,
			@QueryParam("propertyName") final String propertyName,
			@QueryParam("scope") final String scope,
			@QueryParam("application") final String application,
			@QueryParam("version") final String version) throws Exception {
		sf.removePropSets(extid, rootElementName, referenceID, propertySetName, propertyName, scope, application, version);
	}
	
	
	
	
	
	@GET
	@Path("/{extid}/properties/property")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public StreamingOutput getProps(
			@PathParam("extid") final String extid, 
			@QueryParam("referenceID") final String referenceID,
			@QueryParam("rootElementName") final String rootElementName,
			@QueryParam("propertySetName") final String propertySetName,
			@QueryParam("propertyName") final String propertyName,
			@QueryParam("scope") final String scope,
			@QueryParam("application") final String application,
			@QueryParam("version") final String version) throws Exception {
		
		return new StreamingOutput() {
			public void write(OutputStream output) throws IOException,
					WebApplicationException {
				try {
					sf.getProperties(extid, rootElementName, referenceID, propertySetName, propertyName, scope, application, version, output);
				} catch (Exception e) {
					throw new WebApplicationException(e);
				}
			}
		};
	}
	
	@DELETE
	@Path("{extid}/properties/property")
	public void deleteProps(@PathParam("extid") final String extid,
			@PathParam("rootElementName") final String rootElementName,
			@QueryParam("referenceID") final String referenceID,
			@QueryParam("propertySetName") final String propertySetName,
			@QueryParam("propertyName") final String propertyName,
			@QueryParam("scope") final String scope,
			@QueryParam("application") final String application,
			@QueryParam("version") final String version) throws Exception {
		
		sf.removeProperties(extid, rootElementName, referenceID, propertySetName, propertyName, scope, application, version);
	}
	
	
	
	
	

}
