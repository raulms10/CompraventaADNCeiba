/**
 * 
 */
package co.com.ceiba.compraventa.dominio.entidad;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import co.com.ceiba.compraventa.dominio.excepcion.ExcepcionLunesViernes;

/**
 * @author raul.martinez
 *
 */
public class ValidadorFecha {
	
	private ValidadorFecha() {}
	
	public static void validarDiaLunesAViernes(Date fecha, String mensaje) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(fecha);
		int numeroDia = calendar.get(Calendar.DAY_OF_WEEK);
		boolean esLunesAViernes = numeroDia >= Calendar.MONDAY && numeroDia <= Calendar.FRIDAY;
		if (!esLunesAViernes) {
			throw new ExcepcionLunesViernes(mensaje);
		}
	}

}
