package com.example.posttest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

public class Splash extends Activity 
{
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.splash);
		
		Thread t = new Thread()
		{
			public void run()
			{
				try
				{
					Thread.sleep(3000);
				}
				catch(InterruptedException IE)
				{
					IE.printStackTrace();
				}
				finally
				{
					Intent intent = new Intent(Splash.this,MainActivity.class);
					startActivity(intent);
					finish();
				}
			}
		};
		t.start();
	}
}
