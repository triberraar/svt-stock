package be.tribersoft.svt.stock.svt_anti_corruption.implementation;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.env.Environment;

import be.tribersoft.svt.stock.svt_anti_corruption.api.SVTConfiguration;
import be.tribersoft.svt.stock.svt_anti_corruption.api.SVTType;

@Configuration
@PropertySources({ @PropertySource("svt.properties"), @PropertySource(value = "file:svt.properties", ignoreResourceNotFound = true) })
public class DefaultSVTConfiguration implements SVTConfiguration {

	private static final String URL_KEY = "url";
	private static final String SVT_PREFIX = "http://www.svt.se/svttext/web/pages/";
	private static final String SVT_PAGE = "203.html";
	private static final String STUB_LOCATION = "/stub.html";
	@Inject
	private Environment environment;

	@SuppressWarnings("unchecked")
	@Override
	public Set<String> getUrls() {
		List<String> urls = environment.getProperty("urls", List.class);
		Set<String> result = new HashSet<String>();
		if (urls == null) {
			urls = new ArrayList<String>();
		}

		for (String url : urls) {
			result.add(getUrl(url));
		}
		if (result.isEmpty()) {
			result.add(getDefaultUrl());
		}
		return result;
	}

	private String getDefaultUrl() {
		if (getType() == SVTType.HTTP) {
			return SVT_PREFIX + SVT_PAGE;
		} else {
			return STUB_LOCATION;
		}
	}

	private String getUrl(String url) {
		if (getType() == SVTType.HTTP) {
			if (url == null || !url.startsWith(SVT_PREFIX)) {
				url = SVT_PREFIX + SVT_PAGE;
			}
			return url;
		} else {
			return STUB_LOCATION;
		}
	}

	@Override
	public SVTType getType() {
		return SVTType.convert(environment.getProperty("type"));
	}
}