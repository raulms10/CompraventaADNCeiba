/**
 * 
 */
package co.com.ceiba.compraventa.infraestructura.configuracion;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import co.com.ceiba.compraventa.dominio.repositorio.RepositorioCompra;
import co.com.ceiba.compraventa.dominio.servicio.compra.ServicioCrearCompra;

/**
 * @author raul.martinez
 *
 */
@Configuration
public class CompraConfiguracionBean {

	@Bean
	public ServicioCrearCompra servicioCrearCompra(RepositorioCompra repositorioCompra) {
		return new ServicioCrearCompra(repositorioCompra);
	}
}
