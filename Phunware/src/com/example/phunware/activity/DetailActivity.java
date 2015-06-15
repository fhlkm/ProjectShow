package com.example.phunware.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.ShareActionProvider;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.androidquery.AQuery;
import com.example.phunware.R;

@SuppressWarnings("deprecation")
public class DetailActivity extends ActionBarActivity {
	private AQuery mAquery =null;
	private ShareActionProvider mShareActionProvider;
	private String name= null;
	private String address= null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);


			setContentView(R.layout.detail_page);
			LinearLayout detailPage = (LinearLayout)findViewById(R.id.detail_page);
			LayoutInflater layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			View view = layoutInflater.inflate(R.layout.detail,detailPage );
			Intent mIntent = this.getIntent(); 
			String city = mIntent.getStringExtra("city");
			address = mIntent.getStringExtra("address");
			String state = mIntent.getStringExtra("state");
			name = 	 mIntent.getStringExtra("name");
			String imageUrl = mIntent.getStringExtra("imageurl");
			String zip =mIntent.getStringExtra("zip");
			if(mAquery ==null){
				mAquery = new AQuery(this);
			}
			if(null != imageUrl&&!imageUrl.equals(""))
			mAquery.id(R.id.image).image(imageUrl, false, true);
			
			TextView mName = (TextView)view.findViewById(R.id.name);
			mName.setText(name);
			TextView homeAddress = (TextView)view.findViewById(R.id.homeAddress);
			homeAddress.setText(address);
			TextView cityAddress = (TextView)view.findViewById(R.id.cityAddress);
			cityAddress.setText(city+","+state+" "+zip);
			
			
			
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
//		return super.onCreateOptionsMenu(menu);
		 // Inflate menu resource file.
	    getMenuInflater().inflate(R.menu.main, menu);

	    // Locate MenuItem with ShareActionProvider
	    MenuItem item = menu.findItem(R.id.menu_item_share);

	    // Fetch and store ShareActionProvider
	    mShareActionProvider = new ShareActionProvider(this);
	    MenuItemCompat.setActionProvider(item, mShareActionProvider);


	    // Return true to display menu
	    return super.onCreateOptionsMenu(menu);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle presses on the action bar items
	    switch (item.getItemId()) {
	        case R.id.menu_item_share:
	    	
	        	composeMessage(name,address);
	            return true;
	
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}

	
	private Intent composeMessage(String name, String address){
	    Intent mIntent = new Intent(Intent.ACTION_SEND);
	    mIntent.setType("text/plain");
	   
		if(name != null && address != null){
			 String message=name+" "+address;
			 mIntent.putExtra(Intent.EXTRA_TEXT, message);
		
		}
		if (mShareActionProvider != null) {
   	        mShareActionProvider.setShareIntent(mIntent);
   	    }
		return mIntent;
		
	}
	

	
	
}
