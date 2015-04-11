package be.tribersoft.svt.stock.domain.internal;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class DefaultStockEqualsTest {

	private static final String DIFFERENT_NAME = "different name";
	private static final String NAME = "name";

	@Test
	public void trueWhenTwoStocksHaveSameName() {
		DefaultStock stock1 = new DefaultStock(NAME);
		DefaultStock stock2 = new DefaultStock(NAME);

		assertTrue(stock1.equals(stock2));
		assertTrue(stock2.equals(stock1));
		assertTrue(stock2.equals(stock2));
	}

	@Test
	public void falseWhenTwoStocksHaveDifferentName() {
		DefaultStock stock1 = new DefaultStock(NAME);
		DefaultStock stock2 = new DefaultStock(DIFFERENT_NAME);

		assertFalse(stock1.equals(stock2));
		assertFalse(stock2.equals(stock1));
	}
}
