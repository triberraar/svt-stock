package be.tribersoft.svt.stock.domain.internal;

import javax.inject.Inject;
import javax.inject.Named;

import be.tribersoft.svt.stock.domain.api.Stock;
import be.tribersoft.svt.stock.domain.api.StockNotFoundException;
import be.tribersoft.svt.stock.domain.api.StockRepository;
import be.tribersoft.svt.stock.services.svt.api.SVTService;

@Named
public class DefaultStockRepository implements StockRepository {

	@Inject
	private SVTService svtService;

	@Override
	public Stock getByName(String name) throws StockNotFoundException {
		for (Stock stock : svtService.getAll()) {
			if (name.equals(stock.getName())) {
				return stock;
			}
		}
		throw new StockNotFoundException(name);
	}

}
