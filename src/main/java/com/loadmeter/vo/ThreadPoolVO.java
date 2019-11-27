package com.loadmeter.vo;

import java.net.URI;
import java.util.Map;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.json.simple.JSONObject;

import com.loadmeter.threadpool.SimpleThreadPoolDriver;
import com.loadmeter.util.CommandLineUtil;

public class ThreadPoolVO {
	Integer corePool;
	Integer maximumPool;
	Integer totalRequest;
	URI restURI;
	JSONObject body;
	String responseContains;
	Map<String, String> headerMap;
	
	ThreadPoolVO() {}
	
	ThreadPoolVO(Integer corePool, Integer maximumPool, Integer totalRequest) {
		this.corePool = corePool;
		this.maximumPool = maximumPool;
		this.totalRequest = totalRequest;
	}
	
	/**
	 * @return the corePool
	 */
	public Integer getCorePool() {
		return corePool;
	}

	/**
	 * @param corePool the corePool to set
	 */
	public void setCorePool(Integer corePool) {
		this.corePool = corePool;
	}

	/**
	 * @return the maximumPool
	 */
	public Integer getMaximumPool() {
		return maximumPool;
	}

	/**
	 * @param maximumPool the maximumPool to set
	 */
	public void setMaximumPool(Integer maximumPool) {
		this.maximumPool = maximumPool;
	}

	/**
	 * @return the totalRequest
	 */
	public Integer getTotalRequest() {
		return totalRequest;
	}

	/**
	 * @param totalRequest the totalRequest to set
	 */
	public void setTotalRequest(Integer totalRequest) {
		this.totalRequest = totalRequest;
	}

	/**
	 * @return the restURI
	 */
	public URI getRestURI() {
		return restURI;
	}

	/**
	 * @param restURI the restURI to set
	 */
	public void setRestURI(URI restURI) {
		this.restURI = restURI;
	}

	/**
	 * @return the body
	 */
	public JSONObject getBody() {
		return body;
	}

	/**
	 * @param body the body to set
	 */
	public void setBody(JSONObject body) {
		this.body = body;
	}

	/**
	 * @return the responseContains
	 */
	public String getResponseContains() {
		return responseContains;
	}

	/**
	 * @param responseContains the responseContains to set
	 */
	public void setResponseContains(String responseContains) {
		this.responseContains = responseContains;
	}
	
	/**
	 * @return the headerMap
	 */
	public Map<String, String> getHeaderMap() {
		return headerMap;
	}

	/**
	 * @param headerMap the headerMap to set
	 */
	public void setHeaderMap(Map<String, String> headerMap) {
		this.headerMap = headerMap;
	}


	public static Options cmdOptions() {
		Options options = new Options();
		
		options.addOption("c", true, "Get core thread count");
		options.addOption("m", true, "Get max thread count");
    	options.addOption("r", true, "Get request count");
    	options.addOption("H", true, "Headers for for the request");
    	options.addOption("uri", true, "URL for for the request");
    	options.addOption("body", true, "JSON for for the request");
    	options.addOption("responseContains", true, "Response substring");
    	options.addOption("help", false, "Usage");
    	return options;
	}

	public static ThreadPoolVO parse(CommandLine line) throws Exception {
		CommandLineUtil.printHelp(line, "help", cmdOptions());
		
		ThreadPoolVO threadPoolVO = new ThreadPoolVO();
		threadPoolVO.corePool = CommandLineUtil.getInteger(line, "c");
		threadPoolVO.maximumPool = CommandLineUtil.getInteger(line, "m");
		threadPoolVO.totalRequest = CommandLineUtil.getInteger(line, "r");
		threadPoolVO.headerMap =  CommandLineUtil.getMap(line, "H");
		threadPoolVO.restURI = CommandLineUtil.getURI(line, "uri");
		threadPoolVO.body =  CommandLineUtil.getJSON(line, "body");
		threadPoolVO.responseContains = CommandLineUtil.getString(line, "responseContains");
		
		return threadPoolVO;
	}

}
