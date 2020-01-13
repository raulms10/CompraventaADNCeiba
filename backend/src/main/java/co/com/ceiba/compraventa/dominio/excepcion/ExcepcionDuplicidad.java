/**
 * 
 */
package co.com.ceiba.compraventa.dominio.excepcion;

/**
 * @author raul.martinez
 *
 */
public class ExcepcionDuplicidad extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public ExcepcionDuplicidad(String mensaje) {
		super(mensaje);
	}
}
