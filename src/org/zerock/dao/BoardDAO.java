package org.zerock.dao;

import java.util.ArrayList;
import java.util.List;

import org.zerock.domain.BoardVO;
import org.zerock.domain.Criteria;
import org.zerock.domain.SQLGenerator;

import lombok.extern.log4j.Log4j;

@Log4j
public class BoardDAO {

	private static BoardDAO instance = new BoardDAO();

	public static BoardDAO getInstance() {

		return instance;

	}

	private BoardDAO() {

	}

	public List<BoardVO> getPage(Criteria cri) throws Exception {

		
		SQLGenerator sqlGen = new SQLGenerator(cri.getType(), cri.getKeyword());
		
		StringBuffer buffer = new StringBuffer();
		
		
		buffer.append("select  ");
		buffer.append(" bno,title,content,writer,viewcnt, ");
		buffer.append(" regdate, updatedate, cnt  ");
		buffer.append("from ");
		buffer.append("  ( ");
		buffer.append("    select /*+ INDEX_DESC(tbl_board pk_board) */ ");
		buffer.append("     rownum rn, bno,title, content, writer, ");
		buffer.append("     viewcnt, regdate, updatedate, count(bno) over() cnt ");
		buffer.append("    from tbl_board  ");
		buffer.append("    where bno > 0 ");
		
		buffer.append(sqlGen.makeSQL());
		
		buffer.append("    and rownum <= ( CEIL(?/10) *100) +1 ");
		buffer.append("  ) ");
		buffer.append("where rn > (? -1) * 10 and rn <= (? * 10)   ");

		log.info("=========================");
		log.info(buffer.toString());
		
		
		List<BoardVO> list = new ArrayList<>();

		new SQLTemplate() {

			@Override
			public void runSQL() throws Exception {

				pstmt = con.prepareStatement(buffer.toString());
				
				int qidx = 1;
				
				int count = sqlGen.getCount();
				
				for(int i = 0; i < count; i++) {
					
					pstmt.setString(qidx++, cri.getKeyword());
				}
				
				pstmt.setInt(qidx++, cri.getPage());
				pstmt.setInt(qidx++, cri.getPage());
				pstmt.setInt(qidx++, cri.getPage());

				rs = pstmt.executeQuery();

				while (rs.next()) {

					int idx = 1;
					// bno,title,content,writer,viewcnt,
					// regdate, updatedate, cnt
					BoardVO vo = new BoardVO(rs.getInt(idx++), rs.getString(idx++), rs.getString(idx++),
							rs.getString(idx++), rs.getInt(idx++),

							rs.getDate(idx++), rs.getDate(idx++), rs.getInt(idx++));

					log.info(vo);
					list.add(vo);
				}
			}
		}.execute();
		

		return list;
	}
	
	public void update(BoardVO vo)throws Exception{
		
		StringBuffer buffer = new StringBuffer();
		
		buffer.append("update tbl_board set title=?, content= ?, updatedate = sysdate  ");
		buffer.append("where bno = ?");
		
		new SQLTemplate() {
			
			@Override
			public void runSQL() throws Exception {
				pstmt = con.prepareStatement(buffer.toString());
				
				pstmt.setString(1, vo.getTitle());
				pstmt.setString(2, vo.getContent());
				pstmt.setInt(3, vo.getBno());
				
				log.info("Update count: " + pstmt.executeUpdate());
			}
		}.execute();
		
	}

	public void delete(Integer bno) throws Exception {

		StringBuffer buffer = new StringBuffer();
		buffer.append("delete from tbl_board where bno = ? ");

		new SQLTemplate() {

			@Override
			public void runSQL() throws Exception {
				pstmt = con.prepareStatement(buffer.toString());
				pstmt.setInt(1, bno);

				log.info("COUNT: " + pstmt.executeUpdate());

			}
		}.execute();
	}

	public BoardVO read(Integer bno, boolean update) throws Exception {

		StringBuffer buffer = new StringBuffer();

		buffer.append("select * from tbl_board where bno = ? ");

		BoardVO vo = new BoardVO();

		new SQLTemplate() {

			@Override
			public void runSQL() throws Exception {
				
				if(update) {
					pstmt = con.prepareStatement("update tbl_board set viewcnt = viewcnt +1 where bno = ?");
					
					pstmt.setInt(1, bno);
					
					pstmt.executeUpdate();
					pstmt.close();
					pstmt = null;
				}
				

				pstmt = con.prepareStatement(buffer.toString());
				pstmt.setInt(1, bno);
				rs = pstmt.executeQuery();

				rs.next();

				int idx = 1;

				vo.setBno(rs.getInt(idx++));
				vo.setTitle(rs.getString(idx++));
				vo.setContent(rs.getString(idx++));
				vo.setWriter(rs.getString(idx++));
				vo.setViewcnt(rs.getInt(idx++));
				vo.setRegdate(rs.getDate(idx++));
				vo.setUpdateDate(rs.getDate(idx++));

				log.info(vo);

			}
		}.execute();

		return vo;

	}

	public void create(BoardVO vo) throws Exception {

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

	public void testConnection() throws Exception {

		new SQLTemplate() {

			@Override
			public void runSQL() throws Exception {

				log.info(con);

			}
		}.execute();
		;

	}

}
