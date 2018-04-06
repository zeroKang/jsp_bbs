package org.zerock.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class BoardController
 */
@WebServlet("/board/*")
public class BoardController extends AbstractController {
	
	public String listGET(JRRequest req, HttpServletResponse res)throws Exception{
		
		return "/board/list";
	}
	
	public String registerGET(JRRequest req, HttpServletResponse res)throws Exception{
		
		return "/board/register";
	}
}
