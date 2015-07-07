package com.example.download;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.example.phunware.R;
import com.example.phunware.activity.MainActivity;

/**
 * @author hanlu Feng
 *
 */
public class DownloadTask extends AsyncTask<String, Integer, String> {
	
	
	ProgressDialog mDialog=null;
	private Context mContext= null;
	public DownloadTask(Context mContext) {
		 mDialog=new ProgressDialog(mContext);
		 this.mContext = mContext;
         mDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
         mDialog.setTitle("Downloading");
         mDialog.setIcon(com.example.phunware.R.drawable.ic_launcher);
         mDialog.setProgress(0);
         mDialog.setIndeterminate(false);
         mDialog.setCancelable(false);  
         mDialog.show(); 
		 
	}

	/* (non-Javadoc)
	 * Download in the background
	 * @see android.os.AsyncTask#doInBackground(java.lang.Object[])
	 */
	@Override
	protected String doInBackground(String... params) {
		if(params!= null){
			HttpURLConnection conn=null;
			try {
				URL url = new URL(params[0]);
				conn = (HttpURLConnection) url.openConnection();
				conn.setRequestMethod("GET");
				conn.connect();
				if(conn.getResponseCode()==HttpURLConnection.HTTP_OK){
					InputStream mInput = conn.getInputStream();
					long mTotal = conn.getContentLength();  
					ByteArrayOutputStream baos = new ByteArrayOutputStream();  
					byte[] mBuf = new byte[1024];  
					int mCount = 0;  
					int mLength = -1;  
					while ((mLength = mInput.read(mBuf)) != -1) {  
						baos.write(mBuf, 0, mLength);  
						mCount += mLength;  
						publishProgress((int) ((mCount / (float) mTotal) * 100));  
					}  
					return new String(baos.toByteArray());  
				}else{
					showToast(R.string.checkurl);
				}

			} catch (MalformedURLException e) {
				showToast(R.string.checkurl);
				e.printStackTrace();
			} catch (IOException e) {
				showToast(R.string.checknet);
				e.printStackTrace();
			}finally{
				conn.disconnect();
			}
		}
		
		return null;
	}

	
	private void showToast(final int info){		
		((Activity)mContext).runOnUiThread(new Runnable() {
	        @Override
	        public void run() {
	        Toast toast = Toast.makeText(mContext, info, Toast.LENGTH_SHORT);
	           toast.show();
	        }
	    });
	}

	/* 
	 * (non-Javadoc)
	 * Update Progress 
	 * @see android.os.AsyncTask#onProgressUpdate(java.lang.Object[])
	 */
	@Override
	protected void onProgressUpdate(Integer... values) {
				if(null != values){
		            mDialog.setProgress(values[0]);;
		            mDialog.show(); 
				}
	}
	
	/* (non-Javadoc)
	 * Download is finished
	 * @see android.os.AsyncTask#onPostExecute(java.lang.Object)
	 */
	@Override
	protected void onPostExecute(String result) {
		mDialog.dismiss();
		((MainActivity)mContext).fillView(result);

	}


	

}
