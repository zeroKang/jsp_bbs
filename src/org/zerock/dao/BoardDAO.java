package org.zerock.dao;

import org.zerock.domain.BoardVO;

import lombok.extern.log4j.Log4j;

@Log4j
public class BoardDAO {
	
	private static BoardDAO instance = new BoardDAO();
	
	public static BoardDAO getInstance() {
		
		return instance;		
		
	}
	
	
	private BoardDAO() {
		
	}
	
	public void create(BoardVO vo)throws Exception{
		
		StringBuffer buffer = new StringBuffer();
		buffer.append("insert into tbl_board (bno, title,content,writer) ");
		buffer.append("values (seq_board.nextval, ?,?,?)");

		
		new SQLTemplate() {
			
			@Override
			public void runSQL() throws Exception {
				
				pstmt = con.prepareStatement(buffer.toString());
				int idx = 1;
				pstmt.setString(idx++, vo.getTitle());
				pstmt.setString(idx++, vo.getContent());
				pstmt.setString(idx++, vo.getWriter());
				
				log.info("COUNT: " + pstmt.executeUpdate());
				
			}
		}.execute();
		
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

