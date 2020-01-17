/**
 * 
 */
package co.com.ceiba.compraventa.aplicacion.manejador.producto;

import java.util.List;

import org.springframework.stereotype.Component;

import co.com.ceiba.compraventa.aplicacion.comando.ComandoProducto;
import co.com.ceiba.compraventa.dominio.servicio.producto.ServicioListarProducto;

/**
 * @author raul.martinez
 *
 */
@Component
public class ManejadorListarProducto {

	private ServicioListarProducto servicioListarProducto;
	
	public ManejadorListarProducto(ServicioListarProducto servicioListarProducto) {
		this.servicioListarProducto = servicioListarProducto;
	}
	
	public List<ComandoProducto> listar(String cedulaVendedor) {
		return this.servicioListarProducto.listar(cedulaVendedor);
	}
}
