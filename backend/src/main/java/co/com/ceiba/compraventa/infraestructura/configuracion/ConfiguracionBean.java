/**
 * 
 */
package co.com.ceiba.compraventa.infraestructura.configuracion;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author raul.martinez
 *
 */
@Configuration
public class ConfiguracionBean {

	@Bean
	public ModelMapper modelMapper() {
	    return new ModelMapper();
	}
}
