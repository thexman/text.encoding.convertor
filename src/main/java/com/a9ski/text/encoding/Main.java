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

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

public class Main {
	public static void main(String[] args) throws IOException {
		final Option inputFileOpt = Option.builder("i").longOpt("in").required().desc("specifies input file").numberOfArgs(1)
				.hasArg().argName("input-file").build();
		
		final Option outputFileOpt = Option.builder("o").longOpt("out").required().desc("specifies output file").numberOfArgs(1)
				.hasArg().argName("output-file").build();
		
		final Option inputCharsetNameOpt = Option.builder("ic").longOpt("input-charset").required().desc("specifies input charset name").numberOfArgs(1)
				.hasArg().argName("charset-name").build();
		
		final Option outputCharsetNameOpt = Option.builder("oc").longOpt("output-charset").required().desc("specifies output charset name").numberOfArgs(1)
				.hasArg().argName("charset-name").build();
		
		final Option helpOpt = Option.builder("h").longOpt("help").desc("print this message").build();
		
		final Options options = new Options();
		options.addOption(helpOpt);
		options.addOption(inputFileOpt);
		options.addOption(outputFileOpt);
		options.addOption(inputCharsetNameOpt);
		options.addOption(outputCharsetNameOpt);
		
		try {
			final CommandLineParser parser = new DefaultParser();
			final CommandLine line = parser.parse(options, args);
			
			final Charset inputCharset = Charset.forName(line.getOptionValue(inputCharsetNameOpt.getOpt()));
			final Charset outputCharset = Charset.forName(line.getOptionValue(outputCharsetNameOpt.getOpt()));
			
			final Convertor convertor = new Convertor(inputCharset, outputCharset);
			
			final File inputFile = new File(line.getOptionValue(inputFileOpt.getOpt()));
			final File outputFile = new File(line.getOptionValue(outputFileOpt.getOpt()));
			
			convertor.convert(inputFile, outputFile);
		} catch (final ParseException ex) {
			final HelpFormatter formatter = new HelpFormatter();
			formatter.printHelp("convertor", options);
		}
	}
}
