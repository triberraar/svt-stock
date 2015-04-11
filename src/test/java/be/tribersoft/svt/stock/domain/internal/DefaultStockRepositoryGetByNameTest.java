package be.tribersoft.svt.stock.domain.internal;

import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import be.tribersoft.svt.stock.domain.api.Stock;
import be.tribersoft.svt.stock.domain.api.StockNotFoundException;
import be.tribersoft.svt.stock.svt_anti_corruption.api.SVTService;

@RunWith(MockitoJUnitRunner.class)
public class DefaultStockRepositoryGetByNameTest {

	private static final String NAME_1 = "name 1";
	private static final String NAME_2 = "name 2";
	private static final String UNEXISTING = "unexisting";

	@InjectMocks
	private DefaultStockRepository defaultStockRepository;

	@Mock
	private SVTService svtService;

	@Mock
	private Stock stock1, stock2;

	@Before
	public void setup() {
		when(stock1.getName()).thenReturn(NAME_1);
		when(stock2.getName()).thenReturn(NAME_2);
		Set<Stock> stocks = new HashSet<>();
		stocks.add(stock1);
		stocks.add(stock2);
		doReturn(stocks).when(svtService).getAll();
	}

	@Test
	public void returnsStockIfFound() throws StockNotFoundException {
		assertSame(stock2, defaultStockRepository.getByName(NAME_2));
		assertSame(stock1, defaultStockRepository.getByName(NAME_1));
	}

	@Test
	public void returnsStockIfFoundCaseInsensitive() throws StockNotFoundException {
		assertSame(stock2, defaultStockRepository.getByName(NAME_2.toLowerCase()));
		assertSame(stock2, defaultStockRepository.getByName(NAME_2.toUpperCase()));
	}

	@Test(expected = StockNotFoundException.class)
	public void failsWhenStockNotFound() throws StockNotFoundException {
		defaultStockRepository.getByName(UNEXISTING);
	}
}
