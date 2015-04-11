package be.tribersoft.svt.stock.services.svt.implementation;

import java.util.Collection;
import java.util.Set;

import javax.inject.Inject;
import javax.inject.Named;

import be.tribersoft.svt.stock.domain.api.Stock;
import be.tribersoft.svt.stock.services.svt.api.SVTService;

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
