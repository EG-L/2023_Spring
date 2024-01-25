package com.sist.web;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sist.mapper.FoodMapper;
import com.sist.vo.FoodVO;

/*
 * 	RedirectAttribute
 *  Model
 *  
 *   
 *   MVC => 화면 변경
 *        1. request를 전송 => forward
 *           => 값을 전송 : Model
 *        	 return "main/main";
 *        2. 재사용 / 재호출  => sendredirect
 *           => RedirectAttributes
 *           return "redirect:~.do"
 *   Model(Controller)
 *       1) 형식
 *          = 리턴형
 *            String , void
 *          = 매개변수
 *            요청값 받기
 *            String , 일반 데이터형 (int)
 *            VO
 *            내장 객체
 *            스프링 지원 객체
 *            ============= 순서는 상관없다.
 *            => ?no=1 ==> (int no)
 *            => <input type=text name=name> ==> (String name)
 *            
 *            => ?no=1&type=2 ==> (int no,int type)
 *            
 *       @GetMapping
 *         => <a> sendRedirect , location.href
 *         => axios.get()
 *       @PostMapping
 *         => <form> , ajax({type:'post'})
 *         => axios.post()
 *       @RequestMapping => GetMapping+PostMapping
 * 
 * */
import java.util.*;
import com.sist.dao.*;
@Controller
public class FoodController {
	@Autowired
	private FoodDAO dao;
	
	@GetMapping("food/detail_before.do")
	public String food_detail_before(int fno,HttpServletResponse response,RedirectAttributes ra) {
		// 쿠키는 일반 객체 => 매개변수로 받을 수 없다.
		Cookie cookies = new Cookie("food_"+fno, String.valueOf(fno));
		cookies.setPath("/");
		cookies.setMaxAge(60*60*24);
		
		response.addCookie(cookies);
		// sendRedirect => 값 전송
		ra.addAttribute("fno",fno);//sendredirect 일 때 전송  , Model일때는 forward일 때
		
				
		return "redirect:../food/detail.do";
	}
	@GetMapping("food/detail.do")
	public String food_detail(int fno,Model model) {
		FoodVO vo = dao.foodDetailData(fno);
		model.addAttribute("vo",vo);
		model.addAttribute("main_jsp","../food/detail.jsp");
		return "main/main";
	}
	
	@RequestMapping("food/find.do")//GET,POST 동시에 사용하기 위해
	public String food_find(String page,String colname,String ss,Model model) {
		if(colname==null) {
			colname="type";
		}
		if(ss==null) {
			ss="한식";
		}
		if(page==null) {
			page="1";
		}
		int curpage = Integer.parseInt(page);
		int rowSize = 12;
		int start = (rowSize*curpage)-(rowSize-1);
		int end = (rowSize*curpage);
		
		Map map = new HashMap();
		map.put("col_name", colname);
		map.put("ss", ss);
		map.put("start", start);
		map.put("end", end);
		List<FoodVO> list = dao.foodFindData(map);
		int totalpage = dao.foodFindTotalPage(map);
		
		final int BLOCK = 10;
		int startPage = ((curpage-1)/BLOCK*BLOCK)+1;
		int endPage = ((curpage-1)/BLOCK*BLOCK)+BLOCK;
		
		if(endPage>totalpage) endPage=totalpage;
		
		model.addAttribute("list",list);
		model.addAttribute("curpage",curpage);
		model.addAttribute("totalpage",totalpage);
		model.addAttribute("startPage",startPage);
		model.addAttribute("endPage",endPage);
		
		model.addAttribute("ss",ss);
		model.addAttribute("colname",colname);
		model.addAttribute("main_jsp","../food/find.jsp");
		
		return "main/main";
	}
}
