package com.example.posttest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class ForumItem 
{
	int id;
	String description;
	String date;
	String time;
	String username;
	String forumname;
	String responsible;
	
	
	
	private JSONObject data;
	public ForumItem()
	{
		
	}
	public ForumItem(int slno, JSONObject jsonData,String forumName)
	{	
		this.data= jsonData;
		
		id = slno + 1;
		try 
		{
			description = jsonData.getString("comment");
			username = jsonData.getString("username");
			date = jsonData.getString("date");
			this.forumname = forumName;
			responsible = jsonData.getString("responsible");
			
			System.out.println("Recived data : \n"+description+" \n"+username+" \n"+date+" \n"+this.forumname+"\n"+responsible+"\n");
		}
		catch (JSONException e) 
		{
			e.printStackTrace();
		}
	}
	public String getDescription(){
		return this.description;
	}
	public String getTime(){
		return this.time;
	}
	public String getUsername(){
		return this.username;
	}
	public String getDay(){
		return this.date;
	}
	public String getForumname()
	{
		return this.forumname;
	}
	
	public void checkWithLog()
	{
		Log.e("id", ""+id);
		Log.e("title", ""+description);
		Log.e("day", ""+date);
		Log.e("time", time);
		Log.e("Username",""+username);	
	}
	public CharSequence getResp() {
		// TODO Auto-generated method stub
		return this.responsible;
	}
}
