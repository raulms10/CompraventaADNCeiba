/**
 * 
 */
package co.com.ceiba.compraventa.infraestructura.controlador;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.com.ceiba.compraventa.aplicacion.comando.ComandoProducto;
import co.com.ceiba.compraventa.aplicacion.comando.manejador.producto.ManejadorCrearProducto;

/**
 * @author raul.martinez
 *
 */
@RestController
@RequestMapping(value = "/producto/")
public class ProductoControlador {

	private final ManejadorCrearProducto manejadorCrearProducto;
	
	public ProductoControlador(ManejadorCrearProducto manejadorCrearProducto) {
		this.manejadorCrearProducto = manejadorCrearProducto;
	}
	
	@PostMapping(value = "crear")
	public void crear(@RequestBody ComandoProducto comandoProducto){
		this.manejadorCrearProducto.ejecutar(comandoProducto);
	}
}
