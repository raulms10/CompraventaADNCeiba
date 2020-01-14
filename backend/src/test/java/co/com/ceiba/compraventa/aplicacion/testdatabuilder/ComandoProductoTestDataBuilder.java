/**
 * 
 */
package co.com.ceiba.compraventa.aplicacion.testdatabuilder;

import java.util.Date;

import co.com.ceiba.compraventa.aplicacion.comando.ComandoProducto;

/**
 * @author raul.martinez
 *
 */
public class ComandoProductoTestDataBuilder {
	
	private String codigo;
	private String nombre;
	private Long valor;
	private Long descuento;
	private Date fecha;
	private String cedulaVendedor;
	private String nombreVendedor;
	
	public ComandoProductoTestDataBuilder() {
		this.codigo = "7705689";
		this.nombre = "Nevera Haceb 2 puertas";
		this.valor = 1785999L;
		this.descuento = 8L;
		this.fecha = new Date();
		this.cedulaVendedor = "1063259485";
		this.nombreVendedor = "Elena Maria Cordero Santos";
	}
	
	public ComandoProductoTestDataBuilder conCodigo(String codigo) {
		this.codigo = codigo;
		return this;
	}
	
	public ComandoProductoTestDataBuilder conNombre(String nombre) {
		this.nombre = nombre;
		return this;
	}
	
	public ComandoProductoTestDataBuilder conValor(Long valor) {
		this.valor = valor;
		return this;
	}
	
	public ComandoProductoTestDataBuilder conDescuento(Long descuento) {
		this.descuento = descuento;
		return this;
	}

	public ComandoProductoTestDataBuilder conFecha(Date fecha) {
		this.fecha = fecha;
		return this;
	}
	
	public ComandoProductoTestDataBuilder conCedulaVendedor(String cedulaVendedor) {
		this.cedulaVendedor = cedulaVendedor;
		return this;
	}
	
	public ComandoProductoTestDataBuilder conNombreVendedor(String nombreVendedor) {
		this.nombreVendedor = nombreVendedor;
		return this;
	}
	
	public ComandoProducto build() {
		return new ComandoProducto(codigo, nombre, valor, descuento, fecha, cedulaVendedor, nombreVendedor);
	}
}
