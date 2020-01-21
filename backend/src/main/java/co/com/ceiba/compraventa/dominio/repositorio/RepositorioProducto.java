/**
 * 
 */
package co.com.ceiba.compraventa.dominio.repositorio;

import java.util.List;

import co.com.ceiba.compraventa.aplicacion.comando.ComandoProducto;
import co.com.ceiba.compraventa.dominio.modelo.Producto;

/**
 * @author raul.martinez
 *
 */
public interface RepositorioProducto {
	
	/**
	 * Permite crear los productos
	 * @param producto
	 */
	void crear(Producto producto);
	
	/**
	 * Permite determinar si el producto ya ha sido ingresado
	 * @param producto
	 * @return si existe o no
	 */
	boolean existe(Producto producto);
	
	/**
	 * Permite buscar los productos sin ningun criterio
	 * @return
	 */
	List<ComandoProducto> listar(String cedulaVendedor);
	
	/**
	 * Permite eliminar el producto
	 * @param producto
	 */
	void eliminar(String codigo);
	
	/**
	 * Permite actualizar el producto
	 * @param poducto
	 */
	boolean comprado(String codigo);
}
