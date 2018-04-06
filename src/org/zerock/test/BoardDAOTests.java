package org.zerock.test;

import org.junit.Test;
import org.zerock.dao.BoardDAO;
import org.zerock.domain.BoardVO;
import org.zerock.domain.Criteria;

import lombok.extern.log4j.Log4j;

@Log4j
public class BoardDAOTests {

	BoardDAO dao = BoardDAO.getInstance();
	
	@Test
	public void listTest()throws Exception{
		
		Criteria cri = new Criteria(3);
		
		dao.getPage(cri).forEach(board ->
		log.info(board));
		
	}
	
	
	
	@Test
	public void deleteTest()throws Exception{
		
		dao.delete(10);
	}
	
	@Test	
	public void testConnection() throws Exception{
		
		dao.testConnection();
	}
	
	@Test
	public void testRead()throws Exception{
		
		log.info(dao.read(10));
		
		
	}
	
	@Test
	public void testCreate()throws Exception {
		
		for(int i = 0; i < 10;i++) {
			
			BoardVO vo = new BoardVO("Title" +i , "Content "+i ,"user0" +i);
			
			dao.create(vo);
			
		}
		
	}
	
}
