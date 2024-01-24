package com.sist.proxy;

public class MainClass {
	public static void main(String[] args) {
		Sawon s = new Sawon();
		s.display();
		
		Proxy p = new Proxy(s);
		p.display();
	}
}
