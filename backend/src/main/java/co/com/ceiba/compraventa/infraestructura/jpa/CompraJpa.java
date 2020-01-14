/**
 * 
 */
package co.com.ceiba.compraventa.infraestructura.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.com.ceiba.compraventa.infraestructura.entidad.CompraEntity;

/**
 * @author raul.martinez
 *
 */
@Repository
public interface CompraJpa extends JpaRepository<CompraEntity, Long> {

}
