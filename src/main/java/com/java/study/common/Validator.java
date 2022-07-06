package com.java.study.common;

import java.util.Scanner;

public class Validator {	
	
	/**
	 * Validate the input value to integer
	 * @param string
	 * @return
	 */
	public static int InputNumber(String string, Scanner scanner) {
		/* Create str to get value input */
		String str = null;
		
		/* Handle value input if wrong */
		while (true) {
			System.out.print(string);
			str = scanner.nextLine();
			if (str.isEmpty()) {
				System.out.println(Constant.INPUT_VALUE_NULL);
				continue;
			}
			if (!isNumeric(str)) {
				System.out.println(Constant.INPUT_NUMBER_WRONG);
			} else break;
		}
		
		/* Parse value input to integer and return value */
		int num = Integer.parseInt(str);
		return num;
	}

	/**
	 * Catch value input number or not
	 * @param str
	 * @return
	 */
	public static boolean isNumeric(String str) {
		try {
			Integer.parseInt(str);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

}
