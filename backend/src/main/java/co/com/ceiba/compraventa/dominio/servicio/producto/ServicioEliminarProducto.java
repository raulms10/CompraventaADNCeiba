/**
 * 
 */
package co.com.ceiba.compraventa.dominio.servicio.producto;

import java.util.Date;

import co.com.ceiba.compraventa.dominio.excepcion.ExcepcionProductoComprado;
import co.com.ceiba.compraventa.dominio.modelo.Producto;
import co.com.ceiba.compraventa.dominio.modelo.ValidadorFecha;
import co.com.ceiba.compraventa.dominio.modelo.ValidadorParametro;
import co.com.ceiba.compraventa.dominio.repositorio.RepositorioProducto;

/**
 * @author raul.martinez
 *
 */
public class ServicioEliminarProducto {
	
	private static final String NO_ELIMINA_PRODUCTO_COMPRADO = "No es posible eliminar un producto comprado.";
	private static final String NO_ELIMINA_PRODUCTO_SABADO_O_DOMINGO = "No es posible eliminar un producto los d<ED>as s<E1>bados y domingos.";
	private static final String LA_FEHCA_ELIMINAR_ES_DATO_OBLIGATORIO = "La fecha para eliminar el producto es un dato obligatorio.";
	
	private RepositorioProducto repositorioProducto;

	public ServicioEliminarProducto(RepositorioProducto repositorioProducto) {
		this.repositorioProducto = repositorioProducto;
	}
	
	public void ejecutar(Producto producto, Date fecha) {
		validarFechaParaEliminar(fecha);
		validarDiaSabadoDomingo(fecha);
		validarCompraPrevia(producto);
		this.repositorioProducto.eliminar(producto);
	}

	private void validarCompraPrevia(Producto producto) {
		boolean comprado = this.repositorioProducto.comprado(producto);
		if (comprado) {
			throw new ExcepcionProductoComprado(NO_ELIMINA_PRODUCTO_COMPRADO);
		}
	}
	
	private void validarDiaSabadoDomingo(Date fecha) {
		ValidadorFecha.validarDiaSabadoODomingo(fecha, NO_ELIMINA_PRODUCTO_SABADO_O_DOMINGO);
	}
	
	private void validarFechaParaEliminar(Date fecha) {
		ValidadorParametro.validarObligatorio(fecha, LA_FEHCA_ELIMINAR_ES_DATO_OBLIGATORIO);
	}
}
