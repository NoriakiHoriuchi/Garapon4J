package me.gizio.garapon4j.other;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class MyUtils {

	public static String getProperties(String key) {
		String configFile = "conf/garapon.conf";
		Properties prop = new Properties();

		try {
			prop.load(new FileInputStream(configFile));
		} catch (IOException e) {
			e.printStackTrace();
		}
		String param = prop.getProperty(key);
		return param;
	}
	

	public static void print(String str){
		System.out.println(str);
	}
	
	public String toMD5(String s){
		return s;
	}
}
