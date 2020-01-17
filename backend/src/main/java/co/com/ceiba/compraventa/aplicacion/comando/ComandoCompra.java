/**
 * 
 */
package co.com.ceiba.compraventa.aplicacion.comando;

import java.util.Date;

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
	private ComandoProducto comandoProducto;
	
	public ComandoCompra(Long idCompra, String cedulaComprador, String nombreComprador, Date fechaCompra, Long valorPagado, ComandoProducto comandoProducto) {
		this.idCompra = idCompra;
		this.cedulaComprador = cedulaComprador;
		this.nombreComprador = nombreComprador;
		this.fechaCompra = fechaCompra == null ? null : new Date(fechaCompra.getTime());
		this.valorPagado = valorPagado;
		this.comandoProducto = comandoProducto;
	}
	
	public ComandoCompra() {}

	public Long getIdCompra() {
		return idCompra;
	}

	public void setIdCompra(Long idCompra) {
		this.idCompra = idCompra;
	}

	public String getCedulaComprador() {
		return cedulaComprador;
	}

	public void setCedulaComprador(String cedulaComprador) {
		this.cedulaComprador = cedulaComprador;
	}

	public String getNombreComprador() {
		return nombreComprador;
	}

	public void setNombreComprador(String nombreComprador) {
		this.nombreComprador = nombreComprador;
	}

	public Date getFechaCompra() {
		return fechaCompra;
	}

	public void setFechaCompra(Date fechaCompra) {
		this.fechaCompra = fechaCompra;
	}

	public Long getValorPagado() {
		return valorPagado;
	}

	public void setValorPagado(Long valorPagado) {
		this.valorPagado = valorPagado;
	}

	public ComandoProducto getComandoProducto() {
		return comandoProducto;
	}

	public void setComandoProducto(ComandoProducto comandoProducto) {
		this.comandoProducto = comandoProducto;
	}
}
