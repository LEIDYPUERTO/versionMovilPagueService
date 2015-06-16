package dao;

import logica.Banco;
import logica.BancoId;
import logica.CamaraComercio;
import logica.Cliente;
import logica.ImpuestoPredial;
import logica.Soat;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

public class PredialDao {
	
	private static final String URL = "http://190.90.98.142:8080/proyectoSW2/" +
			"services/PredialDao?wsdl";
	private static final String NAMESPACE = "http://dao";
	

	private static final String INSERTAR = "guardarPredial";
	private static final String OBTENER = "obtenerPredial";
	
	
	/**
	 * Método que permite almacenar datos del impuesto predial desde 
	 * la aplicación móvil
	 * @param predial
	 * @return
	 */
	public boolean guardaPredial(ImpuestoPredial predial){
		
		SoapObject guardaPredial = new SoapObject(NAMESPACE, INSERTAR);
		SoapObject predialSoapObject = new SoapObject(NAMESPACE, "impuestoPredial");

				
		predialSoapObject.addProperty("NPredio", predial.getNPredio());
		predialSoapObject.addProperty("fechaPagoPredial", predial.getFechaPagoPredial());
		predialSoapObject.addProperty("impuestoPredio", predial.getImpuestoPredio());
		predialSoapObject.addProperty("valorPredio", predial.getValorPredio());
		

		guardaPredial.addSoapObject(predialSoapObject);

		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope
				(SoapEnvelope.VER11);
		envelope.setOutputSoapObject(guardaPredial);
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
	
	public ImpuestoPredial obtenPredial(int numeroPredio){

		ImpuestoPredial predial = null;

		SoapObject obtenPredial = new SoapObject(NAMESPACE,OBTENER);

		obtenPredial.addProperty("id", numeroPredio);

		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER11);

		envelope.setOutputSoapObject(obtenPredial);

		envelope.implicitTypes = true;

		HttpTransportSE http = new HttpTransportSE(URL);

		try {
			http.call("urn:"+ OBTENER, envelope);
			SoapObject respuesta = (SoapObject) envelope.getResponse();

			predial = new ImpuestoPredial();
			
			predial.setNPredio(Integer.parseInt(respuesta.getProperty
					("NPredio").toString()));
			
			predial.setFechaPagoPredial(respuesta.getProperty
					("fechaPagoPredial").toString());
			
			predial.setValorPredio(Double.parseDouble(respuesta.
					getProperty("valorPredio").toString()));
			predial.setImpuestoPredio(Double.parseDouble
					(respuesta.getProperty("impuestoPredio").toString()));
			
			return predial;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return predial;
		} 
	}
}