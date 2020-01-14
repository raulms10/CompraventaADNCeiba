/**
 * 
 */
package co.com.ceiba.compraventa.dominio.entidad;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import co.com.ceiba.compraventa.dominio.BasePrueba;
import co.com.ceiba.compraventa.dominio.excepcion.ExcepcionLongitudMaxima;
import co.com.ceiba.compraventa.dominio.excepcion.ExcepcionValorObligatorio;
import co.com.ceiba.compraventa.dominio.testdatabuilder.CompraTestDataBuilder;

/**
 * @author raul.martinez
 *
 */
class CompraTest {
	
	private static final String LA_CEDULA_COMPRADOR_ES_DATO_OBLIGATORIO = "La c<E9>dula del comprador es un dato obligatoio.";
	private static final String EL_NOMBRE_COMPRADOR_ES_DATO_OBLIGATORIO = "El nombre del comprador es un dato obligatoio.";
	private static final String LA_FECHA_COMPRA_ES_DATO_OBLIGATORIO = "La fecha de compra es un dato obligatorio.";
	private static final String EL_VALOR_PAGADO_ES_DATO_OBLIGATORIO = "El valor pagado es un dato obligatorio.";
	private static final String EL_PRODUCTO_ES_DATO_OBLIGATORIO = "El producto es un dato obligatorio.";
	
	private static final String LA_CEDULA_COMPRADOR_DEBE_TENER_MAXIMO_CARACTERES = "La c<E9>dula del comprador debe tener m<E1>ximo %s caracteres.";
	private static final String EL_NOMBRE_COMPRADOR_DEBE_TENER_MAXIMO_CARACTERES = "El nombre del comprador debe tener m<E1>ximo %s caracteres.";
	
	private static final int LONGITUD_MAXIMA_DE_CEDULA_COMPRADOR = 12;
	private static final int LONGITUD_MAXIMA_DE_NOMBRE_COMPRADOR = 60;

	@Test
	public void validarCreacionCompra() {
		//Arrange
		CompraTestDataBuilder compraTestDataBuilder = new CompraTestDataBuilder();
		//Act
		Compra compra = compraTestDataBuilder.build();
		// Assert
		Assert.assertNotNull(compra);
		Assert.assertNotNull(compra.getCedulaComprador());
		Assert.assertNotNull(compra.getNombreComprador());
		Assert.assertNotNull(compra.getFechaCompra());
		Assert.assertNotNull(compra.getValorPagado());
		Assert.assertNotNull(compra.getProducto());
	}
	
	@Test
	public void validarCedulaVendedorObligatorio() {
		//Arrange
		CompraTestDataBuilder compraTestDataBuilder = new CompraTestDataBuilder();
		compraTestDataBuilder.conCedulaComprador(null);
		//Act - Assert
		BasePrueba.assertThrows(() -> compraTestDataBuilder.build(), ExcepcionValorObligatorio.class, LA_CEDULA_COMPRADOR_ES_DATO_OBLIGATORIO);	
	}
	
	@Test
	public void validarNombreVendedorObligatorio() {
		//Arrange
		CompraTestDataBuilder compraTestDataBuilder = new CompraTestDataBuilder();
		compraTestDataBuilder.conNombreComprador(null);
		//Act - Assert
		BasePrueba.assertThrows(() -> compraTestDataBuilder.build(), ExcepcionValorObligatorio.class, EL_NOMBRE_COMPRADOR_ES_DATO_OBLIGATORIO);		
	}
	
	@Test
	public void validarFechaCompraObligatorio() {
		//Arrange
		CompraTestDataBuilder compraTestDataBuilder = new CompraTestDataBuilder();
		compraTestDataBuilder.conFechaCompra(null);
		//Act - Assert
		BasePrueba.assertThrows(() -> compraTestDataBuilder.build(), ExcepcionValorObligatorio.class, LA_FECHA_COMPRA_ES_DATO_OBLIGATORIO);		
	}
	
	@Test
	public void validarValorPagadoObligatorio() {
		//Arrange
		CompraTestDataBuilder compraTestDataBuilder = new CompraTestDataBuilder();
		compraTestDataBuilder.conValorPagado(null);
		//Act - Assert
		BasePrueba.assertThrows(() -> compraTestDataBuilder.build(), ExcepcionValorObligatorio.class, EL_VALOR_PAGADO_ES_DATO_OBLIGATORIO);		
	}
	
	@Test
	public void validarProductoObligatorio() {
		//Arrange
		CompraTestDataBuilder compraTestDataBuilder = new CompraTestDataBuilder();
		compraTestDataBuilder.conProducto(null);
		//Act - Assert
		BasePrueba.assertThrows(() -> compraTestDataBuilder.build(), ExcepcionValorObligatorio.class, EL_PRODUCTO_ES_DATO_OBLIGATORIO);		
	}
	
	@Test
	public void validarLongitudMaximaCedulaVendedor() {
		//Arrange
		CompraTestDataBuilder compraTestDataBuilder = new CompraTestDataBuilder();
		compraTestDataBuilder.conCedulaComprador("123456789012345");
		//Act - Assert
		BasePrueba.assertThrows(() -> compraTestDataBuilder.build(), ExcepcionLongitudMaxima.class, String.format(LA_CEDULA_COMPRADOR_DEBE_TENER_MAXIMO_CARACTERES, LONGITUD_MAXIMA_DE_CEDULA_COMPRADOR));						
	}
	
	@Test
	public void validarLongitudMaximaNombreVendedor() {
		//Arrange
		CompraTestDataBuilder compraTestDataBuilder = new CompraTestDataBuilder();
		compraTestDataBuilder.conNombreComprador("Este nombre de comprador es demasiado largo para ser aceptado intente con otro");
		//Act - Assert
		BasePrueba.assertThrows(() -> compraTestDataBuilder.build(), ExcepcionLongitudMaxima.class, String.format(EL_NOMBRE_COMPRADOR_DEBE_TENER_MAXIMO_CARACTERES, LONGITUD_MAXIMA_DE_NOMBRE_COMPRADOR));						
	}
	
}
