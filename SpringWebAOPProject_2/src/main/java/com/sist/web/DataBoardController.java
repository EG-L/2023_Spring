package com.sist.web;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.sist.dao.*;
@Controller
@RequestMapping("databoard/") //공통 경로 저장
// GetMapping / PostMapping => ajax,axios,form
// @RequestMapping => GET/POST 동시 적용
// GET => @GetMapping / POST => PostMapping 
// => PathValiable /databoard/detail/1
/*
 * 		1. Model
 * 		  1) 구분자 (메소드 찾기)
 *           => @RequestMapping(URI) 주소
 *              = @GetMapping(URI)
 *              = @PostMapping(URI)
 *       http://localhost:8080/web/databoard/list.do => URL
 *                            ---- ContextPath
 *       ===================== =====================
 *             서버 정보                URI
 *       =========================================== URL
 *        2) 리턴형
 *            => String : JSP파일 지정
 *                        경로명/JSP명 => forward
 *                        redirect: => 확장자 .do
 *            => void : 다운로드 , ajax (X) => @RestController
 *        3) 매개변수 : String , 해당 데이터형 , VO
 *                   => 내장객체 , Model , RedirectAttributes
 *        4) 메소드명은 아무거나 사용이 가능
 *        5) getBean() => DispatcherServlet에서 처리
 *           => @Atuowired => 객체 주소를 받을 때 사용
 *        *** 스프링에서 메모리 할당을 해야 @Autowired를 사용할 수 있다.
 *        
 *        @Value() => 이름을 받을 때 사용
 * */
public class DataBoardController {
	@Autowired
	private DataBoardDAO dao;
	@Autowired
	private BCryptPasswordEncoder encoder;
	// 사용자 요청에 따른 요청 처리
	@GetMapping("list.do")
	/*
	 * Parameter 값
	 * 모든 데이터는 String , 해당 데이터형으로 설정 
	 * Model => 전송객체
	 * addAttribute(String key, Object obj)
	 * {
	 * 		request.setAttribute(key,obj)
	 * }
	 * 내장 객체
	 * 1. HttpServletRequest
	 * 2. HttpServletResponse
	 * 3. HttpSession
	 * 4. RedirectAttributes
	 * 5. 커맨드 객체 => VO단위
	 * 6. String[]
	 * 7. List
	 *    => name="file[0]"
	 *    => 400 bad request
	 *    => 404, 500
	 * */
	public String databoard_list(String page,Model model) {
		if(page==null) page="1";
		
		int curpage = Integer.parseInt(page);
		int rowSize = 10;
		int start=(rowSize*curpage)-(rowSize-1);
		int end=(rowSize*curpage);
		List<DataBoardVO> list = dao.databoardListData(start, end);
		int totalpage = dao.databoardTotalPage();
		model.addAttribute("curpage",curpage);
		model.addAttribute("totalpage",totalpage);
		model.addAttribute("list",list);
		
		return "databoard/list";
	}
	@GetMapping("insert.do")
	public String databoard_insert() {
		return "databoard/insert";
	}
	@PostMapping("insert_ok.do")
	public String databoard_insert_ok(DataBoardVO vo,HttpServletRequest request) {
		
		String path = request.getSession().getServletContext().getRealPath("/")+"upload\\";//aws내 실제 경로 찾기
		path = path.replace("\\", File.separator);//경로 호환
		File dir = new File(path);
		if(!dir.exists()) {//업로드 폴더가 없을 시 자동 생성
			dir.mkdir();
		}
		List<MultipartFile> list = vo.getFiles();
		if(list==null) { //업로드가 없는 상태
			vo.setFilename("");
			vo.setFilesize("");
			vo.setFilecount(0);
		}
		else { // 업로드가 된 상태
			String filename = "";
			String filesize = "";
			for(MultipartFile mf:list) {
				String name = mf.getOriginalFilename();//사용자가 보내준 파일 이름
				File file = new File(path+name);
				try {
					mf.transferTo(file);//파일 업로드 
				} catch (Exception e) {
					// TODO: handle exception
				}
				filename+=name+",";
				filesize+=file.length()+",";
				
			}
			filename=filename.substring(0,filename.lastIndexOf(","));
			filesize=filesize.substring(0,filesize.lastIndexOf(","));
			vo.setFilename(filename);
			vo.setFilesize(filesize);
			vo.setFilecount(list.size());
			
		}
		String enPwd = encoder.encode(vo.getPwd());
		vo.setPwd(enPwd);
		dao.databoardInsert(vo);
		
		return "redirect:list.do";
	}
	@GetMapping("detail.do")
	public String databoard_detail(int no,Model model) {
		DataBoardVO vo = dao.databoardDetailData(no);
		if(vo.getFilecount()>0) {
			String[] filenames = vo.getFilename().split(",");
			String[] filesize = vo.getFilesize().split(",");
			
			List<String> fList = Arrays.asList(filenames);
			List<String> sList = Arrays.asList(filesize);
			
			model.addAttribute("fList",fList);
			model.addAttribute("sList",sList);
		}
		model.addAttribute("vo",vo);
		return "databoard/detail";
	}
	
	@GetMapping("download.do")
	public void databoard_download(String fn,HttpServletRequest request,HttpServletResponse response) {
		try {
			//파일명 , 파일크기
			String path=request.getSession().getServletContext().getRealPath("/")+"upload\\";
			path = path.replace("\\", File.separator);
			File file = new File(path+fn);
			response.setHeader("Content-Disposition", "attachment;filename="
					+ URLEncoder.encode(fn,"UTF-8"));
			response.setContentLength((int)file.length());
			
			BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
			/* 서버에 있는 파일 읽기 */
			BufferedOutputStream bos = new BufferedOutputStream(response.getOutputStream());
			/* 클라이언트에서 읽기 */
			
			int i = 0;
			byte[] buffer = new byte[1024];
			while((i=bis.read(buffer,0,1024))!=-1) {
				bos.write(buffer,0,i);
			}
			
			bis.close();
			bos.close();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	@GetMapping("update.do")
	public String databoard_update(int no,Model model) {
		
		DataBoardVO vo = dao.databoardUpdateData(no);
		model.addAttribute("vo",vo);
		return "databoard/update";
	}
	
	@PostMapping("update_ok.do")
	public String databoard_update_ok(DataBoardVO vo,Model model,HttpServletRequest request) {
		String path=request.getSession().getServletContext().getRealPath("/")+"upload\\";
		path = path.replace("\\", File.separator);
		DataBoardVO fvo = dao.databoardFileInfoData(vo.getNo());
		try {
			if(fvo.getFilecount()>0) {
				StringTokenizer st = new StringTokenizer(vo.getFilename(),",");
				while(st.hasMoreTokens()) {
					File file  = new File(path+st.nextToken());
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		List<MultipartFile> list = vo.getFiles();
		if(list==null) {
			vo.setFilename("");
			vo.setFilesize("");
			vo.setFilecount(0);
		}
		else {
			String filename = "";
			String filesize = "";
			for(MultipartFile mf:list) {
				String name = mf.getOriginalFilename();
				File f = new File(path+name);
				try {
					//업로드 되는 부분
					mf.transferTo(f);
				} catch (Exception e) {
					// TODO: handle exception
				}
				filename+=name+',';
				filesize+=f.length()+",";
				
			}
			filename=filename.substring(0,filename.lastIndexOf(","));
			filesize=filesize.substring(0,filesize.lastIndexOf(","));
			vo.setFilecount(list.size());
			vo.setFilename(filename);
			vo.setFilesize(filesize);
		}
		boolean bCheck = dao.databoardUpdate(vo);
		model.addAttribute("bCheck",bCheck);
		model.addAttribute("no",vo.getNo());
		return "databoard/update_ok";
	}
}
