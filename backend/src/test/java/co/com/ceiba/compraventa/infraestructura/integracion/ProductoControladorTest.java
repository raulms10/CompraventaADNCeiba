/**
 * 
 */
package co.com.ceiba.compraventa.infraestructura.integracion;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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
import co.com.ceiba.compraventa.dominio.excepcion.ExcepcionDuplicidad;
import co.com.ceiba.compraventa.dominio.excepcion.ExcepcionLongitudMaxima;
import co.com.ceiba.compraventa.dominio.excepcion.ExcepcionLunesViernes;
import co.com.ceiba.compraventa.dominio.excepcion.ExcepcionProductoComprado;
import co.com.ceiba.compraventa.dominio.excepcion.ExcepcionRango;
import co.com.ceiba.compraventa.dominio.excepcion.ExcepcionSabadoDomingo;
import co.com.ceiba.compraventa.dominio.excepcion.ExcepcionValorMinimo;
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
class ProductoControladorTest {
	
	private static final String EL_CODIGO_ES_DATO_OBLIGATORIO = "El codigo del producto es un dato obligatorio.";
	private static final String EL_NOMBRE_ES_DATO_OBLIGATORIO = "El nombre del producto es un dato obligatoio.";
	private static final String EL_VALOR_ES_DATO_OBLIGATORIO = "El valor del producto es un dato obligatoio.";
	private static final String EL_DESCUENTO_ES_DATO_OBLIGATORIO = "El descuento del producto es un dato obligatoio.";
	private static final String LA_FECHA_ES_DATO_OBLIGATORIO = "La fecha es un dato obligatorio.";
	private static final String LA_CEDULA_VENDEDOR_ES_DATO_OBLIGATORIO = "La cedula del vendedor es un dato obligatoio.";
	private static final String EL_NOMBRE_VENDEDOR_ES_DATO_OBLIGATORIO = "El nombre del vendedor es un dato obligatoio.";
	
	private static final String EL_CODIGO_DEBE_TENER_MAXIMO_CARACTERES = "El codigo del producto debe tener maximo %s caracteres.";
	private static final String EL_NOMBRE_DEBE_TENER_MAXIMO_CARACTERES = "El nombre del producto debe tener maximo %s caracteres.";
	private static final String LA_CEDULA_VENDEDOR_DEBE_TENER_MAXIMO_CARACTERES = "La cedula del vendedor debe tener maximo %s caracteres.";
	private static final String EL_NOMBRE_VENDEDOR_DEBE_TENER_MAXIMO_CARACTERES = "El nombre del vendedor debe tener maximo %s caracteres.";
	private static final String EL_DESCUENTO_DEBE_ESTAR_EN_EL_RANGO = "El porcentaje de descuento debe estar entre %s y %s";
	private static final String EL_VALOR_DEBE_SER_MAYOR_QUE = "El valor del producto debe ser mayor que %s";
	
	private static final int LONGITUD_MAXIMA_DE_CODIGO = 10;
	private static final int LONGITUD_MAXIMA_DE_NOMBRE = 30;
	private static final int LONGITUD_MAXIMA_DE_CEDULA_VENDEDOR = 12;
	private static final int LONGITUD_MAXIMA_DE_NOMBRE_VENDEDOR = 60;
	private static final Long MINIMO_VALOR_PERMITIDO = 0L;
	private static final Long MINIMO_DESCUENTO_PERMITIDO = 0L;
	private static final Long MAXIMO_DESCUENTO_PERMITIDO = 75L;
	
	private static final String SOLO_CREA_PRODCUTOS_LUNES_A_VIERNES = "Solo se permite crear productos de lunes a viernes.";
	private static final String PRODUCTO_YA_EXISTE = "El producto ya ha sido ingresado.";
	
	private static final String NO_ELIMINA_PRODUCTO_COMPRADO = "No es posible eliminar un producto comprado.";
	private static final String NO_ELIMINA_PRODUCTO_SABADO_O_DOMINGO = "No es posible eliminar un producto los dias sabados y domingos.";
	private static final String LA_FEHCA_ELIMINAR_ES_DATO_OBLIGATORIO = "La fecha para eliminar el producto es un dato obligatorio.";
	private static final String EL_CODIGO_PRODCUTO_ES_DATO_OBLIGATORIO = "El codigo del producto es un dato obligatorio.";
	
	private static final String URL_PRODUCTOS = "/productos";
	private static final String URL_COMPRAS = "/compras";

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
    public void validarCrear() throws Exception{
        // Arrange
    	ComandoProductoTestDataBuilder comandoProductoTestDataBuilder = new ComandoProductoTestDataBuilder();
    	comandoProductoTestDataBuilder.conFecha(new SimpleDateFormat("yyyy-MM-dd").parse("2020-01-14"));
        ComandoProducto comandoProducto = comandoProductoTestDataBuilder.build();
        // Act - Assert
        mockMvc.perform(post(URL_PRODUCTOS)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(comandoProducto)))
        		.andExpect(status().isOk());
    }
    
    @Test
    public void validarCrearConCodigoNulo() throws Exception {
    	// Arrange
    	ComandoProductoTestDataBuilder comandoProductoTestDataBuilder = new ComandoProductoTestDataBuilder();
    	comandoProductoTestDataBuilder.conCodigo(null);
        ComandoProducto comandoProducto = comandoProductoTestDataBuilder.build();
        // Act - Assert
        mockMvc.perform(post(URL_PRODUCTOS)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(comandoProducto)))
        		.andExpect(status().isBadRequest())
        		.andExpect(jsonPath("$.nombreExcepcion").value(ExcepcionValorObligatorio.class.getSimpleName()))
        		.andExpect(jsonPath("$.mensaje").value(EL_CODIGO_ES_DATO_OBLIGATORIO));
    }
    
    @Test
    public void validarCrearConNombreNulo() throws Exception {
    	// Arrange
    	ComandoProductoTestDataBuilder comandoProductoTestDataBuilder = new ComandoProductoTestDataBuilder();
    	comandoProductoTestDataBuilder.conNombre(null);
        ComandoProducto comandoProducto = comandoProductoTestDataBuilder.build();
        // Act - Assert
        mockMvc.perform(post(URL_PRODUCTOS)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(comandoProducto)))
        		.andExpect(status().isBadRequest())
        		.andExpect(jsonPath("$.nombreExcepcion").value(ExcepcionValorObligatorio.class.getSimpleName()))
        		.andExpect(jsonPath("$.mensaje").value(EL_NOMBRE_ES_DATO_OBLIGATORIO));
    }
    
    @Test
    public void validarCrearConValorNulo() throws Exception {
    	// Arrange
    	ComandoProductoTestDataBuilder comandoProductoTestDataBuilder = new ComandoProductoTestDataBuilder();
    	comandoProductoTestDataBuilder.conValor(null);
        ComandoProducto comandoProducto = comandoProductoTestDataBuilder.build();
        // Act - Assert
        mockMvc.perform(post(URL_PRODUCTOS)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(comandoProducto)))
        		.andExpect(status().isBadRequest())
        		.andExpect(jsonPath("$.nombreExcepcion").value(ExcepcionValorObligatorio.class.getSimpleName()))
        		.andExpect(jsonPath("$.mensaje").value(EL_VALOR_ES_DATO_OBLIGATORIO));
    }
    
    @Test
    public void validarCrearConDescuentoNulo() throws Exception {
    	// Arrange
    	ComandoProductoTestDataBuilder comandoProductoTestDataBuilder = new ComandoProductoTestDataBuilder();
    	comandoProductoTestDataBuilder.conDescuento(null);
        ComandoProducto comandoProducto = comandoProductoTestDataBuilder.build();
        // Act - Assert
        mockMvc.perform(post(URL_PRODUCTOS)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(comandoProducto)))
        		.andExpect(status().isBadRequest())
        		.andExpect(jsonPath("$.nombreExcepcion").value(ExcepcionValorObligatorio.class.getSimpleName()))
        		.andExpect(jsonPath("$.mensaje").value(EL_DESCUENTO_ES_DATO_OBLIGATORIO));
    }
    
    @Test
    public void validarCrearConFechaNula() throws Exception {
    	// Arrange
    	ComandoProductoTestDataBuilder comandoProductoTestDataBuilder = new ComandoProductoTestDataBuilder();
    	comandoProductoTestDataBuilder.conFecha(null);
        ComandoProducto comandoProducto = comandoProductoTestDataBuilder.build();
        // Act - Assert
        mockMvc.perform(post(URL_PRODUCTOS)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(comandoProducto)))
        		.andExpect(status().isBadRequest())
        		.andExpect(jsonPath("$.nombreExcepcion").value(ExcepcionValorObligatorio.class.getSimpleName()))
        		.andExpect(jsonPath("$.mensaje").value(LA_FECHA_ES_DATO_OBLIGATORIO));
    }
    
    @Test
    public void validarCrearConCedulaVendedorNula() throws Exception {
    	// Arrange
    	ComandoProductoTestDataBuilder comandoProductoTestDataBuilder = new ComandoProductoTestDataBuilder();
    	comandoProductoTestDataBuilder.conCedulaVendedor(null);
        ComandoProducto comandoProducto = comandoProductoTestDataBuilder.build();
        // Act - Assert
        mockMvc.perform(post(URL_PRODUCTOS)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(comandoProducto)))
        		.andExpect(status().isBadRequest())
        		.andExpect(jsonPath("$.nombreExcepcion").value(ExcepcionValorObligatorio.class.getSimpleName()))
        		.andExpect(jsonPath("$.mensaje").value(LA_CEDULA_VENDEDOR_ES_DATO_OBLIGATORIO));
    }
    
    @Test
    public void validarCrearConNombreVendedorNulo() throws Exception {
    	// Arrange
    	ComandoProductoTestDataBuilder comandoProductoTestDataBuilder = new ComandoProductoTestDataBuilder();
    	comandoProductoTestDataBuilder.conNombreVendedor(null);
        ComandoProducto comandoProducto = comandoProductoTestDataBuilder.build();
        // Act - Assert
        mockMvc.perform(post(URL_PRODUCTOS)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(comandoProducto)))
        		.andExpect(status().isBadRequest())
        		.andExpect(jsonPath("$.nombreExcepcion").value(ExcepcionValorObligatorio.class.getSimpleName()))
        		.andExpect(jsonPath("$.mensaje").value(EL_NOMBRE_VENDEDOR_ES_DATO_OBLIGATORIO));
    }
    
    @Test
    public void validarCrearConCodigoDeLongitudMaxima() throws Exception {
    	// Arrange
    	ComandoProductoTestDataBuilder comandoProductoTestDataBuilder = new ComandoProductoTestDataBuilder();
    	comandoProductoTestDataBuilder.conCodigo("784523213894395349857");
        ComandoProducto comandoProducto = comandoProductoTestDataBuilder.build();
        // Act - Assert
        mockMvc.perform(post(URL_PRODUCTOS)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(comandoProducto)))
        		.andExpect(status().isBadRequest())
        		.andExpect(jsonPath("$.nombreExcepcion").value(ExcepcionLongitudMaxima.class.getSimpleName()))
        		.andExpect(jsonPath("$.mensaje").value(String.format(EL_CODIGO_DEBE_TENER_MAXIMO_CARACTERES, LONGITUD_MAXIMA_DE_CODIGO)));
    }
    
    
    @Test
    public void validarCrearConNombreDeLongitudMaxima() throws Exception {
    	// Arrange
    	ComandoProductoTestDataBuilder comandoProductoTestDataBuilder = new ComandoProductoTestDataBuilder();
    	comandoProductoTestDataBuilder.conNombre("Este nombre es demasiado largo para crear un producto pruebe con otro nombre");
        ComandoProducto comandoProducto = comandoProductoTestDataBuilder.build();
        // Act - Assert
        mockMvc.perform(post(URL_PRODUCTOS)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(comandoProducto)))
        		.andExpect(status().isBadRequest())
        		.andExpect(jsonPath("$.nombreExcepcion").value(ExcepcionLongitudMaxima.class.getSimpleName()))
        		.andExpect(jsonPath("$.mensaje").value(String.format(EL_NOMBRE_DEBE_TENER_MAXIMO_CARACTERES, LONGITUD_MAXIMA_DE_NOMBRE)));
    }
    
    @Test
    public void validarCrearConCedulaVendedorDeLongitudMaxima() throws Exception {
    	// Arrange
    	ComandoProductoTestDataBuilder comandoProductoTestDataBuilder = new ComandoProductoTestDataBuilder();
    	comandoProductoTestDataBuilder.conCedulaVendedor("700555678093223");
        ComandoProducto comandoProducto = comandoProductoTestDataBuilder.build();
        // Act - Assert
        mockMvc.perform(post(URL_PRODUCTOS)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(comandoProducto)))
        		.andExpect(status().isBadRequest())
        		.andExpect(jsonPath("$.nombreExcepcion").value(ExcepcionLongitudMaxima.class.getSimpleName()))
        		.andExpect(jsonPath("$.mensaje").value(String.format(LA_CEDULA_VENDEDOR_DEBE_TENER_MAXIMO_CARACTERES, LONGITUD_MAXIMA_DE_CEDULA_VENDEDOR)));
    }
    
    @Test
    public void validarCrearConNombreVendedorDeLongitudMaxima() throws Exception {
    	// Arrange
    	ComandoProductoTestDataBuilder comandoProductoTestDataBuilder = new ComandoProductoTestDataBuilder();
    	comandoProductoTestDataBuilder.conNombreVendedor("Este nombre de vendedor es demasiado largo para crear un producto");
        ComandoProducto comandoProducto = comandoProductoTestDataBuilder.build();
        // Act - Assert
        mockMvc.perform(post(URL_PRODUCTOS)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(comandoProducto)))
        		.andExpect(status().isBadRequest())
        		.andExpect(jsonPath("$.nombreExcepcion").value(ExcepcionLongitudMaxima.class.getSimpleName()))
        		.andExpect(jsonPath("$.mensaje").value(String.format(EL_NOMBRE_VENDEDOR_DEBE_TENER_MAXIMO_CARACTERES, LONGITUD_MAXIMA_DE_NOMBRE_VENDEDOR)));
    }
    
    @Test
    public void validarCrearConDescuentoDeOchenta() throws Exception {
    	// Arrange
    	ComandoProductoTestDataBuilder comandoProductoTestDataBuilder = new ComandoProductoTestDataBuilder();
    	comandoProductoTestDataBuilder.conDescuento(80L);
        ComandoProducto comandoProducto = comandoProductoTestDataBuilder.build();
        // Act - Assert
        mockMvc.perform(post(URL_PRODUCTOS)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(comandoProducto)))
        		.andExpect(status().isBadRequest())
        		.andExpect(jsonPath("$.nombreExcepcion").value(ExcepcionRango.class.getSimpleName()))
        		.andExpect(jsonPath("$.mensaje").value(String.format(EL_DESCUENTO_DEBE_ESTAR_EN_EL_RANGO, MINIMO_DESCUENTO_PERMITIDO, MAXIMO_DESCUENTO_PERMITIDO)));
    }
    
    @Test
    public void validarCrearConDescuentoDeMenosDiez() throws Exception {
    	// Arrange
    	ComandoProductoTestDataBuilder comandoProductoTestDataBuilder = new ComandoProductoTestDataBuilder();
    	comandoProductoTestDataBuilder.conDescuento(-10L);
        ComandoProducto comandoProducto = comandoProductoTestDataBuilder.build();
        // Act - Assert
        mockMvc.perform(post(URL_PRODUCTOS)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(comandoProducto)))
        		.andExpect(status().isBadRequest())
        		.andExpect(jsonPath("$.nombreExcepcion").value(ExcepcionRango.class.getSimpleName()))
        		.andExpect(jsonPath("$.mensaje").value(String.format(EL_DESCUENTO_DEBE_ESTAR_EN_EL_RANGO, MINIMO_DESCUENTO_PERMITIDO, MAXIMO_DESCUENTO_PERMITIDO)));
    }
    
    @Test
    public void validarCrearConValorDeMenosMil() throws Exception {
    	// Arrange
    	ComandoProductoTestDataBuilder comandoProductoTestDataBuilder = new ComandoProductoTestDataBuilder();
    	comandoProductoTestDataBuilder.conValor(-1000L);
        ComandoProducto comandoProducto = comandoProductoTestDataBuilder.build();
        // Act - Assert
        mockMvc.perform(post(URL_PRODUCTOS)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(comandoProducto)))
        		.andExpect(status().isBadRequest())
        		.andExpect(jsonPath("$.nombreExcepcion").value(ExcepcionValorMinimo.class.getSimpleName()))
        		.andExpect(jsonPath("$.mensaje").value(String.format(EL_VALOR_DEBE_SER_MAYOR_QUE, MINIMO_VALOR_PERMITIDO)));
    }
    
    @Test
    public void validarCrearConFechaSabado() throws Exception {
    	// Arrange
    	ComandoProductoTestDataBuilder comandoProductoTestDataBuilder = new ComandoProductoTestDataBuilder();
    	comandoProductoTestDataBuilder.conFecha(new SimpleDateFormat("yyyy-MM-dd").parse("2020-01-11"));
        ComandoProducto comandoProducto = comandoProductoTestDataBuilder.build();
        // Act - Assert
        mockMvc.perform(post(URL_PRODUCTOS)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(comandoProducto)))
        		.andExpect(status().isBadRequest())
        		.andExpect(jsonPath("$.nombreExcepcion").value(ExcepcionLunesViernes.class.getSimpleName()))
        		.andExpect(jsonPath("$.mensaje").value(SOLO_CREA_PRODCUTOS_LUNES_A_VIERNES));
    }
    
    @Test
    public void validarCrearConFechaDomingo() throws Exception {
    	// Arrange
    	ComandoProductoTestDataBuilder comandoProductoTestDataBuilder = new ComandoProductoTestDataBuilder();
    	comandoProductoTestDataBuilder.conFecha(new SimpleDateFormat("yyyy-MM-dd").parse("2020-01-12"));
        ComandoProducto comandoProducto = comandoProductoTestDataBuilder.build();
        // Act - Assert
        mockMvc.perform(post(URL_PRODUCTOS)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(comandoProducto)))
        		.andExpect(status().isBadRequest())
        		.andExpect(jsonPath("$.nombreExcepcion").value(ExcepcionLunesViernes.class.getSimpleName()))
        		.andExpect(jsonPath("$.mensaje").value(SOLO_CREA_PRODCUTOS_LUNES_A_VIERNES));
    }
    
    @Test
    public void validarCrearProductoIngresado() throws Exception {
        // Arrange
    	ComandoProductoTestDataBuilder comandoProductoTestDataBuilder = new ComandoProductoTestDataBuilder();
    	comandoProductoTestDataBuilder.conFecha(new SimpleDateFormat("yyyy-MM-dd").parse("2020-01-14"));
        ComandoProducto comandoProducto = comandoProductoTestDataBuilder.build();
        // Act - Assert
        mockMvc.perform(post(URL_PRODUCTOS)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(comandoProducto)))
        		.andExpect(status().isOk());
        mockMvc.perform(post(URL_PRODUCTOS)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(comandoProducto)))
        		.andExpect(status().isBadRequest())
        		.andExpect(jsonPath("$.nombreExcepcion").value(ExcepcionDuplicidad.class.getSimpleName()))
        		.andExpect(jsonPath("$.mensaje").value(PRODUCTO_YA_EXISTE));
    }
       
    @Test 
    public void validarListar() throws Exception {
    	// Arrange
    	ComandoProductoTestDataBuilder comandoProductoTestDataBuilder = new ComandoProductoTestDataBuilder();
    	comandoProductoTestDataBuilder.conFecha(new SimpleDateFormat("yyyy-MM-dd").parse("2020-01-14"));
        ComandoProducto comandoProducto = comandoProductoTestDataBuilder.build();
        mockMvc.perform(post(URL_PRODUCTOS)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(comandoProducto)))
        		.andExpect(status().isOk());
    	// Act - Assert
        mockMvc.perform(get(URL_PRODUCTOS)
        		.contentType(MediaType.APPLICATION_JSON))
        		.andExpect(status().isOk())
        		.andExpect(jsonPath("$").isArray())
        		.andExpect(jsonPath("$[0].codigo").isNotEmpty());
    }
    
    @Test 
    public void validarListarConCedula111() throws Exception {
    	// Arrange
    	ComandoProductoTestDataBuilder comandoProductoTestDataBuilder = new ComandoProductoTestDataBuilder();
    	comandoProductoTestDataBuilder.conFecha(new SimpleDateFormat("yyyy-MM-dd").parse("2020-01-14"));
    	comandoProductoTestDataBuilder.conCedulaVendedor("111");
        ComandoProducto comandoProducto = comandoProductoTestDataBuilder.build();
        mockMvc.perform(post(URL_PRODUCTOS)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(comandoProducto)))
        		.andExpect(status().isOk());
    	// Act - Assert
        mockMvc.perform(get(URL_PRODUCTOS)
        		.param("cedula", "111"))
        		.andExpect(status().isOk())
        		.andExpect(jsonPath("$").isArray())
        		.andExpect(jsonPath("$[0].codigo").value(comandoProducto.getCodigo()))
        		.andExpect(jsonPath("$[0].cedulaVendedor").value("111"))
        		.andExpect(jsonPath("$[1]").doesNotExist());
    }
    
    @Test 
    public void validarEliminar() throws Exception {
    	// Arrange
    	ComandoProductoTestDataBuilder comandoProductoTestDataBuilder = new ComandoProductoTestDataBuilder();
    	comandoProductoTestDataBuilder.conCedulaVendedor("111");
    	comandoProductoTestDataBuilder.conCodigo("1010000001");
    	comandoProductoTestDataBuilder.conFecha(new SimpleDateFormat("yyyy-MM-dd").parse("2020-01-15"));
        ComandoProducto comandoProducto = comandoProductoTestDataBuilder.build();
        mockMvc.perform(post(URL_PRODUCTOS)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(comandoProducto)))
        		.andExpect(status().isOk());
    	// Act - Assert
        mockMvc.perform(delete(URL_PRODUCTOS)
        		.param("codigo", comandoProducto.getCodigo())
        		.param("fecha", "2020-01-15"))
		        .andExpect(status().isOk());
    }
    
    @Test 
    public void validarEliminarSabado() throws Exception {
    	// Arrange
    	ComandoProductoTestDataBuilder comandoProductoTestDataBuilder = new ComandoProductoTestDataBuilder();
    	comandoProductoTestDataBuilder.conCedulaVendedor("111");
    	comandoProductoTestDataBuilder.conFecha(new SimpleDateFormat("yyyy-MM-dd").parse("2020-01-15"));
        ComandoProducto comandoProducto = comandoProductoTestDataBuilder.build();
        mockMvc.perform(post(URL_PRODUCTOS)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(comandoProducto)))
        		.andExpect(status().isOk());
    	// Act - Assert
        mockMvc.perform(delete(URL_PRODUCTOS)
        		.param("codigo", comandoProducto.getCodigo())
        		.param("fecha", "2020-01-25"))
		        .andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.nombreExcepcion").value(ExcepcionSabadoDomingo.class.getSimpleName()))
				.andExpect(jsonPath("$.mensaje").value(NO_ELIMINA_PRODUCTO_SABADO_O_DOMINGO));
    }
    
    @Test 
    public void validarEliminarDomingo() throws Exception {
    	// Arrange
    	ComandoProductoTestDataBuilder comandoProductoTestDataBuilder = new ComandoProductoTestDataBuilder();
    	comandoProductoTestDataBuilder.conCedulaVendedor("111");
    	comandoProductoTestDataBuilder.conFecha(new SimpleDateFormat("yyyy-MM-dd").parse("2020-01-16"));
        ComandoProducto comandoProducto = comandoProductoTestDataBuilder.build();
        mockMvc.perform(post(URL_PRODUCTOS)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(comandoProducto)))
        		.andExpect(status().isOk());
    	// Act - Assert
        mockMvc.perform(delete(URL_PRODUCTOS)
        		.param("codigo", comandoProducto.getCodigo())
        		.param("fecha", "2020-01-26"))
		        .andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.nombreExcepcion").value(ExcepcionSabadoDomingo.class.getSimpleName()))
				.andExpect(jsonPath("$.mensaje").value(NO_ELIMINA_PRODUCTO_SABADO_O_DOMINGO));
    }
    
    @Test 
    public void validarEliminarCodigoNulo() throws Exception {
    	// Arrange
    	ComandoProductoTestDataBuilder comandoProductoTestDataBuilder = new ComandoProductoTestDataBuilder();
    	comandoProductoTestDataBuilder.conCedulaVendedor("111");
    	comandoProductoTestDataBuilder.conFecha(new SimpleDateFormat("yyyy-MM-dd").parse("2020-01-16"));
        ComandoProducto comandoProducto = comandoProductoTestDataBuilder.build();
        mockMvc.perform(post(URL_PRODUCTOS)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(comandoProducto)))
        		.andExpect(status().isOk());
    	// Act - Assert
        mockMvc.perform(delete(URL_PRODUCTOS)
        		.param("fecha", "2020-01-14"))
		        .andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.nombreExcepcion").value(ExcepcionValorObligatorio.class.getSimpleName()))
				.andExpect(jsonPath("$.mensaje").value(EL_CODIGO_PRODCUTO_ES_DATO_OBLIGATORIO));
    }
    
    @Test 
    public void validarFechaEliminarNula() throws Exception {
    	// Arrange
    	ComandoProductoTestDataBuilder comandoProductoTestDataBuilder = new ComandoProductoTestDataBuilder();
    	comandoProductoTestDataBuilder.conCedulaVendedor("111");
    	comandoProductoTestDataBuilder.conFecha(new SimpleDateFormat("yyyy-MM-dd").parse("2020-01-16"));
        ComandoProducto comandoProducto = comandoProductoTestDataBuilder.build();
        mockMvc.perform(post(URL_PRODUCTOS)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(comandoProducto)))
        		.andExpect(status().isOk());
    	// Act - Assert
        mockMvc.perform(delete(URL_PRODUCTOS)
        		.param("codigo", comandoProducto.getCodigo()))
		        .andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.nombreExcepcion").value(ExcepcionValorObligatorio.class.getSimpleName()))
				.andExpect(jsonPath("$.mensaje").value(LA_FEHCA_ELIMINAR_ES_DATO_OBLIGATORIO));
    }
    
    @Test 
    public void validarEliminarProductoComprado() throws Exception {
    	// Arrange
    	SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    	Date fecha = simpleDateFormat.parse("2020-01-14");
    	ComandoProductoTestDataBuilder comandoProductoTestDataBuilder = new ComandoProductoTestDataBuilder();
    	comandoProductoTestDataBuilder.conFecha(fecha);
        ComandoProducto comandoProducto = comandoProductoTestDataBuilder.build();
        ComandoCompraTestDataBuilder comandoCompraTestDataBuilder = new ComandoCompraTestDataBuilder();
    	comandoCompraTestDataBuilder.conComandoProducto(comandoProducto);
    	comandoCompraTestDataBuilder.conFechaCompra(fecha);
        ComandoCompra comandoCompra = comandoCompraTestDataBuilder.build();
     
        this.mockMvc.perform(post(URL_PRODUCTOS)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(comandoProducto)))
        		.andExpect(status().isOk());
        this.mockMvc.perform(post(URL_COMPRAS)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(comandoCompra)))
        		.andExpect(status().isOk());
               
        // Act - Assert
        mockMvc.perform(delete(URL_PRODUCTOS)
        		.param("codigo", comandoProducto.getCodigo())
        		.param("fecha", "2020-01-15"))
		        .andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.nombreExcepcion").value(ExcepcionProductoComprado.class.getSimpleName()))
				.andExpect(jsonPath("$.mensaje").value(NO_ELIMINA_PRODUCTO_COMPRADO));
    }
}
