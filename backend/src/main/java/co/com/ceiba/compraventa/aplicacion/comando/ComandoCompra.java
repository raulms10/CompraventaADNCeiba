/**
 * 
 */
package co.com.ceiba.compraventa.aplicacion.comando;

import java.util.Date;

import co.com.ceiba.compraventa.dominio.entidad.Producto;

/**
 * @author raul.martinez
 *
 */
public class ComandoCompra {

	private Long idCompra;
	private String cedulaComprador;
	private String nombreComprador;
	private Date fechaCompra;
	private Long valorPagado;
	private Producto producto;
	
	public ComandoCompra() {}
	
	public ComandoCompra(Long idCompra, String cedulaComprador, String nombreComprador, Date fechaCompra, Long valorPagado, Producto producto) {
		this.idCompra = idCompra;
		this.cedulaComprador = cedulaComprador;
		this.nombreComprador = nombreComprador;
		this.fechaCompra = fechaCompra;
		this.valorPagado = valorPagado;
		this.producto = producto;
	}

	public Long getIdCompra() {
		return idCompra;
	}

	public String getCedulaComprador() {
		return cedulaComprador;
	}
	
	public String getNombreComprador() {
		return nombreComprador;
	}
	
	public Date getFechaCompra() {
		return fechaCompra;
	}
	
	public Long getValorPagado() {
		return valorPagado;
	}
	
	public Producto getProducto() {
		return producto;
	}
}
