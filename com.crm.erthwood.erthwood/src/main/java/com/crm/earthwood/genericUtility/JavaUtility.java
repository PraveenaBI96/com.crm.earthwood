package com.crm.earthwood.genericUtility;

import java.util.Date;
import java.util.Random;

/**
 * @author Praveen
 */
public class JavaUtility {
/**
 * It's used to get Random number
 * @param random
 */
	public int randomNum()
	{
		Random r = new Random();
		int random=r.nextInt(10000);
		return random;
	}
	/**
	 * It's used to get Date and Time in IST format
	 * @return 
	 */
	public String getSystemDateWithFormate()
	{
		Date date = new Date();
		return date.toString();
	}
}
