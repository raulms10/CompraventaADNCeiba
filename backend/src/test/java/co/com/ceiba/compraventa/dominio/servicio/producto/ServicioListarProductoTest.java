/**
 * 
 */
package co.com.ceiba.compraventa.dominio.servicio.producto;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import co.com.ceiba.compraventa.aplicacion.comando.ComandoProducto;
import co.com.ceiba.compraventa.dominio.repositorio.RepositorioProducto;
import co.com.ceiba.compraventa.infraestructura.testdatabuilder.ComandoProductoTestDataBuilder;

/**
 * @author raul.martinez
 *
 */
class ServicioListarProductoTest {

	@Test
	public void listar() {
		// Arrange
		List<ComandoProducto> listComandoProductos = new ArrayList<ComandoProducto>();
		ComandoProducto comandoProducto = new ComandoProductoTestDataBuilder().build();
		listComandoProductos.add(comandoProducto);
		RepositorioProducto repositorioProducto = Mockito.mock(RepositorioProducto.class);
		Mockito.when(repositorioProducto.listar(comandoProducto.getCedulaVendedor())).thenReturn(listComandoProductos);
		ServicioListarProducto servicioListarProducto = new ServicioListarProducto(repositorioProducto);
		// Act
		List<ComandoProducto> listServicio = servicioListarProducto.listar(comandoProducto.getCedulaVendedor());
		// Assert
		Assert.assertEquals(1, listServicio.size());
		Assert.assertEquals(listComandoProductos.get(0).getCodigo(), listServicio.get(0).getCodigo());
	}

}
