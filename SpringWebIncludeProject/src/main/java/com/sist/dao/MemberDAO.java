package com.sist.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.sist.mapper.*;
import java.util.*;
import com.sist.vo.*;
@Repository
public class MemberDAO {
	@Autowired
	private MemberMapper mapper;
	public MemberVO memberLogin(MemberVO vo) {
		MemberVO mvo = new MemberVO();
		int count = mapper.idCount(vo.getId());
		if(count==0) {
			mvo.setMsg("NOID");
		}
		else {
			mvo=mapper.memberLogin(vo);
			if(mvo.getPwd().equals(vo.getPwd())) {
				mvo.setMsg("OK");
			}
			else {
				mvo.setMsg("NOPWD");
			}
		}
		
		return mvo;
	}
}
