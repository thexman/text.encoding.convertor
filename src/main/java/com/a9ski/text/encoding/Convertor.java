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

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;

import org.apache.commons.io.IOUtils;

public class Convertor {
	private final Charset inputCharset;
	private final Charset outputCharset;
	
	public Convertor(Charset inputCharset, Charset outputCharset) {
		super();
		this.inputCharset = inputCharset;
		this.outputCharset = outputCharset;
	}
	
	public void convert(InputStream is, OutputStream os) throws IOException {
		try (final BufferedReader r = new BufferedReader(new InputStreamReader(is, inputCharset))) {
			try (final BufferedWriter w = new BufferedWriter(new OutputStreamWriter(os, outputCharset))) {
				IOUtils.copy(r, w);
			}
		}
	}
	
	public String convert(InputStream is) throws IOException {
		try (final ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
			convert(is, bos);
			return new String(bos.toByteArray(), outputCharset);
		}
	}
	
	public String convert(byte[] input) throws IOException {
		try(final ByteArrayInputStream bis = new ByteArrayInputStream(input)) {
			try (final ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
				convert(bis, bos);
				return new String(bos.toByteArray(), outputCharset);
			}
		}
	}
	
	public void convert(File in, File out) throws FileNotFoundException, IOException {
		try (final InputStream is = new FileInputStream(in)) {
			try (final OutputStream os = new FileOutputStream(out)) {
				convert(is, os);
			}
		}
	}
	
	
}
