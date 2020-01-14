package co.com.ceiba.compraventa.aplicacion.comando;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import co.com.ceiba.compraventa.aplicacion.testdatabuilder.ComandoProductoTestDataBuilder;

class ComandoProductoTest {

	@Test
	public void validarCreacionProducto() {
		//Arrange
		ComandoProductoTestDataBuilder comandoProductoTestDataBuilder = new ComandoProductoTestDataBuilder();
		//Act
		ComandoProducto comandoProducto = comandoProductoTestDataBuilder.build();
		// Assert
		Assert.assertNotNull(comandoProducto);	
		Assert.assertNotNull(comandoProducto.getCodigo());
		Assert.assertNotNull(comandoProducto.getNombre());
		Assert.assertNotNull(comandoProducto.getValor());
		Assert.assertNotNull(comandoProducto.getDescuento());
		Assert.assertNotNull(comandoProducto.getFecha());
		Assert.assertNotNull(comandoProducto.getCedulaVendedor());
		Assert.assertNotNull(comandoProducto.getNombreVendedor());
	}
	
//	@Test
//	public void validarCodigoProducto() {
//		//Arrange
//		ComandoProductoTestDataBuilder comandoProductoTestDataBuilder = new ComandoProductoTestDataBuilder();
//		//Act
//		comandoProductoTestDataBuilder.conCodigo("770055");
//		ComandoProducto comandoProducto = comandoProductoTestDataBuilder.build();
//		// Assert
//		Assert.assertEquals("770055", comandoProducto.getCodigo());
//	}

}
