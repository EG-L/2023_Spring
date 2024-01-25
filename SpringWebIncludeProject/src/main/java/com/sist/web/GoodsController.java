package com.sist.web;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sist.dao.GoodsDAO;
import com.sist.vo.GoodsVO;

@Controller
public class GoodsController {
	@Autowired
	private GoodsDAO dao;
	
	@GetMapping("goods/goods_home.do")
	public String main_main(String page,Model model) {
		if(page==null) page="1";
		int curpage = Integer.parseInt(page);
		int rowSize = 12;
		int start = (rowSize*curpage)-(rowSize-1);
		int end = (rowSize*curpage);
		
		
		List<GoodsVO> list = dao.goodsListData(start, end);
		int totalpage = dao.goodsTotalPage();
		
		final int BLOCK = 10;
		int startPage = ((curpage-1)/BLOCK*BLOCK)+1;
		int endPage = ((curpage-1)/BLOCK*BLOCK)+BLOCK;
		
		if(endPage>totalpage) endPage=totalpage;
		
		model.addAttribute("list",list);
		model.addAttribute("curpage",curpage);
		model.addAttribute("totalpage",totalpage);
		model.addAttribute("startPage",startPage);
		model.addAttribute("endPage",endPage);
		model.addAttribute("main_jsp","../goods/goods_home.jsp");
		return "main/main";
	}
	
	@GetMapping("goods/detail_before.do")
	public String GoodsDetailBefore(int no,HttpServletResponse response,RedirectAttributes ra) {
		Cookie cookies = new Cookie("goods_"+no, String.valueOf(no));
		cookies.setPath("/");
		cookies.setMaxAge(60*60*24);
		
		response.addCookie(cookies);
		
		ra.addAttribute("no",no);//sendredirect 일 때 전송  , Model일때는 forward일 때
		
		
		return "redirect:../goods/goods_detail.do";
	}
	
	@GetMapping("goods/goods_detail.do")
	public String goodsDeatail(int no,Model model) {
		
		GoodsVO vo = dao.goodsDetailData(no);
		
		model.addAttribute("vo",vo);
		model.addAttribute("main_jsp","../goods/goods_detail.jsp");
		return "main/main";
	}
	
}
