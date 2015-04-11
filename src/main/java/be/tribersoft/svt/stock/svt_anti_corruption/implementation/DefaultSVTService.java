package be.tribersoft.svt.stock.svt_anti_corruption.implementation;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Set;

import javax.inject.Inject;
import javax.inject.Named;

import be.tribersoft.svt.stock.domain.api.Stock;
import be.tribersoft.svt.stock.svt_anti_corruption.api.SVTConfiguration;
import be.tribersoft.svt.stock.svt_anti_corruption.api.SVTService;

@Named
public class DefaultSVTService implements SVTService {

	@Inject
	private SVTDataParser svtDataParser;

	@Inject
	private SVTDataRetriever svtDataRetriever;

	@Inject
	private SVTConfiguration svtConfiguration;

	private Set<Stock> stocks;

	private LocalDateTime last;

	public Set<Stock> getAll() {
		if (stocks == null || last == null || last.isBefore(LocalDateTime.now().minusSeconds(svtConfiguration.getCachingDuration()))) {
			Collection<String[]> dataLines = svtDataRetriever.retrieve();
			stocks = svtDataParser.parse(dataLines);
			last = LocalDateTime.now();
		}
		return stocks;
	}
}
