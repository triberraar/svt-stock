package be.tribersoft.svt.stock.domain.api;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class StockNotFoundExceptionTest {

	private static final String NAME = "name";

	@Test
	public void toStringNotifiesUser() {
		StockNotFoundException exception = new StockNotFoundException(NAME);

		assertEquals("Stock '" + NAME + "' not found", exception.toString());
	}
}
