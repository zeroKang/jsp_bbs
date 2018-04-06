package org.zerock.domain;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class BoardVO {

	private Integer bno;
	private String title, content, writer;
	private int viewcnt;
	private Date regdate, updateDate;

	public BoardVO(String title, String content, String writer) {
		this.title = title;
		this.content = content;
		this.writer = writer;
	}
	
}
