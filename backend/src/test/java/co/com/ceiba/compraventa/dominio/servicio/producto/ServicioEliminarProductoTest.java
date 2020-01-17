/**
 * 
 */
package co.com.ceiba.compraventa.dominio.servicio.producto;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import co.com.ceiba.compraventa.dominio.BasePrueba;
import co.com.ceiba.compraventa.dominio.excepcion.ExcepcionProductoComprado;
import co.com.ceiba.compraventa.dominio.excepcion.ExcepcionSabadoDomingo;
import co.com.ceiba.compraventa.dominio.excepcion.ExcepcionValorObligatorio;
import co.com.ceiba.compraventa.dominio.modelo.Producto;
import co.com.ceiba.compraventa.dominio.repositorio.RepositorioProducto;
import co.com.ceiba.compraventa.dominio.testdatabuilder.ProductoTestDataBuilder;

/**
 * @author raul.martinez
 *
 */
class ServicioEliminarProductoTest {

	private static final String NO_ELIMINA_PRODUCTO_COMPRADO = "No es posible eliminar un producto comprado.";
	private static final String NO_ELIMINA_PRODUCTO_SABADO_O_DOMINGO = "No es posible eliminar un producto los d<ED>as s<E1>bados y domingos.";
	private static final String LA_FEHCA_ELIMINAR_ES_DATO_OBLIGATORIO = "La fecha para eliminar el producto es un dato obligatorio.";
	
	@Test
	public void validarEliminarProducto() throws ParseException {
		//Arrange
		ProductoTestDataBuilder productoTestDataBuilder = new ProductoTestDataBuilder();
		Date fecha = new SimpleDateFormat("yyyy-MM-dd").parse("2020-01-15");
		RepositorioProducto repositorioProducto = Mockito.mock(RepositorioProducto.class);
		ServicioEliminarProducto servicioEliminarProducto = new ServicioEliminarProducto(repositorioProducto);
		Producto producto = productoTestDataBuilder.build();
		//Act
		servicioEliminarProducto.ejecutar(producto, fecha);
		//Assert
		Assert.assertTrue(true);
	}
	
	@Test
	public void validarEliminarProductoSabado() throws ParseException {
		//Arrange
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date fecha = simpleDateFormat.parse("2020-01-18");
		ProductoTestDataBuilder productoTestDataBuilder = new ProductoTestDataBuilder();
		Producto producto = productoTestDataBuilder.build();
		RepositorioProducto repositorioProducto = Mockito.mock(RepositorioProducto.class);
		ServicioEliminarProducto servicioEliminarProducto = new ServicioEliminarProducto(repositorioProducto);
		//Act - Assert
		BasePrueba.assertThrows(() -> servicioEliminarProducto.ejecutar(producto, fecha), ExcepcionSabadoDomingo.class, NO_ELIMINA_PRODUCTO_SABADO_O_DOMINGO);
	}
	
	@Test
	public void validarEliminarProductoDomingo() throws ParseException {
		//Arrange
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date fecha = simpleDateFormat.parse("2020-01-19");
		ProductoTestDataBuilder productoTestDataBuilder = new ProductoTestDataBuilder();
		Producto producto = productoTestDataBuilder.build();
		RepositorioProducto repositorioProducto = Mockito.mock(RepositorioProducto.class);
		ServicioEliminarProducto servicioEliminarProducto = new ServicioEliminarProducto(repositorioProducto);
		//Act - Assert
		BasePrueba.assertThrows(() -> servicioEliminarProducto.ejecutar(producto, fecha), ExcepcionSabadoDomingo.class, NO_ELIMINA_PRODUCTO_SABADO_O_DOMINGO);
	}
	
	@Test
	public void validarEliminarProductoComprado() throws ParseException {
		//Arrange
		ProductoTestDataBuilder productoTestDataBuilder = new ProductoTestDataBuilder();
		Date fecha = new SimpleDateFormat("yyyy-MM-dd").parse("2020-01-13");
		Producto producto = productoTestDataBuilder.build();
		RepositorioProducto repositorioProducto = Mockito.mock(RepositorioProducto.class);
		Mockito.when(repositorioProducto.comprado(Mockito.any())).thenReturn(true);
		ServicioEliminarProducto servicioEliminarProducto = new ServicioEliminarProducto(repositorioProducto);
		//Act - Assert
		BasePrueba.assertThrows(() -> servicioEliminarProducto.ejecutar(producto, fecha), ExcepcionProductoComprado.class, NO_ELIMINA_PRODUCTO_COMPRADO);
	}
	
	@Test
	public void validarEliminarConFechaNula() throws ParseException {
		//Arrange
		ProductoTestDataBuilder productoTestDataBuilder = new ProductoTestDataBuilder();
		Producto producto = productoTestDataBuilder.build();
		RepositorioProducto repositorioProducto = Mockito.mock(RepositorioProducto.class);
		ServicioEliminarProducto servicioEliminarProducto = new ServicioEliminarProducto(repositorioProducto);
		//Act - Assert
		BasePrueba.assertThrows(() -> servicioEliminarProducto.ejecutar(producto, null), ExcepcionValorObligatorio.class, LA_FEHCA_ELIMINAR_ES_DATO_OBLIGATORIO);
	}

}
