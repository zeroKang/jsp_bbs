package org.zerock.dao;

import lombok.extern.log4j.Log4j;

@Log4j
public class BoardDAO {
	
	private static BoardDAO instance = new BoardDAO();
	
	public static BoardDAO getInstance() {
		
		return instance;		
		
	}
	
	
	private BoardDAO() {
		
	}
	
	public void testConnection() throws Exception{
		
		new SQLTemplate() {
			
			@Override
			public void runSQL() throws Exception {
				
				log.info(con);
				
			}
		}.execute();;
		
	}

}

