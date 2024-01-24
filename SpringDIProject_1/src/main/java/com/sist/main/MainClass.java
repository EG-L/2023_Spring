package com.sist.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sist.sawon.Sawon;

public class MainClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//1. 컨테이너 등록 (스프링)
		// 메모리 할당 후에 멤버변수에 값을 첨부 => DI
		ApplicationContext app = new ClassPathXmlApplicationContext("application.xml");
		Sawon sawon = (Sawon)app.getBean("sa1",Sawon.class);
		System.out.println(sawon.getName());
		System.out.println(sawon.getDept());
		System.out.println(sawon.getLoc());
		System.out.println(sawon.getSabun());
		System.out.println(sawon.getJob());
		System.out.println("---------------------------");
		Sawon sawon2 = (Sawon)app.getBean("sa2",Sawon.class);
		System.out.println(sawon2.getName());
		System.out.println(sawon2.getDept());
		System.out.println(sawon2.getLoc());
		System.out.println(sawon2.getSabun());
		System.out.println(sawon2.getJob());
		
	}

}
