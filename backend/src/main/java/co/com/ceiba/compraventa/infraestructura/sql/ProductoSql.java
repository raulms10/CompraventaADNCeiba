/**
 * 
 */
package co.com.ceiba.compraventa.infraestructura.sql;

import org.springframework.data.repository.CrudRepository;

import co.com.ceiba.compraventa.infraestructura.entidad.ProductoEntity;

/**
 * @author raul.martinez
 *
 */
public interface ProductoSql extends CrudRepository<ProductoEntity, String>{

}
