package com.soto.nexxis;


import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;
/*PANTALLA DE BIENVENIDA*/
public class Admin extends MainActivity {
	String user;
	TextView txt_usr, logoff;
	 public void onCreate(Bundle savedInstanceState) {
		 
	       super.onCreate(savedInstanceState);
	       setContentView(R.layout.admin);
            
	        txt_usr= (TextView) findViewById(R.id.usr_name);
            logoff= (TextView) findViewById(R.id.logoff);          
            
            Bundle extras = getIntent().getExtras();
            //Obtenemos datos enviados en el intent.
            if (extras != null) {
         	   user  = extras.getString("user");//usuario
            }else{
         	   user="error";
         	   }
            
            txt_usr.setText(user);//cambiamos texto al nombre del usuario logueado
   		   	             
	        logoff.setOnClickListener(new View.OnClickListener(){
	         	
	         	public void onClick(View view){
    	        //'cerrar  sesion' nos regresa a la ventana anterior.      
	         		finish(); 
	         									}
	         	});	               
	 }
	 public void lanzarou(View view) {
	        Intent i = new Intent(this, Opuser.class );
	        startActivity(i);
	  }  
	 public void lanzares(View view) {
	        Intent j = new Intent(this, Reser.class );
	        startActivity(j);
	  } 
//Definimos que para cuando se presione la tecla BACK no volvamos para atras  	 
	 @Override
	 public boolean onKeyDown(int keyCode, KeyEvent event)  {
	     if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
	         // no hacemos nada.
	         return true;
	     }

	     return super.onKeyDown(keyCode, event);
	 }
	
	  
}
