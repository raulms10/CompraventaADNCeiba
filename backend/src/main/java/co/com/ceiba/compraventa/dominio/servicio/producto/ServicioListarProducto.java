/**
 * 
 */
package co.com.ceiba.compraventa.dominio.servicio.producto;

import java.util.List;

import co.com.ceiba.compraventa.aplicacion.comando.ComandoProducto;
import co.com.ceiba.compraventa.dominio.repositorio.RepositorioProducto;

/**
 * @author raul.martinez
 *
 */
public class ServicioListarProducto {
	
	private RepositorioProducto repositorioProducto;
	
	public ServicioListarProducto(RepositorioProducto repositorioProducto) {
		this.repositorioProducto = repositorioProducto;
	}
	
	public List<ComandoProducto> listar(String cedulaVendedor) {
		return this.repositorioProducto.listar(cedulaVendedor);
	}
}
