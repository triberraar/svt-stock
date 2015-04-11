package be.tribersoft.svt.stock.services.svt.implementation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import be.tribersoft.svt.stock.domain.api.Stock;
import be.tribersoft.svt.stock.domain.api.StockFactory;

@RunWith(MockitoJUnitRunner.class)
public class SVTDataParserParseTest {

	private static final String NAME_1 = "namea";
	private static final String LATEST_PRICE_1 = "12.3";
	private static final String HIGHEST_PRICE_1 = "56.7";
	private static final String LOWEST_PRICE_1 = "34.1";
	private static final String[] VALID_DATA_1 = { NAME_1, LATEST_PRICE_1, HIGHEST_PRICE_1, LOWEST_PRICE_1 };
	private static final String NAME_2 = "nameb";
	private static final String LATEST_PRICE_2 = "212.3";
	private static final String HIGHEST_PRICE_2 = "256.7";
	private static final String LOWEST_PRICE_2 = "234.1";
	private static final String[] VALID_DATA_2 = { NAME_2, LATEST_PRICE_2, HIGHEST_PRICE_2, LOWEST_PRICE_2 };

	@InjectMocks
	private SVTDataParser svtDataParser;

	@Mock
	private StockFactory stockFactory;
	@Mock
	private Stock stock1, stock2;

	@Before
	public void setup() {
		when(stockFactory.create(NAME_1, new BigDecimal(LATEST_PRICE_1), new BigDecimal(HIGHEST_PRICE_1), new BigDecimal(LOWEST_PRICE_1))).thenReturn(stock1);
		when(stockFactory.create(NAME_2, new BigDecimal(LATEST_PRICE_2), new BigDecimal(HIGHEST_PRICE_2), new BigDecimal(LOWEST_PRICE_2))).thenReturn(stock2);
	}

	@Test
	public void returnsANewStockWhenDataContainsNameLatestPriceHighestPriceAndLowestPrice() {
		List<String[]> data = new ArrayList<>();
		data.add(VALID_DATA_1);

		Set<Stock> result = svtDataParser.parse(data);

		assertEquals(1, result.size());
		assertTrue(result.contains(stock1));
	}

	@Test
	public void returnsASetOfStocksWhenDataContainsMultipleValidLines() {
		List<String[]> data = new ArrayList<>();
		data.add(VALID_DATA_1);
		data.add(VALID_DATA_2);

		Set<Stock> result = svtDataParser.parse(data);

		assertEquals(2, result.size());
		assertTrue(result.contains(stock1));
		assertTrue(result.contains(stock2));
	}

	@Test
	public void returnsANewStockEvenWhenPrependedWithOtherData() {
		String[] dataLine = { "other data", NAME_1, LATEST_PRICE_1, HIGHEST_PRICE_1, LOWEST_PRICE_1 };
		List<String[]> data = new ArrayList<>();
		data.add(dataLine);

		Set<Stock> result = svtDataParser.parse(data);

		assertEquals(1, result.size());
		assertTrue(result.contains(stock1));
	}

	@Test
	public void doesntReturnANewStockIfOneOfThePricesIsMissing() {
		String[] dataLine = { NAME_1, HIGHEST_PRICE_1, LOWEST_PRICE_1 };
		List<String[]> data = new ArrayList<>();
		data.add(dataLine);

		Set<Stock> result = svtDataParser.parse(data);

		assertTrue(result.isEmpty());
	}

	@Test
	public void doesntReturnANewStockIfTwoOfThePricesAreMissing() {
		String[] dataLine = { NAME_1, HIGHEST_PRICE_1 };
		List<String[]> data = new ArrayList<>();
		data.add(dataLine);

		Set<Stock> result = svtDataParser.parse(data);

		assertTrue(result.isEmpty());
	}

	@Test
	public void doesntReturnANewStockIfThreeOfThePricesAreMissing() {
		String[] dataLine = { NAME_1 };
		List<String[]> data = new ArrayList<>();
		data.add(dataLine);

		Set<Stock> result = svtDataParser.parse(data);

		assertTrue(result.isEmpty());
	}

	@Test
	public void doesntReturnANewStockIfLineIsEmpty() {
		String[] dataLine = {};
		List<String[]> data = new ArrayList<>();
		data.add(dataLine);

		Set<Stock> result = svtDataParser.parse(data);

		assertTrue(result.isEmpty());
	}

	@Test
	public void doesntReturnANewStockIfLineIsnull() {
		String[] dataLine = null;
		List<String[]> data = new ArrayList<>();
		data.add(dataLine);

		Set<Stock> result = svtDataParser.parse(data);

		assertTrue(result.isEmpty());
	}
}
