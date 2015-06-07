package com.example.pruebaapk;


import android.support.v7.app.ActionBarActivity;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		final EditText txtCedula = (EditText) findViewById(R.id.editText1);
		EditText txtPassword = (EditText) findViewById(R.id.editText2);
		Button btnIniciarSesion = (Button) findViewById(R.id.button1);
		
		btnIniciarSesion.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Toast.makeText(MainActivity.this, "hola "+ txtCedula.getText().toString(), Toast.LENGTH_SHORT).show();
			}
		});
	}
}
