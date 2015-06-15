package com.example.phunware.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.androidquery.AQuery;
import com.example.data.Venue;
import com.example.phunware.R;

public class DetailFragment extends Fragment{
	private View view =null;
	private AQuery mAquery =null;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.detail, container, false);
		
		return view;
	}
	
	public void updateUI(Venue mVenue){
		if(mAquery ==null){
			mAquery = new AQuery(getActivity());
		}
		//this image is huge, avoid memory caching
		boolean memCache = false;
		boolean fileCache = true;
		mAquery.id(R.id.image).image(mVenue.getImageUrl(), memCache, fileCache);
		TextView homeAddress = (TextView)view.findViewById(R.id.name);
		homeAddress.setText(mVenue.getName());
		TextView cityAddress = (TextView)view.findViewById(R.id.cityAddress);
		cityAddress.setText(mVenue.getAddress()+","+mVenue.getCity()+","+mVenue.getState()+","+mVenue.getZip());
	
	}
	
	
	
	
	

}
