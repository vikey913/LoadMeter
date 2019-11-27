
package com.loadmeter.threadpool;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONObject;

import com.loadmeter.service.RestService;
import com.loadmeter.vo.ThreadPoolVO;

public class RestWorkerThread implements Runnable {
	private static Integer successCount;
    private URI uri;
    private JSONObject body;
    private Map<String, String> headerMap;
    private String responseContains;
    
    static {
    	successCount = 0;
    }
    
    public RestWorkerThread() {}
    
    
    
    public RestWorkerThread(ThreadPoolVO threadArgs){
    	this.uri = threadArgs.getRestURI();
        this.body = threadArgs.getBody();
        this.responseContains = threadArgs.getResponseContains();
        this.headerMap = threadArgs.getHeaderMap();
    }

    public void run() {
        System.out.println(Thread.currentThread().getName()+" Start");
        try {
			processRequest();
		} catch (Exception e) {
			e.printStackTrace();
		}
        System.out.println(Thread.currentThread().getName()+" End.");
    }

    private void processRequest() throws Exception {
        try {
        	JSONObject result = RestService.sendPostRequest(this.uri, this.body, this.headerMap);
        	if(! result.toString().contains(responseContains)) {
        		throw new Exception("Response does not contain the string");
        	} else {
        		successCount++;
        	}
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

//    private Map<String, String> getHeaderMap() {
//    	Map<String, String> headerMap = new HashMap();
//		headerMap.put("Authorization", "Bearer dmlnbmVzaDp3ZWxjb21lKjE=");
//		headerMap.put("Content-Type", "application/json");
//		headerMap.put("ORGANISATION_ID", "101");
//		headerMap.put("ACCOUNT_ID", "2");
//		
//		return headerMap;
//	}

	@Override
    public String toString(){
        return this.uri + " " + this.body.toJSONString();
    }

	/**
	 * @param headerMap the headerMap to set
	 */
	public void setHeaderMap(Map<String, String> headerMap) {
		this.headerMap = headerMap;
	}
	
	public static Integer getSuccessCount() {
		return successCount;
	}
}
