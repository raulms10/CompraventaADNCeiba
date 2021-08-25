/**
 * 
 */
package co.com.ceiba.compraventa.infraestructura.configuracion;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;

import co.com.ceiba.compraventa.infraestructura.compartido.FormateadorFecha;

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
	
	@Bean
    public ObjectMapper objectMapper() {
	    DateFormat dateFormat = FormateadorFecha.getDateFormat();
	    ObjectMapper mapper = new ObjectMapper();
	    mapper.setDateFormat(dateFormat);
	    return mapper;
	}
}
