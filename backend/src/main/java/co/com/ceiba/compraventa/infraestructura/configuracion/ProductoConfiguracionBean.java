/**
 * 
 */
package co.com.ceiba.compraventa.infraestructura.configuracion;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import co.com.ceiba.compraventa.dominio.repositorio.RepositorioProducto;
import co.com.ceiba.compraventa.dominio.servicio.producto.ServicioCrearProducto;
import co.com.ceiba.compraventa.dominio.servicio.producto.ServicioEliminarProducto;
import co.com.ceiba.compraventa.dominio.servicio.producto.ServicioListarProducto;

/**
 * @author raul.martinez
 *
 */
@Configuration
public class ProductoConfiguracionBean {

	@Bean
	public ServicioCrearProducto servicioCrearProducto(RepositorioProducto repositorioProducto) {
		return new ServicioCrearProducto(repositorioProducto);
	}
	
	@Bean
	public ServicioListarProducto servicioListarProducto(RepositorioProducto repositorioProducto) {
		return new ServicioListarProducto(repositorioProducto);
	}
	
	@Bean
	public ServicioEliminarProducto servicioEliminarProducto(RepositorioProducto repositorioProducto) {
		return new ServicioEliminarProducto(repositorioProducto);
	}
}
