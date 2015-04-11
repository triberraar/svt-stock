package be.tribersoft.svt.stock.svt_anti_corruption.implementation;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.validator.routines.BigDecimalValidator;

import be.tribersoft.svt.stock.domain.api.Stock;
import be.tribersoft.svt.stock.domain.api.StockFactory;

@Named
public class SVTDataParser {
	private final static Logger LOGGER = Logger.getLogger(SVTDataParser.class.getName());

	@Inject
	private StockFactory stockFactory;

	public Set<Stock> parse(Collection<String[]> data) {
		Set<Stock> stocks = new HashSet<Stock>();
		for (String[] dataPart : data) {
			Stock parsedStock = parse(dataPart);
			if (parsedStock != null) {
				stocks.add(parsedStock);
			}
		}

		return stocks;
	}

	private Stock parse(String[] data) {
		if (data != null && data.length > 3) {
			for (int i = 0; i < data.length - 3; i++)
				if (data[i].matches("[a-zA-Z]+") && isBigDecimal(data[i + 1]) && isBigDecimal(data[i + 2]) && isBigDecimal(data[i + 3])) {
					return stockFactory.create(data[i], convertToBigDecimal(data[i + 1]), convertToBigDecimal(data[i + 2]), convertToBigDecimal(data[i + 3]));
				}
		}
		LOGGER.warning("Skipping line because it is not stock data or has unsufficient data " + Arrays.toString(data));
		return null;
	}

	private boolean isBigDecimal(String data) {
		return new BigDecimalValidator().isValid(data);
	}

	private BigDecimal convertToBigDecimal(String data) {
		return new BigDecimalValidator().validate(data);
	}

}
