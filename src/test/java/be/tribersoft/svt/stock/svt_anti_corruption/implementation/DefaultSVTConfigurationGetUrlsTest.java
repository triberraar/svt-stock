package be.tribersoft.svt.stock.svt_anti_corruption.implementation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.core.env.Environment;

@RunWith(MockitoJUnitRunner.class)
public class DefaultSVTConfigurationGetUrlsTest {

	private static final String STUB_LOCATON = "/stub.html";
	private static final String TYPE_FILE = "file";
	private static final String TYPE_HTTP = "http";
	private static final String TYPE_KEY = "type";
	private static final String VALID_URL_1 = "http://www.svt.se/svttext/web/pages/204.html";
	private static final String VALID_URL_2 = "http://www.svt.se/svttext/web/pages/205.html";
	private static final String URLS_KEY = "urls";

	@InjectMocks
	private DefaultSVTConfiguration defaultSVTConfiguration;

	@Mock
	private Environment environment;

	@Test
	public void returnsTheUrlsWhenInHTTPModeAndURLsAreValid() {
		List<String> urls = new ArrayList<>();
		urls.add(VALID_URL_1);
		urls.add(VALID_URL_2);
		doReturn(urls).when(environment).getProperty(URLS_KEY, List.class);
		when(environment.getProperty(TYPE_KEY)).thenReturn(TYPE_HTTP);

		assertEquals(2, defaultSVTConfiguration.getUrls().size());
		assertTrue(defaultSVTConfiguration.getUrls().contains(VALID_URL_1));
		assertTrue(defaultSVTConfiguration.getUrls().contains(VALID_URL_2));
	}

	@Test
	public void fallbacksWhenURLIsNotSpecifiedInHTTPMode() {
		List<String> urls = new ArrayList<>();
		urls.add(null);
		doReturn(urls).when(environment).getProperty(URLS_KEY, List.class);
		when(environment.getProperty(TYPE_KEY)).thenReturn(TYPE_HTTP);

		assertEquals(1, defaultSVTConfiguration.getUrls().size());
		assertTrue(defaultSVTConfiguration.getUrls().contains("http://www.svt.se/svttext/web/pages/203.html"));
	}

	@Test
	public void fallbacksWhenURLHasWrongBeginningInHTTPMode() {
		List<String> urls = new ArrayList<>();
		urls.add("http://www.google.be");
		doReturn(urls).when(environment).getProperty(URLS_KEY, List.class);
		when(environment.getProperty(TYPE_KEY)).thenReturn(TYPE_HTTP);

		assertEquals(1, defaultSVTConfiguration.getUrls().size());
		assertTrue(defaultSVTConfiguration.getUrls().contains("http://www.svt.se/svttext/web/pages/203.html"));
	}

	@Test
	public void fallbacksWhenNoURLSSpecified() {
		doReturn(null).when(environment).getProperty(URLS_KEY, List.class);
		when(environment.getProperty(TYPE_KEY)).thenReturn(TYPE_HTTP);

		assertEquals(1, defaultSVTConfiguration.getUrls().size());
		assertTrue(defaultSVTConfiguration.getUrls().contains("http://www.svt.se/svttext/web/pages/203.html"));
	}

	@Test
	public void returnsStubLocationWhenInFileMode() {
		when(environment.getProperty(TYPE_KEY)).thenReturn(TYPE_FILE);

		assertEquals(1, defaultSVTConfiguration.getUrls().size());
		assertTrue(defaultSVTConfiguration.getUrls().contains(STUB_LOCATON));
	}

}
