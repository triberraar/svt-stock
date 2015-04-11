package be.tribersoft.svt.stock.svt_anti_corruption.implementation;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.inject.Named;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import be.tribersoft.svt.stock.svt_anti_corruption.api.SVTConfiguration;
import be.tribersoft.svt.stock.svt_anti_corruption.api.SVTType;

@Named
public class SVTDataRetriever {

	private static final String UTF_8 = "UTF-8";
	private final static Logger LOGGER = Logger.getLogger(SVTDataRetriever.class.getName());
	private static final String USER_AGENT = "Mozilla";

	@Inject
	private SVTConfiguration svtConfiguration;

	public Set<String[]> retrieve() {
		Set<String[]> dataLines = new HashSet<>();
		for (String url : svtConfiguration.getUrls()) {
			Document document = getDocument(url);
			if (document != null) {
				Elements dataNodes = document.select("pre.root");
				if (dataNodes.size() == 2) {
					Elements tests = dataNodes.get(1).select("span");
					for (Element test : tests) {
						String[] split = test.text().split("\\s+");
						dataLines.add(split);
					}
				}
			}
		}

		return dataLines;
	}

	private Document getDocument(String url) {
		if (svtConfiguration.getType() == SVTType.HTTP) {
			return getDocumentHTTP(url);
		} else {
			return getDocumentFile(url);
		}

	}

	private Document getDocumentFile(String file) {
		try {
			return Jsoup.parse(getClass().getResourceAsStream(file), UTF_8, "");
		} catch (IOException e) {
			LOGGER.severe("Failed to connect to open file");
		}
		return null;
	}

	private Document getDocumentHTTP(String url) {
		try {
			return Jsoup.connect(url).userAgent(USER_AGENT).get();
		} catch (IOException e) {
			LOGGER.severe("Failed to connect to SVT server");
		}
		return null;
	}

}
