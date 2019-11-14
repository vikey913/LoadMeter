/**
 * vigneshwaran
 * version 1.0
 * 13 Nov 2019 16:19:02
 */
package com.loadmeter.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;


/**
 * @author vigneshwaranarum
 *
 */
public class SimpleThreadPoolDriver {
	static Integer threadCount;
	static Integer requestCount;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		parseCmdArgs(args);
		
		ExecutorService executor = Executors.newFixedThreadPool(SimpleThreadPoolDriver.threadCount);
        
//		for (int i = 0; i < SimpleThreadPoolDriver.requestCount; i++) {
//            Runnable worker = new RestWorkerThread("" + i);
//            executor.execute(worker);
//        }
//        executor.shutdown();
//        while (!executor.isTerminated()) {}
//        
//        System.out.println("Finished all threads");

	}
	private static void parseCmdArgs(String[] args) {
		try {
	    	CommandLineParser parser = new DefaultParser();	
	        CommandLine line = parser.parse( constructOptions(), args );
	        SimpleThreadPoolDriver.threadCount = Integer.parseInt(line.getOptionValue("t"));
	        SimpleThreadPoolDriver.requestCount = Integer.parseInt(line.getOptionValue("r"));
	    } catch( ParseException exp ) {
	        System.err.println( "Parsing failed.  Reason: " + exp.getMessage() );
	    }
		
	}
	private static Options constructOptions() {
		Options options = new Options();
    	options.addOption("t", true, "Get thread count");
    	options.addOption("r", true, "Get request count");
    	return options;
	}

}
