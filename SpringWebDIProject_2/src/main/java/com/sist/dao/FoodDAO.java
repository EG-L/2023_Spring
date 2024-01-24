package com.sist.dao;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sist.mapper.*;
@Repository
public class FoodDAO {
	//스프링에서 구현된 mapper 주소 요청
	@Autowired
	private FoodMapper mapper;
	public List<FoodVO> foodListData(int start,int end){
		return mapper.foodListData(start, end);
	}
	public int foodTotalPage() {
		return mapper.foodTotalPage();
	}
	public FoodVO foodDetailData(int fno) {
		return mapper.foodDetailData(fno);
	}
	
	public List<FoodVO> foodFindData(Map map){
		return mapper.foodFindData(map);
	}
	
	public int foodFindTotalPage(String address) {
		return mapper.foodFindTotalPage(address);
	}
}
