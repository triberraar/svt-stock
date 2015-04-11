package be.tribersoft.svt.stock.svt_anti_corruption.implementation;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.core.env.Environment;

import be.tribersoft.svt.stock.svt_anti_corruption.api.SVTType;

@RunWith(MockitoJUnitRunner.class)
public class DefaultSVTConfigurationGetTypeTest {

	private static final String TYPE_FILE = "file";
	private static final String TYPE_KEY = "type";

	@InjectMocks
	private DefaultSVTConfiguration defaultSVTConfiguration;

	@Mock
	private Environment environment;

	@Test
	public void getTypeReturnsTheSVTType() {
		when(environment.getProperty(TYPE_KEY)).thenReturn(TYPE_FILE);
		assertEquals(SVTType.FILE, defaultSVTConfiguration.getType());
	}
}
