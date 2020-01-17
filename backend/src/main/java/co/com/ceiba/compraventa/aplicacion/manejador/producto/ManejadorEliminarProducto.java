/**
 * 
 */
package co.com.ceiba.compraventa.aplicacion.manejador.producto;

import org.springframework.stereotype.Component;

import co.com.ceiba.compraventa.aplicacion.comando.ComandoProducto;
import co.com.ceiba.compraventa.aplicacion.fabrica.FabricaProducto;
import co.com.ceiba.compraventa.dominio.modelo.Producto;
import co.com.ceiba.compraventa.dominio.servicio.producto.ServicioEliminarProducto;

/**
 * @author raul.martinez
 *
 */
@Component
public class ManejadorEliminarProducto {

	private final ServicioEliminarProducto servicioEliminarProducto;
	private final FabricaProducto fabricaProducto;
	
	public ManejadorEliminarProducto(ServicioEliminarProducto servicioEliminarProducto, FabricaProducto fabricaProducto) {
		this.servicioEliminarProducto = servicioEliminarProducto;
		this.fabricaProducto = fabricaProducto;
	}
	
	public void ejecutar(ComandoProducto comandoProducto) {
		Producto producto = this.fabricaProducto.crearProducto(comandoProducto);
		this.servicioEliminarProducto.ejecutar(producto, comandoProducto.getFechaEliminar());
	}
	
}
