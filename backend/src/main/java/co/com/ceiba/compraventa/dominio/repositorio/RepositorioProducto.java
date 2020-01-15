/**
 * 
 */
package co.com.ceiba.compraventa.dominio.repositorio;

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
}
