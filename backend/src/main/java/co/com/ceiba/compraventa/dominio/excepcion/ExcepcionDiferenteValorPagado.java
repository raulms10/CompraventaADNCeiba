/**
 * 
 */
package co.com.ceiba.compraventa.dominio.excepcion;

/**
 * @author raul.martinez
 *
 */
public class ExcepcionDiferenteValorPagado extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	public ExcepcionDiferenteValorPagado(String mensaje) {
		super(mensaje);
	}

}
