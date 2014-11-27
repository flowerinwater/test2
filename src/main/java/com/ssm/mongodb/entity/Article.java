package com.ssm.mongodb.entity;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Article implements Serializable {

	private static final long serialVersionUID = -3871237353166568102L;

	private String id;
	
	private String url;
	
	private String html;
	
	private String text;
	
	private String title;
	
	private String digest;
	
	private Date createTime;
	
	private Date updateTime;
	
	/**
	 * <br>------------------------------<br>
	 */
	public Article() {
	}


	
	
	public Article(String id) {
		super();
		this.id = id;
	}




	public Article(String id, String url, String html, String text,
			String title, String digest ) {
		super();
		this.id = id;
		this.url = url;
		this.html = html;
		this.text = text;
		this.title = title;
		this.digest = digest;
		this.createTime = Calendar.getInstance().getTime();
		this.updateTime = Calendar.getInstance().getTime();
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




	public String getHtml() {
		return html;
	}




	public void setHtml(String html) {
		this.html = html;
	}




	public String getText() {
		return text;
	}




	public void setText(String text) {
		this.text = text;
	}




	public String getTitle() {
		return title;
	}




	public void setTitle(String title) {
		this.title = title;
	}




	public String getDigest() {
		return digest;
	}




	public void setDigest(String digest) {
		this.digest = digest;
	}




	public Date getCreateTime() {
		return createTime;
	}




	public Date getUpdateTime() {
		return updateTime;
	}




	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}




	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}




	/**
	 * toString
	 */
	public String toString() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("id", id);
		map.put("url", url);
		map.put("html", html);
		map.put("text", text);
		map.put("title", title);
		map.put("digest", digest);
		map.put("createTime", String.valueOf(createTime));
		return map.toString();
	}
}