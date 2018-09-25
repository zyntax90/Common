package common;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.XMLEvent;

public class RssUtil {

	private static RssUtil _rssUtil;
	private final URL _url;

	private RssUtil(String url) {
		try {
			_url = new URL(url);
		} catch (MalformedURLException e) {
			throw new RuntimeException(e);
		}
	}

	public static RssUtil getInstance(String url) {
		if (_rssUtil == null)
			_rssUtil = new RssUtil(url);
		return _rssUtil;
	}

	public Object getObjectFromRssFeed(Object inputObject) {
		XMLInputFactory inputFactory = XMLInputFactory.newInstance();
		try {
			XMLEventReader eventReader = inputFactory.createXMLEventReader(_url.openStream());

			while (eventReader.hasNext()) {
				XMLEvent xmlEvent = eventReader.nextEvent();
				
				if (xmlEvent.isStartElement()) {
					String localPart = xmlEvent.asStartElement().getName().getLocalPart();
					
					switch(localPart) {
					case "":
					}
					
					
				}
			}

		} catch (XMLStreamException | IOException e) {
			e.printStackTrace();
		}

		return null;
	}
}
