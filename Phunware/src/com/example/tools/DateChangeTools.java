package com.example.tools;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public final class DateChangeTools {

	/**
	 * Change originTime to default timeZone date
	 * @param originTime
	 * @return
	 */
public 	static String changeGMT2DefaultZone(String  originTime){
		String time =null;
		


		DateFormat simpleDateFormat  = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);
		simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
		
		   Date date = null;
		    try {
		        date = simpleDateFormat.parse(originTime);
				TimeZone destTz = TimeZone.getDefault();//here I should get EDT on my phone
				simpleDateFormat.setTimeZone(destTz);
				simpleDateFormat  = new SimpleDateFormat("EE MM/dd  hh:mmaaa", Locale.ENGLISH);
				time = simpleDateFormat.format(date);
				
	
		    } catch (ParseException e) {
		        
		        e.printStackTrace();
		    }


		return time;

	}



}
