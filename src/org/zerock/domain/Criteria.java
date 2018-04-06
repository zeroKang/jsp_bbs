package org.zerock.domain;

import lombok.Getter;
import lombok.ToString;
import lombok.extern.log4j.Log4j;

@Getter
@ToString
@Log4j
public class Criteria {

	private int page;
	
	public Criteria() {
		this.page = 1;
	}
	
	public Criteria(int page) {
		this.page = page;
	}
	
	public String getLink(String url, Integer bno) {
		
		StringBuffer buffer = new StringBuffer();
		buffer.append(url);
		buffer.append("?page=" + page);
		
		log.info("=====================================");
		log.info(bno);
		
		if(bno != null && bno > 0) {
			buffer.append("&bno=" +bno);
		}
		
		return buffer.toString();
	}
}






