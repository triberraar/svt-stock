package be.tribersoft.svt.stock.svt_anti_corruption.implementation;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.core.env.Environment;

@RunWith(MockitoJUnitRunner.class)
public class DefaultSVTConfigurationGetCachingDurationTest {

	private static final String CAHCING_DURATION_INVALID = "invalid";
	private static final String CACHING_DURATION_VALUE = "500";
	private static final String CACHING_DURATION_KEY = "caching.duration";
	private static final Long CACHING_DURATION = 500l;
	private static final Long CACHING_DURATION_DEFAULT = 300l;

	@InjectMocks
	private DefaultSVTConfiguration defaultSVTConfiguration;

	@Mock
	private Environment environment;

	@Test
	public void getsCachingDuration() {
		when(environment.getProperty(CACHING_DURATION_KEY)).thenReturn(CACHING_DURATION_VALUE);

		assertEquals(CACHING_DURATION, defaultSVTConfiguration.getCachingDuration());
	}

	@Test
	public void returnsDefaultWhenCachingDurationIsNotProvided() {
		when(environment.getProperty(CACHING_DURATION_KEY)).thenReturn(null);

		assertEquals(CACHING_DURATION_DEFAULT, defaultSVTConfiguration.getCachingDuration());
	}

	@Test
	public void returnsDefaultWhenCachingDurationIsBadlyConfigured() {
		when(environment.getProperty(CACHING_DURATION_KEY)).thenReturn(CAHCING_DURATION_INVALID);

		assertEquals(CACHING_DURATION_DEFAULT, defaultSVTConfiguration.getCachingDuration());
	}
}
