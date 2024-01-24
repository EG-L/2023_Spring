package com.sist.dao;

public class MyDAO2 {
	public void select() {
		//before
		System.out.println("오라클 => select문장 요청");
		//after
	}
	public void insert() {
		System.out.println("오라클 => insert문장 요청");
	}
	public void update() {
		System.out.println("오라클 => update문장 요청");
	}
	public void delete() {
		System.out.println("오라클 => delete문장 요청");
	}
}
