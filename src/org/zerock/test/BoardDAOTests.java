package org.zerock.test;

import org.junit.Test;
import org.zerock.dao.BoardDAO;

import lombok.extern.log4j.Log4j;

@Log4j
public class BoardDAOTests {

	BoardDAO dao = BoardDAO.getInstance();
	
	@Test	
	public void testConnection() throws Exception{
		
		dao.testConnection();
	}
	
}
