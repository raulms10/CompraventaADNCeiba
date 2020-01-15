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
import co.com.ceiba.compraventa.dominio.excepcion.ExcepcionDuplicidad;
import co.com.ceiba.compraventa.dominio.excepcion.ExcepcionLunesViernes;
import co.com.ceiba.compraventa.dominio.modelo.Producto;
import co.com.ceiba.compraventa.dominio.repositorio.RepositorioProducto;
import co.com.ceiba.compraventa.dominio.servicio.producto.ServicioCrearProducto;
import co.com.ceiba.compraventa.dominio.testdatabuilder.ProductoTestDataBuilder;

/**
 * @author raul.martinez
 *
 */
class ServicioCrearProductoTest {
	
	private static final String PRODUCTO_YA_EXISTE = "El producto ya ha sido ingresado.";
	private static final String SOLO_CREA_PRODCUTOS_LUNES_A_VIERNES = "Solo se permite crear productos de lunes a viernes.";
		
	@Test
	public void validarCrearProducto() throws ParseException {
		//Arrange
		ProductoTestDataBuilder productoTestDataBuilder = new ProductoTestDataBuilder();
		Date fecha = new SimpleDateFormat("yyyy-MM-dd").parse("2020-01-15");
		productoTestDataBuilder.conFecha(fecha);
		RepositorioProducto repositorioProducto = Mockito.mock(RepositorioProducto.class);
		ServicioCrearProducto servicioCrearProducto = new ServicioCrearProducto(repositorioProducto);
		Producto producto = productoTestDataBuilder.build();
		//Act
		servicioCrearProducto.ejecutar(producto);
		//Assert
		Assert.assertTrue(true);
	}
	
	@Test
	public void validarFechaCrearProductoSabado() throws ParseException {
		//Arrange
		ProductoTestDataBuilder productoTestDataBuilder = new ProductoTestDataBuilder();
		Date fecha = new SimpleDateFormat("yyyy-MM-dd").parse("2020-01-11");
		productoTestDataBuilder.conFecha(fecha);
		Producto producto = productoTestDataBuilder.build();
		RepositorioProducto repositorioProducto = Mockito.mock(RepositorioProducto.class);
		ServicioCrearProducto servicioCrearProducto = new ServicioCrearProducto(repositorioProducto);
		//Act - Assert
		BasePrueba.assertThrows(() -> servicioCrearProducto.ejecutar(producto), ExcepcionLunesViernes.class, SOLO_CREA_PRODCUTOS_LUNES_A_VIERNES);
	}
	
	@Test
	public void validarFechaCrearProductoDomingo() throws ParseException {
		//Arrange
		ProductoTestDataBuilder productoTestDataBuilder = new ProductoTestDataBuilder();
		Date fecha = new SimpleDateFormat("yyyy-MM-dd").parse("2020-01-12");
		productoTestDataBuilder.conFecha(fecha);
		Producto producto = productoTestDataBuilder.build();
		RepositorioProducto repositorioProducto = Mockito.mock(RepositorioProducto.class);
		ServicioCrearProducto servicioCrearProducto = new ServicioCrearProducto(repositorioProducto);
		//Act - Assert
		BasePrueba.assertThrows(() -> servicioCrearProducto.ejecutar(producto), ExcepcionLunesViernes.class, SOLO_CREA_PRODCUTOS_LUNES_A_VIERNES);
	}
	
	@Test
	public void validarExistenciaPrevia() throws ParseException {
		//Arrange
		ProductoTestDataBuilder productoTestDataBuilder = new ProductoTestDataBuilder();
		Date fecha = new SimpleDateFormat("yyyy-MM-dd").parse("2020-01-14");
		productoTestDataBuilder.conFecha(fecha);
		Producto producto = productoTestDataBuilder.build();
		RepositorioProducto repositorioProducto = Mockito.mock(RepositorioProducto.class);
		Mockito.when(repositorioProducto.existe(Mockito.any())).thenReturn(true);
		ServicioCrearProducto servicioCrearProducto = new ServicioCrearProducto(repositorioProducto);
		//Act - Assert
		BasePrueba.assertThrows(() -> servicioCrearProducto.ejecutar(producto), ExcepcionDuplicidad.class, PRODUCTO_YA_EXISTE);
	}

}
