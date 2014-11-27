package com.ssm.mongodb.entity;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @author <a href="http://blog.csdn.net/java2000_wl">java2000_wl</a>
 * @version <b>1.0</b>
 */
public class User implements Serializable {

	private static final long serialVersionUID = -5785857960597910259L;
	
	private String id;
	
	private String name;
	
	private int age;
	
	/**
	 * <br>------------------------------<br>
	 */
	public User() {
	}

	/**
	 * 
	 * <br>------------------------------<br>
	 * @param id
	 * @param name
	 * @param age
	 */
	public User(String id, String name, int age) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
	}

	/**
	 * 获得id
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * 设置id
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * 获得name
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * 设置name
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 获得age
	 * @return the age
	 */
	public int getAge() {
		return age;
	}

	/**
	 * 设置age
	 * @param age the age to set
	 */
	public void setAge(int age) {
		this.age = age;
	}
	
	/**
	 * toString
	 */
	public String toString() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("id", id);
		map.put("name", name);
		map.put("age", String.valueOf(age));
		return map.toString();
	}
}