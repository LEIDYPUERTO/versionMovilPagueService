package dao;

import logica.Banco;
import logica.BancoId;
import logica.CamaraComercio;
import logica.Cliente;
import logica.ImpuestoPredial;
import logica.MarshalDate;
import logica.MarshalDouble;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

public class CamaraComercioDao {
	
	private static final String URL = "http://190.90.98.142:8080/proyectoSW2/" +
			"services/ComercioDao?wsdl";
	private static final String NAMESPACE = "http://dao";
	

	private static final String INSERTAR = "guardaComercio";
	private static final String OBTENER = "obtenComercio";
	
	
	/**
	 * Método que permite almacenar datos de la camara de comercio
	 * @param camara
	 * @return
	 */
	public boolean guardaComercio(CamaraComercio camara){
		
		SoapObject guardaCamara = new SoapObject(NAMESPACE, INSERTAR);
//		SoapObject camaraSoap = new SoapObject(NAMESPACE, "camaraComercio");

		PropertyInfo propertyInfo = new PropertyInfo();
		propertyInfo.setName("camaraComercio");
		propertyInfo.setValue(camara);
		propertyInfo.setType(camara.getClass());
		
//		propertyInfo.name = "fechaPagoComercio";
//		propertyInfo.type = MarshalDate.DATE_CLASS;
//		propertyInfo.setValue(camara.getFechaPagoComercio());
		
//		camaraSoap.addProperty("nitComercio", camara.getNitComercio());
//		camaraSoap.addProperty("pagoCamaraComercio", camara.getPagoCamaraComercio());
////		camaraSoap.addProperty("fechaPagoComercio", camara.getFechaPagoComercio());
//		camaraSoap.addProperty(propertyInfo);
//		
//				
//		camaraSoap.addProperty("valorNegocio", camara.getValorNegocio());
		

		guardaCamara.addProperty(propertyInfo);

		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope
				(SoapEnvelope.VER11);
		envelope.setOutputSoapObject(guardaCamara);
		
		envelope.dotNet = true;
		envelope.implicitTypes = true;
		envelope.encodingStyle = SoapSerializationEnvelope.XSD;
				 
		envelope.addMapping(NAMESPACE, "camaraComercio", camara.getClass());
		
//		MarshalDate md = new MarshalDate();
//		md.register(envelope);

//		MarshalDouble mardou = new MarshalDouble();
//		mardou.register(envelope);
		
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
	
	public CamaraComercio obtenCamara(int nitComercio){

		CamaraComercio comercio = null;

		SoapObject obtenPredial = new SoapObject(NAMESPACE,OBTENER);

		obtenPredial.addProperty("id", nitComercio);

		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER11);

		envelope.setOutputSoapObject(obtenPredial);

		envelope.implicitTypes = true;

		HttpTransportSE http = new HttpTransportSE(URL);

		try {
			http.call("urn:"+ OBTENER, envelope);
			SoapObject respuesta = (SoapObject) envelope.getResponse();

			comercio = new CamaraComercio();
			
			comercio.setNitComercio(Integer.parseInt(respuesta.getProperty
					("nitComercio").toString()));
			
			comercio.setFechaPagoComercio(respuesta.getProperty
					("fechaPagoComercio").toString());
			
			comercio.setPagoCamaraComercio(Double.parseDouble(respuesta.
					getProperty("pagoCamaraComercio").toString()));
			comercio.setValorNegocio(Integer.parseInt
					(respuesta.getProperty("valorNegocio").toString()));
			
			return comercio;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return comercio;
		} 
	}
}
