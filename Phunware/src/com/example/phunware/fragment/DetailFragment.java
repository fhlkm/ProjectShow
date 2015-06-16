package com.example.phunware.fragment;

import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.androidquery.AQuery;
import com.example.model.ScheduleItem;
import com.example.model.Venue;
import com.example.phunware.R;
import com.example.tools.DateChangeTools;

public class DetailFragment extends Fragment{
	private View view =null;
	private AQuery mAquery =null;
	private Venue mVenue;

	public  DetailFragment() {
	}


	
	public Venue getmVenue() {
		return mVenue;
	}
	public void setmVenue(Venue mVenue) {
		this.mVenue = mVenue;
	}
	/**
	 * Inflate the view of Fragment
	 */
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.detail, container, false);		
		return view;
	}

	/**
	 * Just in case View is null, we will show view in OnResume
	 */
	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		if(null != mVenue)
		updateUI(mVenue);	
	}
/**
 * Update the UI of DetailFragment
 * @param mVenue, the source of UI
 */
	public void updateUI(Venue mVenue){
		if(mAquery ==null){
			mAquery = new AQuery(getActivity());
		}
		//if this image is huge, avoid memory caching
		boolean memCache = true;
		boolean fileCache = true;
		if(mVenue != null&&null != view){
			mAquery.id(R.id.image).image(mVenue.getImageUrl(), memCache, fileCache,0,R.drawable.empty);
			TextView name = (TextView)view.findViewById(R.id.name);
			name.setText(mVenue.getName());
			TextView homeAddress = (TextView)view.findViewById(R.id.homeAddress);
			homeAddress.setText(mVenue.getAddress());
			TextView cityAddress = (TextView)view.findViewById(R.id.cityAddress);
			cityAddress.setText(mVenue.getCity()+","+mVenue.getState()+","+mVenue.getZip());
			TextView scheduleTime = (TextView)view.findViewById(R.id.scheduleTime);
			List<ScheduleItem> items = mVenue.getSchedule();
			String text="";
			for(ScheduleItem item: items){
				String start_date= DateChangeTools.changeGMT2DefaultZone(item.getStartDateString());
				String end_date= DateChangeTools.changeGMT2DefaultZone(item.getEndDateString());
				text = text +(start_date+"-"+end_date.split(" ")[2]+" "+end_date.split(" ")[3]+"\n");
			}
			scheduleTime.setText(text);
		}

	}
	





}
