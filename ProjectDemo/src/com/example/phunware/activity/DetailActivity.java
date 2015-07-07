package com.example.phunware.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ShareActionProvider;
import android.view.Menu;
import android.view.MenuItem;

import com.example.model.PhunInfo;
import com.example.phunware.R;
import com.example.phunware.fragment.DetailFragment;
/**
 * Detail Activity is used to show the detail information 
 * @author hanlu Feng
 *
 */
public class DetailActivity extends AppCompatActivity {
	private ShareActionProvider mShareActionProvider;
	private DetailFragment mfragment;
	/**
	 * Create DetailActivity
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
			setContentView(R.layout.detail_page);
			setTitle("Detail");
			FragmentManager fragmentManager = getSupportFragmentManager();
			FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
			mfragment = new DetailFragment();
			mfragment.setmVenue(PhunInfo.getInstance().getmVenue());
			fragmentTransaction.add(R.id.detail_page, mfragment);
			fragmentTransaction.commit();
			ActionBar actionBar = getSupportActionBar();
			actionBar.setHomeButtonEnabled(true);	
			actionBar.setDisplayHomeAsUpEnabled(true);
			actionBar.setLogo(R.drawable.home);
			actionBar.setHomeAsUpIndicator(R.drawable.up);		
			
		
	}
/**
 * 	Override the method to Inflate menu resource file.
 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		 // Inflate menu resource file.
	    getMenuInflater().inflate(R.menu.main, menu);

	    // Locate MenuItem with ShareActionProvider
	    MenuItem item = menu.findItem(R.id.menu_item_share);

	    // Fetch and store ShareActionProvider
	    mShareActionProvider = new ShareActionProvider(this);
	    MenuItemCompat.setActionProvider(item, mShareActionProvider);
	    composeMessage(PhunInfo.getInstance().getmVenue().getName(),PhunInfo.getInstance().getmVenue().getAddress());
	    return super.onCreateOptionsMenu(menu);
	}
	/**
	 * When we select the UpIndicator, we will finish the Activity
	 */
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle presses on the action bar items
		switch (item.getItemId()) {
		case android.R.id.home:
			finish();
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
