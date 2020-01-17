/**
 * 
 */
package co.com.ceiba.compraventa.infraestructura.entidad;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author raul.martinez
 *
 */
@Entity
@Table(name = "producto")
public class ProductoEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "codigo")
	private String codigo;
	
	@Column(name = "nombre")
	private String nombre;
	
	@Column(name = "valor")
	private Long valor;
	
	@Column(name = "descuento")
	private Long descuento;
	
	@Column(name = "fecha")
	@Temporal(TemporalType.DATE)
	private Date fecha;
	
	@Column(name = "cedula_vendedor")
	private String cedulaVendedor;
	
	@Column(name = "nombre_vendedor")
	private String nombreVendedor;
	
	@OneToOne(mappedBy = "producto")
	private CompraEntity compra;
	
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
		return fecha = new Date(fecha.getTime());
	}

	public void setFecha(Date fecha) {
		this.fecha = new Date(fecha.getTime());
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

	public CompraEntity getCompra() {
		return compra;
	}

	public void setCompra(CompraEntity compra) {
		this.compra = compra;
	}
}
