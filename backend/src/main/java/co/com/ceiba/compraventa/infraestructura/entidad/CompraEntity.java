/**
 * 
 */
package co.com.ceiba.compraventa.infraestructura.entidad;

import java.io.Serializable;
import java.util.Date;

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

import lombok.Getter;
import lombok.Setter;

/**
 * @author raul.martinez
 *
 */
@Entity
@Table(name = "compra")
@Getter
@Setter
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
	
	@OneToOne
	@JoinColumn(name = "producto", referencedColumnName = "codigo", nullable = false)
	private ProductoEntity producto;
}
