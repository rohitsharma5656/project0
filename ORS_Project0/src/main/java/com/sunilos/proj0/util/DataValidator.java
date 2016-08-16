package com.sunilos.proj0.util;

import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Component;

/**
 * Validates Input data.
 * 
 * @author SunilOS
 * @version 1.0
 * @Copyright (c) SunilOS
 * 
 */
@Component
public class DataValidator {

	/**
	 * Checks if value is Null
	 * 
	 * @param val
	 * @return
	 */
	public static boolean isNull(String val) {
		if (val == null || val.trim().length() == 0) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Checks if value is NOT Null
	 * 
	 * @param val
	 * @return
	 */
	public static boolean isNotNull(String val) {
		return !isNull(val);
	}

	/**
	 * Checks if value is an Integer
	 * 
	 * @param val
	 * @return
	 */
	public static boolean isInteger(String val) {
		if (isNotNull(val)) {
			try {
				int i = Integer.parseInt(val);
				return true;
			} catch (NumberFormatException e) {
				return false;
			}
		} else {
			return false;
		}
	}

	/**
	 * Checks if value is Long
	 * 
	 * @param val
	 * @return
	 */
	public static boolean isLong(String val) {
		if (isNotNull(val)) {
			try {
				long i = Long.parseLong(val);
				return true;
			} catch (NumberFormatException e) {
				return false;
			}

		} else {
			return false;
		}
	}

	/**
	 * Checks if value is valid Email ID
	 * 
	 * @param val
	 * @return
	 */
	public static boolean isEmail(String val) {
		String emailreg = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
		if (isNotNull(val)) {
			try {
				return val.matches(emailreg);
			} catch (NumberFormatException e) {
				return false;
			}
		} else {
			return false;
		}
	}
public static boolean ageLimit(Date date) {
		
		System.out.println("data validatoer "+date);

		Calendar dob = Calendar.getInstance();
		dob.setTime(date);
		Calendar today = Calendar.getInstance();
		int age = today.get(Calendar.YEAR) - dob.get(Calendar.YEAR);
		if (today.get(Calendar.DAY_OF_YEAR) < dob.get(Calendar.DAY_OF_YEAR))
			age--;
		if (age >= 17) {
			return true;
		} else {
			return false;
		}
	}
public static boolean isName(String val) {

	String firstname = "[a-zA-Z]*";
	// String name = "/^[A-Z]";
	if (isNotNull(val)) {
		try {

			return val.matches(firstname);

		} catch (NumberFormatException e) {
			return false;
		}
	} else {
		return false;
	}
}

public static boolean ismob(String val) {

//	String mob = "\\+?[7-9][0-9]*";
	 String mob = "^[7-9][0-9]{9}$";
	if (isNotNull(val)) {
		try {

			return val.matches(mob);

		} catch (NumberFormatException e) {
			return false;
		}
	} else {
		return false;
	}
}
public static boolean isName1(String val) {

	String name = "[a-zA-Z][a-zA-Z ]+";

	if (isNotNull(val)) {
		try {

			return val.matches(name);

		} catch (NumberFormatException e) {
			return false;
		}
	} else {
		return false;
	}
}

public static boolean isRole(String val) {

	String name = "[a-zA-Z]+";

	if (isNotNull(val)) {
		try {

			return val.matches(name);

		} catch (NumberFormatException e) {
			return false;
		}
	} else {
		return false;
	}
}

	/**
	 * Test above methods
	 * 
	 * @param args
	 */

	public static void main(String[] args) {
		System.out.println("Not Null 1" + isEmail("salman.khan@sunrays.co.in"));
		System.out.println("Not Null 2" + isNotNull("salman"));
		System.out.println("Not Null 3" + isNotNull(null));
		System.out.println("Not Null 4" + isNull("123"));

		System.out.println("Is Int " + isInteger(null));
		System.out.println("Is Int " + isInteger("ABC1"));
		System.out.println("Is Int " + isInteger("123"));
		System.out.println("Is Int " + isNotNull("123"));

	}

}
