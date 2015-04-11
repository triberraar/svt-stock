package be.tribersoft.svt.stock.svt_anti_corruption.implementation;

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

	@Override
	public String getUrl() {
		if (getType() == SVTType.HTTP) {
			String url = environment.getProperty(URL_KEY);
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
