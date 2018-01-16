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
