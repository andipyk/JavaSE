package com.cosmo.io;

import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

public class PreferencesDemo {

	public static void main(String[] args) {
		Preferences userPrefs = Preferences.userNodeForPackage(PreferencesDemo.class);
		try{
			String[] keys = userPrefs.keys();
			if(keys == null || keys.length == 0){
				userPrefs.put("hostname", "www.codejava.net");
                userPrefs.putInt("port", 12345);
                userPrefs.putBoolean("authentication", true);
                userPrefs.putLong("timeout", 90000);
			}else{
				String hostname = userPrefs.get("hostname", null);
                int port = userPrefs.getInt("port", 80);
                boolean authentication = userPrefs.getBoolean("authentication", false);
                long timeout = userPrefs.getLong("timeout", 20000);
 
                String username = userPrefs.get("username", "tom");
                 
                System.out.println(hostname);
                System.out.println(port);
                System.out.println(authentication);
                System.out.println(timeout);
                System.out.println(username);
			}
		}catch(BackingStoreException ex){
			ex.printStackTrace();
		}
	}

}
