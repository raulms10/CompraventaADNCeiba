/**
 * 
 */
package co.com.ceiba.compraventa.dominio.servicio.compra;

import java.text.ParseException;
import java.util.Date;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import co.com.ceiba.compraventa.dominio.BasePrueba;
import co.com.ceiba.compraventa.dominio.excepcion.ExcepcionDescuento;
import co.com.ceiba.compraventa.dominio.excepcion.ExcepcionDiferenteValorPagado;
import co.com.ceiba.compraventa.dominio.excepcion.ExcepcionDuplicidad;
import co.com.ceiba.compraventa.dominio.modelo.Compra;
import co.com.ceiba.compraventa.dominio.modelo.Producto;
import co.com.ceiba.compraventa.dominio.repositorio.RepositorioCompra;
import co.com.ceiba.compraventa.dominio.testdatabuilder.CompraTestDataBuilder;
import co.com.ceiba.compraventa.dominio.testdatabuilder.ProductoTestDataBuilder;
import co.com.ceiba.compraventa.infraestructura.compartido.FormateadorFecha;

/**
 * @author raul.martinez
 *
 */
class ServicioCrearCompraTest {
	
	private static final String EL_PRODUCTO_HA_SIDO_VENDIDO = "El producto ya ha sido vendido.";
	private static final String EL_VALOR_PAGADO_DIFERENTE_A_VALOR = "El valor pagado es diferente al valor del producto";
	private static final String EL_VALOR_PAGADO_NO_ES_DESCUENTO = "El valor pagado no corresponde al descuento aplicado al producto";

	@Test
	public void validarCrearCompra() throws ParseException {
		//Arrange
		Date fecha = FormateadorFecha.getDate("2020-01-16");
		ProductoTestDataBuilder productoTestDataBuilder = new ProductoTestDataBuilder();
		productoTestDataBuilder.conFecha(fecha);
		Producto producto = productoTestDataBuilder.build();
		
		CompraTestDataBuilder compraTestDataBuilder = new CompraTestDataBuilder();
		compraTestDataBuilder.conFechaCompra(fecha);
		compraTestDataBuilder.conProducto(producto);
		
		Compra compra = compraTestDataBuilder.build();
		RepositorioCompra repositorioCompra = Mockito.mock(RepositorioCompra.class);
		ServicioCrearCompra servicioCrearCompra = new ServicioCrearCompra(repositorioCompra);
		//Act
		servicioCrearCompra.ejecutar(compra);
		//Assert
		Assert.assertTrue(true);
	}
	
	@Test
	public void validarCrearCompraConDescuentoElViernes() throws ParseException {
		//Arrange
		Date fecha = FormateadorFecha.getDate("2020-01-17");
		ProductoTestDataBuilder productoTestDataBuilder = new ProductoTestDataBuilder();
		productoTestDataBuilder.conFecha(fecha);
		productoTestDataBuilder.conValor(500000L);
		productoTestDataBuilder.conDescuento(10L);
		Producto producto = productoTestDataBuilder.build();
		
		CompraTestDataBuilder compraTestDataBuilder = new CompraTestDataBuilder();
		compraTestDataBuilder.conFechaCompra(fecha);
		compraTestDataBuilder.conValorPagado(450000L);
		compraTestDataBuilder.conProducto(producto);
		
		Compra compra = compraTestDataBuilder.build();
		RepositorioCompra repositorioCompra = Mockito.mock(RepositorioCompra.class);
		ServicioCrearCompra servicioCrearCompra = new ServicioCrearCompra(repositorioCompra);
		//Act
		servicioCrearCompra.ejecutar(compra);
		//Assert
		Assert.assertTrue(true);
	}
	
	@Test
	public void validarExistenciaPrevia() throws ParseException {
		//Arrange
		Date fecha = FormateadorFecha.getDate("2020-01-15");
		CompraTestDataBuilder compraTestDataBuilder = new CompraTestDataBuilder();
		compraTestDataBuilder.conFechaCompra(fecha);
		Compra compra = compraTestDataBuilder.build();
		RepositorioCompra repositorioCompra = Mockito.mock(RepositorioCompra.class);
		Mockito.when(repositorioCompra.existe(Mockito.any())).thenReturn(true);
		ServicioCrearCompra servicioCrearCompra = new ServicioCrearCompra(repositorioCompra);
		//Act - Assert
		BasePrueba.assertThrows(() -> servicioCrearCompra.ejecutar(compra), ExcepcionDuplicidad.class, EL_PRODUCTO_HA_SIDO_VENDIDO);
	}
	
	
	@Test
	public void validarValorPagadoSinAplicarDescuentoViernes() throws ParseException {
		//Arrange
		Date fecha = FormateadorFecha.getDate("2020-01-17");
		CompraTestDataBuilder compraTestDataBuilder = new CompraTestDataBuilder();
		compraTestDataBuilder.conFechaCompra(fecha);
		Compra compra = compraTestDataBuilder.build();
		RepositorioCompra repositorioCompra = Mockito.mock(RepositorioCompra.class);
		Mockito.when(repositorioCompra.existe(Mockito.any())).thenReturn(true);
		ServicioCrearCompra servicioCrearCompra = new ServicioCrearCompra(repositorioCompra);
		//Act - Assert
		BasePrueba.assertThrows(() -> servicioCrearCompra.ejecutar(compra), ExcepcionDescuento.class, EL_VALOR_PAGADO_NO_ES_DESCUENTO);
	}
	
	@Test
	public void validarValorPagadoDiferenteAValorNoViernes() throws ParseException {
		//Arrange
		Date fecha = FormateadorFecha.getDate("2020-01-14");
		ProductoTestDataBuilder productoTestDataBuilder = new ProductoTestDataBuilder();
		productoTestDataBuilder.conFecha(fecha);
		productoTestDataBuilder.conValor(250000L);
		Producto producto = productoTestDataBuilder.build();
		CompraTestDataBuilder compraTestDataBuilder = new CompraTestDataBuilder();
		compraTestDataBuilder.conFechaCompra(fecha);
		compraTestDataBuilder.conValorPagado(100000L);
		compraTestDataBuilder.conProducto(producto);
		Compra compra = compraTestDataBuilder.build();
		RepositorioCompra repositorioCompra = Mockito.mock(RepositorioCompra.class);
		Mockito.when(repositorioCompra.existe(Mockito.any())).thenReturn(true);
		ServicioCrearCompra servicioCrearCompra = new ServicioCrearCompra(repositorioCompra);
		//Act - Assert
		BasePrueba.assertThrows(() -> servicioCrearCompra.ejecutar(compra), ExcepcionDiferenteValorPagado.class, EL_VALOR_PAGADO_DIFERENTE_A_VALOR);
	}

}
