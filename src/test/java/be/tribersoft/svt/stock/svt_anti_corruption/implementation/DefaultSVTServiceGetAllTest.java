package be.tribersoft.svt.stock.svt_anti_corruption.implementation;

import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.internal.util.reflection.Whitebox;
import org.mockito.runners.MockitoJUnitRunner;

import be.tribersoft.svt.stock.domain.api.Stock;
import be.tribersoft.svt.stock.svt_anti_corruption.api.SVTConfiguration;

@RunWith(MockitoJUnitRunner.class)
public class DefaultSVTServiceGetAllTest {

	private static final String STRING = "string";
	private static final Set<String[]> DATA_LINES = new HashSet<String[]>();
	private static final Set<Stock> STOCKS = new HashSet<Stock>();
	private static final Long CACHING_DURATION = 60l;

	@Mock
	private SVTDataParser svtDataParser;

	@Mock
	private SVTDataRetriever svtDataRetriever;

	@InjectMocks
	private DefaultSVTService defaultSVTDataService;

	@Mock
	private Stock stock;

	@Mock
	private SVTConfiguration svtConfiguration;

	@Before
	public void setup() {
		String[] strings = { STRING };
		DATA_LINES.add(strings);
		STOCKS.add(stock);
		when(svtDataRetriever.retrieve()).thenReturn(DATA_LINES);
		when(svtDataParser.parse(DATA_LINES)).thenReturn(STOCKS);
		when(svtConfiguration.getCachingDuration()).thenReturn(CACHING_DURATION);
	}

	@Test
	public void firstGetsDataThenParsesIt() {
		InOrder inOrder = inOrder(svtDataRetriever, svtDataParser);

		Set<Stock> data = defaultSVTDataService.getAll();

		inOrder.verify(svtDataRetriever).retrieve();
		inOrder.verify(svtDataParser).parse(DATA_LINES);
		assertSame(STOCKS, data);
	}

	@Test
	public void returnsCachedDataIfCacheDurationNotExceeded() {
		Whitebox.setInternalState(defaultSVTDataService, "stocks", STOCKS);
		Whitebox.setInternalState(defaultSVTDataService, "last", LocalDateTime.now());

		Set<Stock> data = defaultSVTDataService.getAll();
		assertSame(STOCKS, data);
		verifyZeroInteractions(svtDataRetriever, svtDataParser);
	}

	@Test
	public void requriesDataIfCacheDurationIsExceeded() {
		InOrder inOrder = inOrder(svtDataRetriever, svtDataParser);
		Whitebox.setInternalState(defaultSVTDataService, "stocks", STOCKS);
		Whitebox.setInternalState(defaultSVTDataService, "last", LocalDateTime.now().minusSeconds(CACHING_DURATION + 1));

		Set<Stock> data = defaultSVTDataService.getAll();

		inOrder.verify(svtDataRetriever).retrieve();
		inOrder.verify(svtDataParser).parse(DATA_LINES);
		assertSame(STOCKS, data);
	}

	@Test
	public void requeriesDataIfNotCachedYet() {
		InOrder inOrder = inOrder(svtDataRetriever, svtDataParser);
		Whitebox.setInternalState(defaultSVTDataService, "stocks", null);
		Whitebox.setInternalState(defaultSVTDataService, "last", null);

		Set<Stock> data = defaultSVTDataService.getAll();

		inOrder.verify(svtDataRetriever).retrieve();
		inOrder.verify(svtDataParser).parse(DATA_LINES);
		assertSame(STOCKS, data);
	}

}
