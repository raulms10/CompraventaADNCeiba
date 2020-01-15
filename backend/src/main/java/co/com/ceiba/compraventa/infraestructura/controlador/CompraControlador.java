/**
 * 
 */
package co.com.ceiba.compraventa.infraestructura.controlador;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.com.ceiba.compraventa.aplicacion.comando.ComandoCompra;
import co.com.ceiba.compraventa.aplicacion.manejador.compra.ManejadorCrearCompra;

/**
 * @author raul.martinez
 *
 */
@RestController
@RequestMapping(value = "/compras")
public class CompraControlador {

	private final ManejadorCrearCompra manejadorCrearCompra;
	
	public CompraControlador(ManejadorCrearCompra manejadorCrearCompra) {
		this.manejadorCrearCompra = manejadorCrearCompra;
	}
	
	@PostMapping
	public void crear(@RequestBody ComandoCompra comandoCompra) {
		this.manejadorCrearCompra.ejecutar(comandoCompra);
	}
}
