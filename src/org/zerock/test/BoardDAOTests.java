package org.zerock.test;

import org.junit.Test;
import org.zerock.dao.BoardDAO;
import org.zerock.domain.BoardVO;

import lombok.extern.log4j.Log4j;

@Log4j
public class BoardDAOTests {

	BoardDAO dao = BoardDAO.getInstance();
	
	@Test	
	public void testConnection() throws Exception{
		
		dao.testConnection();
	}
	
	@Test
	public void testCreate()throws Exception {
		
		for(int i = 0; i < 10;i++) {
			
			BoardVO vo = new BoardVO("Title" +i , "Content "+i ,"user0" +i);
			
			dao.create(vo);
			
		}
		
	}
	
}
