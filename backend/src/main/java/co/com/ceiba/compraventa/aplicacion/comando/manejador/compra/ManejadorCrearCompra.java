/**
 * 
 */
package co.com.ceiba.compraventa.aplicacion.comando.manejador.compra;

import org.springframework.stereotype.Component;

import co.com.ceiba.compraventa.aplicacion.comando.ComandoCompra;
import co.com.ceiba.compraventa.aplicacion.comando.fabrica.FabricaCompra;
import co.com.ceiba.compraventa.dominio.entidad.Compra;
import co.com.ceiba.compraventa.dominio.servicio.compra.ServicioCrearCompra;

/**
 * @author raul.martinez
 *
 */
@Component
public class ManejadorCrearCompra {

	private final ServicioCrearCompra servicioCrearCompra;
	private final FabricaCompra fabricaCompra;
	
	public ManejadorCrearCompra(ServicioCrearCompra servicioCrearCompra, FabricaCompra fabricaCompra) {
		this.servicioCrearCompra = servicioCrearCompra;
		this.fabricaCompra = fabricaCompra;
	}
	
	public void ejecutar(ComandoCompra comandoCompra) {
		Compra compra = this.fabricaCompra.crearCompra(comandoCompra);
		this.servicioCrearCompra.ejecutar(compra);
	}
}
