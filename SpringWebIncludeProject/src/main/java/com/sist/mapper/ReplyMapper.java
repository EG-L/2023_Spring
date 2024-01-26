package com.sist.mapper;
import java.util.*;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

import com.sist.vo.*;

public interface ReplyMapper {
	//추가
	@SelectKey(keyProperty = "no",resultType = int.class,before = true,statement = "SELECT NVL(MAX(no)+1,1) as no FROM springReply")
	@Insert("INSERT INTO springReply VALUES(#{no},#{fno},#{id},#{name},#{msg},SYSDATE)")
	public void replyInsert(ReplyVO vo);
	//목록
	@Select("SELECT no,fno,id,name,TO_CHAR(regdate,'YYYY-MM-DD HH24:MI:SS') as dbday,msg "
			+ "FROM springReply "
			+ "WHERE fno=#{fno} "
			+ "ORDER BY no DESC")
	public List<ReplyVO> replyListData(int fno);
	//수정
	@Update("UPDATE springReply SET "
			+ "msg=#{msg} "
			+ "WHERE no=#{no}")
	public void replyUpdate(ReplyVO vo);
	//삭제
	@Delete("DELETE FROM springReply "
			+ "WHERE no=#{no}")
	public void replyDelete(int no);
}
