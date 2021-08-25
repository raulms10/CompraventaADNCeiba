/**
 * 
 */
package co.com.ceiba.compraventa.infraestructura.integracion;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Date;
import java.util.Locale;

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
import co.com.ceiba.compraventa.dominio.excepcion.ExcepcionDescuento;
import co.com.ceiba.compraventa.dominio.excepcion.ExcepcionDiferenteValorPagado;
import co.com.ceiba.compraventa.dominio.excepcion.ExcepcionDuplicidad;
import co.com.ceiba.compraventa.dominio.excepcion.ExcepcionLongitudMaxima;
import co.com.ceiba.compraventa.dominio.excepcion.ExcepcionValorObligatorio;
import co.com.ceiba.compraventa.infraestructura.compartido.FormateadorFecha;
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
	
	private static final String LA_CEDULA_COMPRADOR_ES_DATO_OBLIGATORIO = "La cedula del comprador es un dato obligatoio.";
	private static final String EL_NOMBRE_COMPRADOR_ES_DATO_OBLIGATORIO = "El nombre del comprador es un dato obligatoio.";
	private static final String LA_FECHA_COMPRA_ES_DATO_OBLIGATORIO = "La fecha de compra es un dato obligatorio.";
	private static final String EL_VALOR_PAGADO_ES_DATO_OBLIGATORIO = "El valor pagado es un dato obligatorio.";
	
	private static final String LA_CEDULA_COMPRADOR_DEBE_TENER_MAXIMO_CARACTERES = "La cedula del comprador debe tener maximo %s caracteres.";
	private static final String EL_NOMBRE_COMPRADOR_DEBE_TENER_MAXIMO_CARACTERES = "El nombre del comprador debe tener maximo %s caracteres.";
	
	private static final String EL_PRODUCTO_HA_SIDO_VENDIDO = "El producto ya ha sido vendido.";
	private static final String EL_VALOR_PAGADO_DIFERENTE_A_VALOR = "El valor pagado es diferente al valor del producto";
	private static final String EL_VALOR_PAGADO_NO_ES_DESCUENTO = "El valor pagado no corresponde al descuento aplicado al producto";
	
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
    	objectMapper.setLocale(new Locale("es", "ES"));
    }
	
    @Test
    public void validarCrear() throws Exception {
        // Arrange
    	Date fecha = FormateadorFecha.getDate("2020-01-14");
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
    	Date fecha = FormateadorFecha.getDate("2020-01-14");
    	ComandoCompraTestDataBuilder comandoCompraTestDataBuilder = new ComandoCompraTestDataBuilder();
    	comandoCompraTestDataBuilder.conFechaCompra(fecha);
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
    	Date fecha = FormateadorFecha.getDate("2020-01-14");
    	ComandoCompraTestDataBuilder comandoCompraTestDataBuilder = new ComandoCompraTestDataBuilder();
    	comandoCompraTestDataBuilder.conFechaCompra(fecha);
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
    	Date fecha = FormateadorFecha.getDate("2020-01-14");
    	ComandoCompraTestDataBuilder comandoCompraTestDataBuilder = new ComandoCompraTestDataBuilder();
    	comandoCompraTestDataBuilder.conFechaCompra(fecha);
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
    	Date fecha = FormateadorFecha.getDate("2020-01-14");
    	ComandoCompraTestDataBuilder comandoCompraTestDataBuilder = new ComandoCompraTestDataBuilder();
    	comandoCompraTestDataBuilder.conFechaCompra(fecha);
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
    	Date fecha = FormateadorFecha.getDate("2020-01-14");
    	ComandoCompraTestDataBuilder comandoCompraTestDataBuilder = new ComandoCompraTestDataBuilder();
    	comandoCompraTestDataBuilder.conFechaCompra(fecha);
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
    	Date fecha = FormateadorFecha.getDate("2020-01-14");
    	ComandoCompraTestDataBuilder comandoCompraTestDataBuilder = new ComandoCompraTestDataBuilder();
    	comandoCompraTestDataBuilder.conFechaCompra(fecha);
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
    	Date fecha = FormateadorFecha.getDate("2020-01-14");
    	ComandoCompraTestDataBuilder comandoCompraTestDataBuilder = new ComandoCompraTestDataBuilder();
    	comandoCompraTestDataBuilder.conFechaCompra(fecha);
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
    
    @Test
    public void validarCrearConProductoVendido() throws Exception {
        // Arrange
    	Date fecha = FormateadorFecha.getDate("2020-01-15");
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
        this.mockMvc.perform(post(URL_COMPRAS)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(comandoCompra)))
        		.andExpect(status().isBadRequest())
        		.andExpect(jsonPath("$.nombreExcepcion").value(ExcepcionDuplicidad.class.getSimpleName()))
        		.andExpect(jsonPath("$.mensaje").value(EL_PRODUCTO_HA_SIDO_VENDIDO));
    }
    
    @Test
    public void validarCrearElViernesSinAplicarDescuento() throws Exception {
        // Arrange
    	Date fecha = FormateadorFecha.getDate("2020-01-17");
    	ComandoCompraTestDataBuilder comandoCompraTestDataBuilder = new ComandoCompraTestDataBuilder();
    	comandoCompraTestDataBuilder.conFechaCompra(fecha);
        ComandoCompra comandoCompra = comandoCompraTestDataBuilder.build();
        // Act - Assert
        this.mockMvc.perform(post(URL_COMPRAS)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(comandoCompra)))
        		.andExpect(status().isBadRequest())
        		.andExpect(jsonPath("$.nombreExcepcion").value(ExcepcionDescuento.class.getSimpleName()))
        		.andExpect(jsonPath("$.mensaje").value(EL_VALOR_PAGADO_NO_ES_DESCUENTO));
    }
    
    @Test
    public void validarCrearConDescuentoElViernes() throws Exception {
        // Arrange
    	Date fecha = FormateadorFecha.getDate("2020-01-17");
    	ComandoProductoTestDataBuilder comandoProductoTestDataBuilder = new ComandoProductoTestDataBuilder();
    	comandoProductoTestDataBuilder.conFecha(fecha);
    	comandoProductoTestDataBuilder.conValor(400000L);
    	comandoProductoTestDataBuilder.conDescuento(10L);
        ComandoProducto comandoProducto = comandoProductoTestDataBuilder.build();
        ComandoCompraTestDataBuilder comandoCompraTestDataBuilder = new ComandoCompraTestDataBuilder();
    	comandoCompraTestDataBuilder.conComandoProducto(comandoProducto);
    	comandoCompraTestDataBuilder.conFechaCompra(fecha);
    	comandoCompraTestDataBuilder.conValorPagado(360000L);
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
    public void validarCrearConValorPagadoDiferenteAValorNoViernes() throws Exception {
        // Arrange
    	Date fecha = FormateadorFecha.getDate("2020-01-13");
    	ComandoProductoTestDataBuilder comandoProductoTestDataBuilder = new ComandoProductoTestDataBuilder();
    	comandoProductoTestDataBuilder.conFecha(fecha);
    	comandoProductoTestDataBuilder.conValor(500000L);
        ComandoProducto comandoProducto = comandoProductoTestDataBuilder.build();
        ComandoCompraTestDataBuilder comandoCompraTestDataBuilder = new ComandoCompraTestDataBuilder();
    	comandoCompraTestDataBuilder.conComandoProducto(comandoProducto);
    	comandoCompraTestDataBuilder.conFechaCompra(fecha);
    	comandoCompraTestDataBuilder.conValorPagado(300000L);
        ComandoCompra comandoCompra = comandoCompraTestDataBuilder.build();
        
        // Act - Assert
        this.mockMvc.perform(post(URL_COMPRAS)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(comandoCompra)))
        		.andExpect(status().isBadRequest())
        		.andExpect(jsonPath("$.nombreExcepcion").value(ExcepcionDiferenteValorPagado.class.getSimpleName()))
        		.andExpect(jsonPath("$.mensaje").value(EL_VALOR_PAGADO_DIFERENTE_A_VALOR));
    }
}
