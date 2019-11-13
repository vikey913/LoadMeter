package com.loadmeter.util;

import org.apache.commons.cli.CommandLine;

public class CommandLineUtil {

	public static int getInteger(CommandLine line, String string) throws Exception {
		if (line.hasOption(string)) {
			return Integer.parseInt(line.getOptionValue(string));
		}
		throw new Exception(string + "missing in cmd args");
	}

}
