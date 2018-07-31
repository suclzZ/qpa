package com.app.jep;

import org.nfunk.jep.JEP;

public class JepDemo {

	public static void main(String[] args) {
		
		JEP jep = new JEP();
		jep.addVariableAsObject("abc","0.12");
		System.out.println(jep);
		jep.parseExpression("abc+3");
		System.out.println(jep);

		JEP jep2 = new JEP();
		jep2.addVariableAsObject("abc",5);
		jep2.parseExpression("abc+2<8");
		System.out.println("jep2 : "+jep2.getValueAsObject());

	}
	
	public static void t2() {
		String fn = "aa*bb";
		Bean bean = new Bean("1200","0.11");
		
//		JEP jep = new JEP();
//		jep.addVariableAsObject("aa", "5");
//		jep.addVariableAsObject("bb", "0.12");
//		jep.parseExpression(fn);
//		System.err.println(jep.getValueAsObject());
	}
	
	public static void t3() {
		String fn = "bb*aa";
		JEP jep = new JEP();
		jep.addVariable("aa", 5);
		jep.addVariable("bb", 0.12);
		jep.parseExpression(fn);
		System.err.println(jep.getValueAsObject());
//		System.err.println(jep.getValueAsObject());
	}
	
	public static void t1() {
		JEP jep = new JEP();
		
		jep.addVariable("x", 12);
		jep.addVariable("y", 8);
		
		jep.parseExpression("x*y < 100");
		
//		System.out.println("res:" + jep.getValueAsObject());
		
		
		JEP jep2 = new JEP();
		jep2.parseExpression("((7*3+5)/2-3)<10");
		Object res = jep2.getValueAsObject();
		System.out.println(res);
		System.out.println(Boolean.valueOf( res.toString()) );
	}
	
}
