package org.zerock.dao;

import java.util.ArrayList;
import java.util.List;

import org.zerock.domain.ReplyVO;

import lombok.extern.log4j.Log4j;

@Log4j
public class ReplyDAO {
	
	private static ReplyDAO instance = new ReplyDAO();

	public static ReplyDAO getInstance() {

		return instance;

	}

	private ReplyDAO() {

	}
	
	public List<ReplyVO> list(Integer bno)throws Exception{
		
		String sql ="select * from tbl_reply where bno = ? order by gno asc, rno asc";
		
		List<ReplyVO> list = new ArrayList<>();
		
		new SQLTemplate() {
			
			@Override
			public void runSQL() throws Exception {
				
				pstmt = con.prepareStatement(sql);
				
				pstmt.setInt(1, bno);
				
				rs = pstmt.executeQuery();
				
				while(rs.next()) {
					
					ReplyVO vo = new ReplyVO();
					vo.setRno(rs.getInt(1));
					vo.setBno(rs.getInt(2));
					vo.setReply(rs.getString(3));
					vo.setReplyer(rs.getString(4));
					vo.setGno(rs.getInt(5));
					vo.setReplydate(rs.getDate(6));

					list.add(vo);
					
				}
				
				
				
				
			}
		}.execute();
		
		
		return list;
	}

	
	public void create(ReplyVO vo)throws Exception{
		
		StringBuffer sql = new StringBuffer("insert into tbl_reply (rno, bno, reply,replyer,gno) ");
		
		if(vo.getGno() == -1) {
				sql.append(" values (seq_reply.nextval, ?, ?, ?, seq_reply.currval)");
		}else {
			sql.append(" values (seq_reply.nextval, ?, ?, ?, ?)");
		}
		
		new SQLTemplate() {
			
			@Override
			public void runSQL() throws Exception {
				
				pstmt = con.prepareStatement(sql.toString());
				
				pstmt.setInt(1, vo.getBno());
				pstmt.setString(2, vo.getReply());
				pstmt.setString(3, vo.getReplyer());
				
				if(vo.getGno() != -1) {
					pstmt.setInt(4, vo.getGno());
				}
				
				log.info("REPLY COUNT: " + pstmt.executeUpdate());
				
				
			}
		}.execute();
		
	}
}
