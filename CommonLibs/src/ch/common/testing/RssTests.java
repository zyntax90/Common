package ch.common.testing;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import ch.common.utils.RssUtil;
import ch.common.utils.RssChannel;

public class RssTests {

	@Test
	void testRssUtil() {

		RssUtil rssUtil = new RssUtil("https://nyaa.si/?page=rss");
		RssChannel channel = rssUtil.getObjectFromRssFeed();
		assertTrue(channel.getRssItems().size() > 1);
	}

}
