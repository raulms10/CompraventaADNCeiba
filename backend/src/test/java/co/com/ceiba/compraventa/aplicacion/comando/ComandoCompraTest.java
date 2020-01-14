/**
 * 
 */
package co.com.ceiba.compraventa.aplicacion.comando;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import co.com.ceiba.compraventa.aplicacion.testdatabuilder.ComandoCompraTestDataBuilder;

/**
 * @author raul.martinez
 *
 */
class ComandoCompraTest {

	@Test
	public void validarCreacionCompraNoNula() {
		//Arrange
		ComandoCompraTestDataBuilder comandoCompraTestDataBuilder = new ComandoCompraTestDataBuilder();
		//Act
		ComandoCompra comandoCompra = comandoCompraTestDataBuilder.build();
		// Assert
		Assert.assertNotNull(comandoCompra);
		Assert.assertNull(comandoCompra.getIdCompra());
		Assert.assertNotNull(comandoCompra.getCedulaComprador());
		Assert.assertNotNull(comandoCompra.getNombreComprador());
		Assert.assertNotNull(comandoCompra.getFechaCompra());
		Assert.assertNotNull(comandoCompra.getValorPagado());
		Assert.assertNotNull(comandoCompra.getComandoProducto());
	}
	
	@Test
	public void validarCreacionCompraNula() {
		//Arrange - Act
		ComandoCompra comandoCompra = new ComandoCompra();
		// Assert
		Assert.assertNotNull(comandoCompra);
		Assert.assertNull(comandoCompra.getIdCompra());
		Assert.assertNull(comandoCompra.getCedulaComprador());
		Assert.assertNull(comandoCompra.getNombreComprador());
		Assert.assertNull(comandoCompra.getFechaCompra());
		Assert.assertNull(comandoCompra.getValorPagado());
		Assert.assertNull(comandoCompra.getComandoProducto());
	}
	
	@Test
	public void validarCompraConFechaCompraNula() {
		//Arrange
		ComandoCompraTestDataBuilder comandoCompraTestDataBuilder = new ComandoCompraTestDataBuilder();
		comandoCompraTestDataBuilder.conFechaCompra(null);
		//Act
		ComandoCompra comandoCompra = comandoCompraTestDataBuilder.build();
		// Assert
		Assert.assertNotNull(comandoCompra);
		Assert.assertNull(comandoCompra.getIdCompra());
		Assert.assertNotNull(comandoCompra.getCedulaComprador());
		Assert.assertNotNull(comandoCompra.getNombreComprador());
		Assert.assertNull(comandoCompra.getFechaCompra());
		Assert.assertNotNull(comandoCompra.getValorPagado());
		Assert.assertNotNull(comandoCompra.getComandoProducto());
	}

}
