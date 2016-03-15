package com.soto.nexxis;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import test.Droidlogin.library.JSONParser;

public class Register extends Activity implements OnClickListener{
     private EditText user, pass,nom;
    private Button  mRegister;
 
  // Progress Dialog
    private ProgressDialog pDialog;
 
    // JSON parser class
    JSONParser jsonParser = new JSONParser();
    
    //si lo trabajan de manera local en xxx.xxx.x.x va su ip local
   // private static final String REGISTER_URL = "http://xxx.xxx.x.x:1234/cas/register.php";
    
    //testing on Emulator:
    private static final String REGISTER_URL = "http://nexiss.webcindario.com/app/register.php";
        
    //ids
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_MESSAGE = "message";
 
 @Override
 protected void onCreate(Bundle savedInstanceState) {
  // TODO Auto-generated method stub
  super.onCreate(savedInstanceState);
  setContentView(R.layout.registrar);
  
  user = (EditText)findViewById(R.id.username);
  pass = (EditText)findViewById(R.id.password);
  nom = (EditText)findViewById(R.id.nombre);
  

  mRegister = (Button)findViewById(R.id.register);
  mRegister.setOnClickListener(this);
  
 }

 @Override
 public void onClick(View v) {
  // TODO Auto-generated method stub
  
    new CreateUser().execute();
  
 }
 
 class CreateUser extends AsyncTask<String, String, String> {

  
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(Register.this);
            pDialog.setMessage("Registrando Usuario...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }
  
  @Override
  protected String doInBackground(String... args) {
   // TODO Auto-generated method stub
    // Check for success tag
            int success;
            String username = user.getText().toString();
            String password = pass.getText().toString();
            String nombre = nom.getText().toString();
            try {
                // Building Parameters
                List params = new ArrayList();
                params.add(new BasicNameValuePair("username", username));
                params.add(new BasicNameValuePair("password", password));
                params.add(new BasicNameValuePair("nombre", nombre));
 
                Log.d("request!", "starting");
                
                //Posting user data to script 
                JSONObject json = jsonParser.makeHttpRequest(
                       REGISTER_URL, "POST", params);
 
                // full json response
                Log.d("Registering attempt", json.toString());
 
                // json success element
                success = json.getInt(TAG_SUCCESS);
                if (success == 1) {
                 Log.d("User Created!", json.toString());               
                 finish();
                 return json.getString(TAG_MESSAGE);
                }else{
                 Log.d("Registering Failure!", json.getString(TAG_MESSAGE));
                 return json.getString(TAG_MESSAGE);
                 
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
 
            return null;
   
  }
  
        protected void onPostExecute(String file_url) {
            // dismiss the dialog once product deleted
            pDialog.dismiss();
            if (file_url != null){
            	Vibrator vibrator =(Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        	    vibrator.vibrate(200);
        	    Toast toast1 = Toast.makeText(Register.this, file_url, Toast.LENGTH_SHORT);
         	    toast1.show();              	
            }
        } 
 }
}