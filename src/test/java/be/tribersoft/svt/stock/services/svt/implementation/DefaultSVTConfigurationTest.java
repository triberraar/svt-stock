package be.tribersoft.svt.stock.services.svt.implementation;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.core.env.Environment;

import be.tribersoft.svt.stock.services.svt.api.SVTType;

@RunWith(MockitoJUnitRunner.class)
public class DefaultSVTConfigurationTest {

	private static final String STUB_LOCATON = "/stub.html";
	private static final String TYPE_FILE = "file";
	private static final String TYPE_HTTP = "http";
	private static final String TYPE_KEY = "type";
	private static final String VALID_URL = "http://www.svt.se/svttext/web/pages/204.html";
	private static final String URL_KEY = "url";

	@InjectMocks
	private DefaultSVTConfiguration defaultSVTConfiguration;

	@Mock
	private Environment environment;

	@Test
	public void getUrlReturnsTheUrlWhenInHTTPModeAndURLIsValid() {
		when(environment.getProperty(URL_KEY)).thenReturn(VALID_URL);
		when(environment.getProperty(TYPE_KEY)).thenReturn(TYPE_HTTP);

		assertEquals(VALID_URL, defaultSVTConfiguration.getUrl());
	}

	@Test
	public void fallbacksWhenURLIsNotSpecifiedInHTTPMode() {
		when(environment.getProperty(URL_KEY)).thenReturn(null);
		when(environment.getProperty(TYPE_KEY)).thenReturn(TYPE_HTTP);

		assertEquals("http://www.svt.se/svttext/web/pages/203.html", defaultSVTConfiguration.getUrl());
	}

	@Test
	public void fallbacksWhenURLHasWrongBeginningInHTTPMode() {
		when(environment.getProperty(URL_KEY)).thenReturn("http://www.google.be");
		when(environment.getProperty(TYPE_KEY)).thenReturn(TYPE_HTTP);

		assertEquals("http://www.svt.se/svttext/web/pages/203.html", defaultSVTConfiguration.getUrl());
	}

	@Test
	public void returnsStubLocationWhenInFileMode() {
		when(environment.getProperty(TYPE_KEY)).thenReturn(TYPE_FILE);
		assertEquals(STUB_LOCATON, defaultSVTConfiguration.getUrl());
	}

	@Test
	public void getTypeReturnsTheSVTType() {
		when(environment.getProperty(TYPE_KEY)).thenReturn(TYPE_FILE);
		assertEquals(SVTType.FILE, defaultSVTConfiguration.getType());
	}
}
