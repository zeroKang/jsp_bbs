package org.zerock.controller;

import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletResponse;

import org.zerock.dao.BoardDAO;
import org.zerock.domain.BoardVO;
import org.zerock.domain.Criteria;

import lombok.extern.log4j.Log4j;

/**
 * Servlet implementation class BoardController
 */
@WebServlet("/board/*")
@Log4j
public class BoardController extends AbstractController {
	
	public String registerGET(JRRequest req, HttpServletResponse res)throws Exception{
		
		return "/board/register";
	}
	
	public String registerPOST(JRRequest req, HttpServletResponse res)throws Exception{
		
		log.info("register Post....................");
		
		BoardVO vo = 
				new BoardVO(
						req.param("title"," "),
						req.param("content"," "),
						req.param("writer"," ")
				);
		
		BoardDAO.getInstance().create(vo);
		
		return "/result";
	}	
	
	public String listGET(JRRequest req, HttpServletResponse res)throws Exception{
		
		log.info("list ...get");
		
		Criteria cri = new Criteria(req.paramInt("page",1));
		
		List<BoardVO> list = BoardDAO.getInstance().getPage(cri);
		
		log.info("===================");
		log.info(list);
		
		
		req.setAttribute("list", list);
		req.setAttribute("cri", cri);
		
		return "/board/list";
	}
}
