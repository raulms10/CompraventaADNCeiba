/**
 * 
 */
package co.com.ceiba.compraventa.infraestructura.controlador;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import javax.transaction.Transactional;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;

import co.com.ceiba.compraventa.CompraventaApplication;
import co.com.ceiba.compraventa.aplicacion.comando.ComandoProducto;
import co.com.ceiba.compraventa.aplicacion.testdatabuilder.ComandoProductoTestDataBuilder;

/**
 * @author raul.martinez
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CompraventaApplication.class)
@AutoConfigureMockMvc
@Transactional
class ProductoControladorTest {

	@Autowired
	private WebApplicationContext context;
	
	@Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mocMvc;
    
    @Before
    public void setup() {
    	this.mocMvc = MockMvcBuilders.webAppContextSetup(this.context).build();
    }
	
    @Test
    public void crear() throws Exception{
        // Arrange
        ComandoProducto comandoProducto = new ComandoProductoTestDataBuilder().build();

        // Act - Assert
        mocMvc.perform(post("/producto/crear")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(comandoProducto)))
        		.andExpect(status().isOk());
    }

}
