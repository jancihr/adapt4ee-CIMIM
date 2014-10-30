package eu.adapt4ee.cimim.rest.resource;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;

import eu.adapt4ee._2012.schema.cim.BPMImport;
import eu.adapt4ee._2012.schema.cim.BuildingImport;
import eu.adapt4ee._2012.schema.cim.DeviceDiscovered;
import eu.adapt4ee._2012.schema.cim.EventsImport;
import eu.adapt4ee._2012.schema.cim.MeasurementFile;
import eu.adapt4ee._2012.schema.cim.ObjectFactory;
import eu.adapt4ee._2012.schema.cim.SimulationFile;
import eu.adapt4ee.cimim.rest.storage.StorageFactory;
import eu.adapt4ee.cimim.rest.storage.StorageFactoryImplementationXbase;
import eu.adapt4ee.cimim.rest.storage.StorageUtils;

/**
 * @author jan hreno
 *
 */
@Path("/utils")
public class Utils {
	@Context
	UriInfo uriInfo;
	@Context
	Request request;
	StorageFactory sf = StorageFactoryImplementationXbase.getInstance();
	/*
	@GET
	@Path("/cleardb")
	public String dropDB(){
		sf.dropDB();
		return "OK dropped DB";
	}
	
	@GET
	@Path("/initdb")
	public String initEmptyData() throws FileNotFoundException{
		ObjectFactory of = new ObjectFactory();
		StorageFactory sf = StorageFactoryImplementationXbase.getInstance();
		
		MeasurementFile mf1 = of.createMeasurementFile();
		mf1.setId("1");
		
		MeasurementFile mf2 = of.createMeasurementFile();
		mf2.setId("2");
		
		SimulationFile simf = of.createSimulationFile();
		simf.setId("3");

		//sf.saveMeasurementFile(mf1);
		//sf.saveMeasurementFile(mf2);
		//sf.saveSimulationFile(simf);
		
		
		return "OK initialised DB";
	}
	
	*/
}
