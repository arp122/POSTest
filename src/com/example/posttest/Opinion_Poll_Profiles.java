package com.example.posttest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class Opinion_Poll_Profiles 
{
	int id;
	String cand_name;
	
	String phone;
	String description;
	String party;
	String assets;
	String progress;
	String education;
	private JSONObject data;
	
	public Opinion_Poll_Profiles()
	{
		
	}
	public Opinion_Poll_Profiles(int slno, JSONObject jsonData,int dayParam)
	{	this.data= jsonData;
		
		/*try 
		{
			//id = jsonData.getString("id");
			id= slno+1;
					
		}
		catch (JSONException e) 
		{
			e.printStackTrace();
		}*/
	}
	public String getcadnamce(){
		return this.cand_name;
	}
	
	public String phone()
	{
		return this.phone;
	}
	public String getProgress(){
		return this.progress;
	}
	public String getDesc(){
		return this.description;
	}
	public String getParty()
	{
		return this.party;
	}
	public String getAssets()
	{
		return this.assets;
	}
	
	public String getEducation()
	{
		return this.education;
	}
}
