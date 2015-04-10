package be.tribersoft.svt.stock.services.svt.implementation;

import static org.junit.Assert.assertFalse;

import java.util.Collection;

import org.junit.Test;

public class SVTDataRetrieverRetrieveTest {

	private SVTDataRetriever svtDataRetriever = new SVTDataRetriever();

	@Test
	public void retrievesData() {
		Collection<String[]> dataLines = svtDataRetriever.retrieve();

		assertFalse(dataLines.isEmpty());
	}

}
