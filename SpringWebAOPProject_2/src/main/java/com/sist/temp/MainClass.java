package com.sist.temp;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component("mc")
public class MainClass {
	@Autowired
	private BCryptPasswordEncoder encoder;
	public static void main(String[] args) {
		ApplicationContext app = new ClassPathXmlApplicationContext("app.xml");
		MainClass mc = (MainClass)app.getBean("mc");
		String pwd = "1234";
		String enPwd = mc.encoder.encode(pwd);
		System.out.println(enPwd);
		
		Scanner sc = new Scanner(System.in);
		System.out.print("비밀번호 입력 :");
		String mypwd = sc.next();
		if(mc.encoder.matches(mypwd, enPwd)) {
			System.out.println("완료");
		}
		else {
			System.out.println("실패");
		}
	}
}
