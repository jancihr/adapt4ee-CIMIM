package eu.adapt4ee.cimim.rest.util.xml;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.util.JAXBSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.xml.sax.SAXException;

public class UtilFactory {
	@SuppressWarnings("unchecked")
	public static <T> T unserializeDocument(InputStream is, Class<T> clazz) {
		try {
			JAXBContext ctx = JAXBContext.newInstance(clazz);
			return (T) ctx.createUnmarshaller().unmarshal(is);
		} catch (JAXBException e) {
			e.printStackTrace(System.err);
			throw new RuntimeException(e);
		}
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

	public static Boolean validateDocument(Object obj, String xsdFile)
			throws SAXException, IOException, JAXBException {

		JAXBContext jc = JAXBContext.newInstance(obj.getClass());
		JAXBSource source = new JAXBSource(jc, obj);

		SchemaFactory sf = SchemaFactory
				.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
		String root = System.getProperty("user.dir");
		// System.out.println("root: "+root);
		String filepath = "/test/xsd/" + xsdFile;
		String abspath = root + filepath;
		Schema schema = sf.newSchema(new File(abspath));

		Validator validator = schema.newValidator();
		try {
			validator.validate(source);
		} catch (SAXException e) {
			return false;
		}
		return true;
	}

}
