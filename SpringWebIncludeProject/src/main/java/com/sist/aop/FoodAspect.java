package com.sist.aop;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import com.sist.dao.*;
import com.sist.vo.FoodVO;
import com.sist.vo.GoodsVO;
// DispatcherServlet을 통해서 데이터를 매개변수로 받을 수 있는 클래스
// @Controller , @RestController => Model 클래스에서만 가능
@Aspect
@Component
public class FoodAspect {
	@Autowired
	private FoodDAO dao;
	//finally 수행 => 무조건 전송
	@After("execution(* com.sist.web.MainController.main_main(..))")
	public void cookieSend() {
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		//                           ===============================================
		//                                         PageContext
		Cookie[] cookies = request.getCookies();
		List<FoodVO> cList = new ArrayList<FoodVO>();
		if(cookies!=null) {
			for(int i = cookies.length-1;i>=0;i--) {
				if(cookies[i].getName().startsWith("food_")) {
					String fno=cookies[i].getValue();
					FoodVO vo = dao.foodCookieData(Integer.parseInt(fno));
					cList.add(vo);
				}
			}
		}
		request.setAttribute("count", cList.size());
		request.setAttribute("cList", cList);
	}
}
