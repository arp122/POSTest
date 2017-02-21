package com.example.posttest;

public class RowItemForumItem 
{


	String description;
	String username;
	String day;
	String time;

	public RowItemForumItem(String description, String username2, String day2) 
	{
		this.description = description;
		this.username = username2;
		this.day= day2;
		this.time=time;


	}



	public String getDescription() {
		return description;
	}
	public void setDesciption(String description) {
		this.description = description;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getDay() {
		return day;
	}
	public void setDay(String day) {
		this.day = day;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	@Override
	public String toString() {
		return description + "\n" ;    }
}
