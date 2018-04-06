package org.zerock.controller;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.extern.log4j.Log4j;


@Log4j
public abstract class AbstractController extends HttpServlet {

	@Override
	protected void service(HttpServletRequest req, 
			HttpServletResponse res) throws ServletException, IOException {
	
		System.out.println("====================");
		
		Class clz = this.getClass();
		String spath = req.getServletPath();
		String conPath = req.getServletContext().getContextPath();
		String path = req.getRequestURI();
		
		
		int temp = (conPath + spath).length();
				
		String methodName = path.substring(temp + 1) + req.getMethod();
		
		log.info("methodName : " + methodName);
		
		try {
			Method method = clz.getDeclaredMethod(methodName,
					JRRequest.class, HttpServletResponse.class);
			
			Object result = method.invoke(this, new JRRequest(req), res);
			
			String resultStr = (String)result;
			
			if(resultStr.startsWith("redirect:")) {
				
				res.sendRedirect(conPath + resultStr.substring(9));
				
			}else {
				
				req.getRequestDispatcher("/WEB-INF"+resultStr+".jsp")
				.forward(req, res);
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
	}	
	
	
}
