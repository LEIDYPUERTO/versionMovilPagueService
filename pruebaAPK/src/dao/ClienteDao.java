package dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import logica.Cliente;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpResponseException;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import android.widget.Toast;

import com.example.pruebaapk.MainActivity;


public class ClienteDao {

	private static final String URL = "http://190.90.98.142:8080/" +
			"proyectoSW2/services/ClienteDao?wsdl";
	private static final String NAMESPACE = "http://dao";
	

	private static final String INSERTAR = "guardaCliente";
	private static final String OBTENER = "obtenCliente";

	public boolean guardaCliente(Cliente cliente)  { 

		SoapObject guardaCliente = new SoapObject(NAMESPACE, INSERTAR);
		SoapObject clienteSO = new SoapObject(NAMESPACE, "cliente");

		clienteSO.addProperty("cedula", cliente.getCedula());
		clienteSO.addProperty("nombre", cliente.getNombre());
		clienteSO.addProperty("genero", cliente.getGenero());
		clienteSO.addProperty("contrasena", cliente.getContrasena());

		guardaCliente.addSoapObject(clienteSO);

		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope
				(SoapEnvelope.VER11);
		envelope.setOutputSoapObject(guardaCliente);
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

	public Cliente obtenCliente(int id){
		Cliente cliente = null;
		SoapObject obtenerCliente = new SoapObject(NAMESPACE,OBTENER);
		obtenerCliente.addProperty("id", id);

		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER11);

		envelope.setOutputSoapObject(obtenerCliente);
		envelope.implicitTypes = true;

		HttpTransportSE http = new HttpTransportSE(URL);

		try {

			http.call("urn:"+ OBTENER, envelope);
			SoapObject respuesta = (SoapObject) envelope.getResponse();

			cliente = new Cliente();
			cliente.setCedula(Integer.parseInt(respuesta.getProperty
					("cedula").toString()));
			cliente.setContrasena(respuesta.getProperty("contrasena").toString());
			cliente.setGenero(respuesta.getProperty("genero").toString());
			cliente.setNombre(respuesta.getProperty("nombre").toString());
			return cliente;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return cliente;
		} 

	}

}
