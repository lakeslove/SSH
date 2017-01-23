package com.lx.test;

public class TestError {
	private static String testString;
	static {
		String[] testArray = new String[]{
				
		};
		testString = testArray[2];
	}

	public static String getTestString(){
		return testString;
	}
}
