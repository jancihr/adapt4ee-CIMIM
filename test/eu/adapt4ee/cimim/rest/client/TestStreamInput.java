package eu.adapt4ee.cimim.rest.client;

import java.io.FileNotFoundException;
import java.io.FileReader;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

/**
 * @author jan hreno
 *
 */
public class TestStreamInput {

	/**
	 * @param args
	 * @throws XMLStreamException
	 * @throws FileNotFoundException
	 */
	public static void main(String[] args) throws FileNotFoundException,
			XMLStreamException {
		XMLInputFactory factory = XMLInputFactory.newInstance();
		String measurementID = "";
		XMLStreamReader streamReader = factory
				.createXMLStreamReader(new FileReader(
						"/Users/rodinka/Desktop/measurementfile.xml"));

		int i = 0;

		String noEventsData = "";

		while (streamReader.hasNext()) {
			streamReader.next();
			if (streamReader.isStartElement()) {

				// get ID
				if (streamReader.getLocalName() == "id" && i == 0) {
					// only first ID
					System.out.println("######parsing ID#######");
					i = 1;
					while (!(streamReader.isEndElement() && streamReader
							.getLocalName() == "id")) {
						streamReader.next();
						if (streamReader.isCharacters()) {
							measurementID = measurementID.concat(streamReader
									.getText());
						}
					}
					System.out.println("###########data teraz:");
					System.out.println(noEventsData);
					noEventsData = noEventsData.concat("<id>" + measurementID
							+ "<id/>");
					System.out.println("###########data teraz:");
					System.out.println(noEventsData);

				} else

				// parse Events
				if (streamReader.getLocalName() == "events") {
					System.out.println("######parsing EVENTS#######");

					while (streamReader.hasNext()
							&& !(streamReader.isEndElement() && streamReader
									.getLocalName() == "events")) {
						streamReader.next();

						String singleEvent = "";

						while (streamReader.hasNext()
								&& !(streamReader.isEndElement() && streamReader
										.getLocalName() == "event")) {
							streamReader.next();

							if (streamReader.isStartElement()) {
								String s = "";

								s = s.concat("<"
										+ (streamReader.getPrefix()
												.equalsIgnoreCase("") ? ""
												: streamReader.getPrefix()
														+ ":")
										+ streamReader.getLocalName());
								int x = streamReader.getNamespaceCount();
								int y = streamReader.getAttributeCount();
								while (--x > -1) {

									s = s.concat(" xmlns:"
											+ streamReader
													.getNamespacePrefix(x)
											+ "=\""
											+ streamReader.getNamespaceURI(x)
											+ "\"");
								}

								while (--y > -1) {
									s = s.concat(" "
											+ (streamReader.getAttributePrefix(
													y).equalsIgnoreCase("") ? ""
													: streamReader
															.getAttributePrefix(y)
															+ ":")
											+ streamReader
													.getAttributeLocalName(y)
											+ "=\""
											+ streamReader.getAttributeValue(y)
											+ "\"");
								}

								s = s.concat(">");

								singleEvent = singleEvent.concat(s);

							}

							else if (streamReader.isWhiteSpace()) {
							} else if (streamReader.isCharacters()) {
								String s = (streamReader.getText());
								singleEvent = singleEvent.concat(s);
							} else if (streamReader.isEndElement()) {
								if (streamReader.getLocalName() == "events")
									break;

								String s = ("</"
										+ (streamReader.getPrefix()
												.equalsIgnoreCase("") ? ""
												: streamReader.getPrefix()
														+ ":")
										+ streamReader.getLocalName() + ">");
								singleEvent = singleEvent.concat(s);
							}

						}

						// System.out.println("##########event#########for ID:"+measurementID);
						// System.out.println(singleEvent);

					}

					System.out.println("###########data teraz:");
					System.out.println(noEventsData);
					noEventsData = noEventsData.concat("<events/>");
					System.out.println("###########data teraz:");
					System.out.println(noEventsData);
				} else {
					System.out.println("######parsing Element Start#######");
					String s = "";

					s = s.concat("<"
							+ (streamReader.getPrefix().equalsIgnoreCase("") ? ""
									: streamReader.getPrefix() + ":")
							+ streamReader.getLocalName());
					int x = streamReader.getNamespaceCount();
					int y = streamReader.getAttributeCount();
					while (--x > -1) {

						s = s.concat(" xmlns:"
								+ streamReader.getNamespacePrefix(x) + "=\""
								+ streamReader.getNamespaceURI(x) + "\"");
					}

					while (--y > -1) {
						s = s.concat(" "
								+ (streamReader.getAttributePrefix(y)
										.equalsIgnoreCase("") ? ""
										: streamReader.getAttributePrefix(y)
												+ ":")
								+ streamReader.getAttributeLocalName(y) + "=\""
								+ streamReader.getAttributeValue(y) + "\"");
					}

					s = s.concat(">");

					// System.out.println("start:" + s);
					// System.out.println("startElement element attribute count:"+streamReader.getAttributeCount());
					noEventsData = noEventsData.concat(s);

				}

			} else if (streamReader.isWhiteSpace()) {
				System.out.println("######parsing whitespace#######");
			} else if (streamReader.isCharacters()) {
				System.out.println("######parsing Character#######");
				String s = (streamReader.getText());
				// System.out.println(s);
				noEventsData = noEventsData.concat(s);
			} else if (streamReader.isEndElement()) {
				System.out.println("######parsing End Element#######");
				String s = ("</"
						+ (streamReader.getPrefix().equalsIgnoreCase("") ? ""
								: streamReader.getPrefix() + ":")
						+ streamReader.getLocalName() + ">");
				// System.out.println(s);
				noEventsData = noEventsData.concat(s);
			}

		}

		System.out.println("###########data teraz:");
		System.out.println(noEventsData);

	}



}
