package com.example.posttest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.client.ClientProtocolException;
import org.json.JSONArray;
import org.json.JSONException;







import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

public class Opinion_poll extends Activity implements OnItemClickListener {

	
	ListView feeds;
	PreElectionsStrings obj=new PreElectionsStrings();
	
	List<Opinion_Poll_RowItem> rowItems;
	Opinion_Poll_CustomBaseAdapter adapter;
	Opinion_Poll_DatabaseHandler db;
	String cand_names[];
	String cand_party[];
	int progress[];
	int cand_id[];
	String phone[];
	String desp[];
	String assets[];
	String education[];
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.opinion_poll);
		
		feeds=(ListView)findViewById(R.id.listAllFeed);
		//db.addEvent();
//		List<Opinion_Poll_DayEventJson> event_1=db.getAllEvents();
//		
//		try
//		{
//			cand_names = new String[event_1.size()];
//			cand_party = new String[event_1.size()];
//			progress = new int[event_1.size()];
//			cand_id = new int[event_1.size()];
//			phone = new String[event_1.size()];
//			desp = new String[event_1.size()];
//			assets = new String[event_1.size()];
//			education = new String[event_1.size()];
			
//			int i=0;
//			for (Opinion_Poll_DayEventJson cn : event_1)
//			{
//
//				cand_names[i]=""+cn.getTitle();
//				cand_party[i]=""+cn.getTime();
//				progress[i]=""+cn.getLoc();
//				cand_id[i]=""+cn.getCat();
//				phone[i]=""+cn.getDesc();
//				desp[i]=""+cn.getRules();
//				assets[i]=""+cn.getCor1Name();
//				education[i]=""+cn.getCor2Name();
//				cord1_contact[i]=""+cn.getCor1phone();
//				cord2_contact[i]=""+cn.getCor2phone();
//				googleMaps[i] = ""+cn.getGoogleMapsURL();
//				i++;
//
//			}
//		}
		rowItems = new ArrayList<Opinion_Poll_RowItem>();
		for(int i=0;i<5;i++){
		Opinion_Poll_RowItem item = new Opinion_Poll_RowItem(obj.cand_name[i],obj.cand_party[i],(i*20));
		rowItems.add(item);
		
		
		}
		feeds.setOnItemClickListener(this);
		 adapter = new Opinion_Poll_CustomBaseAdapter(this, rowItems);
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
		
		Intent intent;
		SharedPreferences preferencesRef;
		Bundle bun = new Bundle();
		switch(position)
		{
		
		case 0: intent = new Intent(Opinion_poll.this,Cand_Prof.class);
		preferencesRef=getSharedPreferences("value",0);
		SharedPreferences.Editor editor = preferencesRef.edit();
		editor.putString("status", "0");
		editor.commit();						startActivity(intent);
						break;
		case 1: intent = new Intent(Opinion_poll.this,Cand_Prof.class);
		preferencesRef=getSharedPreferences("value",0);
		SharedPreferences.Editor editor1 = preferencesRef.edit();
		editor1.putString("status", "1");
		editor1.commit();
		startActivity(intent);
							
		

		break;

		case 2: intent = new Intent(Opinion_poll.this,Cand_Prof.class);
		preferencesRef=getSharedPreferences("value",0);
		SharedPreferences.Editor editor2 = preferencesRef.edit();
		editor2.putString("status", "2");
		editor2.commit();
		startActivity(intent);
		break;

		case 3: intent = new Intent(Opinion_poll.this,Cand_Prof.class);
		preferencesRef=getSharedPreferences("value",0);
		SharedPreferences.Editor editor3 = preferencesRef.edit();
		editor3.putString("status", "3");
		editor3.commit();
		startActivity(intent);
		break;

		case 4: intent = new Intent(Opinion_poll.this,Cand_Prof.class);
		preferencesRef=getSharedPreferences("value",0);
		SharedPreferences.Editor editor4 = preferencesRef.edit();
		editor4.putString("status", "4");
		editor4.commit();
		startActivity(intent);
		break;

		}
		// TODO Auto-generated method stub
		
	
	
/*	public class Read extends AsyncTask<String, Integer, String>
	{

		@Override
		protected String doInBackground(String... params)
		{
			/*try 
			{	

				json = getJSONArray();
				if(json != null)
				{
					db.deleteTable();
					db.createTable();
				}
				JSONArray eventsDay1 = json.getJSONArray("eventsday1");
				JSONArray eventsDay2 = json.getJSONArray("eventsday2");
				JSONArray eventsDay3 = json.getJSONArray("eventsday3");
				JSONArray eventsDay4 = json.getJSONArray("eventsday4");

				day1 = new Opinion_Poll_Profiles[eventsDay1.length()];

				int i =0;
				for(i = 0 ; i < day1.length ; i++)
				{
					day1[i] = new Opinion_Poll_Profiles(i,eventsDay1.getJSONObject(i),1);
					db.addEvent(day1[i]);

				}


				/*for( int i = 0 ; i < json.length(); i++)
						{
							JSONObject object = json.getJSONObject(i);
							sb.append(object.getString("title"));
							sb.append(" by ");
							sb.append(object.getString("by"));
							sb.append(" \n ");
							System.out.println(sb.toString());
						}
						return sb.toString();
			} 
			catch (ClientProtocolException e) 
			{

				e.printStackTrace();
			}
			catch (IOException e) 
			{

				e.printStackTrace();
			}
			catch (JSONException e) 
			{

				e.printStackTrace();
			}
			return null;
		}

		@Override
		protected void onPostExecute(String result) 
		{

			//setContentView(R.layout.loading);
			
		}*/



	}


}
