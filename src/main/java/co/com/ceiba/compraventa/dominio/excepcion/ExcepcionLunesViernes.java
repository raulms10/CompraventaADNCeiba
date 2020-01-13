/**
 * 
 */
package co.com.ceiba.compraventa.dominio.excepcion;

/**
 * @author raul.martinez
 *
 */
public class ExcepcionLunesViernes extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public ExcepcionLunesViernes(String message) {
        super(message);
    }
}
