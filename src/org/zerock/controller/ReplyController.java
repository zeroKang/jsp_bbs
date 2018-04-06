package org.zerock.controller;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletResponse;

import org.zerock.dao.ReplyDAO;
import org.zerock.domain.ReplyVO;

import lombok.extern.log4j.Log4j;

@WebServlet("/reply/*")
@Log4j
public class ReplyController extends AbstractController {
	
	public String addPOST(JRRequest req, HttpServletResponse res)throws Exception{

		log.info("reply add post");
		
		ReplyVO vo = new ReplyVO();
		
		vo.setBno(req.paramInt("bno",0));
		vo.setReply(req.param("reply"));
		vo.setReplyer(req.param("replyer"));
		
		vo.setGno(req.paramInt("gno", -1));
		
		log.info(vo);
		
		ReplyDAO.getInstance().create(vo);
		
		req.setAttribute("replyList", ReplyDAO.getInstance().list(req.paramInt("bno",0)));
		
		
		return"/reply/list";
	}
	
	public String listGET(JRRequest req, HttpServletResponse res)throws Exception{
		
		
		
		
		req.setAttribute("replyList", ReplyDAO.getInstance().list(req.paramInt("bno",0)));
		
		
		return "/reply/list";
		
	}
	
}
