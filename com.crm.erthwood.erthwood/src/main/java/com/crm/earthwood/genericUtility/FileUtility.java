package com.crm.earthwood.genericUtility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;

/**
 * It's used to fetch data from Property file
 * @author Praveen
 *
 */
public class FileUtility {
/**
 * It's used to read the data from vtiger.properties based on key which you pass an argument
 * @throws Throwable 
 */
	public String getPropertyKeyValue(String key) throws Throwable
	{
		FileInputStream fis = new FileInputStream("./data/vtiger.properties");
		Properties pobj= new Properties();
		pobj.load(fis);
		String value= pobj.getProperty(key);
		return value;
	}
}
