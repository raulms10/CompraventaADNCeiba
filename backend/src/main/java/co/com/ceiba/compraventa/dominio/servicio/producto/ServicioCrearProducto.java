/**
 * 
 */
package co.com.ceiba.compraventa.dominio.servicio.producto;

import java.util.Date;

import co.com.ceiba.compraventa.dominio.excepcion.ExcepcionDuplicidad;
import co.com.ceiba.compraventa.dominio.modelo.Producto;
import co.com.ceiba.compraventa.dominio.modelo.ValidadorFecha;
import co.com.ceiba.compraventa.dominio.repositorio.RepositorioProducto;

/**
 * @author raul.martinez
 *
 */
public class ServicioCrearProducto {
	
	private static final String PRODUCTO_YA_EXISTE = "El producto ya ha sido ingresado.";
	private static final String SOLO_CREA_PRODCUTOS_LUNES_A_VIERNES = "Solo se permite crear productos de lunes a viernes.";
	
	private RepositorioProducto repositorioProducto;

	public ServicioCrearProducto(RepositorioProducto repositorioProducto) {
		this.repositorioProducto = repositorioProducto;
	}

	public void ejecutar(Producto producto) {
		validarDiaLunesAViernes(producto.getFecha());
		validarExistenciaPrevia(producto);
		this.repositorioProducto.crear(producto);
	}
	
	private void validarExistenciaPrevia(Producto producto) {
		boolean existe = this.repositorioProducto.existe(producto);
		if (existe) {
			throw new ExcepcionDuplicidad(PRODUCTO_YA_EXISTE);
		}
	}
	
	private void validarDiaLunesAViernes(Date fecha) {
		ValidadorFecha.validarDiaLunesAViernes(fecha, SOLO_CREA_PRODCUTOS_LUNES_A_VIERNES);
	}
}
