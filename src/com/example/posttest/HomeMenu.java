package com.example.posttest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class HomeMenu extends Activity implements OnClickListener {

	Button preElec;
	Button postElec;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home_menu);
		preElec = (Button)findViewById(R.id.preElec);
		postElec = (Button)findViewById(R.id.postElec);

		preElec.setOnClickListener(this);
		postElec.setOnClickListener(this);


	}



	@Override
	public void onClick(View view) {

		switch(view.getId())
		{
		case R.id.preElec: Intent intent1 = new Intent(HomeMenu.this,Opinion_poll.class);
		startActivity(intent1);
		break;
		case R.id.postElec: Intent intent2 = new Intent(HomeMenu.this,ForumMenu.class);
		startActivity(intent2);
		break;
		}
	}
	// TODO Auto-generated method stub

}


