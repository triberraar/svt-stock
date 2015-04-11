package be.tribersoft.svt.stock.domain.internal;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class DefaultStockConstructorTest {

	private static final String NAME = "name";

	@Test
	public void constructsCorrectly() {
		DefaultStock defaultStock = new DefaultStock(NAME);

		assertEquals(NAME, defaultStock.getName());
	}

	@Test(expected = IllegalArgumentException.class)
	public void failsWhenNameIsNull() {
		new DefaultStock(null);
	}

}
