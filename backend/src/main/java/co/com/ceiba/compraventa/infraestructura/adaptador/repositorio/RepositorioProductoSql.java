/**
 * 
 */
package co.com.ceiba.compraventa.infraestructura.adaptador.repositorio;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import co.com.ceiba.compraventa.aplicacion.comando.ComandoProducto;
import co.com.ceiba.compraventa.dominio.modelo.Producto;
import co.com.ceiba.compraventa.dominio.repositorio.RepositorioProducto;
import co.com.ceiba.compraventa.infraestructura.entidad.ProductoEntity;
import co.com.ceiba.compraventa.infraestructura.sql.ProductoSql;

/**
 * @author raul.martinez
 *
 */
@Repository
public class RepositorioProductoSql implements RepositorioProducto {

	@Autowired
	private final ProductoSql productoSql;
	
	@Autowired
	private ModelMapper modelMapper;
	
	public RepositorioProductoSql(ProductoSql productoSql) {
		this.productoSql = productoSql;
	}
	
	@Override
	public void crear(Producto producto) {
		ProductoEntity productoEntity = modelMapper.map(producto, ProductoEntity.class);
		productoSql.save(productoEntity);
	}

	@Override
	public boolean existe(Producto producto) {
		Optional<ProductoEntity> productoById = productoSql.findById(producto.getCodigo());
		return productoById.isPresent();
	}

	@Override
	public List<ComandoProducto> listar(String cedulaVendedor) {
		List<ComandoProducto> listComandoProductos = new ArrayList<>();
//		List<ProductoEntity> listEntities = productoSql.findAllOrByCedulaVendedor(cedulaVendedor);
		Iterable<ProductoEntity> listEntities = productoSql.findAll();
		for (ProductoEntity productoEntity : listEntities) {
			if (cedulaVendedor == null || cedulaVendedor.equals(productoEntity.getCedulaVendedor())) {
				ComandoProducto comandoProducto = modelMapper.map(productoEntity, ComandoProducto.class);
				listComandoProductos.add(comandoProducto);
			}
		}
		return listComandoProductos;
	}

	@Override
	public void eliminar(String codigo) {
		productoSql.deleteById(codigo);
	}

	@Override
	public boolean comprado(String codigoProducto) {
		List<ProductoEntity> listProductos = productoSql.findByCompra(codigoProducto);
		return !listProductos.isEmpty();
//		Optional<ProductoEntity> producto = productoSql.findById(codigoProducto);
//		return producto.isPresent() && producto.get().getCompra() != null;
//		return !listProductos.isEmpty();
	}

}
