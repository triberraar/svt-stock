package be.tribersoft.svt.stock.domain.api;

import java.math.BigDecimal;

public interface Stock {

	String getName();

	BigDecimal getLatestPrice();

	BigDecimal getHighestPrice();

	BigDecimal getLowestPrice();
}
