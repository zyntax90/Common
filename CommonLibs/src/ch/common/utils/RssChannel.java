package ch.common.utils;

import java.util.ArrayList;
import java.util.List;

public class RssChannel {

	private List<RssItem> rssItems;

	public RssChannel() {
		rssItems = new ArrayList<RssItem>();
	}

	public void AddItem(RssItem item) {
		rssItems.add(item);
	}
	
	public List<RssItem> getRssItems(){
		return rssItems;
	}
}
