package co.com.ceiba.compraventa.aplicacion.comando;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import co.com.ceiba.compraventa.aplicacion.testdatabuilder.ComandoProductoTestDataBuilder;

class ComandoProductoTest {

	@Test
	public void validarCreacionProductoNoNulo() {
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
	
	@Test
	public void validarCreacionProductoNulo() {
		//Arrange - Act
		ComandoProducto comandoProducto = new ComandoProducto();
		// Assert
		Assert.assertNotNull(comandoProducto);	
		Assert.assertNull(comandoProducto.getCodigo());
		Assert.assertNull(comandoProducto.getNombre());
		Assert.assertNull(comandoProducto.getValor());
		Assert.assertNull(comandoProducto.getDescuento());
		Assert.assertNull(comandoProducto.getFecha());
		Assert.assertNull(comandoProducto.getCedulaVendedor());
		Assert.assertNull(comandoProducto.getNombreVendedor());
	}
	
	@Test
	public void validarProductoConFechaNula() {
		//Arrange
		ComandoProductoTestDataBuilder comandoProductoTestDataBuilder = new ComandoProductoTestDataBuilder();
		comandoProductoTestDataBuilder.conFecha(null);
		//Act
		ComandoProducto comandoProducto = comandoProductoTestDataBuilder.build();
		// Assert
		Assert.assertNotNull(comandoProducto);	
		Assert.assertNotNull(comandoProducto.getCodigo());
		Assert.assertNotNull(comandoProducto.getNombre());
		Assert.assertNotNull(comandoProducto.getValor());
		Assert.assertNotNull(comandoProducto.getDescuento());
		Assert.assertNull(comandoProducto.getFecha());
		Assert.assertNotNull(comandoProducto.getCedulaVendedor());
		Assert.assertNotNull(comandoProducto.getNombreVendedor());
	}

}
