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

public class Eliminar extends Activity implements OnClickListener{
     private EditText idd;
    private Button  mRegister;
 
  // Progress Dialog
    private ProgressDialog pDialog;
 
    // JSON parser class
    JSONParser jsonParser = new JSONParser();
    

    
    //testing on Emulator:
    private static final String REGISTER_URL = "http://nexiss.webcindario.com/app/eliminar.php";
        
    //ids
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_MESSAGE = "message";
 
 @Override
 protected void onCreate(Bundle savedInstanceState) {
  // TODO Auto-generated method stub
  super.onCreate(savedInstanceState);
  setContentView(R.layout.eliminar);
  
  idd = (EditText)findViewById(R.id.id);
  

  mRegister = (Button)findViewById(R.id.eliminar);
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
            pDialog = new ProgressDialog(Eliminar.this);
            pDialog.setMessage("Eliminando Usuario...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }
  
  @Override
  protected String doInBackground(String... args) {
   // TODO Auto-generated method stub
    // Check for success tag
            int success;
            String id = idd.getText().toString();
            try {
                // Building Parameters
                List params = new ArrayList();
                params.add(new BasicNameValuePair("id", id));
 
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
        	    Toast toast1 = Toast.makeText(Eliminar.this, file_url, Toast.LENGTH_SHORT);
         	    toast1.show();              	
            }
        } 
 }
}