/**
 * 
 */
package co.com.ceiba.compraventa.dominio.excepcion;

/**
 * @author raul.martinez
 *
 */
public class ExcepcionDescuento extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public ExcepcionDescuento(String mensaje) {
		super(mensaje);
	}
}
