/**
 * 
 */
package co.com.ceiba.compraventa.dominio.servicio.producto;

import java.util.Date;

import co.com.ceiba.compraventa.dominio.entidad.Producto;
import co.com.ceiba.compraventa.dominio.entidad.ValidadorFecha;
import co.com.ceiba.compraventa.dominio.excepcion.ExcepcionDuplicidad;
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
	
	/**
	 * Valida la existencia previa del producto que se va a agregar
	 * @param producto
	 */
	private void validarExistenciaPrevia(Producto producto) {
		boolean existe = this.repositorioProducto.existe(producto);
		if (existe) {
			throw new ExcepcionDuplicidad(PRODUCTO_YA_EXISTE);
		}
	}
	
	/**
	 * Valida que el día de la semana esté entre Lunes a Viernes para poder agregar el producto
	 * @param producto
	 */
	private void validarDiaLunesAViernes(Date fecha) {
		ValidadorFecha.validarDiaLunesAViernes(fecha, SOLO_CREA_PRODCUTOS_LUNES_A_VIERNES+"l");
	}
}
