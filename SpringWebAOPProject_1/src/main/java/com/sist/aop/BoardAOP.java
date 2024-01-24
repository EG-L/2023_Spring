package com.sist.aop;
import java.util.*;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.*;
import com.sist.dao.*;
/*
 * 		1. 모니터링 / 로깅 / 오류 검사 (처리) / 성능 파악
 * 		   => 트랜잭션 , 보안 , 암호화 복호화 , 데이터 읽기 => 분석
 * 		= 공통된 기능을 재사용하는 기법
 * 		= 공통적인 내용을 모아서 관리 => 유지보수
 *      = 라이브러리 (트랜잭션, 보안)
 *      
 *      Spring Frame Work
 *        => DI
 *        => AOP
 *        => MVC
 *      -------------------------
 *      
 *      1. 어떤 클래스의 메소드에서 적용
 *         PointCut
 *           => execution("특정위치")
 *           => within("패키지명")
 *      2. 메소드 위치 => JoinPoint
 *          = Before
 *          = After : finally
 *          = AfterReturning : 정상수행 => return값을 받을 수 있음
 *          = AfterThrowing : 에러 처리
 *          = Around :
 *      3. 인터셉트 : 자동 로그인 , ID저장 ... 
 *      	public void display(){
 *      		@Before
 *      		try{
 *      			@Around => setAutoCommit(false)
 *                  @Around => commit()
 *      		}
 *      		catch(Exception e){
 *      			@AfterThrowing => RollBack
 *      		}
 *      		finally{
 *      			@After => setAutoCommit(true)
 *      		}
 *      		@afterReturning
 *      	}
 *          
 *          @Before
 *          public void before(){
 *          }
 *          @After
 *          public void after(){
 *          }
 *          @AfterReturning
 *          public void afterReturning(){
 *          }
 *          @AfterThrowing
 *          public void afterThrowing(){
 *          }
 * */
@Aspect
@Component
public class BoardAOP {
	@Autowired
	private BoardDAO dao;
	
	@After("execution(* com.sist.web.*Controller.*(..))")
	public void after() {
		List<BoardVO> list = dao.boardTop5();
		//실제 사용중인 request를 가지고 올 때 사용 => Cookie
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		request.setAttribute("tList", list);
	}
}
