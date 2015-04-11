package be.tribersoft.svt.stock.services.internal;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import be.tribersoft.svt.stock.domain.api.Stock;
import be.tribersoft.svt.stock.domain.api.StockNotFoundException;
import be.tribersoft.svt.stock.domain.api.StockRepository;

@RunWith(MockitoJUnitRunner.class)
public class DefaultStockServiceLookupStockTest {

	private static final String STOCK_STRING = "stock string";
	private static final String NAME = "name";
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();

	@InjectMocks
	private DefaultStockService defaultStockService;

	@Mock
	private Stock stock;

	@Mock
	private StockRepository stockRepository;

	@Before
	public void setUpStreams() {
		System.setOut(new PrintStream(outContent));
		System.setErr(new PrintStream(errContent));
		when(stock.toString()).thenReturn(STOCK_STRING);
	}

	@After
	public void cleanUpStreams() {
		System.setOut(null);
		System.setErr(null);
	}

	@Test
	public void printsStockInformationIfFound() throws StockNotFoundException {
		when(stockRepository.getByName(NAME)).thenReturn(stock);

		defaultStockService.lookupStock(NAME);

		assertEquals(STOCK_STRING + "\n", outContent.toString());
	}

	@Test
	public void handlesNotFoundExceptionWhenStockNotFound() throws StockNotFoundException {
		doThrow(new StockNotFoundException(NAME)).when(stockRepository).getByName(NAME);

		defaultStockService.lookupStock(NAME);

		assertEquals("Stock 'name' not found" + "\n", outContent.toString());
	}
}
