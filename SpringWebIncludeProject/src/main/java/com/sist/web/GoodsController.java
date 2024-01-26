package com.sist.web;

import java.util.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sist.dao.GoodsDAO;
import com.sist.vo.GoodsVO;
import com.sist.vo.ReplyGoodsVO;

@Controller
public class GoodsController {
	@Autowired
	private GoodsDAO dao;
	
	@GetMapping("goods/goods_home.do")
	public String goods_main(String page,Model model) {
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
	public String goodsDetailBefore(int no,HttpServletResponse response,RedirectAttributes ra) {
		Cookie cookies = new Cookie("goods_"+no, String.valueOf(no));
		cookies.setPath("/");
		cookies.setMaxAge(60*60*24);
		
		response.addCookie(cookies);
		
		ra.addAttribute("no",no);//sendredirect 일 때 전송  , Model일때는 forward일 때
		
		
		return "redirect:../goods/goods_detail.do";
	}
	
	@GetMapping("goods/goods_detail.do")
	public String goodsDetail(int no,Model model) {
		
		GoodsVO vo = dao.goodsDetailData(no);
		
		List<ReplyGoodsVO> glist = dao.goodsSelectReply(no);
		
		model.addAttribute("vo",vo);
		model.addAttribute("gList",glist);
		model.addAttribute("main_jsp","../goods/goods_detail.jsp");
		return "main/main";
	}
	
	@RequestMapping("goods/goods_find.do")
	public String goods_Find(String ss,String page,Model model){
		if(page==null) {
			page="1";
		}
		
		if(ss==null) {
			ss="특가";
		}
		
		int curpage = Integer.parseInt(page);
		int rowSize = 12;
		int start = (rowSize*curpage)-(rowSize-1);
		int end = (rowSize*curpage);
		
		Map map = new HashMap();
		map.put("start", start);
		map.put("end", end);
		map.put("ss", ss);
		
		List<GoodsVO> list = dao.goodsFindData(map);
		int totalpage = dao.goodsFindTotalData(ss);
		
		final int BLOCK = 10;
		int startPage = ((curpage-1)/BLOCK*BLOCK)+1;
		int endPage = ((curpage-1)/BLOCK*BLOCK)+BLOCK;
		
		if(endPage>totalpage) endPage=totalpage;
		
		model.addAttribute("list",list);
		model.addAttribute("curpage",curpage);
		model.addAttribute("totalpage",totalpage);
		model.addAttribute("startPage",startPage);
		model.addAttribute("endPage",endPage);
		
		model.addAttribute("list",list);
		model.addAttribute("ss",ss);
		model.addAttribute("main_jsp","../goods/goods_find.jsp");
		return "main/main";
	}
	
	@PostMapping("goods/reply_insert.do")
	public String goods_reply_insert(int fno,String msg,HttpSession session,RedirectAttributes ra) {
		//#{fno},#{id},#{name},#{msg}
		ReplyGoodsVO vo = new ReplyGoodsVO();
		String id = (String)session.getAttribute("id");
		String name = (String)session.getAttribute("name");
		vo.setFno(fno);
		vo.setMsg(msg);
		vo.setId(id);
		vo.setName(name);
		dao.goodsInsertReply(vo);
		
		ra.addAttribute("no",fno);
		return "redirect:../goods/detail_before.do";
	}
	
	@GetMapping("goods/reply_delete.do")
	public String goods_reply_delete(int fno,int no,RedirectAttributes ra) {
		dao.goodsDeleteReply(no);
		
		ra.addAttribute("no",fno);
		return "redirect:../goods/detail_before.do";
	}
}
