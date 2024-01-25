package com.sist.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import com.sist.vo.MemberVO;

public interface MemberMapper {
	@Select("SELECT COUNT(*) FROM springMember "
			+ "WHERE id=#{id}")
	public int idCount(String id);
	
	@Select("SELECT id,pwd,name,sex FROM springMember "
			+ "WHERE id=#{id}")
	public MemberVO memberLogin(MemberVO vo);

}
