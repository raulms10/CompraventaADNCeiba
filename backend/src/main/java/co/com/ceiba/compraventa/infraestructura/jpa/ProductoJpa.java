/**
 * 
 */
package co.com.ceiba.compraventa.infraestructura.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.com.ceiba.compraventa.infraestructura.entidad.ProductoEntity;

/**
 * @author raul.martinez
 *
 */
@Repository
public interface ProductoJpa extends JpaRepository<ProductoEntity, String> {

}
