package org.zerock.dao;

import org.zerock.domain.MemberVO;

public class MemberDAO {
	
	private static MemberDAO instance = new MemberDAO();

	public static MemberDAO getInstance() {

		return instance;

	}
	
	private MemberDAO() {
		
	}

	public MemberVO login(String uid, String upw)throws Exception{
		
		MemberVO vo = new MemberVO();
		
		new SQLTemplate() {
			
			@Override
			public void runSQL() throws Exception {
				
				pstmt = con.prepareStatement("select * from tbl_member where userid =? and userpw = ?");
				pstmt.setString(1, uid);
				pstmt.setString(2, upw);
				
				rs = pstmt.executeQuery();
				
				rs.next();
				
				
				vo.setUserid(rs.getString(1));
				vo.setUserpw(rs.getString(2));
				vo.setUname(rs.getString(3));
				vo.setEmail(rs.getString(4));
				
				
			}
		}.execute();
		
		
		return vo.getUname() != null?vo:null;
	}
}
