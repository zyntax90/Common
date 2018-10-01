package ch.common.utils;

import java.io.IOException;

import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.XMLEvent;

public class RssUtil {

	private final URL url;

	/**
	 * @category: Constant RSS Values
	 */
	private final String ITEM = "ITEM";
	private final String TITLE = "TITLE";
	private final String LINK = "LINK";
	private final String GUID = "GUID";
	private final String PUBDATE = "PUBDATE";
	private final String DESCRIPTION = "DESCRIPTION";
	private final DateFormat rssDateFormat = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss zzz", Locale.ENGLISH);
	private final DateFormat localDateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss", Locale.GERMAN);

	public RssUtil(String url) {
		try {
			this.url = new URL(url);
		} catch (MalformedURLException e) {
			throw new RuntimeException(e);
		}
	}

	public RssChannel getObjectFromRssFeed() {

		RssChannel testRssChannel = new RssChannel();
		RssItem item = null;
		XMLInputFactory inputFactory = XMLInputFactory.newInstance();
		XMLEventReader eventReader;
		boolean isHeader = true;

		try {
			eventReader = inputFactory.createXMLEventReader(url.openStream());
			while (eventReader.hasNext()) {
				XMLEvent xmlEvent = eventReader.nextEvent();

				if (xmlEvent.isStartElement()) {
					String localPart = xmlEvent.asStartElement().getName().getLocalPart();

					if (localPart.toUpperCase().equals(ITEM)) {
						isHeader = false;
						item = new RssItem();
						xmlEvent = eventReader.nextEvent();
					}
					if (isHeader)
						continue;

					switch (localPart.toUpperCase()) {

					case ITEM:
						break;

					case TITLE:
						item.setTitle(getCharacterData(xmlEvent, eventReader));
						break;

					case LINK:
						item.setLink(getCharacterData(xmlEvent, eventReader));
						break;

					case GUID:
						item.setGuid(getCharacterData(xmlEvent, eventReader));
						break;

					case PUBDATE:
						String dateString = getCharacterData(xmlEvent, eventReader);
						Date rssDate = rssDateFormat.parse(dateString);
						String localDateString = localDateFormat.format(rssDate);
						item.setPubDate(localDateString);
						break;

					case DESCRIPTION:
						item.setDescription(getCharacterData(xmlEvent, eventReader));
						break;

					default:
						String itemName = localPart;
						String itemValue = getCharacterData(xmlEvent, eventReader);
						item.addToMap(itemName, itemValue);
					}

				} else if (xmlEvent.isEndElement()) {
					if (xmlEvent.asEndElement().getName().getLocalPart().toUpperCase().equals(ITEM)) {
						testRssChannel.AddItem(item);
						xmlEvent = eventReader.nextEvent();
						continue;
					}
				}

			}
		} catch (XMLStreamException | IOException | ParseException e) {
			throw new RuntimeException(e);
		}

		return testRssChannel;
	}

	private String getCharacterData(XMLEvent event, XMLEventReader eventReader) {
		try {
			event = eventReader.nextEvent();

			if (event instanceof Characters)
				return event.asCharacters().getData();

		} catch (XMLStreamException e) {
			e.printStackTrace();
		}

		return "";
	}
}
