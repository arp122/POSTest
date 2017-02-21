package com.example.posttest;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

public class Cand_Prof extends Activity implements OnClickListener{

	ImageView img;
	TextView name;
	TextView phone;
	TextView party;
	TextView education;
	TextView assets;
	TextView desc;
	Intent intent;
	int n;
	SharedPreferences preferences;
	String str="value";
	
	String s;
	Bundle bun = new Bundle();
	PreElectionsStrings obj = new PreElectionsStrings();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.cad_profile);
		img = (ImageView)findViewById(R.id.thumbsup);
		name = (TextView)findViewById(R.id.textname);
		phone = (TextView)findViewById(R.id.textphone);
		party = (TextView)findViewById(R.id.textparty);
		education = (TextView)findViewById(R.id.texteducation);
		assets = (TextView)findViewById(R.id.textassets);
		desc = (TextView)findViewById(R.id.textdesc);
		intent = getIntent();
		bun = intent.getExtras();
		preferences = getSharedPreferences(str,0);
		s=preferences.getString("status","0");
		
		try
		{
		 n = Integer.parseInt(s);
		}
		catch(NumberFormatException e)
		{
			
		}
		img.setOnClickListener(this);
		
		
		
		name.setText(obj.cand_name[n]);
		phone.setText(obj.cand_phone[n]);
		party.setText(obj.cand_party[n]);
		education.setText(obj.education[n]);
		assets.setText(obj.assets[n]);
		desc.setText(obj.desc[n]);
		}
	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		img.setImageResource(R.drawable.thumbsupblack);
		
	}
	
	

}
