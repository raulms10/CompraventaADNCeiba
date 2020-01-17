/**
 * 
 */
package co.com.ceiba.compraventa.infraestructura.configuracion;

import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author raul.martinez
 *
 */
@Configuration
public class WebMvcConfig {
	
	private static final String CONTEXT_APP = "/compraventa";

	@Bean
	public WebServerFactoryCustomizer<ConfigurableServletWebServerFactory> webServerFactoryCustomizer() {
	       return factory -> factory.setContextPath(CONTEXT_APP);
	}
}
