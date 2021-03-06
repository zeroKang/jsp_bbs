package org.zerock.controller;

import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.zerock.dao.BoardDAO;
import org.zerock.domain.BoardVO;
import org.zerock.domain.Criteria;
import org.zerock.domain.PageMaker;
import org.zerock.util.CookieUtil;

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
	
	public String viewGET(JRRequest req, HttpServletResponse res)throws Exception{
		
		Criteria cri = new Criteria(req.paramInt("page",1), req.param("type"), req.param("keyword"));
		
		Cookie ck = CookieUtil.findCookie(req, "views");
		
		boolean update = false;
		
		if(ck != null) {
			if(CookieUtil.checkValue(ck.getValue(), "|"+req.paramInt("bno", 0))) {
				update = false;
			}else {
				update = true;
				String str = CookieUtil.valueAppend(ck, "|"+req.paramInt("bno", 0));
				ck.setValue(str);
			}
		}else {
			ck = new Cookie("views", "|"+req.paramInt("bno", 0));
			update = true;
		}
		ck.setMaxAge(60*60*24);
		
		log.info("COOKIE>>>>>>>>>>>>>>>>>>>>>>>");
		res.addCookie(ck);
		
		
		req.setAttribute("vo", BoardDAO.getInstance().read(req.paramInt("bno",0),update));
		
		req.setAttribute("cri", cri);
		
		return "/board/view";
	}
	public String removePOST(JRRequest req, HttpServletResponse res)throws Exception{
		
		BoardDAO.getInstance().delete(req.paramInt("bno", 0));
		
		return "/result";
	}
	
	public String modifyGET(JRRequest req, HttpServletResponse res)throws Exception{
		
		Criteria cri = new Criteria(req.paramInt("page",1), req.param("type"), req.param("keyword"));
		
		boolean update = false;
		
		req.setAttribute("vo", BoardDAO.getInstance().read(req.paramInt("bno",0),update));
		
		req.setAttribute("cri", cri);
		
		return "/board/modify";
	}
	
	public String modifyPOST(JRRequest req, HttpServletResponse res)throws Exception{
		
		Criteria cri = new Criteria(req.paramInt("page",1), req.param("type"), req.param("keyword"));
		
		BoardVO vo = new BoardVO();
		vo.setBno(req.paramInt("bno", 0));
		vo.setTitle(req.param("title"));
		vo.setContent(req.param("content"));
		
		
		log.info("=============================");
		log.info(vo);
		log.info(cri);
		
		BoardDAO.getInstance().update(vo);
		
		
		
		return "/result";
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
		
		log.info("list ...get :" + req.param("keyword"));
		
		Criteria cri = 
			new Criteria(req.paramInt("page",1), req.param("type"), req.param("keyword"));
		
		List<BoardVO> list = BoardDAO.getInstance().getPage(cri);
		
		log.info("===================");
		log.info(list);
		
		PageMaker pm = new PageMaker(cri, list.get(0).getCnt());
		
		req.setAttribute("list", list);
		req.setAttribute("pm", pm);
		
		return "/board/list";
	}
}
