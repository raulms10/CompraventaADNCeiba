/**
 * 
 */
package co.com.ceiba.compraventa.dominio.entidad;

import java.text.ParseException;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import co.com.ceiba.compraventa.dominio.BasePrueba;
import co.com.ceiba.compraventa.dominio.excepcion.ExcepcionLongitudMaxima;
import co.com.ceiba.compraventa.dominio.excepcion.ExcepcionRango;
import co.com.ceiba.compraventa.dominio.excepcion.ExcepcionValorMinimo;
import co.com.ceiba.compraventa.dominio.excepcion.ExcepcionValorObligatorio;
import co.com.ceiba.compraventa.dominio.testdatabuilder.ProductoTestDataBuilder;

/**
 * @author raul.martinez
 *
 */
class ProductoTest {

	private static final String EL_CODIGO_ES_DATO_OBLIGATORIO = "El c<F3>digo del producto es un dato obligatorio.";
	private static final String EL_NOMBRE_ES_DATO_OBLIGATORIO = "El nombre del producto es un dato obligatoio.";
	private static final String EL_VALOR_ES_DATO_OBLIGATORIO = "El valor del producto es un dato obligatoio.";
	private static final String EL_DESCUENTO_ES_DATO_OBLIGATORIO = "El descuento del producto es un dato obligatoio.";
	private static final String LA_FECHA_ES_DATO_OBLIGATORIO = "La fecha es un dato obligatorio.";
	private static final String LA_CEDULA_VENDEDOR_ES_DATO_OBLIGATORIO = "La cedula del vendedor es un dato obligatoio.";
	private static final String EL_NOMBRE_VENDEDOR_ES_DATO_OBLIGATORIO = "El nombre del vendedor es un dato obligatoio.";
	
	private static final String EL_CODIGO_DEBE_TENER_MAXIMO_CARACTERES = "El c<F3>digo del producto debe tener m<E1>ximo %s caracteres.";
	private static final String EL_NOMBRE_DEBE_TENER_MAXIMO_CARACTERES = "El nombre del producto debe tener m<E1>ximo %s caracteres.";
	private static final String LA_CEDULA_VENDEDOR_DEBE_TENER_MAXIMO_CARACTERES = "La c<E9>dula del vendedor debe tener m<E1>ximo %s caracteres.";
	private static final String EL_NOMBRE_VENDEDOR_DEBE_TENER_MAXIMO_CARACTERES = "El nombre del vendedor debe tener m<E1>ximo %s caracteres.";
	private static final String EL_DESCUENTO_DEBE_ESTAR_EN_EL_RANGO = "El porcentaje de descuento debe estar entre %s y %s";
	private static final String EL_VALOR_DEBE_SER_MAYOR_QUE = "El valor del producto debe ser mayor que %s";
	
	private static final int LONGITUD_MAXIMA_DE_CODIGO = 10;
	private static final int LONGITUD_MAXIMA_DE_NOMBRE = 30;
	private static final int LONGITUD_MAXIMA_DE_CEDULA_VENDEDOR = 12;
	private static final int LONGITUD_MAXIMA_DE_NOMBRE_VENDEDOR = 60;
	private static final Long MINIMO_VALOR_PERMITIDO = 0L;
	private static final Long MINIMO_DESCUENTO_PERMITIDO = 0L;
	private static final Long MAXIMO_DESCUENTO_PERMITIDO = 75L;
	
	@Test
	public void validarCreacionProducto() {
		//Arrange
		ProductoTestDataBuilder productoTestDataBuilder = new ProductoTestDataBuilder();
		//Act
		Producto producto = productoTestDataBuilder.build();
		// Assert
		Assert.assertNotNull(producto);	
		Assert.assertNotNull(producto.getCodigo());
		Assert.assertNotNull(producto.getNombre());
		Assert.assertNotNull(producto.getValor());
		Assert.assertNotNull(producto.getDescuento());
		Assert.assertNotNull(producto.getFecha());
		Assert.assertNotNull(producto.getCedulaVendedor());
		Assert.assertNotNull(producto.getNombreVendedor());
	}
	
	@Test
	public void validarCodigoObligatorio() {
		//Arrange
		ProductoTestDataBuilder productoTestDataBuilder = new ProductoTestDataBuilder();
		productoTestDataBuilder.conCodigo(null);
		//Act - Assert
		BasePrueba.assertThrows(() -> productoTestDataBuilder.build(), ExcepcionValorObligatorio.class, EL_CODIGO_ES_DATO_OBLIGATORIO);
	}
	
	@Test
	public void validarNombreObligatorio() {
		//Arrange
		ProductoTestDataBuilder productoTestDataBuilder = new ProductoTestDataBuilder();
		productoTestDataBuilder.conNombre(null);
		//Act - Assert
		BasePrueba.assertThrows(() -> productoTestDataBuilder.build(), ExcepcionValorObligatorio.class, EL_NOMBRE_ES_DATO_OBLIGATORIO);	
	}
	
	@Test
	public void validarValorObligatorio() {
		//Arrange
		ProductoTestDataBuilder productoTestDataBuilder = new ProductoTestDataBuilder();
		productoTestDataBuilder.conValor(null);
		//Act - Assert
		BasePrueba.assertThrows(() -> productoTestDataBuilder.build(), ExcepcionValorObligatorio.class, EL_VALOR_ES_DATO_OBLIGATORIO);	
	}
	
	@Test
	public void validarDescuentoObligatorio() {
		//Arrange
		ProductoTestDataBuilder productoTestDataBuilder = new ProductoTestDataBuilder();
		productoTestDataBuilder.conDescuento(null);
		//Act - Assert
		BasePrueba.assertThrows(() -> productoTestDataBuilder.build(), ExcepcionValorObligatorio.class, EL_DESCUENTO_ES_DATO_OBLIGATORIO);	
	}
	
	@Test
	public void validarTipoObligatorio() {
		//Arrange
		ProductoTestDataBuilder productoTestDataBuilder = new ProductoTestDataBuilder();
		productoTestDataBuilder.conFecha(null);
		//Act - Assert
		BasePrueba.assertThrows(() -> productoTestDataBuilder.build(), ExcepcionValorObligatorio.class, LA_FECHA_ES_DATO_OBLIGATORIO);	
	}
	
	@Test
	public void validarCedulaVendedorObligatorio() {
		//Arrange
		ProductoTestDataBuilder productoTestDataBuilder = new ProductoTestDataBuilder();
		productoTestDataBuilder.conCedulaVEndedor(null);
		//Act - Assert
		BasePrueba.assertThrows(() -> productoTestDataBuilder.build(), ExcepcionValorObligatorio.class, LA_CEDULA_VENDEDOR_ES_DATO_OBLIGATORIO);	
	}
	
	@Test
	public void validarNombreVendedorObligatorio() {
		//Arrange
		ProductoTestDataBuilder productoTestDataBuilder = new ProductoTestDataBuilder();
		productoTestDataBuilder.conNombreVendedor(null);
		//Act - Assert
		BasePrueba.assertThrows(() -> productoTestDataBuilder.build(), ExcepcionValorObligatorio.class, EL_NOMBRE_VENDEDOR_ES_DATO_OBLIGATORIO);	
	}
	
	@Test
	public void validarLongitudMaximaCodigo() {
		//Arrange
		ProductoTestDataBuilder productoTestDataBuilder = new ProductoTestDataBuilder();
		productoTestDataBuilder.conCodigo("7704861277048612");
		//Act - Assert
		BasePrueba.assertThrows(() -> productoTestDataBuilder.build(), ExcepcionLongitudMaxima.class, String.format(EL_CODIGO_DEBE_TENER_MAXIMO_CARACTERES, LONGITUD_MAXIMA_DE_CODIGO));	
	}
	
	@Test
	public void validarLongitudMaximaNombre() {
		//Arrange
		ProductoTestDataBuilder productoTestDataBuilder = new ProductoTestDataBuilder();
		productoTestDataBuilder.conNombre("Este nombre de producto es demasiado largo");
		//Act - Assert
		BasePrueba.assertThrows(() -> productoTestDataBuilder.build(), ExcepcionLongitudMaxima.class, String.format(EL_NOMBRE_DEBE_TENER_MAXIMO_CARACTERES, LONGITUD_MAXIMA_DE_NOMBRE));		
	}
	
	@Test
	public void validarLongitudMaximaCedulaVendedor() {
		//Arrange
		ProductoTestDataBuilder productoTestDataBuilder = new ProductoTestDataBuilder();
		productoTestDataBuilder.conCedulaVEndedor("12356789012345667890");
		//Act - Assert
		BasePrueba.assertThrows(() -> productoTestDataBuilder.build(), ExcepcionLongitudMaxima.class, String.format(LA_CEDULA_VENDEDOR_DEBE_TENER_MAXIMO_CARACTERES, LONGITUD_MAXIMA_DE_CEDULA_VENDEDOR));			
	}
	
	@Test
	public void validarLongitudMaximaNombreVendedor() {
		//Arrange
		ProductoTestDataBuilder productoTestDataBuilder = new ProductoTestDataBuilder();
		productoTestDataBuilder.conNombreVendedor("Este nombre de vendedor es demasiado para ser aceptado en el sistema debe cambiarlo");
		//Act - Assert
		BasePrueba.assertThrows(() -> productoTestDataBuilder.build(), ExcepcionLongitudMaxima.class, String.format(EL_NOMBRE_VENDEDOR_DEBE_TENER_MAXIMO_CARACTERES, LONGITUD_MAXIMA_DE_NOMBRE_VENDEDOR));
	}
	
	@Test
	public void validarRangoDescuentoConNoventa() {
		//Arrange
		ProductoTestDataBuilder productoTestDataBuilder = new ProductoTestDataBuilder();
		productoTestDataBuilder.conDescuento(90L);
		//Act - Assert
		BasePrueba.assertThrows(() -> productoTestDataBuilder.build(), ExcepcionRango.class, String.format(EL_DESCUENTO_DEBE_ESTAR_EN_EL_RANGO, MINIMO_DESCUENTO_PERMITIDO, MAXIMO_DESCUENTO_PERMITIDO));
	}
	
	@Test
	public void validarRangoDescuentoConMenosCinco() {
		//Arrange
		ProductoTestDataBuilder productoTestDataBuilder = new ProductoTestDataBuilder();
		productoTestDataBuilder.conDescuento(-5L);
		//Act - Assert
		BasePrueba.assertThrows(() -> productoTestDataBuilder.build(), ExcepcionRango.class, String.format(EL_DESCUENTO_DEBE_ESTAR_EN_EL_RANGO, MINIMO_DESCUENTO_PERMITIDO, MAXIMO_DESCUENTO_PERMITIDO));
	}
	
	@Test
	public void validarValorConMenosDiez() {
		//Arrange
		ProductoTestDataBuilder productoTestDataBuilder = new ProductoTestDataBuilder();
		productoTestDataBuilder.conValor(-10L);
		//Act - Assert
		BasePrueba.assertThrows(() -> productoTestDataBuilder.build(), ExcepcionValorMinimo.class, String.format(EL_VALOR_DEBE_SER_MAYOR_QUE, MINIMO_VALOR_PERMITIDO));
	}
}
