package be.tribersoft.svt.stock.services.svt.implementation;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

import be.tribersoft.svt.stock.domain.internal.DefaultStock;

public class DefaultStockToStringTest {
	private static final String NAME = "name";
	private static final BigDecimal HIGHEST_PRICE = new BigDecimal("12.4");
	private static final BigDecimal LATEST_PRICE = new BigDecimal("34.8");
	private static final BigDecimal LOWEST_PRICE = new BigDecimal("907");

	private DefaultStock defaultStock = new DefaultStock(NAME);

	@Before
	public void setup() {
		defaultStock.setLatestPrice(LATEST_PRICE);
		defaultStock.setHighestPrice(HIGHEST_PRICE);
		defaultStock.setLowestPrice(LOWEST_PRICE);
	}

	@Test
	public void providesAReadableMessageWhenAllDataProvided() {
		assertEquals("Stock: 'name' latest price: 34.8 highest price: 12.4 lowest price 907.", defaultStock.toString());
	}

	@Test
	public void providesAReadableMessageWhenLatestPriceNotProvided() {
		defaultStock.setLatestPrice(null);

		assertEquals("Stock: 'name' latest price: UNKNOWN highest price: 12.4 lowest price 907.", defaultStock.toString());
	}

	@Test
	public void providesAReadableMessageWhenHighestPriceNotProvided() {
		defaultStock.setHighestPrice(null);

		assertEquals("Stock: 'name' latest price: 34.8 highest price: UNKNOWN lowest price 907.", defaultStock.toString());
	}

	@Test
	public void providesAReadableMessageWhenLowestPriceNotProvided() {
		defaultStock.setLowestPrice(null);

		assertEquals("Stock: 'name' latest price: 34.8 highest price: 12.4 lowest price UNKNOWN.", defaultStock.toString());
	}
}
