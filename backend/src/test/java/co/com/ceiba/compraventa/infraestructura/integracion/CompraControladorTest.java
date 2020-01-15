/**
 * 
 */
package co.com.ceiba.compraventa.infraestructura.integracion;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.text.SimpleDateFormat;
import java.util.Date;

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
import co.com.ceiba.compraventa.aplicacion.comando.ComandoCompra;
import co.com.ceiba.compraventa.aplicacion.comando.ComandoProducto;
import co.com.ceiba.compraventa.dominio.excepcion.ExcepcionLongitudMaxima;
import co.com.ceiba.compraventa.dominio.excepcion.ExcepcionValorObligatorio;
import co.com.ceiba.compraventa.infraestructura.testdatabuilder.ComandoCompraTestDataBuilder;
import co.com.ceiba.compraventa.infraestructura.testdatabuilder.ComandoProductoTestDataBuilder;
/**
 * @author raul.martinez
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CompraventaApplication.class)
@AutoConfigureMockMvc
@Transactional
class CompraControladorTest {
	
	private static final String HA_OCURRIDO_ERROR_CONCTACTE_ADMINISTRADOR = "Ha ocurrido un error, por favor contactar al administrador.";
	
	private static final String LA_CEDULA_COMPRADOR_ES_DATO_OBLIGATORIO = "La c<E9>dula del comprador es un dato obligatoio.";
	private static final String EL_NOMBRE_COMPRADOR_ES_DATO_OBLIGATORIO = "El nombre del comprador es un dato obligatoio.";
	private static final String LA_FECHA_COMPRA_ES_DATO_OBLIGATORIO = "La fecha de compra es un dato obligatorio.";
	private static final String EL_VALOR_PAGADO_ES_DATO_OBLIGATORIO = "El valor pagado es un dato obligatorio.";
	
	private static final String LA_CEDULA_COMPRADOR_DEBE_TENER_MAXIMO_CARACTERES = "La c<E9>dula del comprador debe tener m<E1>ximo %s caracteres.";
	private static final String EL_NOMBRE_COMPRADOR_DEBE_TENER_MAXIMO_CARACTERES = "El nombre del comprador debe tener m<E1>ximo %s caracteres.";
	
	private static final int LONGITUD_MAXIMA_DE_CEDULA_COMPRADOR = 12;
	private static final int LONGITUD_MAXIMA_DE_NOMBRE_COMPRADOR = 60;
	
	private static final String URL_COMPRAS = "/compras";
	private static final String URL_PRODUCTOS = "/productos";
	
	@Autowired
	private WebApplicationContext context;
	
	@Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;
    
    @Before
    public void setup() {
    	this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context).build();
    }
	
    @Test
    public void validarCrear() throws Exception {
        // Arrange
    	Date fecha = new SimpleDateFormat("yyyy-MM-dd").parse("2020-01-14");
    	ComandoProductoTestDataBuilder comandoProductoTestDataBuilder = new ComandoProductoTestDataBuilder();
    	comandoProductoTestDataBuilder.conFecha(fecha);
        ComandoProducto comandoProducto = comandoProductoTestDataBuilder.build();
        ComandoCompraTestDataBuilder comandoCompraTestDataBuilder = new ComandoCompraTestDataBuilder();
    	comandoCompraTestDataBuilder.conComandoProducto(comandoProducto);
    	comandoCompraTestDataBuilder.conFechaCompra(fecha);
        ComandoCompra comandoCompra = comandoCompraTestDataBuilder.build();
        // Act - Assert
        this.mockMvc.perform(post(URL_PRODUCTOS)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(comandoProducto)))
        		.andExpect(status().isOk());
        this.mockMvc.perform(post(URL_COMPRAS)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(comandoCompra)))
        		.andExpect(status().isOk());
    }
    
    @Test
    public void validarCrearConCedulaCompradorNula() throws Exception {
        // Arrange
    	ComandoCompraTestDataBuilder comandoCompraTestDataBuilder = new ComandoCompraTestDataBuilder();
    	comandoCompraTestDataBuilder.conCedulaComprador(null);
        ComandoCompra comandoCompra = comandoCompraTestDataBuilder.build();
        // Act - Assert
        this.mockMvc.perform(post(URL_COMPRAS)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(comandoCompra)))
        		.andExpect(status().isBadRequest())
        		.andExpect(jsonPath("$.nombreExcepcion").value(ExcepcionValorObligatorio.class.getSimpleName()))
        		.andExpect(jsonPath("$.mensaje").value(LA_CEDULA_COMPRADOR_ES_DATO_OBLIGATORIO));
    }
    
    @Test
    public void validarCrearConNombreCompradorNulo() throws Exception {
        // Arrange
    	ComandoCompraTestDataBuilder comandoCompraTestDataBuilder = new ComandoCompraTestDataBuilder();
    	comandoCompraTestDataBuilder.conNombreComprador(null);
        ComandoCompra comandoCompra = comandoCompraTestDataBuilder.build();
        // Act - Assert
        this.mockMvc.perform(post(URL_COMPRAS)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(comandoCompra)))
        		.andExpect(status().isBadRequest())
        		.andExpect(jsonPath("$.nombreExcepcion").value(ExcepcionValorObligatorio.class.getSimpleName()))
        		.andExpect(jsonPath("$.mensaje").value(EL_NOMBRE_COMPRADOR_ES_DATO_OBLIGATORIO));
    }
    
    @Test
    public void validarCrearConFechaCompraNula() throws Exception {
        // Arrange
    	ComandoCompraTestDataBuilder comandoCompraTestDataBuilder = new ComandoCompraTestDataBuilder();
    	comandoCompraTestDataBuilder.conFechaCompra(null);
        ComandoCompra comandoCompra = comandoCompraTestDataBuilder.build();
        // Act - Assert
        this.mockMvc.perform(post(URL_COMPRAS)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(comandoCompra)))
        		.andExpect(status().isBadRequest())
        		.andExpect(jsonPath("$.nombreExcepcion").value(ExcepcionValorObligatorio.class.getSimpleName()))
        		.andExpect(jsonPath("$.mensaje").value(LA_FECHA_COMPRA_ES_DATO_OBLIGATORIO));
    }
    
    @Test
    public void validarCrearConValorPagadoNulo() throws Exception {
        // Arrange
    	ComandoCompraTestDataBuilder comandoCompraTestDataBuilder = new ComandoCompraTestDataBuilder();
    	comandoCompraTestDataBuilder.conValorPagado(null);
        ComandoCompra comandoCompra = comandoCompraTestDataBuilder.build();
        // Act - Assert
        this.mockMvc.perform(post(URL_COMPRAS)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(comandoCompra)))
        		.andExpect(status().isBadRequest())
        		.andExpect(jsonPath("$.nombreExcepcion").value(ExcepcionValorObligatorio.class.getSimpleName()))
        		.andExpect(jsonPath("$.mensaje").value(EL_VALOR_PAGADO_ES_DATO_OBLIGATORIO));
    }
    
    @Test
    public void validarCrearConComandoProductoNulo() throws Exception {
        // Arrange
    	ComandoCompraTestDataBuilder comandoCompraTestDataBuilder = new ComandoCompraTestDataBuilder();
    	comandoCompraTestDataBuilder.conComandoProducto(null);
        ComandoCompra comandoCompra = comandoCompraTestDataBuilder.build();
        // Act - Assert
        this.mockMvc.perform(post(URL_COMPRAS)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(comandoCompra)))
        		.andExpect(status().isInternalServerError())
        		.andExpect(jsonPath("$.nombreExcepcion").value(NullPointerException.class.getSimpleName()))
        		.andExpect(jsonPath("$.mensaje").value(HA_OCURRIDO_ERROR_CONCTACTE_ADMINISTRADOR));
    }
    
    @Test
    public void validarCrearConCedulaCompradorDeLongitudMaxima() throws Exception {
        // Arrange
    	ComandoCompraTestDataBuilder comandoCompraTestDataBuilder = new ComandoCompraTestDataBuilder();
    	comandoCompraTestDataBuilder.conCedulaComprador("700555678093223");
        ComandoCompra comandoCompra = comandoCompraTestDataBuilder.build();
        // Act - Assert
        this.mockMvc.perform(post(URL_COMPRAS)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(comandoCompra)))
        		.andExpect(status().isBadRequest())
        		.andExpect(jsonPath("$.nombreExcepcion").value(ExcepcionLongitudMaxima.class.getSimpleName()))
        		.andExpect(jsonPath("$.mensaje").value(String.format(LA_CEDULA_COMPRADOR_DEBE_TENER_MAXIMO_CARACTERES, LONGITUD_MAXIMA_DE_CEDULA_COMPRADOR)));
    }
    
    @Test
    public void validarCrearConNombreCompradorDeLongitudMaxima() throws Exception {
        // Arrange
    	ComandoCompraTestDataBuilder comandoCompraTestDataBuilder = new ComandoCompraTestDataBuilder();
    	comandoCompraTestDataBuilder.conNombreComprador("Este nombre de vendedor es demasiado largo para crear un producto");
        ComandoCompra comandoCompra = comandoCompraTestDataBuilder.build();
        // Act - Assert
        this.mockMvc.perform(post(URL_COMPRAS)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(comandoCompra)))
        		.andExpect(status().isBadRequest())
        		.andExpect(jsonPath("$.nombreExcepcion").value(ExcepcionLongitudMaxima.class.getSimpleName()))
        		.andExpect(jsonPath("$.mensaje").value(String.format(EL_NOMBRE_COMPRADOR_DEBE_TENER_MAXIMO_CARACTERES, LONGITUD_MAXIMA_DE_NOMBRE_COMPRADOR)));
    }
}
