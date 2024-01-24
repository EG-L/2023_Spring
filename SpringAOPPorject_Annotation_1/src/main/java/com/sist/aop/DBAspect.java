package com.sist.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.sist.dao.*;
//<aop:aspect>
@Aspect
@Component
public class DBAspect {
	@Autowired
	private EmpDAO dao;
	// 1. before
	@Before("execution(* com.sist.dao.EmpDAO.emp*(..))")
	public void before() {
		dao.getConnection();
		System.out.println("오라클 연결 자동 호출:Callback");//callback함수
	}
	// 2. after
	@After("execution(* com.sist.dao.EmpDAO.emp*(..))")
	public void after() {
		dao.disConnection();
		System.out.println("오라클 해제 자동 호출:Callback");//callback함수
	}
	// 3. around
	@Around("execution(* com.sist.dao.EmpDAO.emp*(..))")
	public Object around(ProceedingJoinPoint jp) throws Throwable{
		System.out.println("오라클 로그 자동 호출:Callback");//callback함수
		Object obj = null;
		long start = System.currentTimeMillis();
		System.out.println("호출된 메소드:"+jp.getSignature().getName());
		obj = jp.proceed();
		long end = System.currentTimeMillis();
		System.out.println("수행시간 :"+(end-start));
		return obj;
	}
}
