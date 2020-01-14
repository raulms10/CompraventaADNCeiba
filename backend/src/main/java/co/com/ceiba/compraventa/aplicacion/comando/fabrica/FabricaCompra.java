/**
 * 
 */
package co.com.ceiba.compraventa.aplicacion.comando.fabrica;

import org.springframework.stereotype.Component;

import co.com.ceiba.compraventa.aplicacion.comando.ComandoCompra;
import co.com.ceiba.compraventa.dominio.entidad.Compra;

/**
 * @author raul.martinez
 *
 */
@Component
public class FabricaCompra {

	public Compra crearCompra(ComandoCompra comandoCompra) {
		return new Compra(comandoCompra.getIdCompra(), comandoCompra.getCedulaComprador(), comandoCompra.getNombreComprador(), comandoCompra.getFechaCompra(), comandoCompra.getValorPagado(), comandoCompra.getProducto());
	}
}
