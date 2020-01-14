/**
 * 
 */
package co.com.ceiba.compraventa.infraestructura.adaptador.repositorio;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import co.com.ceiba.compraventa.dominio.entidad.Producto;
import co.com.ceiba.compraventa.dominio.repositorio.RepositorioProducto;
import co.com.ceiba.compraventa.infraestructura.entidad.ProductoEntity;
import co.com.ceiba.compraventa.infraestructura.jpa.ProductoJpa;

/**
 * @author raul.martinez
 *
 */
@Repository
public class RepositorioProductoJpa implements RepositorioProducto {
	
	@Autowired
	private final ProductoJpa productoJpa;
	
	private ModelMapper modelMapper = new ModelMapper();
	
	public RepositorioProductoJpa(ProductoJpa productoJpa) {
		this.productoJpa = productoJpa;
	}

	@Override
	public void crear(Producto producto) {
		ProductoEntity productoEntity = modelMapper.map(producto, ProductoEntity.class);
		productoJpa.save(productoEntity);
	}

	@Override
	public boolean existe(Producto producto) {
		return false;
	}

}