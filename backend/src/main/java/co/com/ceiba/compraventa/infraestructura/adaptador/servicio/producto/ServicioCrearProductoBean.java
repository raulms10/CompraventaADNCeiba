/**
 * 
 */
package co.com.ceiba.compraventa.infraestructura.adaptador.servicio.producto;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import co.com.ceiba.compraventa.dominio.repositorio.RepositorioProducto;
import co.com.ceiba.compraventa.dominio.servicio.producto.ServicioCrearProducto;

/**
 * @author raul.martinez
 *
 */
@Configuration
public class ServicioCrearProductoBean {

	@Bean
	public ServicioCrearProducto servicioCrearProducto(RepositorioProducto repositorioProducto) {
		return new ServicioCrearProducto(repositorioProducto);
	}
}
