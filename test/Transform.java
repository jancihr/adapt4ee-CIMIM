import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.sax.SAXTransformerFactory;
import javax.xml.transform.sax.TransformerHandler;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

/**
 * @author jan hreno
 *
 */
public class Transform {

	/**
	 * @param args
	 * @throws TransformerException
	 * @throws FileNotFoundException
	 */
	public static void main(String[] args) throws FileNotFoundException,
			TransformerException {
		// use Joost as transformation engine
		System.setProperty("javax.xml.transform.TransformerFactory",
				"net.sf.joost.trax.TransformerFactoryImpl");

		// Create a transform factory instance.
		TransformerFactory tfactory = TransformerFactory.newInstance();
		// Create a transformer for the stylesheet.
		Transformer transformer = tfactory
				.newTransformer(new StreamSource(
						new File(
								"/Users/rodinka/Develop/adapt4ee/eclipse_juno/workspace/Adapt4EE_CIMIM/test/joost-0.9.1/examples/mfa.stx")));

		// Transform the source XML to System.out.
//		transformer
//				.transform(
//						new StreamSource(
//								new File(
//										"/Users/janci/Desktop/mf.xml")),									
//		new StreamResult(new File("/Users/janci/Desktop/mf_transformed.xml")));
		
		
		
				transformer
						.transform(
								new StreamSource(
										new File(
												"/Users/rodinka/Develop/adapt4ee/eclipse_juno/workspace/Adapt4EE_CIMIM/test/joost-0.9.1/examples/mf.xml")),
												new StreamResult(
														System.out));
														//new File("/Users/janci/Develop/adapt4ee/eclipse_juno/workspace/Adapt4EE_CIMIM/test/joost-0.9.1/examples/mf_transformed.xml")));
				

	}

}
