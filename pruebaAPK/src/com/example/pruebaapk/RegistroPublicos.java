package com.example.pruebaapk;


import java.util.Date;

import logica.Banco;
import logica.BancoId;
import logica.EGas;
import logica.EGasId;
import logica.FechaYValoresRecibos;
import dao.BancoDao;
import dao.GasDao;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegistroPublicos extends Activity {
	
	private EditText txtReciboGas = null;
	private EditText txtReciboLuz = null;
	private EditText txtReciboAgua = null;
	private EditText txtReciboInternet = null;
	private EditText txtReciboTelefono = null;
	private int cedula = 0;
	private FechaYValoresRecibos fechaYsaldo;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.registro_recibos_publicos);
		
		txtReciboGas = (EditText) findViewById(R.id.txtNReciboGas);
		txtReciboLuz = (EditText) findViewById(R.id.txtReciboLuz);
		txtReciboAgua = (EditText) findViewById(R.id.txtReciboAgua);
		txtReciboInternet = (EditText) findViewById(R.id.txtReciboInternet);
		txtReciboTelefono = (EditText) findViewById(R.id.txtReciboTelefono);
		fechaYsaldo = new FechaYValoresRecibos();
		
		Button btnFinalizar = (Button) findViewById(R.id.btnFinalizarRegistro);
		
		btnFinalizar.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(RegistroPublicos.this, 
						RegistroPublicos.class);
				startActivity(intent);
				
				cedula = getIntent().getExtras().getInt("cedula");
			}
		});

	}
	
	
	public boolean registrarGas(){

		boolean registro = false ;

		if(txtReciboGas.getText().toString().equalsIgnoreCase("")){

			registro = false;
		}

		else{
			
			GasDao gasGao = new GasDao();

			double saldo = fechaYsaldo.obtenerValorRecibo();

			Date ahora = new Date();
			
			boolean resultadoAlmacenarGas = gasGao.guardarGas(new EGas
					(new EGasId(Integer.parseInt(txtReciboGas.getText().toString()),
							cedula), fechaYsaldo.obtenerFechaMes(new 
									java.sql.Date(ahora.getTime())), saldo));
			
			
			if(resultadoAlmacenarGas == true){
				registro = true;
			}
		}
		return registro;
	}
	
//	public boolean registrarLuz(){
//
//		boolean registro = false ;
//
//		if(txtReciboLuz.getText().toString().equalsIgnoreCase("")){
//
//			registro = false;
//		}
//
//		else{
//			
//			GasDao gasGao = new GasDao();
//
//			double saldo = fechaYsaldo.obtenerValorRecibo();
//
//			Date ahora = new Date();
//			
//			boolean resultadoAlmacenarGas = gasGao.guardarGas(new EGas
//					(new EGasId(Integer.parseInt(txtReciboGas.getText().toString()),
//							cedula), fechaYsaldo.obtenerFechaMes(new 
//									java.sql.Date(ahora.getTime())), saldo));
//			
//			
//			if(resultadoAlmacenarGas == true){
//				registro = true;
//			}
//		}
//		return registro;
//	}
}
