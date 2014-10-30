package eu.adapt4ee.cimim.rest.client;

import java.io.*;

import eu.adapt4ee.cimim.rest.storage.BaseXClient;

/**
 * This example shows how documents can be added to databases, and how existing
 * documents can be replaced.
 * 
 * This example required a running database server instance. Documentation:
 * http://docs.basex.org/wiki/Clients
 * 
 * @author BaseX Team 2005-12, BSD License
 */
public final class BaseXTest {
	/** Hidden default constructor. */
	private BaseXTest() {
	}

	/**
	 * Main method.
	 * 
	 * @param args
	 *            command-line arguments
	 * @throws IOException
	 *             I/O exception
	 */
	public static void main(final String[] args) throws IOException {
		// create session

		final BaseXClient session = new BaseXClient("localhost", 1984, "admin",
				"jablko11");

		try {
			// create empty database
			session.execute("create db database");
			System.out.println(session.info());

			// define input stream
			InputStream bais = new ByteArrayInputStream(
					"<x>Hello World!</x>".getBytes());

			// add document
			session.add("world/world.xml", bais);
			System.out.println(session.info());

			// define input stream
			bais = new ByteArrayInputStream("<x>Hello Universe!</x>".getBytes());

			// add document
			session.add("universe.xml", bais);
			System.out.println(session.info());

			// define input stream
			bais = new ByteArrayInputStream("<x>Hello Universe!</x>".getBytes());

			// add 2nd document
			session.add("universe2.xml", bais);
			System.out.println(session.info());

			// run query on database
			System.out
					.println(session.execute("xquery collection('database')"));

			// define input stream
			bais = new ByteArrayInputStream(
					"<x>Hello Replacement!</x>".getBytes());

			// add document
			session.replace("universe.xml", bais);
			System.out.println(session.info());

			// run query on database
			System.out
					.println(session.execute("xquery collection('database')"));

			// run query on database
			System.out.println(session
					.execute("xquery collection('database/universe2.xml')"));

			// drop database
			session.execute("drop db database");

		} finally {
			// close session
			session.close();
		}
	}
}
