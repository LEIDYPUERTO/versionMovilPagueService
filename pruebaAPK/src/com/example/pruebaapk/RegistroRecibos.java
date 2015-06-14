package com.example.pruebaapk;

import java.text.SimpleDateFormat;
import java.util.Date;

import logica.Banco;
import logica.BancoId;
import logica.CamaraComercio;
import logica.Cliente;
import logica.FechaYValoresRecibos;
import logica.ImpuestoPredial;
import logica.Soat;
import dao.BancoDao;
import dao.CamaraComercioDao;
import dao.ClienteDao;
import dao.PredialDao;
import dao.SoatDao;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegistroRecibos extends Activity {

	private EditText txtNombreBanco = null;
	private EditText txtNumeroCuenta = null;
	private EditText txtNIT = null;
	private EditText txtPredial = null;
	private EditText txtPlaca = null;
	private FechaYValoresRecibos fechaYsaldo = null;
	private Intent intent = null;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.registro_recibos);

		txtNombreBanco = (EditText) findViewById
				(R.id.txtNombreBanco);
		txtNumeroCuenta = (EditText) findViewById
				(R.id.txtNumeroCuenta);
		txtNIT = (EditText) findViewById
				(R.id.txtNIT);
		txtPredial = (EditText) findViewById
				(R.id.txtPredial);
		txtPlaca = (EditText) findViewById
				(R.id.txtPlaca);

		Button btnSiguiente = (Button) findViewById(R.id.btnServiciosPublicos);

		fechaYsaldo = new FechaYValoresRecibos();

		btnSiguiente.setOnClickListener(new OnClickListener() {

			@TargetApi(Build.VERSION_CODES.GINGERBREAD)
			@SuppressLint("NewApi")
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.
						Builder().permitAll().build();
				StrictMode.setThreadPolicy(policy);

				intent = new Intent(RegistroRecibos.this, 
						RegistroPublicos.class);

				registrarBanco();
				registrarComercio();
				registrarPredio();
				registrarVehiculo();
				startActivity(intent);
			}
		});
	}

	public boolean registrarBanco(){

		boolean registro = false ;

		if(txtNombreBanco.getText().toString().equalsIgnoreCase("")&&
				txtNumeroCuenta.getText().toString().equalsIgnoreCase("")){

			Toast.makeText(RegistroRecibos.this, "Para realizar el" +
					" pago sin cuenta de banco por favor ingrese " +
					"a nuestra página web", Toast.LENGTH_LONG).show();

			registro = false;
		}

		else{
			BancoDao bancoDao = new BancoDao();	

			double saldo = fechaYsaldo.obtenerValorRecibo();

			int cedula = getIntent().getExtras().getInt("cedula");

			boolean resultadoAlmacenarBanco = bancoDao.guardaBanco(
					new Banco(new BancoId(Integer.parseInt(txtNumeroCuenta.
							getText().toString()),
							cedula),
							saldo,
							txtNombreBanco.getText().toString()));
			
			intent.putExtra("cedula", cedula);
			
			if(resultadoAlmacenarBanco == true){
				registro = true;
//				startActivity(intent);
			}
		}
		return registro;
	}

	public boolean registrarComercio(){

		boolean registro = false ;

		if(txtNIT.getText().toString().equalsIgnoreCase("")){
			registro = false;
		}

		else{
			CamaraComercioDao comercio = new CamaraComercioDao();

			int saldo = (int) fechaYsaldo.obtenerValorRecibo();

			Date ahora = new Date();

			boolean resultadoAlmacenarComercio = comercio.guardaComercio(new 
					CamaraComercio(Integer.parseInt(txtNIT.getText().toString()),
							saldo, fechaYsaldo.obtenerFechaAnual(
									new java.sql.Date(ahora.getTime())), 
									fechaYsaldo.obtenerValorReciboPredialComercio
									(saldo)));

			if(resultadoAlmacenarComercio == true){
				registro = true;
//				startActivity(intent);
			}
		}
		return registro;
	}

	public boolean registrarPredio(){

		boolean registro = false ;

		if(txtPredial.getText().toString().equalsIgnoreCase("")){
			registro = false;
		}

		else{

			PredialDao predialDao = new PredialDao();

			int saldo = (int) fechaYsaldo.obtenerValorRecibo();

			Date ahora = new Date();

			boolean resultadoAlmacenarPredio = predialDao.guardaPredial(new 
					ImpuestoPredial(Integer.parseInt(txtPredial.getText().toString()),
							fechaYsaldo.obtenerFechaAnual
							(new java.sql.Date(ahora.getTime())), 
							saldo, 
							fechaYsaldo.obtenerValorReciboPredialComercio(saldo)));

			if(resultadoAlmacenarPredio == true){
				registro = true;
//				startActivity(intent);
			}
		}
		return registro;
	}

	public boolean registrarVehiculo(){

		boolean registro = false ;

		if(txtPlaca.getText().toString().equalsIgnoreCase("")){
			registro = false;
		}

		else{

			SoatDao soat = new SoatDao();

			int saldo = (int) fechaYsaldo.obtenerValorRecibo();

			Date ahora = new Date();

			boolean resultadoAlmacenarSoat = soat.guardaSoat(
					new Soat(txtPlaca.getText().toString(), saldo,
							fechaYsaldo.obtenerFechaAnual(
									new java.sql.Date(ahora.getTime())), 
									fechaYsaldo.obtenerValorReciboPredialComercio(saldo)));

			if(resultadoAlmacenarSoat == true){
				registro = true;
//				startActivity(intent);
			}
		}
		return registro;
	}
}
