package com.example.pruebaapk;



import dao.AguaDao;
import dao.BancoDao;
import dao.CamaraComercioDao;
import dao.GasDao;
import dao.InternetDao;
import dao.LuzDao;
import dao.PredialDao;
import dao.SoatDao;
import dao.TelefonoDao;
import logica.Banco;
import logica.CamaraComercio;
import logica.EAgua;
import logica.EGas;
import logica.ELuz;
import logica.ImpuestoPredial;
import logica.InternetTv;
import logica.Soat;
import logica.Telefonia;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class Principal extends Activity{
	
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.principal2);
		
		ImageButton btnAgua = (ImageButton) findViewById(R.id.btnAgua);

		final Intent intent = new Intent(Principal.this, Recibos.class);
		final int cedula = getIntent().getExtras().getInt("cedula");
		
		btnAgua.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				
				intent.putExtra("recibo", "Agua");
				
				AguaDao aguaDao = new AguaDao();
				EAgua agua = aguaDao.obtenAgua(cedula);
				Log.d("asasasa", agua.getDetalleAgua()+ " "+ 
						agua.getFechaPagoAgua()+ " "+ agua.getValorAgua());
				intent.putExtra("cedula", cedula);
				intent.putExtra("agua", agua);
				startActivity(intent);
			}
		});
		
		ImageButton btnLuz = (ImageButton) findViewById(R.id.btnLuz);
		btnLuz.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
				intent.putExtra("recibo", "Luz");
				
				LuzDao luzDao = new LuzDao();
				ELuz luz = luzDao.obtenLuz(cedula);
				Log.d("asasasa", luz.getDetallerLuz()+ " "+ 
						luz.getFechaPagoLuz()+ " "+ luz.getValorLuz());
				intent.putExtra("cedula", cedula);
				intent.putExtra("luz", luz);
				startActivity(intent);
			}
		});
		
		ImageButton btnInternet = (ImageButton) findViewById(R.id.btnInternet);
		btnInternet.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
				intent.putExtra("recibo", "Internet");
				
				InternetDao internetDao = new InternetDao();
				InternetTv internet = internetDao.obtenInternet(cedula);
//				Log.d("asasasa", luz.getDetallerLuz()+ " "+ 
//						luz.getFechaPagoLuz()+ " "+ luz.getValorLuz());
				intent.putExtra("cedula", cedula);
				intent.putExtra("internet", internet);
				startActivity(intent);
			}
		});
		
		ImageButton btnGas = (ImageButton) findViewById(R.id.btnGas);
		btnGas.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
				intent.putExtra("recibo", "Gas");
				
				GasDao gasDao = new GasDao();
				EGas gas = gasDao.obtenGas(cedula);
				intent.putExtra("cedula", cedula);
				intent.putExtra("gas", gas);
				startActivity(intent);
			}
		});
		
		ImageButton btnTelefonia = (ImageButton) findViewById(R.id.btnTelefonia);
		btnTelefonia.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
				intent.putExtra("recibo", "Telefonia");
				
				TelefonoDao telefoniaDao = new TelefonoDao();
				Telefonia telefonia = telefoniaDao.obtenTelefonia(cedula);
				intent.putExtra("cedula", cedula);
				intent.putExtra("telefonia", telefonia);
				startActivity(intent);
			}
		});
		
		ImageButton btnSoat = (ImageButton) findViewById(R.id.btnSOAT);
		btnSoat.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
				intent.putExtra("recibo", "Soat");
				
				SoatDao soatDao = new SoatDao();
				String placa = "BER170";
				Soat soat = soatDao.obtenSoat(placa);
				intent.putExtra("placa", placa);
				intent.putExtra("soat", soat);
				startActivity(intent);
			}
		});
		
		ImageButton btnPredial = (ImageButton) findViewById(R.id.btnPredial);
		btnPredial.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
				intent.putExtra("recibo", "Predial");
				
				PredialDao predialDao = new PredialDao();
				int numeroPredio = 2147483647;
				ImpuestoPredial predial = predialDao.obtenPredial(numeroPredio);
				intent.putExtra("predial", predial);
				startActivity(intent);
			}
		});
		
		ImageButton btnComercio = (ImageButton) findViewById(R.id.btnComercio);
		btnComercio.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
				intent.putExtra("recibo", "Comercio");
				
				CamaraComercioDao camaraDao = new CamaraComercioDao();
				int nitComercio = 1234671111;
				CamaraComercio camara = camaraDao.obtenCamara(nitComercio);
				intent.putExtra("camara", camara);
				startActivity(intent);
			}
		});
		
		ImageButton btnBanco = (ImageButton) findViewById(R.id.btnBanco);
		btnBanco.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
				BancoDao bancoDao = new BancoDao();
				Banco banco = bancoDao.obtenBanco(cedula);
				
				Toast.makeText(Principal.this, "Su saldo en la cuenta es de "+
				banco.getSaldo(), Toast.LENGTH_SHORT).show();
			}
		});
	}
 
	
}