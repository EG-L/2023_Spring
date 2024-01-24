package com.sist.spring3;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/*
 *  1. 스프링은 프로젝트에 필요한 클래스를 모아서 관리
 *                       ================ 컨테이너
 *     => 경량 컨테이너
 *     => 단순한 연결 관계를 가지고 있다.
 *     => 라이브러리로 제공
 *        => Open Source
 *        => Core
 *        => 컨테이너 클래스
 *           BeanFactory => core
 *                |
 *         ApplicationContext => core / AOP
 *                |
 *        WebApplicationContext => core / AOP / MVC
 *     
 *     컨테이너
 *     컴포넌트 : 개발자가 만든 클래스 한 개(기능을 수행하는 클래스)
 *             => 컴포넌트 여러 개를 묶어서 관리 => 컨테이너
 *     컨테이너의 역할
 *     ===========
 *       1. 객체 생성    ==> new를 사용해야되는 클래스
 *                        ~VO는 데이터형
 *       2. 객체 변수의 초기화 ==> DI
 *       3. 객체 소멸
 *       ====================== 객체의 생명주기
 *       4. 객체를 찾아주는 역할 수행 (DL)
 *       DL / DI => core
 *       
 *       Spring : 클래스 관리자 / 멤버변수 초기화 / 메소드 호출 / 생성자 매개변수
 *                           ===================================
 *                              | DI(값 주입) => XML
 *       1. 클래스 관리
 *         => 클래스 등록 => 찾기
 *         =================== DL 
 *       2. 객체 생성 시에 필요한 변수가 주입 
 *       ============================ DI
 * */ 
public class MainClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// 스프링에서 사용하는 default 폴더 / classpath
		// classpath => src/main/java
		// 1. XML 파싱
		// 2. 컨테이너 => XML 전송
		ApplicationContext app = new ClassPathXmlApplicationContext("application.xml");
		// 등록된 클래스를 모아서 관리하는 역할의 클래스 => 컨테이너
		// 객체 메모리 할당 => 등록 
		/*
		 *     class ApplicationContext{
		 *     	Map map = new HashMap();
		 *     	map.put(key값,value값);
		 *                   ======= class.forName("class명");
		 *                   
		 *      public Object getBean(String key){
		 *      	return map(key)
		 *      }
		 *     }
		 * */
		
		//객체 찾기 => DL
		Board board = (Board)app.getBean("board");
		System.out.println("board="+board);
		board.board_list();
		Board board1 = (Board)app.getBean("board");
		System.out.println("board1="+board1);
		board1.board_list();
		Board board2 = (Board)app.getBean("board");
		System.out.println("board2="+board2);
		board2.board_list();
		Member member = (Member)app.getBean("member");
		Notice notice = (Notice)app.getBean("notice");
		member.member_insert();
		notice.notice_list();
	}

}
