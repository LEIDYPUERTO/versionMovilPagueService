package dao;

import logica.Banco;
import logica.BancoId;
import logica.Cliente;
import logica.EGas;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

public class GasDao {
	
	private static final String URL = "http://190.90.98.142:8080/proyectoSW2/" +
			"services/GasDao?wsdl";
	private static final String NAMESPACE = "http://dao";
	

	private static final String INSERTAR = "guardarGas";
	private static final String OBTENER = "obtenerGas";
	
	
	/**
	 * Método que permite almacenar el recibo de gas en la base de datos
	 * @param banco
	 * @return
	 */
	public boolean guardarGas(EGas gas){
		SoapObject guardaBanco = new SoapObject(NAMESPACE, INSERTAR);
		SoapObject bancoSO = new SoapObject(NAMESPACE, "eGas");

				
		bancoSO.addProperty("detalleGas", gas.getDetalleGas());
		bancoSO.addProperty("fechaPagoGas", gas.getFechaPagoGas());
		bancoSO.addProperty("clienteCedula", gas.getId().getClienteCedula());
		bancoSO.addProperty("reciboGas", gas.getId().getReciboGas());
		bancoSO.addProperty("valorGas", gas.getValorGas());
		

		guardaBanco.addSoapObject(bancoSO);

		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope
				(SoapEnvelope.VER11);
		envelope.setOutputSoapObject(guardaBanco);
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
