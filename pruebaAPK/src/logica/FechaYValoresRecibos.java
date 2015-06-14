package logica;

import java.sql.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Random;

public class FechaYValoresRecibos {

	/**
	 * Método que permite obtener una fecha mensualmente, dada una fecha inicial
	 * @param fechaReciboActual
	 * @return
	 */
	public Date obtenerFechaMes(Date fechaReciboActual){
		Date fechaReciboProximo=null;
				
		Calendar cal = new GregorianCalendar();
		        cal.setTimeInMillis(fechaReciboActual.getTime());
        cal.add(Calendar.DAY_OF_MONTH, 30);
        fechaReciboProximo = new java.sql.Date(cal.getTimeInMillis());
		return fechaReciboProximo;
	}
	
	/**
	 * Método que permite obtener una fecha anualmente, dada una fecha inicial
	 * @param fechaReciboActual
	 * @return
	 */
	public Date obtenerFechaAnual(Date fechaReciboActual){
		Date fechaReciboProximo=null;
				
		Calendar cal = new GregorianCalendar();
		        cal.setTimeInMillis(fechaReciboActual.getTime());
        cal.add(Calendar.DAY_OF_MONTH, 365);
        fechaReciboProximo = new java.sql.Date(cal.getTimeInMillis());
		return fechaReciboProximo;
	}
	
	
	public double obtenerValorRecibo(){
		Random random = new Random();
		return Math.rint(random.nextDouble() * 10000000 + 5000)/10;
	}
	
	
	/**
	 * Metodo que permite obtener el valor de un recibo de impuesto 
	 * predial y comercio, dado el valor de valorizacion
	 * @param valor
	 * @return
	 */
	public double obtenerValorReciboPredialComercio(int valor){
		Random random = new Random();
		return Math.rint(random.nextDouble() * valor + 25000)/10;
	}	
}
