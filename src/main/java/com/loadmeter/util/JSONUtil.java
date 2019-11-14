/**
 * 
 */
package com.loadmeter.util;

import java.io.IOException;
import java.util.Map;

import org.json.simple.JSONObject;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.wnameless.json.unflattener.JsonUnflattener;

/**
 * @author vigneshwaran
 * @date 26 Aug 2019 13:14:51
 * @Version 1.0
 */
public class JSONUtil {
	
	public static JSONObject parseAsJSONObject(Object object) {
		JSONObject jsonObject = new JSONObject();
		ObjectMapper oMapper = new ObjectMapper();
		Map<String, Object> objectMap = oMapper.convertValue(object, Map.class);
		jsonObject.putAll(objectMap);
		return jsonObject;
	}
	
	public static String toJSONString(Object object) {
		try {
			return new ObjectMapper().writeValueAsString(object);
		} catch (JsonProcessingException e) {
			System.out.println(e);
		}
		return null;
	}
	
	public static Object parseAsObject(JSONObject json, Class<?> classInstance) throws JsonParseException, JsonMappingException, IOException {
		return new ObjectMapper().readValue(json.toJSONString(), classInstance);	
	}
	
	public static Object parseAsObject(String jsonString, Class<?> classInstance) throws JsonParseException, JsonMappingException, IOException {
		return new ObjectMapper().readValue(jsonString, classInstance);	
	}

	public static boolean hasValidValue(JSONObject json, String key) {
		return json.get(key) != null && json.get(key) != "";
	}

	public static String unflatten(Object object) {
		return JsonUnflattener.unflatten(JSONUtil.toJSONString(object));
	}
	
}
