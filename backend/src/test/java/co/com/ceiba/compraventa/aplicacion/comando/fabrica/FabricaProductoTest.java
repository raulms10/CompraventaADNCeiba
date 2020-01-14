/**
 * 
 */
package co.com.ceiba.compraventa.aplicacion.comando.fabrica;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import co.com.ceiba.compraventa.aplicacion.comando.ComandoProducto;
import co.com.ceiba.compraventa.aplicacion.comando.fabrica.FabricaProducto;
import co.com.ceiba.compraventa.aplicacion.testdatabuilder.ComandoProductoTestDataBuilder;
import co.com.ceiba.compraventa.dominio.entidad.Producto;
import co.com.ceiba.compraventa.dominio.testdatabuilder.ProductoTestDataBuilder;

/**
 * @author raul.martinez
 *
 */
class FabricaProductoTest {

	@Test
	public void validarFabricaProdcuto() {
		//Arrange
		ComandoProductoTestDataBuilder comandoProductoTestDataBuilder = new ComandoProductoTestDataBuilder();
		ComandoProducto comandoProducto = comandoProductoTestDataBuilder.build();
		Producto prod = new ProductoTestDataBuilder().build();
		FabricaProducto fabricaProducto = Mockito.mock(FabricaProducto.class);
		Mockito.when(fabricaProducto.crearProducto(comandoProducto)).thenReturn(prod);
		//Act
		Producto producto = fabricaProducto.crearProducto(comandoProducto);
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
}
