/**
 * 
 */
package co.com.ceiba.compraventa.infraestructura.controlador;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.com.ceiba.compraventa.aplicacion.comando.ComandoProducto;
import co.com.ceiba.compraventa.aplicacion.manejador.producto.ManejadorCrearProducto;
import co.com.ceiba.compraventa.aplicacion.manejador.producto.ManejadorEliminarProducto;
import co.com.ceiba.compraventa.aplicacion.manejador.producto.ManejadorListarProducto;
/**
 * @author raul.martinez
 *
 */
@RestController
@RequestMapping(value = "/productos")
public class ProductoControlador {

	private final ManejadorCrearProducto manejadorCrearProducto;
	private final ManejadorListarProducto manejadorListarProducto;
	private final ManejadorEliminarProducto manejadorEliminarProducto;
	
	public ProductoControlador(ManejadorCrearProducto manejadorCrearProducto, ManejadorListarProducto manejadorListarProducto, ManejadorEliminarProducto manejadorEliminarProducto) {
		this.manejadorCrearProducto = manejadorCrearProducto;
		this.manejadorListarProducto = manejadorListarProducto;
		this.manejadorEliminarProducto = manejadorEliminarProducto;
	}
	
	@PostMapping
	public void crear(@RequestBody ComandoProducto comandoProducto) {
		 this.manejadorCrearProducto.ejecutar(comandoProducto);
	}
	
	@GetMapping
	public List<ComandoProducto> listar(@RequestParam(value = "cedula", required = false) String cedulaVendedor) {
		return this.manejadorListarProducto.listar(cedulaVendedor);
	}
	
	@DeleteMapping
	public void eliminar(@RequestBody ComandoProducto comandoProducto) {
		this.manejadorEliminarProducto.ejecutar(comandoProducto);
	}
}
