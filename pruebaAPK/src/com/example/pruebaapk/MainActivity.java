package com.example.pruebaapk;


import dao.ClienteDao;
import logica.Cliente;
import android.support.v7.app.ActionBarActivity;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {

	@SuppressLint("NewApi") @Override
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		final EditText txtCedula = (EditText) findViewById(R.id.txtCedulaLogin);
		final EditText txtPassword = (EditText) findViewById(R.id.txtPasswordLogin);
		Button btnIniciarSesion = (Button) findViewById(R.id.btnIniciarSesion);
		Button btnRegistrarse = (Button) findViewById(R.id.btnRegistrarse);

		btnIniciarSesion.setOnClickListener(new OnClickListener() {



			@TargetApi(Build.VERSION_CODES.GINGERBREAD) @SuppressLint("NewApi") 
			public void onClick(View arg0) {

				StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
				StrictMode.setThreadPolicy(policy); 

				Intent intent = new Intent(MainActivity.this, Principal.class);
				//				
				ClienteDao clienteDao = new ClienteDao();
				Cliente cliente = clienteDao.obtenCliente(Integer.parseInt
						(txtCedula.getText().toString()));

				if(cliente.getContrasena().equals(txtPassword.getText().toString())){
					Toast.makeText(MainActivity.this, "Bienvenido "+ 
							cliente.getNombre(), 
							Toast.LENGTH_SHORT).show();
					
					intent.putExtra("cedula", cliente.getCedula());
					
					startActivity(intent);
				}
			}



		});

		btnRegistrarse.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub

				Intent intent = new Intent(MainActivity.this,Registro.class);
				startActivity(intent);

			}
		});
	}
}
