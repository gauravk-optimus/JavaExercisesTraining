package com.w3s.testcases;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Properties;

import org.testng.Assert;
import org.testng.annotations.AfterSuite;

public class BaseClass {

	public static HashMap<String, String> latency = new HashMap<>();

	@AfterSuite
	public static void afterSuite() {
		setProperty();
	}

	public static Properties loadPropertiesFile(String fileName) {
		Properties properties = new Properties();
		try {
			InputStream in;
			in = new FileInputStream(fileName);
			// Properties file Loaded
			properties.load(in);
		} catch (FileNotFoundException exception) {
			System.out.println(exception.getMessage());
			
			Assert.fail("File not found");
		} catch (IOException exception) {
			System.out.println(exception.getMessage());
			Assert.fail("Input output exception occurred");
		}
		return properties;
	}
	
	 public static void setProperty() {
	        try {
	            Properties prop = new Properties(); 
	            OutputStream out;
	            System.out.println(System.getProperty("user.dir"));
	            out = new FileOutputStream(System.getProperty("user.dir")+"//artifacts//"+"latencyReport.properties");
	            prop.putAll(latency);
	            prop.store(out, "Property Saved");
	        } catch (IOException exception) {
				System.out.println(exception.getMessage());
				Assert.fail("Input output exception occurred");
	        }
	    }
}
