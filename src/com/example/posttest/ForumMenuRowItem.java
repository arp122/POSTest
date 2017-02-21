package com.example.posttest;

public class ForumMenuRowItem {

	private String title;
	
	public ForumMenuRowItem(String title) {

		this.title = title;
			}



	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	@Override
	public String toString() {
		return title + "\n" ;    }
}
