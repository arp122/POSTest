package com.example.posttest;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.util.Log;

public class Opinion_Poll_DatabaseHandler extends SQLiteOpenHelper 
{
	Context context;
	private static String makeTable = "CREATE TABLE IF NOT EXISTS main"+"" +
			" (id NUMBER, cand_name TEXT, phone TEXT, desp TEXT, party TEXT, assets TEXT,"+
			" progress NUMBER, education TEXT";
	private static final int DATABASE_VERSION = 1;
	private static final String DATABASE_NAME = "new1234";
	
	public Opinion_Poll_DatabaseHandler(Context context) 
	{

		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		this.context = context;
	}
	public void createTable()
	{
		SQLiteDatabase db = this.getWritableDatabase();
		Log.d("Query", makeTable);
		
		db.execSQL(makeTable);
		
	}
	@Override
	public void onCreate(SQLiteDatabase db)
	{
		
				
	}
	
	public void addEvent(Opinion_Poll_Profiles profiles)
	{
		/*if(dayEvent.id == 79 || dayEvent.id == 90)
			return;*/
		SQLiteDatabase db = this.getWritableDatabase();
		
		ContentValues values_main= new ContentValues();
		//Log.d("Here", profiles.id + profiles.title);
		values_main.put("id", profiles.id);
		values_main.put("cand_name", profiles.cand_name);
		values_main.put("phone", profiles.phone);
		values_main.put("desp", profiles.description);
		values_main.put("party", profiles.party);
		values_main.put("assets", profiles.assets);
		values_main.put("progress", profiles.progress);
		values_main.put("education", profiles.education);
			
		
		db.insert("main", null, values_main);
	}
	
	public List<Opinion_Poll_Profiles> getEvents() throws SQLException
	{
		String query = "SELECT * FROM main ";
		SQLiteDatabase db = this.getWritableDatabase();
		List<Opinion_Poll_Profiles> data =new ArrayList<Opinion_Poll_Profiles>();
		Cursor cursor = db.rawQuery(query, null);
		if(cursor.moveToFirst())
		{
			do
			{
				Opinion_Poll_Profiles event = new Opinion_Poll_Profiles();
				event.id = Integer.parseInt(cursor.getString(cursor.getColumnIndex("id")));
				//event.day = day;
				
				event.progress = cursor.getString(cursor.getColumnIndex("progress"));
				event.cand_name = cursor.getString(cursor.getColumnIndex("name"));
				event.party = cursor.getString(cursor.getColumnIndex("party"));
				event.description = cursor.getString(cursor.getColumnIndex("desp"));
				event.assets = cursor.getString(cursor.getColumnIndex("assests"));
				event.education = cursor.getString(cursor.getColumnIndex("education"));
				event.phone= cursor.getString(cursor.getColumnIndex("phone"));
				//event.cashPrizethird = cursor.getString(cursor.getColumnIndex("prize3"));
				
				data.add(event);
				
				
				
			}
			while(cursor.moveToNext());
		}
		cursor.close();
		return data;
	}
	
	public List<Opinion_Poll_DayEventJson> getAllEvents()
	{
		String query = "SELECT * FROM main";
		SQLiteDatabase db = this.getWritableDatabase();
		List<Opinion_Poll_DayEventJson> data =new ArrayList<Opinion_Poll_DayEventJson>();
		Cursor cursor = db.rawQuery(query, null);
		if(cursor.moveToFirst())
		{
			do
			{
				Opinion_Poll_DayEventJson event = new Opinion_Poll_DayEventJson();
				event.id = Integer.parseInt(cursor.getString(cursor.getColumnIndex("id")));
				event.category = cursor.getString(cursor.getColumnIndex("category"));
				event.title = cursor.getString(cursor.getColumnIndex("title"));
				event.time = cursor.getString(cursor.getColumnIndex("time"));
				event.desc = cursor.getString(cursor.getColumnIndex("desp"));
				event.eventLocation = cursor.getString(cursor.getColumnIndex("venue"));
				event.prize[0] = cursor.getString(cursor.getColumnIndex("prize1"));
				event.prize[1] = cursor.getString(cursor.getColumnIndex("prize2"));
				event.prize[2] = cursor.getString(cursor.getColumnIndex("prize3"));
				event.color = cursor.getString(cursor.getColumnIndex("color"));
				event.thumb = cursor.getString(cursor.getColumnIndex("thumb"));
				event.cover = cursor.getString(cursor.getColumnIndex("cover"));
				data.add(event);				
			}
			while(cursor.moveToNext());
		}
		cursor.close();
		return data;
	}
	
	public List<Opinion_Poll_DayEventJson> getEventsCate(int cid)
	{
		String query = "SELECT * FROM main WHERE cid="+cid;
		SQLiteDatabase db = this.getWritableDatabase();
		List<Opinion_Poll_DayEventJson> data =new ArrayList<Opinion_Poll_DayEventJson>();
		Cursor cursor = db.rawQuery(query, null);
		if(cursor.moveToFirst())
		{
			do
			{
				Opinion_Poll_DayEventJson event = new Opinion_Poll_DayEventJson();
				event.id = Integer.parseInt(cursor.getString(cursor.getColumnIndex("id")));
				event.category = cursor.getString(cursor.getColumnIndex("category"));
				event.title = cursor.getString(cursor.getColumnIndex("title"));
				event.time = cursor.getString(cursor.getColumnIndex("time"));
				event.desc = cursor.getString(cursor.getColumnIndex("desp"));
				event.eventLocation = cursor.getString(cursor.getColumnIndex("venue"));
				event.prize[0] = cursor.getString(cursor.getColumnIndex("prize1"));
				event.prize[1] = cursor.getString(cursor.getColumnIndex("prize2"));
				event.prize[2] = cursor.getString(cursor.getColumnIndex("prize3"));
				event.color = cursor.getString(cursor.getColumnIndex("color"));
				event.thumb = cursor.getString(cursor.getColumnIndex("thumb"));
				event.cover = cursor.getString(cursor.getColumnIndex("cover"));
				
				data.add(event);
			}
			while(cursor.moveToNext());
		}
		cursor.close();
		return data;
	}
	
	public List<Opinion_Poll_DayEventJson> getEvent(int day, int id)
	{
		String query = "SELECT * FROM main WHERE day="+day+" AND id="+id;
		SQLiteDatabase db = this.getWritableDatabase();
		List<Opinion_Poll_DayEventJson> data =new ArrayList<Opinion_Poll_DayEventJson>();
		Cursor cursor = db.rawQuery(query, null);
		if(cursor.moveToFirst())
		{
			do
			{
				Opinion_Poll_DayEventJson event = new Opinion_Poll_DayEventJson();
				event.id = Integer.parseInt(cursor.getString(cursor.getColumnIndex("id")));
				event.day = day;
				event.title = cursor.getString(cursor.getColumnIndex("title"));
				event.time = cursor.getString(cursor.getColumnIndex("time"));
				event.desc = cursor.getString(cursor.getColumnIndex("desp"));
				event.eventLocation = cursor.getString(cursor.getColumnIndex("venue"));
				event.prize[0] = cursor.getString(cursor.getColumnIndex("prize1"));
				event.prize[1] = cursor.getString(cursor.getColumnIndex("prize2"));
				event.prize[2] = cursor.getString(cursor.getColumnIndex("prize3"));
				event.color = cursor.getString(cursor.getColumnIndex("color"));
				event.thumb = cursor.getString(cursor.getColumnIndex("thumb"));
				event.cover = cursor.getString(cursor.getColumnIndex("cover"));
				
				data.add(event);
				
				
				
			}
			while(cursor.moveToNext());
		}
		cursor.close();
		return data;

	}
	
	public List<Opinion_Poll_DayEventJson> getEvent(int id)
	{
		String query = "SELECT * FROM main WHERE id="+id;
		SQLiteDatabase db = this.getWritableDatabase();
		List<Opinion_Poll_DayEventJson> data =new ArrayList<Opinion_Poll_DayEventJson>();
		Cursor cursor = db.rawQuery(query, null);
		if(cursor.moveToFirst())
		{
			do
			{
				Opinion_Poll_DayEventJson event = new Opinion_Poll_DayEventJson();
				event.id = Integer.parseInt(cursor.getString(cursor.getColumnIndex("id")));
				event.day = Integer.parseInt(cursor.getString(cursor.getColumnIndex("day")));
				event.title = cursor.getString(cursor.getColumnIndex("title"));
				event.time = cursor.getString(cursor.getColumnIndex("time"));
				event.desc = cursor.getString(cursor.getColumnIndex("desp"));
				event.eventLocation = cursor.getString(cursor.getColumnIndex("venue"));
				event.prize[0] = cursor.getString(cursor.getColumnIndex("prize1"));
				event.prize[1] = cursor.getString(cursor.getColumnIndex("prize2"));
				event.prize[2] = cursor.getString(cursor.getColumnIndex("prize3"));
				event.color = cursor.getString(cursor.getColumnIndex("color"));
				event.thumb = cursor.getString(cursor.getColumnIndex("thumb"));
				event.cover = cursor.getString(cursor.getColumnIndex("cover"));
				
				data.add(event);
				
				
				
			}
			while(cursor.moveToNext());
		}
		cursor.close();
		return data;

	}
	
	public Cursor listTable()
	{
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor table = db.rawQuery("SELECT name _id FROM sqlite_master WHERE type = 'table' ", null);
		if(table.moveToFirst())
		{
			Log.d("Table List", "Not Null " + table.getCount());
			do
			{
				Log.d("Table Name", table.getString(0));
			}
			while(table.moveToNext());
			
			return table;

			
		}
		table.close();
		return null;
	}
	public boolean exists(String table) {
	    try {
	    	SQLiteDatabase db = this.getWritableDatabase();
	         db.execSQL("SELECT * FROM main");
	         System.out.println("TRUE");
	         return true;
	    } catch (SQLException e) {
	    	System.out.println("FALSE");
	    	return false;
	    }
	}
	
	public void deleteTable()
	{
		SQLiteDatabase db = this.getWritableDatabase();
		String query1 = "DROP TABLE IF EXISTS main";
		
		db.execSQL(query1);
		
	}
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// Drop older table if existed
		db.execSQL("DROP TABLE IF EXISTS  main");
		// Create tables again
		onCreate(db);
	}
	


	
}
