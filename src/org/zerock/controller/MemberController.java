package org.zerock.controller;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletResponse;

import org.zerock.dao.MemberDAO;
import org.zerock.domain.MemberVO;

import lombok.extern.log4j.Log4j;

@Log4j
@WebServlet("/member/*")
public class MemberController extends AbstractController {
	
	public String loginPOST(JRRequest req, HttpServletResponse res)throws Exception{

		MemberVO vo = MemberDAO.getInstance().login(req.param("uid"), req.param("upw"));
		
		if(vo != null) {
			
			req.getSession().setAttribute("member", vo);
			
			log.info("login........success");
		}
		
		return "/result";

	}

	public String loginGET(JRRequest req, HttpServletResponse res)throws Exception{
		
		log.info("member login....");
		
		
		
		return "/member/login";
	}
}
