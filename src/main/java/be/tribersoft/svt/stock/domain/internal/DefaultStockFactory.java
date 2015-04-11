package be.tribersoft.svt.stock.domain.internal;

import java.math.BigDecimal;

import javax.inject.Named;

import be.tribersoft.svt.stock.domain.api.Stock;
import be.tribersoft.svt.stock.domain.api.StockFactory;

@Named
public class DefaultStockFactory implements StockFactory {

	@Override
	public Stock create(String name, BigDecimal latestPrice, BigDecimal highestPrice, BigDecimal lowestPrice) {
		DefaultStock stock = new DefaultStock(name);
		stock.setLatestPrice(latestPrice);
		stock.setHighestPrice(highestPrice);
		stock.setLowestPrice(lowestPrice);
		return stock;
	}

}
