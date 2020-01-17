/**
 * 
 */
package co.com.ceiba.compraventa.dominio.modelo;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import co.com.ceiba.compraventa.dominio.excepcion.ExcepcionLunesViernes;
import co.com.ceiba.compraventa.dominio.excepcion.ExcepcionSabadoDomingo;

/**
 * @author raul.martinez
 *
 */
public final class ValidadorFecha {
	
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
	
	public static void validarDiaSabadoODomingo(Date fecha, String mensaje) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(fecha);
		int numeroDia = calendar.get(Calendar.DAY_OF_WEEK);
		boolean esSabadoODomingo = numeroDia == Calendar.SATURDAY || numeroDia == Calendar.SUNDAY;
		if (esSabadoODomingo) {
			throw new ExcepcionSabadoDomingo(mensaje);
		}
	}
	
}
