package com.loadmeter.util;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.commons.cli.CommandLine;
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

}
