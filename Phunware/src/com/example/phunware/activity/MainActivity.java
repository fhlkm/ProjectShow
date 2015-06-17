package com.example.phunware.activity;

import java.util.List;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ShareActionProvider;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.ListView;

import com.example.download.DownloadTask;
import com.example.model.PhunInfo;
import com.example.model.Venue;
import com.example.phunware.R;
import com.example.phunware.fragment.DetailFragment;
import com.example.phunware.fragment.MenuFragment;
import com.example.url.DownloadURL;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

public class MainActivity extends AppCompatActivity	 {

	private ListView mlist;
	private MenuFragment menuFragment;
	private FrameLayout mDetailLayout;
	private ShareActionProvider mShareActionProvider;
	private DetailFragment mDetailfragment;
	private Venue mVenue;
	/* (non-Javadoc)
	 * Create Activity
	 * @see android.support.v7.app.AppCompatActivity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_page);
		ActionBar actionBar = getSupportActionBar();
		actionBar.setHomeButtonEnabled(false);
		actionBar.setDisplayHomeAsUpEnabled(true);
		actionBar.setHomeAsUpIndicator(R.drawable.home);
		menuFragment =(MenuFragment) getSupportFragmentManager().findFragmentById(R.id.menu_fragment);
		menuFragment.setmContext(this);
		mDetailLayout =(FrameLayout)findViewById(R.id.details_layout);
		if(mDetailLayout==null ){
			setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		}else{
			setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
			FragmentManager fragmentManager = getSupportFragmentManager();
			FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
			mDetailfragment = new DetailFragment();
			fragmentTransaction.add(R.id.details_layout, mDetailfragment);
			fragmentTransaction.commit();
		}	
		mlist = menuFragment.getMenuList();
		if(PhunInfo.getInstance().getMlist()==null){	
		DownloadTask dTask = new DownloadTask(this);
		dTask.execute(DownloadURL.DOWNLOAD_URL);
		}else{
		fillView(PhunInfo.getInstance().getMlist());
		}
	}
	
	
	/**
	 * Inflate ListView by venue (Inflate Detail page if it exists)
	 * @param venue
	 */
	public void fillView(String venue){
		GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setDateFormat("yy-MM-dd hh:mm:ss Z");
		Gson gson = gsonBuilder.create();
		List<Venue> mVenueList = gson.fromJson(venue, new TypeToken<List<Venue>>(){}.getType());
		if(PhunInfo.getInstance().getMlist()==null){
			PhunInfo.getInstance().setMlist(mVenueList);
		}
		fillView(mVenueList);
	
	}

	public void fillView(List<Venue> mVenueList){
		menuFragment.updateUI(mVenueList);			
		if(mDetailLayout!= null&& mVenueList.size()>0){
			mVenue = mVenueList.get(0);
			mDetailfragment.updateUI(mVenue);
			mlist.setItemChecked(0,true);	
		    composeMessage(mVenue.getName(), mVenue.getAddress());
		}
	}

	/* (non-Javadoc)
	 * Create Menu
	 * @see android.app.Activity#onCreateOptionsMenu(android.view.Menu)
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		 // Inflate menu resource file.
		if(mDetailLayout!=null){
	    getMenuInflater().inflate(R.menu.main, menu);

	    // Locate MenuItem with ShareActionProvider
	    MenuItem item = menu.findItem(R.id.menu_item_share);

	    // Fetch and store ShareActionProvider
	    mShareActionProvider = new ShareActionProvider(this);
	    MenuItemCompat.setActionProvider(item, mShareActionProvider); 
	    composeMessage("","");
	    
		}
	    // Return true to display menu
	    return super.onCreateOptionsMenu(menu);
	}
	

	
	
	
	public Venue getmVenue() {
		return mVenue;
	}


	public void setmVenue(Venue mVenue) {
		this.mVenue = mVenue;
	}


	public void composeMessage(String name, String address){
	    Intent mIntent = new Intent(Intent.ACTION_SEND);
	    mIntent.setType("text/plain");
	   
		if(name != null && address != null){
			 String message=name+" "+address;
			 mIntent.putExtra(Intent.EXTRA_TEXT, message);
		
		}
		if (mShareActionProvider != null) {
   	        mShareActionProvider.setShareIntent(mIntent);
   	    }
		
	}
	

	
	public MenuFragment getMenuFragment() {
		return menuFragment;
	}


	public void setMenuFragment(MenuFragment menuFragment) {
		this.menuFragment = menuFragment;
	}


	public FrameLayout getDetailLayout() {
		return mDetailLayout;
	}


	public void setDetailLayout(FrameLayout detailLayout) {
		this.mDetailLayout = detailLayout;
	}


	public ListView getMlist() {
		return mlist;
	}


	public void setMlist(ListView mlist) {
		this.mlist = mlist;
	}


	public DetailFragment getmDetailfragment() {
		return mDetailfragment;
	}


	public void setmDetailfragment(DetailFragment mDetailfragment) {
		this.mDetailfragment = mDetailfragment;
	}



	
}
