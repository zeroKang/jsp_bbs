package org.zerock.domain;

import lombok.Getter;
import lombok.ToString;
import lombok.extern.log4j.Log4j;

@Getter
@ToString
@Log4j
public class Criteria {

	private int page;
	private String type;
	private String keyword;
	
	public Criteria() {
		this.page = 1;
	}
	
	public Criteria(int page) {
		this.page = page;
	}
	
	public Criteria(int page, String type, String keyword) {
		this.page = page;
		this.type = type;
		this.keyword = keyword;
	}
	

}






