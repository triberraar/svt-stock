package be.tribersoft.svt.stock.svt_anti_corruption.api;

import java.util.Set;

public interface SVTConfiguration {

	Set<String> getUrls();

	SVTType getType();

	Long getCachingDuration();

}
