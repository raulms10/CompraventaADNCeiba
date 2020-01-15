/**
 * 
 */
package co.com.ceiba.compraventa.infraestructura.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.com.ceiba.compraventa.infraestructura.entidad.CompraEntity;
import co.com.ceiba.compraventa.infraestructura.entidad.ProductoEntity;

/**
 * @author raul.martinez
 *
 */
@Repository
public interface CompraJpa extends JpaRepository<CompraEntity, Long> {
	
	List<CompraEntity> findByProducto(ProductoEntity producto);
	
}
