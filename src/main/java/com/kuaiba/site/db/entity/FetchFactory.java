package com.kuaiba.site.db.entity;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import com.kuaiba.site.core.GlobalIDs;
import com.kuaiba.site.core.security.CurrentUser;

/**
 * 自动获取网站信息
 * @author larry.qi
 */
public class FetchFactory {

	private FetchFactory() {
	}

	/**
	 * 抓取网站信息
	 * @param url
	 * @return
	 */
	public static Recommend get(String url) {
		Recommend record = new Recommend();
		record.setCreator(CurrentUser.getCurrentUserName());
		record.setState((byte)1);
		record.setNameByUUID();
		record.setUrl(url);
		
		try {
			Document document = connect(url);
			record.setTitle(document.title());
			Element metaKey =  document.select("meta[name=keywords]").first();
			Element metaDesc = document.select("meta[name=description]").first();
			
			if (metaKey != null) {
				record.setKeywords(metaKey.attr("content"));
			}

			if (metaDesc != null) {
				record.setDescription(metaDesc.attr("content"));
			}
			
		} catch (IOException e) {
			record.setState((byte)0);
		}
		
		return record;
	}

	/**
	 * 基于JSOUP的方式获取网页内容
	 * @param url
	 * @return
	 * @throws IOException
	 */
	private static Document connect(String url) throws IOException {
		try {
			return Jsoup.connect(url).userAgent(GlobalIDs.USER_AGENT).timeout(GlobalIDs.TIMEOUT).followRedirects(true)
					.ignoreContentType(true).ignoreHttpErrors(true).get();
		} catch (IOException e) {
			return Jsoup.parse(HttpUtil.get(url));
		}
	}

}
