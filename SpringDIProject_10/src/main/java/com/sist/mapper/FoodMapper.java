package com.sist.mapper;

import org.apache.ibatis.annotations.Select;

import com.sist.dao.FoodVO;

import java.util.*;
public interface FoodMapper {
	//=> 카테고리별 맛집
	@Select("SELECT fno,name,address,phone,type "
			+ "FROM food_house "
			+ "WHERE cno=#{cno}")
	public List<FoodVO> foodCategoryListData(int cno);
	//=> 실제 맛집 정보
	@Select("SELECT fno,name,address,score,phone,type,parking,time,menu,price "
			+ "FROM food_house "
			+ "WHERE fno=#{fno}")
	public FoodVO foodDetailData(int fno);
}
