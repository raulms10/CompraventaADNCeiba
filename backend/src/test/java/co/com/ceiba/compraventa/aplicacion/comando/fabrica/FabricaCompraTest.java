package co.com.ceiba.compraventa.aplicacion.comando.fabrica;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import co.com.ceiba.compraventa.aplicacion.comando.ComandoCompra;
import co.com.ceiba.compraventa.aplicacion.testdatabuilder.ComandoCompraTestDataBuilder;
import co.com.ceiba.compraventa.dominio.entidad.Compra;
import co.com.ceiba.compraventa.dominio.testdatabuilder.CompraTestDataBuilder;

class FabricaCompraTest {

	@Test
	public void validarFabricaCompra() {
		//Arrange
		ComandoCompraTestDataBuilder comandoCompraTestDataBuilder = new ComandoCompraTestDataBuilder();
		ComandoCompra comandoCompra = comandoCompraTestDataBuilder.build();
		Compra com = new CompraTestDataBuilder().build();
		FabricaCompra fabricaCompra = Mockito.mock(FabricaCompra.class);
		Mockito.when(fabricaCompra.crearCompra(comandoCompra)).thenReturn(com);
		//Act
		Compra compra = fabricaCompra.crearCompra(comandoCompra);
		// Assert
		Assert.assertNotNull(compra);
		Assert.assertNull(compra.getIdCompra());
		Assert.assertNotNull(compra.getCedulaComprador());
		Assert.assertNotNull(compra.getNombreComprador());
		Assert.assertNotNull(compra.getFechaCompra());
		Assert.assertNotNull(compra.getValorPagado());
		Assert.assertNotNull(compra.getProducto());
	}
}
