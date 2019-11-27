package com.loadmeter.service;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.loadmeter.util.JSONUtil;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

public class RestService {

	public static JSONObject sendPostRequest(URI uri, JSONObject body, Map<String, String> headerMap) throws Exception {
		JSONObject result = null;
		HttpPost postRequest = new HttpPost(uri);
		
		postRequest.setEntity(new StringEntity(body.toJSONString()));
		
		if(headerMap != null) {
			for (String key : headerMap.keySet()) {
				postRequest.addHeader(key, headerMap.get(key));
			}
		}
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpResponse httpResponse = httpClient.execute(postRequest);
		result = buildResponseMap(httpResponse);
		
		return result;
	}
	
	private static JSONObject buildResponseMap(HttpResponse httpResponse) throws Exception {
		JSONParser parser = new JSONParser();
		JSONObject responseJson = new JSONObject();
		if (httpResponse.getEntity() != null) {
			BufferedReader reader = new BufferedReader(new InputStreamReader(httpResponse.getEntity().getContent()));
			String inputLine;
			StringBuilder responseString = new StringBuilder();
			while ((inputLine = reader.readLine()) != null) {
				responseString.append(inputLine);
			}
			reader.close();
			
			Object obj = parser.parse(responseString.toString());
			if (obj instanceof JSONObject) {
				responseJson = (JSONObject) obj;
			} 
		}
		return responseJson;
	}
	
	public static void main(String args[]) throws Exception {
		RestService restService = new RestService();
		String uri = "http://localhost:8090/Freshchannel/api/v1/messages/transaction";
		String bodyString = "{\"to\": \"919750624490\",\"channelType\": \"WHATSAPP\",\"message\": {\"content\": {\"type\": \"TEXT\",\"text\": \"LoadTester\"}}}";
		JSONObject body = new JSONObject();
		body.put("to", "919750624490");
		body.put("channelType", "WHATSAPP");
		
		JSONObject contentObject = new JSONObject();
		contentObject.put("type", "TEXT");
		contentObject.put("text", "Load tester");
		JSONObject contentWrapper = new JSONObject();
		contentWrapper.put("content", contentObject);
		
		body.put("message", contentWrapper);
		
		Map<String, String> headerMap = new HashMap();
		headerMap.put("Authorization", "Bearer dmlnbmVzaDp3ZWxjb21lKjE=");
		headerMap.put("Content-Type", "application/json");
		headerMap.put("ORGANISATION_ID", "101");
		headerMap.put("ACCOUNT_ID", "2");
		body = (JSONObject) JSONUtil.parseAsObject(bodyString, JSONObject.class);
		body = JSONUtil.parseAsJSONObject(bodyString);
		System.out.println(body);
		Object result = restService.sendPostRequest(new URI(uri), body, headerMap);
		
		System.out.println(result);
	}
}
