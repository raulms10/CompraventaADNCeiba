/**
 * 
 */
package co.com.ceiba.compraventa.aplicacion.manejador.producto;

import java.util.Date;

import org.springframework.stereotype.Component;

import co.com.ceiba.compraventa.aplicacion.fabrica.FabricaProducto;
import co.com.ceiba.compraventa.dominio.servicio.producto.ServicioEliminarProducto;

/**
 * @author raul.martinez
 *
 */
@Component
public class ManejadorEliminarProducto {

	private final ServicioEliminarProducto servicioEliminarProducto;
		
	public ManejadorEliminarProducto(ServicioEliminarProducto servicioEliminarProducto, FabricaProducto fabricaProducto) {
		this.servicioEliminarProducto = servicioEliminarProducto;
	}
	
	public void ejecutar(String codigo, Date fechaEliminar) {
		this.servicioEliminarProducto.ejecutar(codigo, fechaEliminar);
	}
	
}
