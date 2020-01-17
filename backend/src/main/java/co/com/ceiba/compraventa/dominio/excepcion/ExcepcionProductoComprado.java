/**
 * 
 */
package co.com.ceiba.compraventa.dominio.excepcion;

/**
 * @author raul.martinez
 *
 */
public class ExcepcionProductoComprado extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public ExcepcionProductoComprado(String message) {
        super(message);
    }
}
