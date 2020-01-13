/**
 * 
 */
package co.com.ceiba.compraventa.dominio.repositorio;

import co.com.ceiba.compraventa.dominio.entidad.Compra;

/**
 * @author raul.martinez
 *
 */
public interface RepositorioCompra {
	
	/**
	 * Permite crear las compras
	 * @param compra
	 */
	void crear(Compra compra);

	/**
	 * Permite determinar si la compra ya ha sido ingresada
	 * @param compra
	 * @return si existe o no
	 */
	boolean existe(Compra compra);
}
