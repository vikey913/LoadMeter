package com.loadmeter.util;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.json.simple.JSONObject;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

public class CommandLineUtil {

	public static int getInteger(CommandLine line, String arg) throws Exception {
		if (line.hasOption(arg)) {
			return Integer.parseInt(line.getOptionValue(arg));
		}
		throw new Exception(arg + "missing in cmd args");
	}
	

	public static String getString(CommandLine line, String arg) throws Exception {
		if (line.hasOption(arg)) {
			return line.getOptionValue(arg);
		}
		throw new Exception(arg + "missing in cmd args");
	}

	public static URI getURI(CommandLine line, String arg) throws Exception {
		
		if (line.hasOption(arg)) {
			
			try {
				return new URI(line.getOptionValue(arg));
			} catch (URISyntaxException e) {
				e.printStackTrace();
			}
		}
		throw new Exception(arg + "missing in cmd args");
	}

	public static JSONObject getJSON(CommandLine line, String arg) throws JsonParseException, JsonMappingException, IOException {
		if (line.hasOption(arg)) {
			return (JSONObject) JSONUtil.parseAsObject(line.getOptionValue(arg), JSONObject.class);
		}
		return null;
	}


	public static Map<String, String> getMap(CommandLine line, String arg) {
		Map<String, String> headerMap = new HashMap();
		
		if (line.hasOption(arg)) {
			for (String headerPair: line.getOptionValues(arg)) {
				String []pair = headerPair.split(":");
				headerMap.put(pair[0].trim(), pair[1].trim());
			}
		}
		return headerMap;
	}


	public static void printHelp(CommandLine line, String help, Options options) {

		if (line.hasOption(help)) {
			HelpFormatter formatter = new HelpFormatter();
			formatter.printHelp("help", options, true);
			System.exit(0);
		}
        	
	}


}
