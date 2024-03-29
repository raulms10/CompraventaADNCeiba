/**
 * 
 */
package co.com.ceiba.compraventa.infraestructura.adaptador.repositorio;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import co.com.ceiba.compraventa.aplicacion.comando.ComandoProducto;
import co.com.ceiba.compraventa.dominio.modelo.Producto;
import co.com.ceiba.compraventa.dominio.repositorio.RepositorioProducto;
import co.com.ceiba.compraventa.infraestructura.entidad.ProductoEntity;
import co.com.ceiba.compraventa.infraestructura.jpa.ProductoJpa;

/**
 * @author raul.martinez
 *
 */
//@Repository
public class RepositorioProductoJpa {//implements RepositorioProducto {
	
	//@Autowired
	private final ProductoJpa productoJpa;
	
	//@Autowired
	private ModelMapper modelMapper;
	
	public RepositorioProductoJpa(ProductoJpa productoJpa) {
		this.productoJpa = productoJpa;
	}

	//@Override
	public void crear(Producto producto) {
		ProductoEntity productoEntity = modelMapper.map(producto, ProductoEntity.class);
		//productoJpa.save(productoEntity);
	}

	//@Override
	public boolean existe(Producto producto) {
		//Optional<ProductoEntity> productoById = productoJpa.findById(producto.getCodigo());
		//return productoById.isPresent();
		return false;
	}

	//@Override
	public List<ComandoProducto> listar(String cedulaVendedor) {
		List<ComandoProducto> listComandoProductos = new ArrayList<>();
		List<ProductoEntity> listEntities = productoJpa.findAllOrByCedulaVendedor(cedulaVendedor);
		for (ProductoEntity productoEntity : listEntities) {
			ComandoProducto comandoProducto = modelMapper.map(productoEntity, ComandoProducto.class);
			listComandoProductos.add(comandoProducto);
		}
		return listComandoProductos;
	}

	//@Override
	public void eliminar(String codigo) {
		//productoJpa.deleteById(codigo);
	}

	//@Override
	public boolean comprado(String codigo) {
		List<ProductoEntity> listProductos = productoJpa.findByCompra(codigo);
		return !listProductos.isEmpty();
	}
}
