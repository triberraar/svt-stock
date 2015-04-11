package be.tribersoft.svt.stock.domain.api;

public interface StockRepository {

	Stock getByName(String name) throws StockNotFoundException;

}
