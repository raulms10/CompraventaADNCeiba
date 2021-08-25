/**
 * 
 */
package co.com.ceiba.compraventa.infraestructura.adaptador.repositorio;

import java.util.ArrayList;
import java.util.List;

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
		System.out.println("Producto creado");
	}

	@Override
	public boolean existe(Producto producto) {
		System.out.println("Producto verificado, no existe");
		return false;
	}

	@Override
	public List<ComandoProducto> listar(String cedulaVendedor) {
		List<ComandoProducto> listComandoProductos = new ArrayList<>();
		System.out.println("Producto listado");
		return listComandoProductos;
	}

	@Override
	public void eliminar(String codigo) {
		System.out.println("Producto eliminado");
	}

	@Override
	public boolean comprado(String codigo) {
		System.out.println("Producto comprado");
		return false;
	}

}
