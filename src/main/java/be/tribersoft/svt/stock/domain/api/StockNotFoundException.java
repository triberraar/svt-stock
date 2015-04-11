package be.tribersoft.svt.stock.domain.api;

public class StockNotFoundException extends Exception {

	private String name;

	public StockNotFoundException(String name) {
		this.name = name;
	}

	public String toString() {
		return "Stock '" + name + "' not found";
	}
}
