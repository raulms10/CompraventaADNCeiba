/**
 * 
 */
package co.com.ceiba.compraventa.dominio.servicio.compra;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import co.com.ceiba.compraventa.dominio.BasePrueba;
import co.com.ceiba.compraventa.dominio.excepcion.ExcepcionDuplicidad;
import co.com.ceiba.compraventa.dominio.modelo.Compra;
import co.com.ceiba.compraventa.dominio.modelo.Producto;
import co.com.ceiba.compraventa.dominio.repositorio.RepositorioCompra;
import co.com.ceiba.compraventa.dominio.servicio.compra.ServicioCrearCompra;
import co.com.ceiba.compraventa.dominio.testdatabuilder.CompraTestDataBuilder;
import co.com.ceiba.compraventa.dominio.testdatabuilder.ProductoTestDataBuilder;

/**
 * @author raul.martinez
 *
 */
class ServicioCrearCompraTest {
	
	private static final String PRODUCTO_HA_SIDO_VENDIDO = "El producto ya ha sido vendido.";

	@Test
	public void validarCrearCompra() throws ParseException {
		//Arrange
		Date fecha = new SimpleDateFormat("yyyy-MM-dd").parse("2020-01-16");
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
	public void validarExistenciaPrevia() {
		//Arrange
		Compra compra = new CompraTestDataBuilder().build();
		RepositorioCompra repositorioCompra = Mockito.mock(RepositorioCompra.class);
		Mockito.when(repositorioCompra.existe(Mockito.any())).thenReturn(true);
		ServicioCrearCompra servicioCrearCompra = new ServicioCrearCompra(repositorioCompra);
		//Act - Assert
		BasePrueba.assertThrows(() -> servicioCrearCompra.ejecutar(compra), ExcepcionDuplicidad.class, PRODUCTO_HA_SIDO_VENDIDO);
	}

}
