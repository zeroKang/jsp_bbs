package org.zerock.domain;

import java.util.Date;

import lombok.Data;

@Data
public class ReplyVO {

	private int rno, bno;
	private String reply, replyer;
	private int gno;
	private Date replydate;
	
	
}
