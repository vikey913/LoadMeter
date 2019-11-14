
package com.loadmeter.threadpool;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONObject;

import com.loadmeter.service.RestService;

public class RestWorkerThread implements Runnable {
  
    private URI uri;
    private JSONObject body;
    
    public RestWorkerThread(){}
    
    public RestWorkerThread(URI uri, JSONObject body){
        this.uri = uri;
        this.body = body;
    }

    public void run() {
        System.out.println(Thread.currentThread().getName()+" Start");
        try {
			processRequest();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        System.out.println(Thread.currentThread().getName()+" End.");
    }

    private void processRequest() throws Exception {
        try {
        	RestService.sendPostRequest(this.uri, this.body, getHeaderMap());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private Map<String, String> getHeaderMap() {
    	Map<String, String> headerMap = new HashMap();
		headerMap.put("Authorization", "Bearer dmlnbmVzaDp3ZWxjb21lKjE=");
		headerMap.put("Content-Type", "application/json");
		headerMap.put("ORGANISATION_ID", "101");
		headerMap.put("ACCOUNT_ID", "2");
		
		return headerMap;
	}

	@Override
    public String toString(){
        return this.uri + " " + this.body.toJSONString();
    }
}
