package com.loadmeter.util;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.ParseException;

import com.loadmeter.vo.ThreadPoolVO;

public class CmdUtil {

	public static ThreadPoolVO parse(String[] args) throws Exception {
		try {
	    	CommandLineParser parser = new DefaultParser();	
	        CommandLine line = parser.parse( ThreadPoolVO.cmdOptions(), args );
	        return ThreadPoolVO.parse(line);
	    } catch( ParseException exp ) {
	        System.err.println( "Parsing failed.  Reason: " + exp.getMessage() );
	    }
		
		return null;
	}

}
