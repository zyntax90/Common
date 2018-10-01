package ch.common.utils;

import java.util.Map;
import java.util.TreeMap;

public class RssItem {

	public static final String TITLE = "Title";
	public static final String LINK = "Link";
	public static final String GUID = "GUID";
	public static final String PUBDATE = "Published Date";
	public static final String DESCR = "Description";
	
	private String title;
	private String link;
	private String guid;
	private String pubDate;
	private String description;
	private Map<String, String> otherValues;

	public RssItem() {
		otherValues = new TreeMap<String, String>();
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getGuid() {
		return guid;
	}

	public void setGuid(String guid) {
		this.guid = guid;
	}

	public String getPubDate() {
		return pubDate;
	}

	public void setPubDate(String pubDate) {
		this.pubDate = pubDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void addToMap(String itemName, String itemValue) {
		otherValues.put(itemName, itemValue);
	}
	
	public Map<String, String> getOtherValues(){
		return otherValues;
	}

}
