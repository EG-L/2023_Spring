package com.sist.web;
import java.util.*;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sist.vo.*;
import com.sist.dao.*;

@Controller
public class ReplyController {
	@Autowired
	private ReplyDAO dao;
	
	@PostMapping("reply/reply_insert.do")
	/*
	 * 	Model => forward => request를 전송
	 * 	RedirectAttribute => sendredirect
	 * */
	public String reply_insert(int fno,String msg,HttpSession session,RedirectAttributes ra) {
		ReplyVO vo = new ReplyVO();
		String id = (String)session.getAttribute("id");
		String name = (String)session.getAttribute("name");
		vo.setFno(fno);
		vo.setMsg(msg);
		vo.setId(id);
		vo.setName(name);
		dao.replyInsert(vo);
		
		ra.addAttribute("fno",fno);
		return "redirect:../food/detail.do";
	}
	@GetMapping("reply/reply_delete.do")
	public String reply_delete(int no,int fno,RedirectAttributes ra) {
		
		dao.replyDelete(no);
		
		ra.addAttribute("fno",fno);
		return "redirect:../food/detail.do";
	}
	@PostMapping("reply/reply_update.do")
	public String reply_update(int no,int fno,String msg,RedirectAttributes ra) {
		
		ReplyVO vo = new ReplyVO();
		vo.setNo(no);
		vo.setMsg(msg);
		
		dao.replyUpdate(vo);
		
		ra.addAttribute("fno",fno);
		return "redirect:../food/detail.do";
	}
}
