package com.sist.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;

import javax.servlet.http.HttpServletRequest;

import java.io.*;
import com.sist.dao.*;

@RestController
@RequestMapping("databoard/")
public class DataBoardRestController {
	@Autowired
	private DataBoardDAO dao;
	
	@PostMapping("delete_ok.do")
	public String databoard_delete(int no,String pwd,HttpServletRequest request) {
		String path = request.getSession().getServletContext().getRealPath("/")+"upload\\";
		path=path.replace("\\", File.separator);
		String result = "";
		try {
			DataBoardVO vo = dao.databoardFileInfoData(no);
			boolean bCheck = dao.dataBoardDelete(no, pwd);
			if(bCheck==true) {
				result="yes";
				if(vo.getFilecount()>0) {
					StringTokenizer st = new StringTokenizer(vo.getFilename(),",");
					while(st.hasMoreTokens()) {
						File file = new File(path+st.nextToken());
						file.delete();
					}
				}
			}
			else {
				result="no";
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}
	
}
