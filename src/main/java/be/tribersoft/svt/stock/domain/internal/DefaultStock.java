package be.tribersoft.svt.stock.domain.internal;

import java.math.BigDecimal;

import org.springframework.util.Assert;

import be.tribersoft.svt.stock.domain.api.Stock;

public class DefaultStock implements Stock {

	private String name;
	private BigDecimal latestPrice;
	private BigDecimal highestPrice;
	private BigDecimal lowestPrice;

	public DefaultStock(String name) {
		Assert.notNull(name);
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public BigDecimal getLatestPrice() {
		return latestPrice;
	}

	public BigDecimal getHighestPrice() {
		return highestPrice;
	}

	public BigDecimal getLowestPrice() {
		return lowestPrice;
	}

	public void setLatestPrice(BigDecimal latestPrice) {
		this.latestPrice = latestPrice;
	}

	public void setHighestPrice(BigDecimal highestPrice) {
		this.highestPrice = highestPrice;
	}

	public void setLowestPrice(BigDecimal lowestPrice) {
		this.lowestPrice = lowestPrice;
	}

}
