package com.sist.spring2;
/*
 *   인터페이스를 이용하면 결합성이 new보다는 결합성이 약하다.
 *   => 인터페이스의 단점 (인터페이스를 수정하면 모든 클래스가 에러발생)
 *   => SW => 인터페이스는 고정
 *           ======== default 
 * */
public class MainClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Hello hello = new HelloImp1();
		hello.sayHello("심청이");
	}

}
