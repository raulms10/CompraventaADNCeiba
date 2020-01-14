/**
 * 
 */
package co.com.ceiba.compraventa.infraestructura.controlador;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.com.ceiba.compraventa.aplicacion.comando.ComandoCompra;
import co.com.ceiba.compraventa.aplicacion.comando.manejador.compra.ManejadorCrearCompra;

/**
 * @author raul.martinez
 *
 */
@RestController
@RequestMapping(value = "/compra/")
public class CompraControlador {

	private final ManejadorCrearCompra manejadorCrearCompra;
	
	public CompraControlador(ManejadorCrearCompra manejadorCrearCompra) {
		this.manejadorCrearCompra = manejadorCrearCompra;
	}
	
	@PostMapping(value = "crear")
	public void crear(@RequestBody ComandoCompra comandoCompra) {
		this.manejadorCrearCompra.ejecutar(comandoCompra);
	}
}
