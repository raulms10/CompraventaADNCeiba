/**
 * 
 */
package co.com.ceiba.compraventa.aplicacion.comando.fabrica;

import org.springframework.stereotype.Component;

import co.com.ceiba.compraventa.aplicacion.comando.ComandoCompra;
import co.com.ceiba.compraventa.dominio.entidad.Compra;
import co.com.ceiba.compraventa.dominio.entidad.Producto;

/**
 * @author raul.martinez
 *
 */
@Component
public class FabricaCompra {
	
	private final FabricaProducto fabricaProducto;
	
	public FabricaCompra(FabricaProducto fabricaProducto) {
		this.fabricaProducto = fabricaProducto;
	}

	public Compra crearCompra(ComandoCompra comandoCompra) {
		Producto producto = this.fabricaProducto.crearProducto(comandoCompra.getComandoProducto());
		return new Compra(comandoCompra.getIdCompra(), comandoCompra.getCedulaComprador(), comandoCompra.getNombreComprador(), comandoCompra.getFechaCompra(), comandoCompra.getValorPagado(), producto);
	}
}
