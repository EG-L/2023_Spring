package com.sist.web;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sist.mapper.FoodMapper;

@Controller
public class FoodController {
	@Autowired
	private FoodMapper mapper;
	
	@GetMapping("food/detail_before.do")
	public String food_detail_before(int fno,HttpServletResponse response,RedirectAttributes ra) {
		Cookie cookies = new Cookie("food_"+fno, String.valueOf(fno));
		cookies.setPath("/");
		cookies.setMaxAge(60*60*24);
		
		response.addCookie(cookies);
		ra.addAttribute("fno",fno);//sendredirect 일 때 전송  , Model일때는 forward일 때
		
				
		return "redirect:../food/detail.do";
	}
}
