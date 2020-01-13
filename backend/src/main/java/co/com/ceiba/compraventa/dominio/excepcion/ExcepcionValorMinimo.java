/**
 * 
 */
package co.com.ceiba.compraventa.dominio.excepcion;

/**
 * @author raul.martinez
 *
 */
public class ExcepcionValorMinimo extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public ExcepcionValorMinimo(String message) {
        super(message);
    }
}
