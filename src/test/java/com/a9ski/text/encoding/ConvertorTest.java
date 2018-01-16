package com.a9ski.text.encoding;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import org.junit.Test;

public class ConvertorTest {

	@Test
	public void testConvertInputStreamOutputStream() throws IOException {
		final Charset mik = Charset.forName("MIK");
		try(final InputStream is = getClass().getResourceAsStream("/mik.txt")) {
			final Convertor c = new Convertor(mik, StandardCharsets.UTF_8);
			assertEquals("Turbo Pascal \u0422\u0443\u0440\u0431\u043E \u041F\u0430\u0441\u043A\u0430\u043B 7.0", c.convert(is));
		}
	}

}
