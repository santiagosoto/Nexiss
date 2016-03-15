package com.soto.nexxis;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Opuser extends MainActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.opuser);
	}
	public void lanzarour(View view) {
        Intent i = new Intent(this, Register.class );
        startActivity(i);
	}
	public void lanzarouel(View view) {
        Intent i = new Intent(this, Eliminar.class );
        startActivity(i);
	}  
}
