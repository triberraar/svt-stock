package be.tribersoft.svt.stock.svt_anti_corruption.implementation;

import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import be.tribersoft.svt.stock.domain.api.Stock;
import be.tribersoft.svt.stock.svt_anti_corruption.implementation.DefaultSVTService;
import be.tribersoft.svt.stock.svt_anti_corruption.implementation.SVTDataParser;
import be.tribersoft.svt.stock.svt_anti_corruption.implementation.SVTDataRetriever;

@RunWith(MockitoJUnitRunner.class)
public class DefaultSVTServiceGetAllTest {

	private static final String STRING = "string";
	private static final Set<String[]> DATA_LINES = new HashSet<String[]>();
	private static final Set<Stock> STOCKS = new HashSet<Stock>();

	@Mock
	private SVTDataParser svtDataParser;

	@Mock
	private SVTDataRetriever svtDataRetriever;

	@InjectMocks
	private DefaultSVTService defaultSVTDataService;

	@Mock
	private Stock stock;

	@Before
	public void setup() {
		String[] strings = { STRING };
		DATA_LINES.add(strings);
		STOCKS.add(stock);
		when(svtDataRetriever.retrieve()).thenReturn(DATA_LINES);
		when(svtDataParser.parse(DATA_LINES)).thenReturn(STOCKS);
	}

	@Test
	public void firstGetsDataThenParsesIt() {
		InOrder inOrder = inOrder(svtDataRetriever, svtDataParser);

		Set<Stock> data = defaultSVTDataService.getAll();

		inOrder.verify(svtDataRetriever).retrieve();
		inOrder.verify(svtDataParser).parse(DATA_LINES);
		assertSame(STOCKS, data);
	}

}
