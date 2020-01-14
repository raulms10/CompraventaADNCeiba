/**
 * 
 */
package co.com.ceiba.compraventa.infraestructura.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import co.com.ceiba.compraventa.infraestructura.entidad.ProductoEntity;

/**
 * @author raul.martinez
 *
 */
public interface ProductoJpa extends JpaRepository<ProductoEntity, String> {

}
