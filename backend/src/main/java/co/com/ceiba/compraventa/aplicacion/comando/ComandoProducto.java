/**
 * 
 */
package co.com.ceiba.compraventa.aplicacion.comando;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

/**
 * @author raul.martinez
 *
 */
public class ComandoProducto {

	private String codigo;
	private String nombre;
	private Long valor;
	private Long descuento;
	private Date fecha;
	private String cedulaVendedor;
	private String nombreVendedor;
	private ComandoCompra compra;
	
	@JsonProperty(access = Access.WRITE_ONLY)
	private Date fechaEliminar;
	
	public ComandoProducto(String codigo, String nombre, Long valor, Long descuento, Date fecha, String cedulaVendedor, String nombreVendedor) {
		this.codigo = codigo;
		this.nombre = nombre;
		this.valor = valor;
		this.descuento = descuento;
		this.fecha = fecha == null ? null : new Date(fecha.getTime());
		this.cedulaVendedor = cedulaVendedor;
		this.nombreVendedor = nombreVendedor;
	}
	
	public ComandoProducto() {}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Long getValor() {
		return valor;
	}

	public void setValor(Long valor) {
		this.valor = valor;
	}

	public Long getDescuento() {
		return descuento;
	}

	public void setDescuento(Long descuento) {
		this.descuento = descuento;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getCedulaVendedor() {
		return cedulaVendedor;
	}

	public void setCedulaVendedor(String cedulaVendedor) {
		this.cedulaVendedor = cedulaVendedor;
	}

	public String getNombreVendedor() {
		return nombreVendedor;
	}

	public void setNombreVendedor(String nombreVendedor) {
		this.nombreVendedor = nombreVendedor;
	}

	public ComandoCompra getCompra() {
		return compra;
	}

	public void setCompra(ComandoCompra compra) {
		this.compra = compra;
	}

	public Date getFechaEliminar() {
		return fechaEliminar;
	}

	public void setFechaEliminar(Date fechaEliminar) {
		this.fechaEliminar = fechaEliminar;
	}	
}
