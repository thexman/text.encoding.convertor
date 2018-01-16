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
