package com.example.tools;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public final class DateChangeTools {

	/**
	 * 
	 * Change originTime to default timeZone date
	 * @param mOriginTime
	 * @return
	 */
	public 	static String changeGMT2DefaultZone(String  mOriginTime){
		String mTime =null;
		DateFormat mSimpleDateFormat  = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z", Locale.ENGLISH);
//		mSimpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));		
		Date mDate = null;
		try {
			mDate = mSimpleDateFormat.parse(mOriginTime);
			TimeZone destTz = TimeZone.getDefault();
			mSimpleDateFormat.setTimeZone(destTz);
			mSimpleDateFormat  = new SimpleDateFormat("EE MM/dd  hh:mmaaa", Locale.ENGLISH);
			mTime = mSimpleDateFormat.format(mDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return mTime;
	}




}
