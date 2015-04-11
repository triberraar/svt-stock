package be.tribersoft.svt.stock.svt_anti_corruption.implementation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import be.tribersoft.svt.stock.svt_anti_corruption.api.SVTConfiguration;
import be.tribersoft.svt.stock.svt_anti_corruption.api.SVTType;
import be.tribersoft.svt.stock.svt_anti_corruption.implementation.SVTDataRetriever;

@RunWith(MockitoJUnitRunner.class)
public class SVTDataRetrieverRetrieveTest {
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
		when(svtConfiguration.getUrl()).thenReturn("http://www.svt.se/svttext/web/pages/203.html");

		Set<String[]> dataLines = svtDataRetriever.retrieve();

		assertFalse(dataLines.isEmpty());
	}

	@Test
	public void retrievesDataFromFileWhenConfiguredAsFile() {
		when(svtConfiguration.getType()).thenReturn(SVTType.FILE);
		when(svtConfiguration.getUrl()).thenReturn("/stub.html");

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
