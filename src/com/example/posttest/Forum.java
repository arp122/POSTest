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
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class Forum extends Activity 
{
	EditText Desc;
	ListView feeds;
	ArrayList<RowItemForumItem> rowItems;
	SimpleAdapter adpt=null;
	ForumCustomBaseAdapter adapter=null;
	TextView responsible;
	static String comment;
	static String usernameToSend;
	static String forumNameToSend;;

	String f1,f2,f3,f4;

	void getCurrent()
	{
		String sharedPrefKey = "userdetails";
		SharedPreferences preferences;
		preferences = getSharedPreferences(sharedPrefKey, 0);
		f1=preferences.getString("rating1", "0");
		f2=preferences.getString("rating2", "0");
		f3=preferences.getString("rating3", "0");
		f4=preferences.getString("rating4", "0");
	}


	public String[] time=new String[50];
	public String[] description=new String[50];
	public String[] username=new String[50];
	public String[] day=new String[50];

	int j=0,i;
	ForumItem details;
	ForumsDatabaseHandler db;
	List<ForumItem> event_4;
	Intent myintent;
	String responsejson=null;
	ForumItem items[];
	ForumItem resp;
	ForumCustomBaseAdapter adapter1;
	JSONArray array;
	float f = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.forum);
		Desc=(EditText)findViewById(R.id.editTextDesc);
		feeds=(ListView)findViewById(R.id.listAllFeed);
		db=new ForumsDatabaseHandler(this);
		db.createTable();
		rowItems = new ArrayList<RowItemForumItem>();
		adapter=new ForumCustomBaseAdapter(this,rowItems);
		responsible=(TextView)findViewById(R.id.textResp);
		getCurrent();


		myintent = getIntent();
		Bundle bun = myintent.getExtras();
		response=bun.getString("JSONData");

		try 
		{
			JSONObject obj = new JSONObject(response);
			array = obj.getJSONArray("comments");
			items = new ForumItem[array.length()];
			for(int i = 0 ; i < array.length() ; i++)
			{
				items[i] = new ForumItem(0,array.getJSONObject(i),"water");
			}
		}
		catch (JSONException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(i=0;i<items.length;i++){
			username[i]=items[i].getUsername();
			description[i]=items[i].getDescription();
			day[i]=items[i].getDay();
		}
		responsible.setText(items[0].getResp());
		rowItems = new ArrayList<RowItemForumItem>();
		for (int i = 0; i<items.length; i++) {
			RowItemForumItem item = new RowItemForumItem( description[i],username[i],day[i]);
			rowItems.add(item);
		}
		adapter1 = new ForumCustomBaseAdapter(this, rowItems);
		feeds.setAdapter(adapter1);
	}





	public void Rating(View v){

		final RatingBar rt;
		Button btrate;
		final Dialog d = new Dialog(this);
		f = 0;
		d.requestWindowFeature(Window.FEATURE_NO_TITLE);
		d.setContentView(R.layout.dialog);
		d.show();
		rt=((RatingBar)d.findViewById(R.id.ratingBar1));
		btrate=	((Button)d.findViewById(R.id.btnrate));
		TextView tv = ((TextView)d.findViewById(R.id.tvCurrentRating));
		String str = "";
		String sharedPrefKey = "userdetails";
		SharedPreferences preferences;
		preferences = getSharedPreferences(sharedPrefKey, 0);
		comment = Desc.getText().toString();
		usernameToSend = preferences.getString("username", "error");
		forumNameToSend = preferences.getString("forum", "error");
		if(forumNameToSend.equals("water"))
		{
			str = f1;
		}
		if(forumNameToSend.equals("electricity"))
		{
			str = f2;
		}
		if(forumNameToSend.equals("health"))
		{
			str = f3;
		}
		if(forumNameToSend.equals("security"))
		{
			str = f4;
		}
		
		tv.setText(tv.getText() + "\n" + "Current Rating : "+str);
		btrate.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				float rate=rt.getRating();
				f = rate;
				//Toast.makeText(getApplicationContext(), ""+rate, 2000).show();
				d.dismiss();

				new SendRating().execute("hello");
			}
		});
	}

	public void Post(View v)
	{

		String desc="hello this is arpit";
		db.addEvent(desc);
		event_4=db.getEvents();

		username[i]="null";//items[i].getUsername();//shared prefs
		description[i]=Desc.getText().toString();
		day[i]="Now";

		//rowItems = new ArrayList<RowItemForumItem>();

		RowItemForumItem item = new RowItemForumItem( description[i],username[i],day[i]);
		rowItems.add(item);

		ForumCustomBaseAdapter adapter = new ForumCustomBaseAdapter(this, rowItems);
		feeds.setAdapter(adapter);
		//RowItem item = new RowItem(Desc.getText().toString());
		i++;




		//RowItem item = new RowItem(Desc.getText().toString());

		/*	titles[j]=Desc.getText().toString();
		RowItem item = new RowItem(titles[j]);


				rowItems.add(item);

			CustomBaseAdapter adapter = new CustomBaseAdapter(this, rowItems);
			feeds.setAdapter(adapter);

	j++;*/

		String sharedPrefKey = "userdetails";
		SharedPreferences preferences;
		preferences = getSharedPreferences(sharedPrefKey, 0);
		comment = Desc.getText().toString();
		usernameToSend = preferences.getString("username", "error");
		forumNameToSend = preferences.getString("forum", "error");
		new SubmitToPHP().execute("random");




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

			sendData(param);
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
			Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();
			/*try 
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
			}*/
		}


	}

	static String response;
	static String param;

	public static void sendData(String forumName)
	{           
		HttpURLConnection connection;
		OutputStreamWriter request = null;


		URL url = null;   
		response = null;         
		String parameters = "forumName="+forumNameToSend.toLowerCase()+"&comment="+comment+
				"&username="+usernameToSend;   

		try
		{
			url = new URL("http://ieeevit.com/images/hack/forumAdd.php");
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


	public class SendRating extends AsyncTask<String, Integer, String>
	{


		@Override
		protected void onPreExecute()
		{

		}

		@Override
		protected String doInBackground(String... strings) {

			sendRating(param);
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
			Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();
			/*try 
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
		}*/
		}


	}

	public void sendRating(String param)
	{
		HttpURLConnection connection;
		OutputStreamWriter request = null;


		URL url = null;   
		response = null;       
		String sharedPrefKey = "userdetails";
		SharedPreferences preferences;
		preferences = getSharedPreferences(sharedPrefKey, 0);
		comment = Desc.getText().toString();
		usernameToSend = preferences.getString("username", "error");
		forumNameToSend = preferences.getString("forum", "error");

		String parameters = "username="+usernameToSend+"&"+forumNameToSend+"="+f;
		//String parameters = "forumName="+forumNameToSend.toLowerCase()+"&comment="+comment+
		//"&username="+usernameToSend;   

		try
		{
			url = new URL("http://ieeevit.com/images/hack/rating.php");
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
