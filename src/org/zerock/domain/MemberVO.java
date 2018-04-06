package org.zerock.domain;

import java.util.Date;

import lombok.Data;

@Data
public class MemberVO {

	private String userid,userpw,uname,email;
	private Date regdate,updatedate;
	
}
