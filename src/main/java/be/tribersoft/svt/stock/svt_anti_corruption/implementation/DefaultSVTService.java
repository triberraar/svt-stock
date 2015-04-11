package be.tribersoft.svt.stock.svt_anti_corruption.implementation;

import java.util.Collection;
import java.util.Set;

import javax.inject.Inject;
import javax.inject.Named;

import be.tribersoft.svt.stock.domain.api.Stock;
import be.tribersoft.svt.stock.svt_anti_corruption.api.SVTService;

@Named
public class DefaultSVTService implements SVTService {

	@Inject
	private SVTDataParser svtDataParser;

	@Inject
	private SVTDataRetriever svtDataRetriever;

	public Set<Stock> getAll() {
		Collection<String[]> dataLines = svtDataRetriever.retrieve();
		return svtDataParser.parse(dataLines);
	}

}
