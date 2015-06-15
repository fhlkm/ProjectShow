package com.example.download;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.example.phunware.activity.MainActivity;

public class DownloadTask extends AsyncTask<String, Integer, String> {
	
	
	ProgressDialog mypDialog=null;
	private Context mContext= null;
	public DownloadTask(Context mContext) {
		 mypDialog=new ProgressDialog(mContext);
		 this.mContext = mContext;
		 
	}

	@Override
	protected String doInBackground(String... params) {

		if(params!= null){
			HttpClient client = new DefaultHttpClient();  
			HttpGet get = new HttpGet(params[0]);  
			HttpResponse response;
			try {
				response = client.execute(get);
				if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {  
					HttpEntity entity = response.getEntity();  
					InputStream is = entity.getContent();  
					long total = entity.getContentLength();  
					ByteArrayOutputStream baos = new ByteArrayOutputStream();  
					byte[] buf = new byte[1024];  
					int count = 0;  
					int length = -1;  
					while ((length = is.read(buf)) != -1) {  
						baos.write(buf, 0, length);  
						count += length;  
						
						publishProgress((int) ((count / (float) total) * 100));  
						

					}  
					return new String(baos.toByteArray());  
				}  
			} catch (ClientProtocolException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  

		} 
		
		return null;
	}

	@Override
	protected void onProgressUpdate(Integer... values) {

		            mypDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
		            mypDialog.setTitle("Downloading");
		            mypDialog.setIcon(com.example.phunware.R.drawable.ic_launcher);
		            mypDialog.setProgress(values[0]);
		            mypDialog.setIndeterminate(false);
		            mypDialog.setCancelable(false);
		            mypDialog.show();    
	}
	
	@Override
	protected void onPostExecute(String result) {
		// TODO Auto-generated method stub
//		Log.i("info", result);
		mypDialog.dismiss();
		((MainActivity)mContext).fillView(result);

	}


	

	

}
