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

	
	@Override
	public int getCount() {

		if(mVenueList!= null)
			return mVenueList.size();
		return 0;
	}

	
	@Override
	public Object getItem(int arg0) {

		if(mVenueList!= null)
			return mVenueList.get(arg0);
		return null;
	}

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
		ViewHolder holder;
		if(convertView == null){
			 convertView = mInflater.inflate(R.layout.item,null);
             holder = new ViewHolder();
                               
            holder.name = (TextView) convertView.findViewById(R.id.mName);
            holder.address = (TextView) convertView.findViewById(R.id.mAddress);
            convertView.setTag(holder);
		}else{
			 holder = (ViewHolder)convertView.getTag();
		}
		final Venue veneu = mVenueList.get(arg0);
		holder.name.setText(veneu.getName());
		
		holder.address.setText(veneu.getAddress()+","+veneu.getCity()+","+veneu.getState());
		
		View itemLayout = (View) convertView.findViewById(R.id.mItem);
		// In pad
		if(	((MainActivity)mContext).getDetailLayout() != null){
			itemLayout.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					((MainActivity)mContext).getmDetailfragment().updateUI(veneu);				
				}
			});

		}else{
			itemLayout.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					Intent intent = new Intent(mContext, DetailActivity.class);		
					PhunInfo mDetailDate = PhunInfo.getInstance();
					mDetailDate.setmVenue(veneu);
					mContext.startActivity(intent);
				}
			});

		}
		return convertView;
	}
	
	  public class ViewHolder{
	        public TextView name;
	        public TextView address;

	    }

}
