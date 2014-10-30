package eu.adapt4ee.cimim.rest.storage;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.util.LinkedList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

public class StorageUtils {
	
	@SuppressWarnings("unchecked")
	public static  <T> T unserializeDocument(InputStream is, Class<T> clazz) {
		try {
			JAXBContext ctx = JAXBContext.newInstance(clazz);
			return (T) ctx.createUnmarshaller().unmarshal(is);
		} catch (JAXBException e) {
			e.printStackTrace(System.err);
			throw new RuntimeException(e);
		}
	}

	public static  <T> T unserializeDocument(ByteArrayOutputStream os, Class<T> clazz) {
		return unserializeDocument(new ByteArrayInputStream(os.toByteArray()), clazz);
	}
	
	public static InputStream serializeDocument(Object obj) {
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		
		try {
			JAXBContext ctx = JAXBContext.newInstance(obj.getClass());
			ctx.createMarshaller().marshal(obj, os);
			return new ByteArrayInputStream(os.toByteArray());
		} catch (JAXBException e) {
			e.printStackTrace(System.err);
			throw new RuntimeException(e);
		}
	}
	
	public static String serializeObjectToStringXML(Object obj) {
		return serializeObjectToStringXML(obj, false);
	}
	
	public static String serializeObjectToStringXML(Object obj, boolean isFragment){
		StringWriter writer = new StringWriter();
		try {
			JAXBContext ctx = JAXBContext.newInstance(obj.getClass());
			Marshaller marshaller = ctx.createMarshaller(); 
			marshaller.setProperty(Marshaller.JAXB_FRAGMENT, isFragment);
			marshaller.marshal(obj, writer);
			return writer.toString();
		} catch (JAXBException e) {
			e.printStackTrace(System.err);
			throw new RuntimeException(e);
		}
	}
	
	public static List<String> unserializeMeasurementIDs(ByteArrayOutputStream os) {
		List<String> ids = new LinkedList<String>();
		try {
			int i = 0, j;
			String str = new String(os.toByteArray(), "UTF-8");
			
			while (true) {
				i = str.indexOf("\">", i);
				if (i < 0) {
					break;
				}
				j = str.indexOf("</id>", i);
				if (j < 0) {
					break;
				}
				ids.add(str.substring(i + 2, j));
				i = j;
			}
		} catch (UnsupportedEncodingException e) {
			// ignored, UTF-8 should be always supported
		}
		return ids;
	}

}

