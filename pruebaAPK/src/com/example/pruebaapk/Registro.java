package com.example.pruebaapk;

import dao.BancoDao;
import dao.ClienteDao;
import logica.Banco;
import logica.BancoId;
import logica.Cliente;
import logica.FechaYValoresRecibos;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.Toast;


public class Registro extends Activity{
	
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.registro_usuario);
		
		final EditText txtCedula = (EditText) findViewById(R.id.txtCedulaRegistro);
		final EditText txtNombre = (EditText) findViewById(R.id.txtNombreRegistro);
		final RadioGroup group = (RadioGroup) findViewById(R.id.radioGroup);
		final RadioButton btnMasculino = (RadioButton) findViewById(R.id.btnMasculino);
		final RadioButton btnFemenino = (RadioButton) findViewById(R.id.btnFemenino);
		final EditText txtPassword = (EditText) findViewById(R.id.txtPasswordRegistro);
		final EditText txtNombreBanco = (EditText) findViewById(R.id.txtNombreBanco);
		final EditText txtNumeroCuenta = (EditText) findViewById(R.id.txtNumeroCuenta);
		
		Button btnFinalizar = (Button) findViewById(R.id.btnFinalizar);
		
		btnFinalizar.setOnClickListener(new OnClickListener() {
			
			@TargetApi(Build.VERSION_CODES.GINGERBREAD)
			@SuppressLint("NewApi")
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
				StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.
						Builder().permitAll().build();
				StrictMode.setThreadPolicy(policy);
				
				ClienteDao clienteDao = new ClienteDao();
				String genero = "f";
				Intent intent = new Intent(Registro.this, Principal.class);
//				
				group.setOnCheckedChangeListener(new OnCheckedChangeListener() {
					
					@Override
					public void onCheckedChanged(RadioGroup group, int idButton) {
						// TODO Auto-generated method stub
						Toast.makeText(Registro.this, "id boton "+idButton, 
								Toast.LENGTH_LONG).show();
					}
				});
				
//				if(btnMasculino.isSelected()){
//					genero = "M";
//					Toast.makeText(Registro.this, "Btn Masculino", 
//							Toast.LENGTH_LONG).show();
//				}
//				else if(btnFemenino.isSelected()){
//					genero = "F";
//					Toast.makeText(Registro.this, "Btn Femenino", 
//							Toast.LENGTH_LONG).show();
//				}
				
				boolean resultadoAlmacenar = clienteDao.guardaCliente(new Cliente
						(Integer.parseInt(txtCedula.getText().toString()), 
								txtNombre.getText().toString(),
						genero, txtPassword.getText().toString()));
				
				if(resultadoAlmacenar == true){
					Toast.makeText(Registro.this, "Usuario agregado con �xito", 
							Toast.LENGTH_LONG).show();
					intent.putExtra("cedula", Integer.parseInt
							(txtCedula.getText().toString()));
					startActivity(intent);
				}else{
					Toast.makeText(Registro.this, "El usuario no ha podido ser agregado", 
							Toast.LENGTH_LONG).show();
				}
				
				BancoDao bancoDao = new BancoDao();
				
				FechaYValoresRecibos valor = new FechaYValoresRecibos();
				
				boolean resultadoBanco = bancoDao.guardaBanco(new Banco(new BancoId
						(Integer.parseInt(txtNumeroCuenta.getText().toString()),
								Integer.parseInt(txtCedula.getText().toString())),
								valor.obtenerValorRecibo(), 
								txtNombreBanco.getText().toString()));
				
				if(resultadoBanco == false){
					Toast.makeText(Registro.this, 
							"Para pagar si cuenta bancaria, por favor " +
							"ingrese a nuestra p�gina web", 
							Toast.LENGTH_LONG).show();
				}
				
			}
		});
	}
	
}
