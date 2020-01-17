/**
 * 
 */
package co.com.ceiba.compraventa.infraestructura.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import co.com.ceiba.compraventa.infraestructura.entidad.ProductoEntity;

/**
 * @author raul.martinez
 *
 */
@Repository
public interface ProductoJpa extends JpaRepository<ProductoEntity, String> {
	
	@Query("SELECT p FROM ProductoEntity p WHERE :cedula is null or p.cedulaVendedor = :cedula")
	List<ProductoEntity> findAllOrByCedulaVendedor(@Param("cedula") String cedulaVendedor);
	
	@Query("SELECT p FROM ProductoEntity p WHERE p.codigo = :codigo and p.compra.idCompra is not null")
	List<ProductoEntity> findByCompra(@Param("codigo") String codigo);

}
