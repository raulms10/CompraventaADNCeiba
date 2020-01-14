/**
 * 
 */
package co.com.ceiba.compraventa.infraestructura.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import co.com.ceiba.compraventa.infraestructura.entidad.CompraEntity;

/**
 * @author raul.martinez
 *
 */
public interface CompraJpa extends JpaRepository<CompraEntity, Long> {

	// void existe(String cedulaVendedor, String codigoProducto);
}
