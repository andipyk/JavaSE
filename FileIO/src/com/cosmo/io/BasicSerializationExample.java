package com.cosmo.io;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Date;

import com.cosmo.io.model.User;

public class BasicSerializationExample {

	static final String filePath = "user.ser";

	static void serialize(User user) {
		try {
			FileOutputStream fos = new FileOutputStream(filePath);
			ObjectOutputStream outputStream = new ObjectOutputStream(fos);
			outputStream.writeObject(user);
			outputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	static User deserialize() {
		User saveUser = null;
		try {
			FileInputStream fis = new FileInputStream(filePath);
			ObjectInputStream inputStream = new ObjectInputStream(fis);
			saveUser = (User) inputStream.readObject();
			inputStream.close();
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return saveUser;
	}

	public static void main(String[] args) {
		String username = "codejava";
		String email = "info@codejava.net";
		String password = "secret";
		Date birthDay = new Date();
		int age = 20;

		User newUser = new User(username, email, password, birthDay, age);

		serialize(newUser);
		User saveUser = deserialize();

		if (saveUser != null) {
			saveUser.printInfo();
		}
	}

}
