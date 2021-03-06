/**
 * 
 */
package co.com.ceiba.compraventa.dominio.testdatabuilder;

import java.util.Date;

import co.com.ceiba.compraventa.dominio.modelo.Compra;
import co.com.ceiba.compraventa.dominio.modelo.Producto;

/**
 * @author raul.martinez
 *
 */
public class CompraTestDataBuilder {

	private Long idCompra;
	private String cedulaComprador;
	private String nombreComprador;
	private Date fechaCompra;
	private Long valorPagado;
	private Producto producto;
	
	public CompraTestDataBuilder() {
		this.idCompra = null;
		this.cedulaComprador = "50549708";
		this.nombreComprador = "Carla Baltazar Ortiz";
		this.fechaCompra = new Date();
		this.valorPagado = 679999L;
		this.producto = new ProductoTestDataBuilder().build();
	}
	
	public CompraTestDataBuilder conCedulaComprador(String cedulaComprador) {
		this.cedulaComprador = cedulaComprador;
		return this;
	}
	
	public CompraTestDataBuilder conNombreComprador(String nombreComprador) {
		this.nombreComprador = nombreComprador;
		return this;
	}
	
	public CompraTestDataBuilder conFechaCompra(Date fechaCompra) {
		this.fechaCompra = fechaCompra;
		return this;
	}
	
	public CompraTestDataBuilder conValorPagado(Long valorPagado) {
		this.valorPagado = valorPagado;
		return this;
	}
	
	public CompraTestDataBuilder conProducto(Producto producto) {
		this.producto = producto;
		return this;
	}
	
	public Compra build() {
		return new Compra(idCompra, cedulaComprador, nombreComprador, fechaCompra, valorPagado, producto);
	}
}
