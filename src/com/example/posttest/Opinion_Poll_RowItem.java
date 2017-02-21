package com.example.posttest;

public class Opinion_Poll_RowItem {

	private String title;
	private String cand_party;
	int progress;

	public Opinion_Poll_RowItem(String title, String cand_party,int progress) {

		this.title = title;
		this.cand_party = cand_party;
		this.progress = progress;

	}



	public String getTitle() {
		return title;
	}
	public int getProgress(){
		return progress;
	}
	public String getParty()
	{
		return cand_party;
	}
	public int setProgress()
	{
		return progress;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	public void setParty(String cand_party)
	{
		this.cand_party = cand_party;
	}
	@Override
	public String toString() {
		return title + "\n" ;    }
}
