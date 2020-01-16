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
		System.out.println("Valores: " + valor + " -" + descuento + "%" + " = " + valorPagado + ", se obtiene: " + valorConDescuento + " valido: " + valorConDescuento.equals(valorPagado));
		boolean esDescuentoValido = valorConDescuento.equals(valorPagado);
		if (!esDescuentoValido) {
			throw new ExcepcionDescuento(mensaje);
		}
	}
}
