package com.sist.commons;

import org.springframework.web.bind.annotation.ControllerAdvice;
/*
 *  AOP : 공통 모듈 (공통으로 사용되는 소스를 모아서 관리)
 *  	  유지 보수, 재사용
 *  	  => OOP에서 불가능한 것을 추가해서 보완한 상태
 *          =====
 *            |자동 호출(Callback)이 불가능
 *        => CallBack 원하는 위치를 지정하면 호출 가능
 *                   ==========
 *                   1. 메소드 지정 (지정된 메소드 => Target)
 *                   2. 메소드의 위치 (JoinPoint)
 *              		public void display(){
 *                         @Before
 *                         try{
 *                            @Around => 로그 / 트랜잭션
 *                            ------- 핵심 코딩
 *                            
 *                            -------
 *                            @Around
 *                         }
 *                         catch(Exception e){
 *                            @AfterThrowing
 *                            => @ControllerAdvice
 *                         }
 *                         finally{
 *                            @After
 *                         }
 *                         @AfterReturning
 *                         return;
 *              		}
 *                  PointCut+JoinPoint => Advice
 *                  Advice가 다중일 때 => 공통모듈(Aspect)
 *  	=> PointCut : 어떤 메소드
 *  	=> JoinPoint : 메소드 호출 위치
 *  	=> Weaving : 묶어준다.(조립)
 *  	=> Target : 대상 메소드
 *  	=> Proxy : 조립된 메소드 (대리자)
 *  	=> Advice : 공통 모듈 단위
 *      => Aspect : 공통 모듈
 *      
 *      public void getConnection(){}
 *      public void disConnection(){}
 *      public void success(){}
 *      
 *      class A{
 *          public void list(){
 *             getConnection()
 *             =================
 *              select 관련 소스코딩
 *             =================
 *             disConnection()
 *             success()
 *          }
 *          public void insert(){
 *             getConnection()
 *             =================
 *              insert 관련 소스코딩
 *             =================
 *             disConnection()
 *             success()
 *          }
 *          public void update(){
 *             getConnection()
 *             =================
 *              update 관련 소스코딩
 *             =================
 *             disConnection()
 *             success()
 *          }
 *      }
 * */
@ControllerAdvice
public class CommonsException {

}
