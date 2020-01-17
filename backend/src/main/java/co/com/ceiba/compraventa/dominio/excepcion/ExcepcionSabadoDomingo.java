/**
 * 
 */
package co.com.ceiba.compraventa.dominio.excepcion;

/**
 * @author raul.martinez
 *
 */
public class ExcepcionSabadoDomingo extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ExcepcionSabadoDomingo(String message) {
        super(message);
    }
	
}
