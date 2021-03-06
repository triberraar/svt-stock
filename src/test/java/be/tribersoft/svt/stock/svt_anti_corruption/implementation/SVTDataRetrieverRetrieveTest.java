package be.tribersoft.svt.stock.svt_anti_corruption.implementation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import be.tribersoft.svt.stock.svt_anti_corruption.api.SVTConfiguration;
import be.tribersoft.svt.stock.svt_anti_corruption.api.SVTType;

@RunWith(MockitoJUnitRunner.class)
public class SVTDataRetrieverRetrieveTest {
	private static final String STUB_LOCATION = "/stub.html";
	private static final String INVALID_SVT_URL = "http://www.svt.se/svttext/web/pages/1203.html";
	private static final String SVT_URL_2 = "http://www.svt.se/svttext/web/pages/204.html";
	private static final String SVT_URL_1 = "http://www.svt.se/svttext/web/pages/203.html";
	private static final String[] STUB_LINE_1 = { "STOCKa", "12.2", "4.5", "70" };
	private static final String[] STUB_LINE_2 = { "STOCKb", "123.2", "2123.8", "890" };
	private static final String[] STUB_LINE_3 = { "STOCKc", "123.2" };

	@InjectMocks
	private SVTDataRetriever svtDataRetriever;

	@Mock
	private SVTConfiguration svtConfiguration;

	@Test
	public void retrievesDataFromHTTPWhenConfiguredAsHTTP() {
		when(svtConfiguration.getType()).thenReturn(SVTType.HTTP);
		doReturn(new HashSet<>(Arrays.asList(SVT_URL_1, SVT_URL_2))).when(svtConfiguration).getUrls();

		Set<String[]> dataLines = svtDataRetriever.retrieve();

		assertFalse(dataLines.isEmpty());
	}

	@Test
	public void retrievesDataFromHTTPWhenConfiguredAsHTTPAndAUrlIsInvlaid() {
		when(svtConfiguration.getType()).thenReturn(SVTType.HTTP);
		doReturn(new HashSet<>(Arrays.asList(INVALID_SVT_URL, SVT_URL_2))).when(svtConfiguration).getUrls();

		Set<String[]> dataLines = svtDataRetriever.retrieve();

		assertFalse(dataLines.isEmpty());
	}

	@Test
	public void retrievesDataFromFileWhenConfiguredAsFile() {
		when(svtConfiguration.getType()).thenReturn(SVTType.FILE);
		doReturn(new HashSet<>(Arrays.asList(STUB_LOCATION))).when(svtConfiguration).getUrls();

		Set<String[]> dataLines = svtDataRetriever.retrieve();

		assertEquals(3, dataLines.size());
		assertTrue(assertStubDataContains(dataLines, STUB_LINE_1));
		assertTrue(assertStubDataContains(dataLines, STUB_LINE_2));
		assertTrue(assertStubDataContains(dataLines, STUB_LINE_3));
	}

	private boolean assertStubDataContains(Set<String[]> dataLines, String[] expectedDataLine) {
		for (String[] dataLine : dataLines) {
			if (Arrays.equals(dataLine, expectedDataLine)) {
				return true;
			}
		}
		return false;
	}

}
