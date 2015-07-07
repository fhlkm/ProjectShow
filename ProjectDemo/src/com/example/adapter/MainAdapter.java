package com.example.adapter;

import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.model.PhunInfo;
import com.example.model.Venue;
import com.example.phunware.R;
import com.example.phunware.activity.DetailActivity;
import com.example.phunware.activity.MainActivity;


public class MainAdapter extends BaseAdapter{
	private List<Venue> mVenueList= null;
	private Context mContext=null;
	private LayoutInflater mInflater;
	
	public MainAdapter(List<Venue> mVenueList, Context mContext) {
		this.mVenueList = mVenueList;
		this.mContext = mContext;
		this.mInflater = LayoutInflater.from(mContext);
	}

	
	/* (non-Javadoc)
	 * Get Count of items
	 * @see android.widget.Adapter#getCount()
	 */
	@Override
	public int getCount() {

		if(mVenueList!= null)
			return mVenueList.size();
		return 0;
	}

	
	/* (non-Javadoc)
	 * Get detail of Item
	 * @see android.widget.Adapter#getItem(int)
	 */
	@Override
	public Object getItem(int arg0) {

		if(mVenueList!= null)
			return mVenueList.get(arg0);
		return null;
	}

	/* (non-Javadoc)
	 * Get item Id
	 * @see android.widget.Adapter#getItemId(int)
	 */
	@Override
	public long getItemId(int arg0) {

		return arg0;
	}

	
	/* Inflate View by Venue
	 * (non-Javadoc)
	 * @see android.widget.Adapter#getView(int, android.view.View, android.view.ViewGroup)
	 */
	@Override
	public View getView(int arg0, View convertView, ViewGroup arg2) {
		ViewHolder mholder;
		if(convertView == null){
			convertView = mInflater.inflate(R.layout.item,null);
			mholder = new ViewHolder();                             
			mholder.mName = (TextView) convertView.findViewById(R.id.mName);
			mholder.mAddress = (TextView) convertView.findViewById(R.id.mAddress);
			convertView.setTag(mholder);
		}else{
			mholder = (ViewHolder)convertView.getTag();
		}
		final Venue mVeneu = mVenueList.get(arg0);
		mholder.mName.setText(mVeneu.getName());
		mholder.mAddress.setText(mVeneu.getAddress()+","+mVeneu.getCity()+","+mVeneu.getState());
		View itemLayout = (View) convertView.findViewById(R.id.mItem);	
		// In pad
		if(	((MainActivity)mContext).getDetailLayout() != null){
			itemLayout.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					PhunInfo mDetailDate = PhunInfo.getInstance();
					mDetailDate.setmVenue(mVeneu);
					((MainActivity)mContext).getmDetailfragment().updateUI(mVeneu);	
					((MainActivity)mContext).composeMessage(mVeneu.getName(), mVeneu.getAddress());
				}
			});
		}else{
			itemLayout.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					Intent intent = new Intent(mContext, DetailActivity.class);		
					PhunInfo mDetailDate = PhunInfo.getInstance();
					mDetailDate.setmVenue(mVeneu);
					mContext.startActivity(intent);			
				}
			});

		}
		return convertView;
	}
	
	  public class ViewHolder{
	        public TextView mName;
	        public TextView mAddress;

	    }

}
