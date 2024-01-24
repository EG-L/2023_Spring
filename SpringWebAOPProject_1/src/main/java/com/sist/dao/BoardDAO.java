package com.sist.dao;
import java.lang.reflect.Executable;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sist.mapper.*;
@Repository("bDao")
public class BoardDAO {
	@Autowired
	private BoardMapper mapper;
	public List<BoardVO> boardListData(Map map){
		return mapper.boardListData(map);
	}
	
	public void boardInsert(BoardVO vo) {
		mapper.boardInsert(vo);
	}
	
	public int boardTotalPage() {
		return mapper.boardTotalPage();
	}
	
	public BoardVO boardDetailData(int no) {
		mapper.hitIncrement(no);
		return mapper.boardDetailData(no);
	}
	public BoardVO boardUpdateData(int no) {
		return mapper.boardDetailData(no);
	}
	
	public boolean boardUpdate(BoardVO vo) {
		boolean bCheck=false;
		String db_pwd = mapper.boardGetPassword(vo.getNo());
		if(db_pwd.equals(vo.getPwd())) {
			bCheck=true;
			mapper.boardUpdate(vo);
		}
		return bCheck;
	}
	public List<BoardVO> boardTop5(){
		return mapper.boardTop5();
	}
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
	public void boardReplyInsert(int pno,BoardVO vo) {
		BoardVO pvo = mapper.boardParentInfoData(pno);
		mapper.boardGroupStepIncrement(pvo);
		vo.setGroup_id(pvo.getGroup_id());
		vo.setGroup_step(pvo.getGroup_step()+1);
		vo.setGroup_tab(pvo.getGroup_tab()+1);
		vo.setRoot(pno);
		mapper.boardReplyInsert(vo);
		mapper.boardDepthIncrement(pno);
	}
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
	public boolean boardReplyDelete(int no,String pwd) {
		// @Around => setAutoCommit(false)
		boolean bCheck = false;
		//비밀번호 검색
		String db_pwd = mapper.boardGetPassword(no);
		if(db_pwd.equals(pwd)) {
			bCheck=true;
			BoardVO vo = mapper.boardDeleteInfo(no);
			if(vo.getDepth()==0) {
				mapper.boardReplyDelete(no);
			}
			else {
				vo.setSubject("관리자가 삭제한 게시물입니다.");
				vo.setContent("관리자가 삭제한 게시물입니다.");
				vo.setNo(no);
				mapper.boardReplyDeleteUpdate(vo);
			}
			mapper.boardDepthDecrement(vo.getRoot());
		}
		// commit()
		// catch=>rollback()
		//finally=>setAutoCommit(true)
		return bCheck;
	}
}
