package com.example.phunware.fragment;


import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.adapter.MainAdapter;
import com.example.data.Venue;
import com.example.phunware.R;

public class MenuFragment extends Fragment {
	private ListView menuList;
	private MainAdapter mAdapter ;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		View view = inflater.inflate(R.layout.menu_fragment, container, false);
		menuList = (ListView) view.findViewById(R.id.mlistView);
		return view;
	}
	
	public void updateUI(List<Venue> mVenueList){
		MainAdapter mAdapter = new MainAdapter(mVenueList,getActivity());
		menuList.setAdapter(mAdapter);
		mAdapter.notifyDataSetChanged();
		synchronized(menuList){
			menuList.notify();
		}
	
	}
	
	
	


}
