/**
 * 
 */
package co.com.ceiba.compraventa.infraestructura.entidad;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author raul.martinez
 *
 */
@Entity
@Table(name = "compra")
public class CompraEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "id_compra")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idCompra;
	
	@Column(name = "cedula_comprador")
	private String cedulaComprador;
	
	@Column(name = "nombre_comprador")
	private String nombreComprador;
	
	@Column(name = "fecha_compra")
	@Temporal(TemporalType.DATE)
	private Date fechaCompra;
	
	@Column(name = "valor_pagado")
	private Long valorPagado;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "producto", referencedColumnName = "codigo", nullable = false)
	private ProductoEntity producto;

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
		return fechaCompra == null ? null : new Date(fechaCompra.getTime());
	}

	public void setFechaCompra(Date fechaCompra) {
		this.fechaCompra = fechaCompra == null ? null : new Date(fechaCompra.getTime());
	}

	public Long getValorPagado() {
		return valorPagado;
	}

	public void setValorPagado(Long valorPagado) {
		this.valorPagado = valorPagado;
	}

	public ProductoEntity getProducto() {
		return producto;
	}

	public void setProducto(ProductoEntity producto) {
		this.producto = producto;
	}
}
