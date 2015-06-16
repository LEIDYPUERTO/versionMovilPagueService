package dao;

import logica.Banco;
import logica.BancoId;
import logica.Cliente;
import logica.MarshalDouble;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

public class BancoDao {

	private static final String URL = "http://190.90.98.142:8080/proyectoSW2/" +
			"services/BancoDao?wsdl";
	private static final String NAMESPACE = "http://dao";


	private static final String INSERTAR = "guardaBanco";
	private static final String OBTENER = "obtenBanco";
	private static final String ACTUALIZAR = "actualizaBanco";


	/**
	 * Método que permite almacenar un Banco en la base de datos
	 * @param banco
	 * @return
	 */
	public boolean guardaBanco(Banco banco){
		SoapObject guardaBanco = new SoapObject(NAMESPACE, INSERTAR);
		SoapObject bancoSO = new SoapObject(NAMESPACE, "banco");
		SoapObject idSO = new SoapObject(NAMESPACE, "id");

		idSO.addProperty("clienteCedula", banco.getId().getClienteCedula());
		idSO.addProperty("numeroCuenta", banco.getId().getNumeroCuenta());
		bancoSO.addProperty("nombreBanco", banco.getNombreBanco());
		bancoSO.addProperty("saldo", banco.getSaldo());

		bancoSO.addSoapObject(idSO);
		
		guardaBanco.addSoapObject(bancoSO);

		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope
				(SoapEnvelope.VER11);
		envelope.setOutputSoapObject(guardaBanco);

		envelope.dotNet = true;
		envelope.implicitTypes = true;
		envelope.encodingStyle = SoapSerializationEnvelope.XSD;
		MarshalDouble md = new MarshalDouble();
		md.register(envelope);

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
	
	public Banco obtenBanco(int id){
		Banco banco = null;
		SoapObject obtenerBanco = new SoapObject(NAMESPACE,OBTENER);
		obtenerBanco.addProperty("id", id);

		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER11);

		envelope.setOutputSoapObject(obtenerBanco);
		envelope.implicitTypes = true;

		HttpTransportSE http = new HttpTransportSE(URL);

		try {

			http.call("urn:"+ OBTENER, envelope);
			SoapObject respuesta = (SoapObject) envelope.getResponse();

			banco = new Banco();

			banco.setSaldo(Double.parseDouble(respuesta.getProperty
					("saldo").toString()));
			banco.setNombreBanco(respuesta.getProperty("nombreBanco").toString());
			banco.setId(new BancoId(111111, id));
			return banco;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return banco;
		} 

	}

	public boolean actualizaBanco (int id, double nuevoSaldo){
		SoapObject actualizaBanco = new SoapObject(NAMESPACE, ACTUALIZAR);
//		SoapObject bancoSO = new SoapObject(NAMESPACE, "banco");

	
		actualizaBanco.addProperty("id", id);
		actualizaBanco.addProperty("saldoNuevo", nuevoSaldo);

		
//		actualizaBanco.addSoapObject(bancoSO);

		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope
				(SoapEnvelope.VER11);
		envelope.setOutputSoapObject(actualizaBanco);

		envelope.dotNet = true;
		envelope.implicitTypes = true;
		envelope.encodingStyle = SoapSerializationEnvelope.XSD;
		MarshalDouble md = new MarshalDouble();
		md.register(envelope);

		HttpTransportSE http = new HttpTransportSE(URL);

		try {

			http.call("urn:"+ ACTUALIZAR, envelope);
			SoapPrimitive respuesta = (SoapPrimitive) envelope.getResponse();

			return Boolean.parseBoolean(respuesta.toString());

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} 
	}

}
