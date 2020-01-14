/**
 * 
 */
package co.com.ceiba.compraventa.infraestructura.adaptador.servicio.compra;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import co.com.ceiba.compraventa.dominio.repositorio.RepositorioCompra;
import co.com.ceiba.compraventa.dominio.servicio.compra.ServicioCrearCompra;

/**
 * @author raul.martinez
 *
 */
@Configuration
public class ServicioCrearCompraBean {

	@Bean
	public ServicioCrearCompra servicioCrearCompra(RepositorioCompra repositorioCompra) {
		return new ServicioCrearCompra(repositorioCompra);
	}
}
