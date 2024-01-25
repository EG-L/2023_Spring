package com.sist.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.sist.vo.GoodsVO;

public interface GoodsMapper {
	@Select("SELECT no,goods_name as name,goods_poster as poster,goods_price as price,num "
			+ "FROM (SELECT no,goods_name,goods_poster,goods_price,rownum as num "
			+ "FROM (SELECT no,goods_name,goods_poster,goods_price "
			+ "FROM goods_all ORDER BY no)) "
			+ "WHERE num BETWEEN #{start} AND #{end}")
	public List<GoodsVO> goodsListData(@Param("start") int start,@Param("end") int end);
	
	@Select("SELECT CEIL(COUNT(*)/12) "
			+ "FROM goods_all")
	public int goodsTotalPage();
	
	@Select("SELECT no,goods_name as name,goods_sub as sub,goods_price as price,goods_discount as discount,goods_first_price as first_price,goods_delivery as delivery,goods_poster as poster "
			+ "FROM goods_all WHERE no=#{no}")
	public GoodsVO goodsDetailData(int no);
	
	@Select("SELECT CEIL(COUNT(*)/12) "
			+ "FROM goods_all WHERE "
			+ "REGEXP_LIKE(goods_name,#{ss})")
	public int goodsFindTotalData(String ss);
	
	@Select("SELECT no,goods_name as name,goods_sub as sub,goods_price as price,goods_discount as discount,goods_first_price as first_price,goods_delivery as delivery,goods_poster as poster,num "
			+ "FROM (SELECT no,goods_name,goods_sub,goods_price,goods_discount,goods_first_price,goods_delivery,goods_poster,rownum as num "
			+ "FROM goods_all WHERE "
			+ "REGEXP_LIKE(goods_name,#{ss}) "
			+ ") WHERE num BETWEEN ${start} AND ${end}")
	public List<GoodsVO> goodsFindData(Map map);
}