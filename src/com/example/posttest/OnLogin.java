package com.example.posttest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

public class OnLogin extends FragmentActivity 
{
	static String param;
	
	public static void getForumData(String forumName)
	{           
		HttpURLConnection connection;
		OutputStreamWriter request = null;
		

		URL url = null;   
		response = null;         
		String parameters = "forumName="+forumName.toLowerCase();   

		try
		{
			url = new URL("http://ieeevit.com/images/hack/forumInitialization.php");
			connection = (HttpURLConnection) url.openConnection();
			connection.setDoOutput(true);
			connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			connection.setRequestMethod("POST");    

			request = new OutputStreamWriter(connection.getOutputStream());
			request.write(parameters);
			request.flush();
			request.close();            
			String line = "";               
			InputStreamReader isr = new InputStreamReader(connection.getInputStream());
			BufferedReader reader = new BufferedReader(isr);
			StringBuilder sb = new StringBuilder();
			while ((line = reader.readLine()) != null)
			{
				sb.append(line);
			}
			// Response from server after login process will be stored in response variable.                
			response = sb.toString();
			// You can perform UI operations here          
			isr.close();
			reader.close();

		}
		catch(IOException e)
		{
			// Error
		}
	}
	
	
	ViewPager mViewPager;
	SectionsPagerAdapter mSectionsPagerAdapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.onlogin);
		mSectionsPagerAdapter = new SectionsPagerAdapter(
				getSupportFragmentManager());
		mViewPager = (ViewPager) findViewById(R.id.pager);
		mViewPager.setAdapter(mSectionsPagerAdapter);
	}

	/**
	 * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
	 * one of the sections/tabs/pages.
	 */
	public class SectionsPagerAdapter extends FragmentPagerAdapter {

		public SectionsPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		Bundle args;
		@Override
		public Fragment getItem(int position) {

			switch (position) {

			case 0:
				Fragment fragment = new DummySectionFragment1();
				args = new Bundle();
				args.putInt(DummySectionFragment1.ARG_SECTION_NUMBER, position + 1);
				fragment.setArguments(args);
				return fragment;

			case 1:
				Fragment fragment2 = new DummySectionFragment2();
				args = new Bundle();
				args.putInt(DummySectionFragment2.ARG_SECTION_NUMBER, position + 2);
				fragment2.setArguments(args);
				return fragment2;


			default:
				return null;
			}


		}


		@Override
		public int getCount() {
			// Show 3 total pages.
			return 2;
		}

		@Override
		public CharSequence getPageTitle(int position) {
			Locale l = Locale.getDefault();
			switch (position) {
			case 0:
				return "PRE-ELECTIONS";
			case 1:
				return "POST-ELECTIONS";

			}
			return null;
		}
	}
	public static class DummySectionFragment1 extends Fragment  {

		public static final String ARG_SECTION_NUMBER = "section_number";
		/** Called when the activity is first created. */

		public DummySectionFragment1()
		{

		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) 
		{
			View rootView = inflater.inflate(R.layout.pre, container, false);

			return rootView;
		}





	}
	public static class DummySectionFragment2 extends Fragment 
	{

		List<ForumMenuRowItem> rowItems;
		ForumMenuCustomBaseAdapter adapter;
		ListView feeds;

		public static final String ARG_SECTION_NUMBER = "section_number";

		public DummySectionFragment2()
		{

		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) 
		{
			View rootView = inflater.inflate(R.layout.forum_menu_activity_main, container, false);
			feeds=(ListView)rootView.findViewById(R.id.listAllFeed);

			rowItems = new ArrayList<ForumMenuRowItem>();
			for(int i=0;i<5;i++){
				switch(i)
				{
				case 0:
					ForumMenuRowItem item = new ForumMenuRowItem("Water");
					rowItems.add(item);
					break;
				case 1: ForumMenuRowItem item1 = new ForumMenuRowItem("Electrcity");
				rowItems.add(item1);
				break;
				case 2:
					ForumMenuRowItem item2 = new ForumMenuRowItem("Health");
					rowItems.add(item2);
					break;
				case 3:
					ForumMenuRowItem item3 = new ForumMenuRowItem("Security");
					rowItems.add(item3);
					break;

				}




			}
			feeds.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> arg0, View arg1,
						int position, long arg3)
				{
					param = "";
					// TODO Auto-generated method stub
					switch(position)
					{
					case 0:
						param = "water";
						break;

					case 1:
						param = "electricity";
						break;
					case 2:
						param = "health";
						break;
					case 3:
						param = "security";
						break;


					}
					new OnLogin().new SubmitToPHP().execute("sfsgs");



				}
			});
			adapter = new ForumMenuCustomBaseAdapter(this.getActivity(), rowItems);
			feeds.setAdapter(adapter);
			return rootView;

		}


	}



	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}


	



	public class SubmitToPHP extends AsyncTask<String, Integer, String>
	{


		@Override
		protected void onPreExecute()
		{

		}

		@Override
		protected String doInBackground(String... strings) {

			getForumData(param);
			return null;
		}


		@Override
		protected void onProgressUpdate(Integer... values)
		{

		}

		@Override
		protected void onPostExecute(String s)
		{
			System.out.println("Here");
			if(response.equals("failure") )
			{
				System.out.println("Here in");
				response = "Invalid credentials, please try again";
			}
			else
			{
				System.out.println("Here abc "+response+" "+response.length());
				try 
				{

					JSONObject json = new JSONObject(response);
					//String username = json.getString("username");
					//String userType = json.getString("user_type");
					//String sharedPrefKey = "userdetails";
					//SharedPreferences preferences;
					//preferences = getSharedPreferences(sharedPrefKey, 0);
					//SharedPreferences.Editor editor = preferences.edit();
					//editor.putString("username", username);
					//editor.putString("user_type", userType);
					//Toast.makeText(getActi, response, Toast.LENGTH_LONG).show();
				} 
				catch (JSONException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			//Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();
			Intent intent  = new Intent("android.intent.action.FORUM");
			startActivity(intent);
		}


	}

	static String response;

	

}

