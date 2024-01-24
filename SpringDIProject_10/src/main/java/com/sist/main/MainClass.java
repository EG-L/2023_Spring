package com.sist.main;
import com.sist.dao.*;
import java.util.*;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
public class MainClass {
	public static void main(String[] args) {
		ApplicationContext app = new ClassPathXmlApplicationContext("application-*.xml");
		CategoryDAO cDao = (CategoryDAO)app.getBean("cDao");
		FoodDAO fDao = (FoodDAO)app.getBean("fDao");
		Scanner sc = new Scanner(System.in);
		List<CategoryVO> cList = cDao.foodCategoryData();
		for(CategoryVO vo:cList) {
			System.out.println(vo.getCno()+"."+vo.getTitle());
		}
		System.out.println("===========================");
		System.out.print("카테고리 선택(1~30) :");
		int cno = sc.nextInt();
		CategoryVO cvo = cDao.categoryInfoData(cno);
		System.out.println("===================================");
		System.out.println(cvo.getTitle());
		System.out.println(cvo.getSubject());
		System.out.println("===================================");
		List<FoodVO> fList = fDao.foodCategoryListData(cno);
		for(FoodVO fvo:fList) {
			System.out.println(fvo.getFno()+"."+fvo.getName()+
					" "+fvo.getAddress()+" "+
					fvo.getPhone()+" "+
					fvo.getType());
		}
		System.out.println("===========================");
		System.out.println("맛집 선택:");
		int fno = sc.nextInt();
		FoodVO vo = fDao.foodDetailData(fno);
		System.out.println("업체명 :"+vo.getName()+"("+vo.getScore()+")");
		System.out.println("주소 :"+vo.getAddress());
		System.out.println("전화번호 :"+vo.getPhone());
		System.out.println("음식 종류 :"+vo.getType());
		System.out.println("가격대 :"+vo.getPrice());
		System.out.println("영업시간 :"+vo.getTime());
		System.out.println("주차 :"+vo.getParking());
		System.out.println("메뉴 :"+vo.getMenu());
		System.out.println("===========================");
		
	}
}
