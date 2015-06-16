package com.example.pruebaapk;

import dao.BancoDao;
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
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;


public class Recibos extends Activity{

	private TextView txtLbl = null;
	private TextView txtValor = null;
	private TextView txtFecha = null;
	private ImageButton btnPagar = null;
	private EAgua agua = null;
	private ELuz luz = null;
	private InternetTv internet = null;
	private EGas gas = null;
	private Telefonia telefonia = null;
	private Soat soat = null;
	private ImpuestoPredial predial = null;
	private CamaraComercio camara = null;
	private int cc = 0;
	private BancoDao bancoDao = null;
	private Banco banco = null;
	private String recibo = null;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.pagos2);

		txtLbl = (TextView) findViewById(R.id.textView2);

		txtValor = (TextView) findViewById(R.id.TextView05);
		txtFecha = (TextView) findViewById(R.id.TextView06);
		btnPagar = (ImageButton) findViewById(R.id.btnPagar);

		recibo = getIntent().getExtras().getString("recibo");
		agua =  (EAgua) getIntent().getExtras().get("agua");
		luz = (ELuz)getIntent().getExtras().get("luz");
		internet = (InternetTv)getIntent().getExtras().get("internet");
		gas = (EGas)getIntent().getExtras().get("gas");
		telefonia = (Telefonia)getIntent().getExtras().get("telefonia");
		soat = (Soat)getIntent().getExtras().get("soat");
		predial = (ImpuestoPredial)getIntent().getExtras().get("predial");
		camara = (CamaraComercio)getIntent().getExtras().get("camara");

		cc = getIntent().getExtras().getInt("cedula");

		txtLbl.setText(""+ recibo);
		txtValor.setText(""+agua.getValorAgua());
		txtFecha.setText(""+agua.getFechaPagoAgua());

		bancoDao = new BancoDao();

		banco = bancoDao.obtenBanco(cc);


		btnPagar.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub

				if(recibo.equalsIgnoreCase("Agua")){
					pagarAgua();
				}
				else{
					if(recibo.equalsIgnoreCase("Luz")){
						pagarLuz();
					}
					else{
						if(recibo.equalsIgnoreCase("Internet")){
							pagarInternet();
						}
						else{
							if(recibo.equalsIgnoreCase("Gas")){
								pagarGas();
							}
							else{
								if(recibo.equalsIgnoreCase("Telefonia")){
									pagarTelefono();
								}
								else{
									if(recibo.equalsIgnoreCase("Soat")){
										pagarSoat();
									}
									else{
										if(recibo.equalsIgnoreCase("Predial")){
											pagarPredial();
										}
										else{
											if(recibo.equalsIgnoreCase("Comercio")){
												pagarCamara();
											}
										}

									}
								}
							}
						}
					}
				}
			}
		});	

	}

	public void pagarAgua(){
		if(banco.getSaldo() > agua.getValorAgua()){
			boolean a = bancoDao.actualizaBanco(cc, banco.getSaldo()-
					agua.getValorAgua());
			Log.d("aaaaaaaaaaaa", "es menor agua que saldo "+a);

		}
		else{
			Toast.makeText(Recibos.this, "La transacción no se pudo " +
					"realizar porque no dispone del saldo suficiente",
					Toast.LENGTH_SHORT).show();
		}
		Toast.makeText(Recibos.this, "Saldo: "+ banco.getSaldo(),
				Toast.LENGTH_SHORT).show();
	}

	public void pagarLuz(){
		if(banco.getSaldo() > luz.getValorLuz()){
			boolean a = bancoDao.actualizaBanco(cc, banco.getSaldo()-
					luz.getValorLuz());
		}
		else{
			Toast.makeText(Recibos.this, "La transacción no se pudo " +
					"realizar porque no dispone del saldo suficiente",
					Toast.LENGTH_SHORT).show();
		}
		Toast.makeText(Recibos.this, "Saldo: "+ banco.getSaldo(),
				Toast.LENGTH_SHORT).show();
	}


	public void pagarInternet(){
		if(banco.getSaldo() > internet.getValorInternet()){
			boolean a = bancoDao.actualizaBanco(cc, banco.getSaldo()-
					internet.getValorInternet());
		}
		else{
			Toast.makeText(Recibos.this, "La transacción no se pudo " +
					"realizar porque no dispone del saldo suficiente",
					Toast.LENGTH_SHORT).show();
		}
		Toast.makeText(Recibos.this, "Saldo: "+ banco.getSaldo(),
				Toast.LENGTH_SHORT).show();
	}

	public void pagarGas(){
		if(banco.getSaldo() > gas.getValorGas()){
			boolean a = bancoDao.actualizaBanco(cc, banco.getSaldo()-
					gas.getValorGas());
		}
		else{
			Toast.makeText(Recibos.this, "La transacción no se pudo " +
					"realizar porque no dispone del saldo suficiente",
					Toast.LENGTH_SHORT).show();
		}
		Toast.makeText(Recibos.this, "Saldo: "+ banco.getSaldo(),
				Toast.LENGTH_SHORT).show();
	}

	public void pagarTelefono(){
		if(banco.getSaldo() > telefonia.getValorTelefonia()){
			boolean a = bancoDao.actualizaBanco(cc, banco.getSaldo()-
					telefonia.getValorTelefonia());
		}
		else{
			Toast.makeText(Recibos.this, "La transacción no se pudo " +
					"realizar porque no dispone del saldo suficiente",
					Toast.LENGTH_SHORT).show();
		}
		Toast.makeText(Recibos.this, "Saldo: "+ banco.getSaldo(),
				Toast.LENGTH_SHORT).show();
	}

	public void pagarSoat(){
		if(banco.getSaldo() > soat.getPagoSoat()){
			boolean a = bancoDao.actualizaBanco(cc, banco.getSaldo()-
					soat.getPagoSoat());
		}
		else{
			Toast.makeText(Recibos.this, "La transacción no se pudo " +
					"realizar porque no dispone del saldo suficiente",
					Toast.LENGTH_SHORT).show();
		}
		Toast.makeText(Recibos.this, "Saldo: "+ banco.getSaldo(),
				Toast.LENGTH_SHORT).show();
	}

	public void pagarPredial(){
		if(banco.getSaldo() > predial.getImpuestoPredio()){
			boolean a = bancoDao.actualizaBanco(cc, banco.getSaldo()-
					predial.getImpuestoPredio());
		}
		else{
			Toast.makeText(Recibos.this, "La transacción no se pudo " +
					"realizar porque no dispone del saldo suficiente",
					Toast.LENGTH_SHORT).show();
		}
		Toast.makeText(Recibos.this, "Saldo: "+ banco.getSaldo(),
				Toast.LENGTH_SHORT).show();
	}

	public void pagarCamara(){
		if(banco.getSaldo() > camara.getPagoCamaraComercio()){
			boolean a = bancoDao.actualizaBanco(cc, banco.getSaldo()-
					camara.getPagoCamaraComercio());
		}
		else{
			Toast.makeText(Recibos.this, "La transacción no se pudo " +
					"realizar porque no dispone del saldo suficiente",
					Toast.LENGTH_SHORT).show();
		}
		Toast.makeText(Recibos.this, "Saldo: "+ banco.getSaldo(),
				Toast.LENGTH_SHORT).show();
	}
}