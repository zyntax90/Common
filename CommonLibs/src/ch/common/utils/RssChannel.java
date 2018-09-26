package ch.common.utils;

import java.util.ArrayList;
import java.util.List;

public class RssChannel {

	private List<RssItem> _rssItems;

	public RssChannel() {
		_rssItems = new ArrayList<RssItem>();
	}

	public void AddItem(RssItem item) {
		_rssItems.add(item);
	}
	
	public List<RssItem> getRssItems(){
		return _rssItems;
	}
}
