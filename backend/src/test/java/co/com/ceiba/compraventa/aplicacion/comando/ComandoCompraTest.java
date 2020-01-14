/**
 * 
 */
package co.com.ceiba.compraventa.aplicacion.comando;

import java.text.ParseException;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import co.com.ceiba.compraventa.aplicacion.testdatabuilder.ComandoCompraTestDataBuilder;

/**
 * @author raul.martinez
 *
 */
class ComandoCompraTest {

	@Test
	public void validarCreacionCompra() throws ParseException {
		//Arrange
		ComandoCompraTestDataBuilder comandoCompraTestDataBuilder = new ComandoCompraTestDataBuilder();
		//Act
		ComandoCompra comandoCompra = comandoCompraTestDataBuilder.build();
		// Assert
		Assert.assertNotNull(comandoCompra);
		Assert.assertNotNull(comandoCompra.getCedulaComprador());
		Assert.assertNotNull(comandoCompra.getNombreComprador());
		Assert.assertNotNull(comandoCompra.getFechaCompra());
		Assert.assertNotNull(comandoCompra.getValorPagado());
		Assert.assertNotNull(comandoCompra.getProducto());
	}

}
