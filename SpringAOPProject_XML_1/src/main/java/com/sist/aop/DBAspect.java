package com.sist.aop;
import com.sist.dao.*;
import java.util.*;

import org.aspectj.lang.ProceedingJoinPoint;
// DI => Injection => setter , 생성자 
// DI => 클ㄹ스와 클래스의 연결관계설정
/*
 * 	aspect: 공통으로 사용되는 기능을 모아서 관리 : 공통모듈
 *  Advice
 *  ======
 *     PointCut => 어던 메소드에 적용할지
 *     JoinPoint => 메소드 호출 위치
 *       = before => 메소드 시작점
 *       = after => finally
 *       = AfterThrowing => catch절
 *       = AfterReturning => return(정상 수행)
 *       = Around : 전후
 *        ======== Around
 *          소스
 *        ======== Around
 *   ==================================================
 *       통합해서 새로운 기능을 만든다 (위빙 => Weaving) => Proxy 패턴
 *       
 *       PointCut : 어떤 메소드 적용 여부
 *        execution("* 패키지.클래스명.*(..)")
 *                  ===          ====== 모든 메소드 매개변수가 없는
 *                  리턴형          매개변수 상관없이 (..)
 *                                (String),(String,int)
 *       모든 패키지에 있는 모든 클래스에 적용
 *       => 로그
 *       within("패키지명.*")
 *       
 *       => 모든 모델 클래스 => * 패키지명.*Controller.*(..)
 *       
 * */
public class DBAspect {
	private EmpDAO dao;

	public void setDao(EmpDAO dao) {
		this.dao = dao;
	}
	// try 진입 전
	public void before() {
		dao.getConnection();
	}
	//finally
	public void after() {
		dao.disConnection();
	}
	
	//데이터 출력 => After-Returning
	public void afterReturning(Object obj) {
		
		System.out.println("======== 결과값 자동 처리 ========");
		List<EmpVO> list = (List<EmpVO>)obj;
		
		for(EmpVO vo:list) {
			System.out.println(vo.getEmpno()+" "+vo.getEname()+" "+vo.getJob()+" "+vo.getDbday()+" "+vo.getSal());
		}
	}
	//에러 => After-Throwing
	public void afterThrowing(Throwable ex) {
		// Catch 처리 
		System.out.println("=========== 에러 발생 ============");
		ex.printStackTrace();
		//Web => @ControllerAdvice : 공통 예외처리
	}
	//시간 => Around
	public Object around(ProceedingJoinPoint jp) throws Throwable{
		Object obj = null;
		long start = System.currentTimeMillis();
		System.out.println("호출된 메소드 :"+jp.getSignature().getName());
		// 사용자가 호출한 메소드 
		// 메소드 호출
		obj = jp.proceed();//invoke()
		long end = System.currentTimeMillis();
		System.out.println("수행시간:"+(end-start));
		
		return obj;
	}
}
