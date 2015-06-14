package dao;

import logica.Banco;
import logica.BancoId;
import logica.CamaraComercio;
import logica.Cliente;

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
		SoapObject camaraSoap = new SoapObject(NAMESPACE, "camaraComercio");

				
		camaraSoap.addProperty("nitComercio", camara.getNitComercio());
		camaraSoap.addProperty("pagoCamaraComercio", camara.getPagoCamaraComercio());
		camaraSoap.addProperty("fechaPagoComercio", camara.getFechaPagoComercio());
		camaraSoap.addProperty("valorNegocio", camara.getValorNegocio());
		

		guardaCamara.addSoapObject(camaraSoap);

		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope
				(SoapEnvelope.VER11);
		envelope.setOutputSoapObject(guardaCamara);
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
}
