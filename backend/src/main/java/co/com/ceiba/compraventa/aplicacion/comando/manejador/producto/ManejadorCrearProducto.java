/**
 * 
 */
package co.com.ceiba.compraventa.aplicacion.comando.manejador.producto;

import org.springframework.stereotype.Component;

import co.com.ceiba.compraventa.aplicacion.comando.ComandoProducto;
import co.com.ceiba.compraventa.aplicacion.comando.fabrica.FabricaProducto;
import co.com.ceiba.compraventa.dominio.entidad.Producto;
import co.com.ceiba.compraventa.dominio.servicio.producto.ServicioCrearProducto;

/**
 * @author raul.martinez
 *
 */
@Component
public class ManejadorCrearProducto {

	private final ServicioCrearProducto servicioCrearProducto;
	private final FabricaProducto fabricaProducto;
	
	public ManejadorCrearProducto(ServicioCrearProducto servicioCrearProducto, FabricaProducto fabricaProducto) {
		this.servicioCrearProducto = servicioCrearProducto;
		this.fabricaProducto = fabricaProducto;
	}
	
	public void ejecutar(ComandoProducto comandoProducto) {
		Producto producto = this.fabricaProducto.crearProducto(comandoProducto);
		this.servicioCrearProducto.ejecutar(producto);
	}
}
