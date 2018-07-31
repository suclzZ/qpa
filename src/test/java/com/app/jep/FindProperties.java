package com.app.jep;

import java.util.Arrays;

public class FindProperties {

	public static void main(String[] args) {
		
		String fn = "(x+y)/z+(a-b)*c";
		
		System.out.println(Arrays.toString( parse(fn) ));
	}
	
	public static String[] parse(String fn) {
		return fn.split("\\(|\\)|\\+|\\-|\\*|\\/");//  \\(\\|)|\\+|\\-|\\*|\\/
	}
}
