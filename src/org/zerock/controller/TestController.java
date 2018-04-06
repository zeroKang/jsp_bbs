package org.zerock.controller;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletResponse;

import org.zerock.dao.BoardDAO;

import lombok.extern.log4j.Log4j;

/**
 * Servlet implementation class TestController
 */
@WebServlet("/test/*")
@Log4j
public class TestController extends AbstractController {
	private static final long serialVersionUID = 1L;
	

	public String testGET(JRRequest req, HttpServletResponse res)throws Exception{
		
		log.info("test");
		
		BoardDAO.getInstance().testConnection();
		
		
		return "/test/test";
		
	}
	
	

}
