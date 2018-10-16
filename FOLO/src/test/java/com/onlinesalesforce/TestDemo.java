package com.onlinesalesforce;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class TestDemo {
	
	public static void main(String args[]){
		
		
		SimpleDateFormat date=new SimpleDateFormat("d");
		
		Calendar cal = Calendar.getInstance();
			cal.add(Calendar.DATE, 1);
			String increment= date.format(cal.getTime());
		
			System.out.println(increment);
		
		
	}

}
