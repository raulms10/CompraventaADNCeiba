/**
 * 
 */
package co.com.ceiba.compraventa.aplicacion.fabrica;

import org.springframework.stereotype.Component;

import co.com.ceiba.compraventa.aplicacion.comando.ComandoProducto;
import co.com.ceiba.compraventa.dominio.modelo.Producto;

/**
 * @author raul.martinez
 *
 */
@Component
public class FabricaProducto {

	public Producto crearProducto(ComandoProducto comandoProducto) {
		return new Producto(comandoProducto.getCodigo(), comandoProducto.getNombre(), comandoProducto.getValor(), comandoProducto.getDescuento(), comandoProducto.getFecha(), comandoProducto.getCedulaVendedor(), comandoProducto.getNombreVendedor());
	}
}
