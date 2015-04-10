package be.tribersoft.svt.stock.domain.api;

import java.math.BigDecimal;

public interface StockFactory {

	Stock create(String name, BigDecimal latestPrice, BigDecimal highestPrice, BigDecimal lowestPrice);
}
