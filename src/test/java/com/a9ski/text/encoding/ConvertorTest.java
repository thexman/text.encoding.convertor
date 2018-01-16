/*-
 * #%L
 * convertor
 * %%
 * Copyright (C) 2017 - 2018 Kiril Arabadzhiyski
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as
 * published by the Free Software Foundation, either version 2 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/gpl-2.0.html>.
 * #L%
 */
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
