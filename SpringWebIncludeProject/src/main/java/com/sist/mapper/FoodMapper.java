package com.sist.mapper;
/*
 * 	1. web.xml
 * 		DispatcherServlet 등록
 * 		한글변환
 * 		  = 보안 등록
 *        = 에러 처리
 *  2. 클래스 제작 => 메모리 할당 / 주입
 *  3. Spring => xml코드 설정
 *  4. mapper => dao => model처리
 *  5. jsp제작
 * */
import java.util.*;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.sist.vo.FoodVO;
public interface FoodMapper {
	@Select("SELECT fno,name,poster,num "
			+ "FROM (SELECT fno,name,poster,rownum as num "
			+ "FROM (SELECT fno,name,poster "
			+ "FROM food_menu_house ORDER BY fno)) "
			+ "WHERE num BETWEEN #{start} AND #{end}")
	public List<FoodVO> foodListData(@Param("start") int start,@Param("end") int end);
	
	@Select("SELECT CEIL(COUNT(*)/12) "
			+ "FROM food_menu_house")
	public int foodTotalPage();
}
