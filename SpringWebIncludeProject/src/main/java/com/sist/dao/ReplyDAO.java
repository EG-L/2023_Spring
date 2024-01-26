package com.sist.dao;
import com.sist.vo.*;
import com.sist.mapper.*;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
/*
 * 
 *               |
 * 		*.do  ======== DispatcherServlet
 *                            | => preHandle() => 자동 로그인 / ID저장 
 *                            | HandlerMapping
 *                           @Controller / @RestController
 *                            | => postHandle()
 *                            | Model=request => ViewResolver
 *                              => afterCompletion() => 권한 부여
 *                           JSP
 *     => AOP
 *        void aaa(); => before
 *        void bbb(); => AfterThrowing
 *        void ccc(); => After
 *        void ddd(); => AfterReturning
 *        
 *        public String display(){
 *        	       before
 *        	try{
 *                 Around
 *        		==============
 *        			핵심소스
 *              ==============
 *              	Around
 *        	}
 *        	catch(Exception ex){
 *        	      AfterThrowing
 *        	}
 *        	finally{
 *                After
 *        	}
 *          AfterReturning
 *        	return "";
 *        }
 *        
 *        JoinPoint : 호출 위치 지정
 *        PointCut : 대상 메소드 지정
 *        ====================== Advice
 *                              ======== 여러 개를 모아서 처리 => Aspect
 *        => MVC
 *            | DI,AOP => Annotation,XML
 *              =======================
 *              Annotation : 개발자마다 각자 개발할 때 사용(기능별)
 *              XML : 공통적으로 사용할 때 => 라이브러리 설정
 * */
@Repository
public class ReplyDAO {
	@Autowired
	private ReplyMapper mapper;
	
	public void replyInsert(ReplyVO vo) {
		mapper.replyInsert(vo);
	}
	
	public List<ReplyVO> replyListData(int fno){
		return mapper.replyListData(fno);
	}
	
	public void replyUpdate(ReplyVO vo) {
		mapper.replyUpdate(vo);
	}
	
	public void replyDelete(int no) {
		mapper.replyDelete(no);
	}
}
