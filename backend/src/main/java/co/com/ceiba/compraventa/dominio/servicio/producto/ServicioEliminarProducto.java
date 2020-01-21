/**
 * 
 */
package co.com.ceiba.compraventa.dominio.servicio.producto;

import java.util.Date;

import co.com.ceiba.compraventa.dominio.excepcion.ExcepcionProductoComprado;
import co.com.ceiba.compraventa.dominio.modelo.ValidadorFecha;
import co.com.ceiba.compraventa.dominio.modelo.ValidadorParametro;
import co.com.ceiba.compraventa.dominio.repositorio.RepositorioProducto;

/**
 * @author raul.martinez
 *
 */
public class ServicioEliminarProducto {
	
	private static final String NO_ELIMINA_PRODUCTO_COMPRADO = "No es posible eliminar un producto comprado.";
	private static final String NO_ELIMINA_PRODUCTO_SABADO_O_DOMINGO = "No es posible eliminar un producto los dias sabados y domingos.";
	private static final String LA_FEHCA_ELIMINAR_ES_DATO_OBLIGATORIO = "La fecha para eliminar el producto es un dato obligatorio.";
	private static final String EL_CODIGO_PRODCUTO_ES_DATO_OBLIGATORIO = "El codigo del producto es un dato obligatorio.";
	
	private RepositorioProducto repositorioProducto;

	public ServicioEliminarProducto(RepositorioProducto repositorioProducto) {
		this.repositorioProducto = repositorioProducto;
	}
	
	public void ejecutar(String codigo, Date fecha) {
		validarCodigoProducto(codigo);
		validarFechaParaEliminar(fecha);
		validarDiaSabadoDomingo(fecha);
		validarCompraPrevia(codigo);
		this.repositorioProducto.eliminar(codigo);
	}

	private void validarCompraPrevia(String codigo) {
		boolean comprado = this.repositorioProducto.comprado(codigo);
		if (comprado) {
			throw new ExcepcionProductoComprado(NO_ELIMINA_PRODUCTO_COMPRADO);
		}
	}
	
	private void validarDiaSabadoDomingo(Date fecha) {
		ValidadorFecha.validarDiaSabadoODomingo(fecha, NO_ELIMINA_PRODUCTO_SABADO_O_DOMINGO);
	}
	
	private void validarCodigoProducto(String codigo) {
		ValidadorParametro.validarObligatorio(codigo, EL_CODIGO_PRODCUTO_ES_DATO_OBLIGATORIO);
	}
	
	private void validarFechaParaEliminar(Date fecha) {
		ValidadorParametro.validarObligatorio(fecha, LA_FEHCA_ELIMINAR_ES_DATO_OBLIGATORIO);
	}
}
