/**
 * 
 */
package co.com.ceiba.compraventa.aplicacion.comando;

import java.util.Date;

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
	
	public ComandoProducto() {}
	
	public ComandoProducto(String codigo, String nombre, Long valor, Long descuento, Date fecha, String cedulaVendedor, String nombreVendedor) {
		this.codigo = codigo;
		this.nombre = nombre;
		this.valor = valor;
		this.descuento = descuento;
		this.fecha = fecha;
		this.cedulaVendedor = cedulaVendedor;
		this.nombreVendedor = nombreVendedor;
	}

	public String getCodigo() {
		return codigo;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public Long getValor() {
		return valor;
	}
	
	public Long getDescuento() {
		return descuento;
	}
	
	public Date getFecha() {
		return fecha;
	}
	
	public String getCedulaVendedor() {
		return cedulaVendedor;
	}
	
	public String getNombreVendedor() {
		return nombreVendedor;
	}
}
