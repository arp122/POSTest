package com.example.posttest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class ForumMenu extends Activity implements OnItemClickListener 
{

	ListView feeds;
	
	ForumItem[] items;

	List<ForumMenuRowItem> rowItems;
	ForumMenuCustomBaseAdapter adapter;
	
	
	

	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.forum_menu_activity_main);
		feeds=(ListView)findViewById(R.id.listAllFeed);

		rowItems = new ArrayList<ForumMenuRowItem>();
		for(int i=0;i<5;i++)
		{
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
		feeds.setOnItemClickListener(this);
		adapter = new ForumMenuCustomBaseAdapter(this, rowItems);
		feeds.setAdapter(adapter);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
		// TODO Auto-generated method stub
		Intent intent;
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
		
		new SubmitToPHP().execute(param);



	}
	
	
	protected void tryRegister(String forumName)
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
			try 
			{
				JSONObject obj = new JSONObject(response);
				JSONArray array = obj.getJSONArray("comments");
				items = new ForumItem[array.length()];
				for(int i = 0 ; i < array.length() ; i++)
				{
					items[i] = new ForumItem(0,array.getJSONObject(i),param);
				}
			}
			catch (JSONException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			String sharedPrefKey = "userdetails";
			SharedPreferences preferences;
			preferences = getSharedPreferences(sharedPrefKey, 0);
			SharedPreferences.Editor editor = preferences.edit();
			editor.putString("forum", param);
			editor.commit();
			
			Intent intent  = new Intent("android.intent.action.FORUM");
			Bundle bun = new Bundle();
			bun.putString("JSONData", response);
			intent.putExtras(bun);
			startActivity(intent);
		}
	}

	static String response;
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
			response = sb.toString();        
			isr.close();
			reader.close();

		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}

	public class AskRating extends AsyncTask<String, Integer, String>
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
			try 
			{
				JSONObject obj = new JSONObject(response);
				JSONArray array = obj.getJSONArray("comments");
				items = new ForumItem[array.length()];
				for(int i = 0 ; i < array.length() ; i++)
				{
					items[i] = new ForumItem(0,array.getJSONObject(i),param);
				}
			}
			catch (JSONException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			try
			{
				
				JSONObject obj = new JSONObject(response);
				JSONArray array = obj.getJSONArray("rating");
				String str1,str2,str3,str4;
				str1 = array.getString(0);
				str2 = array.getString(1);
				str3 = array.getString(2);
				str4 = array.getString(3);
				
				String sharedPrefKey = "userdetails";
				SharedPreferences preferences;
				preferences = getSharedPreferences(sharedPrefKey, 0);
				SharedPreferences.Editor editor = preferences.edit();
				editor.putString("rating1", str1);
				editor.putString("rating2", str2);
				editor.putString("rating3", str3);
				editor.putString("rating4", str4);
				//water,elecricity,security,health
				editor.commit();

			}
			catch (JSONException e) 
			{		
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
						
			Intent intent  = new Intent("android.intent.action.FORUM");
			Bundle bun = new Bundle();
			bun.putString("JSONData", response);
			intent.putExtras(bun);
			startActivity(intent);
		}
	}

	
	public static void getRating(String forumName)
	{           
		HttpURLConnection connection;
		OutputStreamWriter request = null;
		

		URL url = null;   
		response = null;         
		String parameters = "forumName="+forumName.toLowerCase();   

		try
		{
			url = new URL("http://ieeevit.com/images/hack/rate.php");
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

	
	
}
