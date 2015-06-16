package dao;

import logica.Cliente;
import logica.Soat;
import logica.Telefonia;
import logica.TelefoniaId;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

public class SoatDao {
	
	private static final String URL = "http://190.90.98.142:8080/proyectoSW2/" +
			"services/SoatDao?wsdl";
	private static final String NAMESPACE = "http://dao";
	

	private static final String INSERTAR = "guardarSoat";
	private static final String OBTENER = "obtenerSoat";
	
	
	/**
	 * Método que permite almacenar el SOAT en la base de datos
	 * desde la aplicación móvil
	 * @param SOAT
	 * @return
	 */
	public boolean guardaSoat(Soat soat){
		SoapObject guardaSOAT = new SoapObject(NAMESPACE, INSERTAR);
		SoapObject soatSoapObject = new SoapObject(NAMESPACE, "soat");

				
		soatSoapObject.addProperty("NPlaca", soat.getNPlaca());
		soatSoapObject.addProperty("fechaPagoSoat", soat.getFechaPagoSoat());
		soatSoapObject.addProperty("pagoSoat", soat.getPagoSoat());
		soatSoapObject.addProperty("valorVehiculo", soat.getValorVehiculo());
		

		guardaSOAT.addSoapObject(soatSoapObject);

		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope
				(SoapEnvelope.VER11);
		envelope.setOutputSoapObject(guardaSOAT);
		envelope.implicitTypes = true;

		HttpTransportSE http = new HttpTransportSE(URL);

		try {

			http.call("urn:"+ INSERTAR, envelope);
			SoapPrimitive respuesta = (SoapPrimitive) envelope.getResponse();

			return Boolean.parseBoolean(respuesta.toString());

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} 
	}

	public Soat obtenSoat(String placa){

		Soat soat = null;

		SoapObject obtenSoat = new SoapObject(NAMESPACE,OBTENER);

		obtenSoat.addProperty("id", placa);

		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER11);

		envelope.setOutputSoapObject(obtenSoat);

		envelope.implicitTypes = true;

		HttpTransportSE http = new HttpTransportSE(URL);

		try {
			http.call("urn:"+ OBTENER, envelope);
			SoapObject respuesta = (SoapObject) envelope.getResponse();

			soat = new Soat();
			
			soat.setNPlaca(respuesta.getProperty("NPlaca").toString());
			soat.setFechaPagoSoat(respuesta.getProperty("fechaPagoSoat").toString());
			soat.setPagoSoat(Double.parseDouble(respuesta.getProperty("pagoSoat").
						toString()));
			soat.setValorVehiculo(Double.parseDouble
					(respuesta.getProperty("valorVehiculo").toString()));
			
			return soat;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return soat;
		} 
	}
}
