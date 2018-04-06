package org.zerock.domain;

import java.util.Arrays;

public class SQLGenerator {

	private String type;
	private String keyword;
	private int count;
	
	
	public SQLGenerator(String type, String keyword) {
		super();
		this.type = type;
		this.keyword = keyword;
	}

	public String[] getTypes() {
		
		if(type == null || type.trim().length() == 0) {
			return null;
		}
		
		String[] arr = type.split("");
		
		this.count = arr.length;
		
		return arr;
		
	}
	
	public int getCount() {
		return this.count;
	}
	
	public String makeSQL() {
		
		String[] types = getTypes();
		
		if(types == null) {
			return "";
		}
		
		StringBuffer buffer = new StringBuffer();
		buffer.append("and (");
		
		for (int i = 0; i < types.length; i++) {
			
			if(i != 0) {
				buffer.append(" OR ");
			}
			
			if(types[i].equals("T")) {
				
				buffer.append("title like '%'||?||'%'");
			}
			if(types[i].equals("C")) {
				
				buffer.append("content like '%'||?||'%'");
			}			
			if(types[i].equals("W")) {
				
				buffer.append("writer like '%'||?||'%'");
			}			
			
		}	
		
		buffer.append(") ");
		return buffer.toString();
	}
	
}
