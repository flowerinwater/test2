package com.ssm.mongodb.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class History implements Serializable {
	private static final long serialVersionUID = 8186340210705235977L;
	private String id;
	private String url;
	private Set<String> visitedUrl = new HashSet<String>();
	
	/**
	 * <br>------------------------------<br>
	 */
	public History() {
	}

	public History(String id) {
		super();
		this.id = id;
	}

	public History(String id, String url) {
		super();
		this.id = id;
		this.url = url;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Set<String> getVisitedUrl() {
		return visitedUrl;
	}

	public void setVisitedUrl(Set<String> visitedUrl) {
		this.visitedUrl = visitedUrl;
	}

	/**
	 * toString
	 */
	public String toString() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("url", url);
		map.put("visitedUrl", visitedUrl);
		return map.toString();
	}
}