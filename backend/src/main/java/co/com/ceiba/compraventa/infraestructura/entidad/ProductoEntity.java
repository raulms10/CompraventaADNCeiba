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

import lombok.Getter;
import lombok.Setter;

/**
 * @author raul.martinez
 *
 */
@Entity
@Table(name = "producto")
@Getter
@Setter
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
}
