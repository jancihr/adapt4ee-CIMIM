package eu.adapt4ee.cimim.rest.client;

import java.io.File;
import javax.ws.rs.core.MediaType;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;

/**
 * @author jan hreno
 *
 */
public class TestClientOld_pre_ID1 {

	/**
	 * @param args
	 */

	public static void main(String[] args) {
		
		//String serverURL = "http://localhost:8080/Adapt4EE_CIMIM/rest/";
		String serverURL = "http://147.232.202.15:8080/Adapt4EE_CIMIM/rest/";
		
		String createdID = "";
		
		Client c = Client.create();
		WebResource r = c
				.resource(serverURL+"measurement");
		String root = System.getProperty("user.dir");
		
		c = Client.create();
		r = c.resource(serverURL+"measurement/"+1+"/importevents");
		System.out.println("===== Create eventsResource III =====");

		String filepath = "/test/xml/EventsImport.xml"; // in case of Windows:
		String abspath = root + filepath;

		String resp = r.type(MediaType.APPLICATION_XML)
				.accept(MediaType.TEXT_XML)
				.put(String.class, new File(abspath));
		System.out.println("Resp: "+resp);

		


		// System.out.println("===== Create eventsResource =====");
		// Events evs=new Events();
		// evs.setId("fromClient");
		// evs.setName("name2");
		// List<Event> evl = new LinkedList<Event>();
		// evl.add(new Event("daco","nieco"));
		// evl.add(new Event("nieco", "vselico"));
		// evs.setEvents(evl);
		// ClientResponse response =
		// r.accept(MediaType.APPLICATION_XML).put(ClientResponse.class, c);
		// System.out.println(response.getStatus());
		//
		// System.out.println("===== Create eventsResource II =====");
		// EventsImport evim = new EventsImport();
		// TEvent ev1 = new TEvent();
		// TEvent ev2 = new TEvent();
		// List<TEvent> list = evim.getEvent();
		// list.add(ev1);
		// list.add(ev2);
		// TEquipmentUsedEvent tequ = new TEquipmentUsedEvent();
		// tequ.setEquipmentRef("equipmentID");
		// ev1.setEquipmentUsed(tequ);
		// TSpaceEnvironmentEvent sen = new TSpaceEnvironmentEvent();
		// ev1.setSpaceEnvironmentChange(sen);

		// ClientResponse responseIII =
		// r.accept(MediaType.APPLICATION_XML).put(ClientResponse.class, evim);
		// System.out.println(responseIII.getStatus());

	}

}
