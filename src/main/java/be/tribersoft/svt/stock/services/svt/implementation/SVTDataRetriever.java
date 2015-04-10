package be.tribersoft.svt.stock.services.svt.implementation;

import java.io.IOException;
import java.util.Collection;
import java.util.HashSet;
import java.util.logging.Logger;

import javax.inject.Named;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

@Named
public class SVTDataRetriever {

	private final static Logger LOGGER = Logger.getLogger(SVTDataRetriever.class.getName());

	private static final String USER_AGENT = "Mozilla";
	private static final String SVT_URL = "http://www.svt.se/svttext/web/pages/203.html";

	public Collection<String[]> retrieve() {
		Collection<String[]> dataLines = new HashSet<>();
		Document doc;
		try {
			doc = Jsoup.connect(SVT_URL).userAgent(USER_AGENT).get();
			Elements dataNodes = doc.select("pre.root");
			if (dataNodes.size() == 2) {
				Elements tests = dataNodes.get(1).select("span");
				for (Element test : tests) {
					String[] split = test.text().split("\\s+");
					dataLines.add(split);
				}
			}
		} catch (IOException e) {
			LOGGER.severe("Failed to connect to SVT server");
		}

		return dataLines;
	}

}
