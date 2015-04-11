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

	public String toString() {
		return "Stock: '" + name + "' latest price: " + (latestPrice != null ? latestPrice : "UNKNOWN") + " highest price: " + (highestPrice != null ? highestPrice : "UNKNOWN") + " lowest price "
				+ (lowestPrice != null ? lowestPrice : "UNKNOWN") + ".";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DefaultStock other = (DefaultStock) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

}
