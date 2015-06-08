package com.example.pruebaapk;

import dao.ClienteDao;
import logica.Cliente;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;


public class Registro extends Activity{
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.registro_usuario);
		
		final EditText txtCedula = (EditText) findViewById(R.id.txtCedulaRegistro);
		final EditText txtNombre = (EditText) findViewById(R.id.txtNombreRegistro);
		final RadioButton btnMasculino = (RadioButton) findViewById(R.id.btnMasculino);
		final RadioButton btnFemenino = (RadioButton) findViewById(R.id.btnFemenino);
		final EditText txtPassword = (EditText) findViewById(R.id.txtPasswordRegistro);
		
		Button btnSiguiente = (Button) findViewById(R.id.btnSiguiente);
		
		btnSiguiente.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				ClienteDao clienteDao = new ClienteDao();
				String genero = null;
//				
				if(btnMasculino.isSelected()){
					genero = "M";
				}
				else if(btnFemenino.isSelected()){
					genero = "F";
				}
				
				boolean resultadoAlamacenar = clienteDao.guardaCliente(new Cliente
						(Integer.parseInt(txtCedula.getText().toString()), 
								txtNombre.getText().toString(),
						genero, txtPassword.getText().toString()));
				
				if(resultadoAlamacenar == true){
					Toast.makeText(Registro.this, "Usuario agregado con éxito", 
							Toast.LENGTH_LONG).show();
				}else{
					Toast.makeText(Registro.this, "El usuario no ha podido ser agregado", 
							Toast.LENGTH_LONG).show();
				}
				
			}
		});
	}

}
