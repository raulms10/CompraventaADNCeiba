/**
 * 
 */
package co.com.ceiba.compraventa.dominio.excepcion;

/**
 * @author raul.martinez
 *
 */
public class ExcepcionRango extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	public ExcepcionRango(String message) {
        super(message);
    }
}
