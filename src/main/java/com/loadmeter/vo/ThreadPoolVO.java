package com.loadmeter.vo;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Options;

import com.loadmeter.threadpool.SimpleThreadPoolDriver;
import com.loadmeter.util.CommandLineUtil;

public class ThreadPoolVO {
	Integer corePool;
	Integer maximumPool;
	Integer totalRequest;
	
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

	public static Options cmdOptions() {
		Options options = new Options();
		options.addOption("c", true, "Get core thread count");
		options.addOption("m", true, "Get max thread count");
    	options.addOption("r", true, "Get request count");
    	
    	return options;
	}

	public static ThreadPoolVO parse(CommandLine line) throws Exception {
		ThreadPoolVO threadPoolVO = new ThreadPoolVO();
		threadPoolVO.corePool = CommandLineUtil.getInteger(line, "c");
		threadPoolVO.maximumPool = CommandLineUtil.getInteger(line, "m");
		threadPoolVO.totalRequest = CommandLineUtil.getInteger(line, "r");
		
		return threadPoolVO;
	}

}
