/**
 * 
 */
package co.com.ceiba.compraventa.infraestructura.sql;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import co.com.ceiba.compraventa.infraestructura.entidad.CompraEntity;
import co.com.ceiba.compraventa.infraestructura.entidad.ProductoEntity;

/**
 * @author raul.martinez
 *
 */
@Repository
public interface CompraSql extends CrudRepository<CompraEntity, Long>{
	
	List<CompraEntity> findByProducto(ProductoEntity producto);
}
