package com.example.phunware.activity;

import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.ShareActionProvider;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.example.data.Venue;
import com.example.download.DownloadTask;
import com.example.phunware.R;
import com.example.phunware.fragment.DetailFragment;
import com.example.phunware.fragment.MenuFragment;
import com.example.url.DownloadURL;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class MainActivity extends ActionBarActivity	 {

	private ListView mlist;
	private MenuFragment menuFragment;
	private DetailFragment detailFragment;
	private ShareActionProvider mShareActionProvider;
	private Venue mVenue;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_page);
		DownloadTask dTask = new DownloadTask(this);
		dTask.execute(DownloadURL.DOWNLOAD_URL);
		menuFragment =(MenuFragment) getSupportFragmentManager().findFragmentById(R.id.menu_fragment);
		detailFragment =(DetailFragment) getSupportFragmentManager().findFragmentById(R.id.details_layout);
		
		
	}
	
	
	public void fillView(String venue){
		Gson gson = new Gson();
		List<Venue> mVenueList = gson.fromJson(venue, new TypeToken<List<Venue>>(){}.getType());
		menuFragment.updateUI(mVenueList);
		if(detailFragment!= null&& mVenueList.size()>0){
			mlist.setItemChecked(0,true);
		}
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
	    	
	        	composeMessage(getmVenue().getName(),getmVenue().getAddress());
	            return true;
	
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}

	
	
	
	public Venue getmVenue() {
		return mVenue;
	}


	public void setmVenue(Venue mVenue) {
		this.mVenue = mVenue;
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
	

	
	public MenuFragment getMenuFragment() {
		return menuFragment;
	}


	public void setMenuFragment(MenuFragment menuFragment) {
		this.menuFragment = menuFragment;
	}


	public DetailFragment getDetailFragment() {
		return detailFragment;
	}


	public void setDetailFragment(DetailFragment detailFragment) {
		this.detailFragment = detailFragment;
	}
	
	
}
