package be.tribersoft.svt.stock.services.internal;

import javax.inject.Inject;
import javax.inject.Named;

import be.tribersoft.svt.stock.domain.api.StockNotFoundException;
import be.tribersoft.svt.stock.domain.api.StockRepository;
import be.tribersoft.svt.stock.services.api.StockService;

@Named
public class DefaultStockService implements StockService {

	@Inject
	private StockRepository stockRepository;

	@Override
	public void lookupStock(String stockName) {
		try {
			System.out.println(stockRepository.getByName(stockName));
		} catch (StockNotFoundException e) {
			System.out.println(e.toString());
		}

	}

}
