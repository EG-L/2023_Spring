package com.sist.main;
import java.util.*;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sist.dao.MyDAO;
import com.sist.dao.MyDAO2;
/*
 * OOP => 반복 소스가 있는 경우
 *        => 한 개의 클래스안에서는 메소드 
 *        => 여러 개의 클래스에서 적용 => 클래스
 *        => 자동 호출(AOP)
 * AOP => OOP를 보완한 패턴
 * */
public class MainClass {
	public static void main(String[] args) {
//		ApplicationContext app = new ClassPathXmlApplicationContext("app.xml");
//		MyDAO dao = (MyDAO)app.getBean("dao");
//		dao.select();
//		System.out.println("================");
//		dao.insert();
//		System.out.println("================");
//		dao.update();
//		System.out.println("================");
//		dao.delete();
//		System.out.println("================");
		MyDAO2 dao = new MyDAO2();
		dao.select(); // this.conn-null
		dao.insert();
		dao.update();
		dao.delete();
		System.out.println("========= AOP 적용 후 ==========");
		ApplicationContext app = new ClassPathXmlApplicationContext("app2.xml");
		MyDAO2 dao2 = (MyDAO2)app.getBean("dao");
		dao2.select();
		dao2.insert();
		dao2.update();
		dao2.delete();
	}
}
