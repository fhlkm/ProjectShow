package com.example.model;

import java.util.List;
/**
 * Create a singleton to store data
 * @author hanlu Feng
 *
 */
public class PhunInfo {
	
	private static PhunInfo mDetailDate = null;
	private Venue mVenue = null;
	private List<Venue> mlist;
	private PhunInfo(){
		
	}
	public static PhunInfo getInstance(){
		if(mDetailDate == null){
			mDetailDate = new PhunInfo();
		}
		return mDetailDate;
	}
	public static PhunInfo getmDetailDate() {
		return mDetailDate;
	}
	public static void setmDetailDate(PhunInfo mDetailDate) {
		PhunInfo.mDetailDate = mDetailDate;
	}
	public Venue getmVenue() {
		return mVenue;
	}
	public void setmVenue(Venue mVenue) {
		this.mVenue = mVenue;
	}
	public List<Venue> getMlist() {
		return mlist;
	}
	public void setMlist(List<Venue> mlist) {
		this.mlist = mlist;
	}
	
	

}
