/**
 * 
 */
package co.com.ceiba.compraventa.dominio.servicio.compra;

import co.com.ceiba.compraventa.dominio.excepcion.ExcepcionDuplicidad;
import co.com.ceiba.compraventa.dominio.modelo.Compra;
import co.com.ceiba.compraventa.dominio.repositorio.RepositorioCompra;

/**
 * @author raul.martinez
 *
 */
public class ServicioCrearCompra {

	private static final String PRODUCTO_HA_SIDO_VENDIDO = "El producto ya ha sido vendido.";
	
	private RepositorioCompra repositorioCompra;

	public ServicioCrearCompra(RepositorioCompra repositorioCompra) {
		this.repositorioCompra = repositorioCompra;
	}

	public void ejecutar(Compra compra) {
		validarExistenciaPrevia(compra);
		this.repositorioCompra.crear(compra);
	}
	
	private void validarExistenciaPrevia(Compra compra) {
		boolean existe = this.repositorioCompra.existe(compra);
		if (existe) {
			throw new ExcepcionDuplicidad(PRODUCTO_HA_SIDO_VENDIDO);
		}
	}
}
