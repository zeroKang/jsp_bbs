package org.zerock.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

public class CookieUtil {
	
	public static void main(String[] args) {
		
		String value = "|123|456";
		
		String target = "|456";
		
		System.out.println(checkValue(value, target));
		
	}
	
	public static String valueAppend(Cookie ck, String target) {
		
		String value = ck.getValue();
		
		if(checkValue(value, target) == false) {
			
			 return value+target;
		};
		return value;
	}
	
	public static boolean checkValue(String value, String target) {
		
		if(value == null || target == null|| value.length() < target.length()) {
			return false;
		}
		
		return value.indexOf(target) >= 0;
		
	}

	
	public static Cookie findCookie(HttpServletRequest req, String name) {
		
		Cookie[] cks = req.getCookies();
		
		if(cks == null || cks.length == 0) {
			return null;
		}
		
		for (Cookie cookie : cks) {
			if(cookie.getName().equals(name)) {
				return cookie;
			}
		}
		return null;
		
	}
	
}
