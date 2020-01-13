/**
 * 
 */
package co.com.ceiba.compraventa.dominio.excepcion;

/**
 * @author raul.martinez
 *
 */
public class ExcepcionLongitudMaxima extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
    public ExcepcionLongitudMaxima(String message) {
        super(message);
    }
}
