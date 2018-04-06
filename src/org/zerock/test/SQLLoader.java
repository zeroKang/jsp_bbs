package org.zerock.test;

import java.io.File;
import java.util.Scanner;

public class SQLLoader {

	public static void main(String[] args) throws Exception{
		
		String path = "C:\\zzz\\0404.sql";
		
		Scanner scanner = new Scanner(new File(path),"UTF-8");
		
		while(true) {
			try {
				System.out.println("buffer.append(\""+scanner.nextLine()+" \");");
			}catch(Exception e) {
				break;
			}
			
		}
		
		
		
		scanner.close();
	}
}
