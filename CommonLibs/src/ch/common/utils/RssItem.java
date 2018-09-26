package ch.common.utils;

import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

class RssItem {

	private String _title;
	private String _link;
	private String _guid;
	private Date _pubDate;
	private String _description;
	private Map<String, String> _otherValues;

	public RssItem() {
		_otherValues = new TreeMap<String, String>();
	}

	public String getTitle() {
		return _title;
	}

	public void setTitle(String title) {
		_title = title;
	}

	public String getLink() {
		return _link;
	}

	public void setLink(String link) {
		this._link = link;
	}

	public String getGuid() {
		return _guid;
	}

	public void setGuid(String guid) {
		_guid = guid;
	}

	public Date getPubDate() {
		return _pubDate;
	}

	public void setPubDate(Date pubDate) {
		_pubDate = pubDate;
	}

	public String getDescription() {
		return _description;
	}

	public void setDescription(String description) {
		_description = description;
	}

	public void addToMap(String itemName, String itemValue) {
		_otherValues.put(itemName, itemValue);
	}

}
