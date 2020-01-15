/**
 * 
 */
package co.com.ceiba.compraventa.aplicacion.testdatabuilder;

import java.util.Date;

import co.com.ceiba.compraventa.aplicacion.comando.ComandoCompra;
import co.com.ceiba.compraventa.aplicacion.comando.ComandoProducto;
/**
 * @author raul.martinez
 *
 */
public class ComandoCompraTestDataBuilder {

	private Long idCompra;
	private String cedulaComprador;
	private String nombreComprador;
	private Date fechaCompra;
	private Long valorPagado;
	private ComandoProducto comandoProducto;
	
	public ComandoCompraTestDataBuilder() {
		this.idCompra = null;
		this.cedulaComprador = "1063281456";
		this.nombreComprador = "Andres David Cardona Cano";
		this.fechaCompra = new Date();
		this.valorPagado = 1785999L;
		this.comandoProducto = new ComandoProductoTestDataBuilder().build();
	}
	
	public ComandoCompraTestDataBuilder conCedulaComprador(String cedulaComprador) {
		this.cedulaComprador = cedulaComprador;
		return this;
	}
	
	public ComandoCompraTestDataBuilder conNombreComprador(String nombreComprador) {
		this.nombreComprador = nombreComprador;
		return this;
	}
	
	public ComandoCompraTestDataBuilder conFechaCompra(Date fehcaCompra) {
		this.fechaCompra = fehcaCompra;
		return this;
	}
	
	public ComandoCompraTestDataBuilder conValorPagado(Long valorPagado) {
		this.valorPagado = valorPagado;
		return this;
	}
	
	public ComandoCompraTestDataBuilder conComandoProducto(ComandoProducto comandoProducto) {
		this.comandoProducto = comandoProducto;
		return this;
	}
	
	public ComandoCompra build() {
		return new ComandoCompra(idCompra, cedulaComprador, nombreComprador, fechaCompra, valorPagado, comandoProducto);
	}
}
