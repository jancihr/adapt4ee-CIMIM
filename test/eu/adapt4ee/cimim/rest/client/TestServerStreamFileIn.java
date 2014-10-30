package eu.adapt4ee.cimim.rest.client;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import eu.adapt4ee.cimim.rest.storage.BaseXClient;


/**
 * @author jan hreno
 *
 */
public class TestServerStreamFileIn {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
		      // create session
		      final BaseXClient session = new BaseXClient("localhost", 1984, "admin", "admin");

		      try {
		        // define input stream
		        final InputStream bais =
		          new ByteArrayInputStream("<xml>Hello World!</xml>".getBytes());

		        String filepath = "/Users/rodinka/Desktop/_MeasurementFile.xml";
		        String bigfilepath = "/Users/rodinka/Desktop/measurementfile.xml";
		        
		        session.create("data", new FileInputStream(filepath));
		        // create new database
		        System.out.println(session.info());

		        
		        session.create("data", new FileInputStream(bigfilepath));
		        
		        // run query on database
		        //System.out.println(session.execute("xquery doc('database')"));

		        // drop database
		        //session.execute("drop db database");

		      } catch(final IOException ex) {
		        // print exception
		        ex.printStackTrace();
		      }

		      // close session
		      session.close();

		    } catch(final IOException ex) {
		      // print exception
		      ex.printStackTrace();
		    }
	}

}
