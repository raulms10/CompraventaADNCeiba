/**
 * 
 */
package co.com.ceiba.compraventa.infraestructura.sql;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import co.com.ceiba.compraventa.infraestructura.entidad.CompraEntity;

/**
 * @author raul.martinez
 *
 */
@Repository
public interface CompraSql extends CrudRepository<CompraEntity, Long>{
	
}
