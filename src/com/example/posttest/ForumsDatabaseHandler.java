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

public class ForumsDatabaseHandler extends SQLiteOpenHelper 
{
	Context context;
	private static String makeTable = "CREATE TABLE IF NOT EXISTS main"+"" +
			" (id NUMBER,username TEXT, desp TEXT, date TEXT,time TEXT type TEXT)";
	private static final int DATABASE_VERSION = 1;
	private static final String DATABASE_NAME = "new1234";
	
	public ForumsDatabaseHandler(Context context) 
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
	
	
	public void addEvent(String desc)
	{
		/*if(dayEvent.id == 79 || dayEvent.id == 90)
			return;*/
		SQLiteDatabase db = this.getWritableDatabase();
		
		ContentValues values_main= new ContentValues();
		//Log.d("Here", dayEvent.id + dayEvent.title);
		values_main.put("id", 0);
	//	values_main.put("day", dayEvent.day);
		//values_main.put("username", dayEvent.username);
		values_main.put("desp", desc);
		//values_main.put("time", dayEvent.time);
		db.insert("main", null, values_main);
	}
	
	public List<ForumItem> getEvents() throws SQLException
	{
		String query = "SELECT * FROM main";
		SQLiteDatabase db = this.getWritableDatabase();
		List<ForumItem> data =new ArrayList<ForumItem>();
		Cursor cursor = db.rawQuery(query, null);
		if(cursor.moveToFirst())
		{
			do
			{
				ForumItem event = new ForumItem();
			//	event.id = Integer.parseInt(cursor.getString(cursor.getColumnIndex("id")));
				//event.time = cursor.getString(cursor.getColumnIndex("time"));
				event.description = cursor.getString(cursor.getColumnIndex("desp"));
				//event.day = cursor.getString(cursor.getColumnIndex("day"));
				//event.username = cursor.getString(cursor.getColumnIndex("username"));
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
	
	public int getContactsCount() {
        String countQuery = "SELECT  * FROM main " ;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        //cursor.close();

        // return count
        return cursor.getCount();
    }

	
}
