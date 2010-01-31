package com.kirhgoff.zephyr.client;

import java.util.HashMap;
import java.util.Map;

public class MapBuilder {
	private Map<String, String> data = new HashMap<String, String> ();
	
	public MapBuilder() {
	}
	
	public MapBuilder put (String key, String value) {
		data.put(key, value);
		return this;
	}

	public Map<String, String> instance() {
		return data;
	}
}
