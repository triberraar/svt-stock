package be.tribersoft.svt.stock.svt_anti_corruption.implementation;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import be.tribersoft.svt.stock.svt_anti_corruption.api.SVTType;

public class SVTTypeConvertTest {

	@Test
	public void returnsFILETypeWhenPassedFile() {
		assertEquals(SVTType.FILE, SVTType.convert("file"));
		assertEquals(SVTType.FILE, SVTType.convert("FILE"));
		assertEquals(SVTType.FILE, SVTType.convert("fILe"));
	}

	@Test
	public void returnsHTTPTypeWhenPassedHttp() {
		assertEquals(SVTType.HTTP, SVTType.convert("http"));
		assertEquals(SVTType.HTTP, SVTType.convert("HTTP"));
		assertEquals(SVTType.HTTP, SVTType.convert("HtTp"));
	}

	@Test
	public void returnsHTTPTypeWhenPassedSomethingElse() {
		assertEquals(SVTType.HTTP, SVTType.convert("something else"));
		assertEquals(SVTType.HTTP, SVTType.convert(""));
	}

	@Test
	public void returnsHTTPTypeWhenPassedNull() {
		assertEquals(SVTType.HTTP, SVTType.convert(null));
	}

}
