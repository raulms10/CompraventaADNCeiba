/**
 * 
 */
package co.com.ceiba.compraventa.dominio.modelo;

import co.com.ceiba.compraventa.dominio.excepcion.ExcepcionLongitudMaxima;
import co.com.ceiba.compraventa.dominio.excepcion.ExcepcionRango;
import co.com.ceiba.compraventa.dominio.excepcion.ExcepcionValorMinimo;
import co.com.ceiba.compraventa.dominio.excepcion.ExcepcionValorObligatorio;

/**
 * @author raul.martinez
 *
 */
public final class ValidadorParametro {

	private ValidadorParametro() {}
	
	public static void validarObligatorio(Object valor, String mensaje) {
        if (valor == null) {
            throw new ExcepcionValorObligatorio(mensaje);
        }
    }
	
	public static void validarLongitudMaxima(String valor, int longitud, String mensaje) {
        if(valor.length() > longitud) {
            throw new ExcepcionLongitudMaxima(mensaje);
        }
    }
	
	public static void validarRango(Long valor, Long valorMinimo, Long valorMaximo, String mensaje) {
        if(valor < valorMinimo || valor > valorMaximo) {
            throw new ExcepcionRango(mensaje);
        }
    }
	
	public static void validarValorMinimo(Long valor, Long valorMinimo, String mensaje) {
		if (valor < valorMinimo) {
			throw new ExcepcionValorMinimo(mensaje);
		}
	}
}
