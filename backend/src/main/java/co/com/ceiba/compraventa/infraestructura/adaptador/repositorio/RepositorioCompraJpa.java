/**
 * 
 */
package co.com.ceiba.compraventa.infraestructura.adaptador.repositorio;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import co.com.ceiba.compraventa.dominio.modelo.Compra;
import co.com.ceiba.compraventa.dominio.repositorio.RepositorioCompra;
import co.com.ceiba.compraventa.infraestructura.entidad.CompraEntity;
import co.com.ceiba.compraventa.infraestructura.jpa.CompraJpa;

/**
 * @author raul.martinez
 *
 */
@Repository
public class RepositorioCompraJpa implements RepositorioCompra {
	
	@Autowired
	private final CompraJpa compraJpa;
	
	private ModelMapper modelMapper = new ModelMapper();
	
	public RepositorioCompraJpa(CompraJpa compraJpa) {
		this.compraJpa = compraJpa;
	}

	@Override
	public void crear(Compra compra) {
		CompraEntity compraEntity = modelMapper.map(compra, CompraEntity.class);
		compraJpa.save(compraEntity);
	}

	@Override
	public boolean existe(Compra compra) {
		CompraEntity compraEntity = modelMapper.map(compra, CompraEntity.class);
		List<CompraEntity> listFindByProducto = compraJpa.findByProducto(compraEntity.getProducto());
		return !listFindByProducto.isEmpty();
	}
}
