package be.tribersoft.svt.stock.domain.internal;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.Test;

import be.tribersoft.svt.stock.domain.api.Stock;

public class DefaultStockFactoryCreateTest {
	private static final String NAME = "name";
	private static final BigDecimal LATEST_PRICE = new BigDecimal(12.4);
	private static final BigDecimal HIGHEST_PRICE = new BigDecimal(90.1);
	private static final BigDecimal LOWEST_PRICE = new BigDecimal(123.8);

	private DefaultStockFactory defaultStockFactory = new DefaultStockFactory();

	@Test
	public void createsAStock() {
		Stock stock = defaultStockFactory.create(NAME, LATEST_PRICE, HIGHEST_PRICE, LOWEST_PRICE);

		assertEquals(NAME, stock.getName());
		assertEquals(LATEST_PRICE, stock.getLatestPrice());
		assertEquals(HIGHEST_PRICE, stock.getHighestPrice());
		assertEquals(LOWEST_PRICE, stock.getLowestPrice());
	}
}
