package com.cosmo.io.model;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable {

	private static final long serialVersionUID = 4300030005262689503L;

	private String username;
	private String email;
	private transient String password;
	private Date birthday;
	private int age;

	public User(String username, String email, String password, Date birthday,
			int age) {
		this.username = username;
		this.email = email;
		this.password = password;
		this.birthday = birthday;
		this.age = age;
	}

	public void printInfo() {
		System.out.println("username: " + username);
		System.out.println("email: " + email);
		System.out.println("password: " + password);
		System.out.println("birthday: " + birthday);
		System.out.println("age: " + age);
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

}
