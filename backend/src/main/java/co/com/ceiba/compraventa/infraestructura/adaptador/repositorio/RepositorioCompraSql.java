/**
 * 
 */
package co.com.ceiba.compraventa.infraestructura.adaptador.repositorio;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import co.com.ceiba.compraventa.dominio.modelo.Compra;
import co.com.ceiba.compraventa.dominio.repositorio.RepositorioCompra;
import co.com.ceiba.compraventa.infraestructura.entidad.CompraEntity;
import co.com.ceiba.compraventa.infraestructura.jpa.CompraJpa;
import co.com.ceiba.compraventa.infraestructura.sql.CompraSql;

/**
 * @author raul.martinez
 *
 */
@Repository
public class RepositorioCompraSql implements RepositorioCompra {

	@Autowired
	private final CompraSql compraSql;
	
	@Autowired
	private ModelMapper modelMapper;
	
	public RepositorioCompraSql(CompraSql compraSql) {
		this.compraSql = compraSql;
	}
	
	@Override
	public void crear(Compra compra) {
		System.out.println("Compra creada");
	}

	@Override
	public boolean existe(Compra compra) {
		CompraEntity compraEntity = modelMapper.map(compra, CompraEntity.class);
		System.out.println("Compra verificad, no existe");
		return false;
	}

}
