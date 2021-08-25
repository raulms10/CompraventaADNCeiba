/**
 * 
 */
package co.com.ceiba.compraventa.infraestructura.compartido;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * @author raul.martinez
 *
 */
public class FormateadorFecha {
	
	public static Date getDate(String fechaString) throws ParseException {
		SimpleDateFormat simpleDateFormat =  new SimpleDateFormat("yyyy-MM-dd", new Locale("es", "ES"));
		return simpleDateFormat.parse(fechaString); 
	}
	
	public static DateFormat getDateFormat() {
		SimpleDateFormat simpleDateFormat =  new SimpleDateFormat("yyyy-MM-dd", new Locale("es", "ES"));
		return simpleDateFormat;
	}
	
}
