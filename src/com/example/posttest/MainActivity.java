package com.example.posttest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener
{

	private Button login,register;
	private volatile EditText username, password,newUsername,newEmail;
	CheckBox checkbox;
	String mUsername, mPassword;
	String response = "";
	TextView tvUser, tvPassword, tvNewUser, tvNewEmail;
	Color enabled,disabled;

	public void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		enabled = new Color();
		Color.rgb(255, 255, 255);

		disabled = new Color();
		Color.rgb(93,110 , 126);


		login = (Button) findViewById(R.id.bSubmit);
		login.setOnClickListener(this);

		register= (Button)findViewById(R.id.bRegister);
		register.setOnClickListener(this);

		username = (EditText) findViewById(R.id.etUser);
		password = (EditText) findViewById(R.id.etPassword);
		newUsername = (EditText)findViewById(R.id.etNewUser);
		newEmail = (EditText)findViewById(R.id.etNewEmail);




		tvUser = (TextView)findViewById(R.id.tvUser);
		tvPassword = (TextView)findViewById(R.id.tvPassword);
		tvNewUser = (TextView)findViewById(R.id.tvNewUser);
		tvNewEmail = (TextView)findViewById(R.id.tvNewEmail);

		username.setEnabled(true);
		tvUser.setEnabled(true);
		password.setEnabled(true);
		tvPassword.setEnabled(true);
		login.setEnabled(true);


		newUsername.setEnabled(false);
		tvNewUser.setEnabled(false);
		newEmail.setEnabled(false);
		tvNewEmail.setEnabled(false);
		register.setEnabled(false);

		checkbox = (CheckBox)findViewById(R.id.cbNewUser);
		checkbox.setOnCheckedChangeListener(new OnCheckedChangeListener() 
		{

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) 
			{
				if(isChecked == true)
				{
					username.setEnabled(false);
					tvUser.setEnabled(false);
					password.setEnabled(false);
					tvPassword.setEnabled(false);
					login.setEnabled(false);

					newUsername.setEnabled(true);
					tvNewUser.setEnabled(true);
					newEmail.setEnabled(true);
					tvNewEmail.setEnabled(true);
					register.setEnabled(true);
				}
				else
				{
					username.setEnabled(true);
					tvUser.setEnabled(true);
					password.setEnabled(true);
					tvPassword.setEnabled(true);
					login.setEnabled(true);


					newUsername.setEnabled(false);
					tvNewUser.setEnabled(false);
					newEmail.setEnabled(false);
					tvNewEmail.setEnabled(false);
					register.setEnabled(false);
				}

			}
		});




	}

	protected void tryLogin(String mUsername, String mPassword)
	{           
		HttpURLConnection connection;
		OutputStreamWriter request = null;

		URL url = null;   
		response = null;         
		String parameters = "username="+mUsername+"&password="+mPassword;   

		try
		{
			url = new URL("http://ieeevit.com/images/hack/login.php");
			connection = (HttpURLConnection) url.openConnection();
			connection.setDoOutput(true);
			connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			connection.setRequestMethod("POST");    

			request = new OutputStreamWriter(connection.getOutputStream());
			request.write(parameters);
			request.flush();
			request.close();            
			String line = "";               
			InputStreamReader isr = new InputStreamReader(connection.getInputStream());
			BufferedReader reader = new BufferedReader(isr);
			StringBuilder sb = new StringBuilder();
			while ((line = reader.readLine()) != null)
			{
				sb.append(line);
			}
			// Response from server after login process will be stored in response variable.                
			response = sb.toString();
			// You can perform UI operations here          
			isr.close();
			reader.close();

		}
		catch(IOException e)
		{
			// Error
		}
	}


	protected void tryRegister(String mUsername, String mPassword)
	{           
		HttpURLConnection connection;
		OutputStreamWriter request = null;

		URL url = null;   
		response = null;         
		String parameters = "username="+mUsername+"&email="+mPassword;   

		try
		{
			url = new URL("http://ieeevit.com/images/hack/addUser.php");
			connection = (HttpURLConnection) url.openConnection();
			connection.setDoOutput(true);
			connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			connection.setRequestMethod("POST");    

			request = new OutputStreamWriter(connection.getOutputStream());
			request.write(parameters);
			request.flush();
			request.close();            
			String line = "";               
			InputStreamReader isr = new InputStreamReader(connection.getInputStream());
			BufferedReader reader = new BufferedReader(isr);
			StringBuilder sb = new StringBuilder();
			while ((line = reader.readLine()) != null)
			{
				sb.append(line);
			}
			// Response from server after login process will be stored in response variable.                
			response = sb.toString();
			// You can perform UI operations here          
			isr.close();
			reader.close();

		}
		catch(IOException e)
		{
			// Error
		}
	}


	@Override
	public void onClick(View v)
	{
		switch(v.getId())
		{
		case R.id.bSubmit:
			mUsername = username.getText().toString();
			mPassword = password.getText().toString();
			String blah = "xyz";
			new SubmitToPHP().execute(blah);
			break;

		case R.id.bRegister:
			mUsername = newUsername.getText().toString();
			mPassword = newEmail.getText().toString();
			new RegisterWithPHP().execute("Random");
			break;
		}


	}


	public class SubmitToPHP extends AsyncTask<String, Integer, String>
	{


		@Override
		protected void onPreExecute()
		{

		}

		@Override
		protected String doInBackground(String... strings) {

			System.out.print("I'm here");
			if(mUsername.length() != 0 && mPassword.length() != 0)
			{
				tryLogin(mUsername, mPassword);
			}
			else
			{
				response = "Please ensure all fields are entered";
			}
			return null;
		}


		@Override
		protected void onProgressUpdate(Integer... values)
		{

		}

		@Override
		protected void onPostExecute(String s)
		{
			System.out.println("Here");
			if(response.equals("failure") )
			{
				System.out.println("Here in");
				response = "Invalid credentials, please try again";
			}
			else
			{
				System.out.println("Here abc "+response+" "+response.length());
				try 
				{

					JSONObject json = new JSONObject(response);
					String username = json.getString("username");
					String userType = json.getString("user_type");
					String sharedPrefKey = "userdetails";
					SharedPreferences preferences;
					preferences = getSharedPreferences(sharedPrefKey, 0);
					SharedPreferences.Editor editor = preferences.edit();
					editor.putString("username", username);
					editor.putString("user_type", userType);
					editor.commit();
					
					Intent intent = new Intent(MainActivity.this,HomeMenu.class);
					startActivity(intent);
					Toast.makeText(getApplicationContext(), username+userType, Toast.LENGTH_LONG).show();
				} 
				catch (JSONException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();
		}


	}

	public class RegisterWithPHP extends AsyncTask<String, Integer, String>
	{


		@Override
		protected void onPreExecute()
		{

		}

		@Override
		protected String doInBackground(String... strings) {

			if(mUsername.length() != 0 && mPassword.length() != 0)
			{
				tryRegister(mUsername, mPassword);
			}
			else
			{
				response = "Please ensure all fields are entered";
			}
			return null;
		}


		@Override
		protected void onProgressUpdate(Integer... values)
		{

		}

		@Override
		protected void onPostExecute(String s)
		{
			//Toast.makeText(getApplicationContext(), response, Toast.LENGTH_SHORT).show();

		}


	}


}


