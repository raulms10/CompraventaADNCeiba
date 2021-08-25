/**
 * 
 */
package co.com.ceiba.compraventa.infraestructura.sql;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import co.com.ceiba.compraventa.infraestructura.entidad.ProductoEntity;

/**
 * @author raul.martinez
 *
 */
public interface ProductoSql extends CrudRepository<ProductoEntity, String>{

	@Query("SELECT p FROM ProductoEntity p WHERE :cedula is null or p.cedulaVendedor = :cedula")
	List<ProductoEntity> findAllOrByCedulaVendedor(@Param("cedula") String cedulaVendedor);
	
	@Query("SELECT p FROM ProductoEntity p WHERE p.codigo = :codigo and p.compra.idCompra is not null")
	List<ProductoEntity> findByCompra(@Param("codigo") String codigo);
}
