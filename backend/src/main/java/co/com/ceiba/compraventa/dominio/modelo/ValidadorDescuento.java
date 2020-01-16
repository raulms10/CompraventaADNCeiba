/**
 * 
 */
package co.com.ceiba.compraventa.dominio.modelo;

import co.com.ceiba.compraventa.dominio.excepcion.ExcepcionDescuento;

/**
 * @author raul.martinez
 *
 */
public final class ValidadorDescuento {
	
	private ValidadorDescuento() {}
	
	public static void validarDescuentoLosViernes(Long valorPagado, Long valor, Long descuento, String mensaje) {
		Long valorConDescuento = valor - valor * descuento / 100L;
		boolean esDescuentoValido = valorConDescuento.equals(valorPagado);
		if (!esDescuentoValido) {
			throw new ExcepcionDescuento(mensaje);
		}
	}
}
