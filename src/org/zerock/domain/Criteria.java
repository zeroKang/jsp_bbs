package org.zerock.domain;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class Criteria {

	private int page;
	
	public Criteria() {
		this.page = 1;
	}
	
	public Criteria(int page) {
		this.page = page;
	}
}
