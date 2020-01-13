/**
 * 
 */
package co.com.ceiba.compraventa.dominio.testdatabuilder.producto;

import java.util.Date;

import co.com.ceiba.compraventa.dominio.entidad.Producto;

/**
 * @author raul.martinez
 *
 */
public class ProductoTestDataBuilder {
	
	private String codigo;
	private String nombre;
	private Long valor;
	private Long descuento;
	private Date fecha;
	private String cedulaVendedor;
	private String nombreVendedor;
	
	public ProductoTestDataBuilder() {
		this.codigo = "77048612";
		this.nombre = "Televisor Kalley 32 pulgadas";
		this.valor = 679999L;
		this.descuento = 5L;
		this.fecha = new Date();
		this.cedulaVendedor = "70065478";
		this.nombreVendedor = "Miguel Mendoza Sotelo";
	}
	
	public ProductoTestDataBuilder conCodigo(String codigo) {
		this.codigo = codigo;
		return this;
	}
	
	public ProductoTestDataBuilder conNombre(String nombre) {
		this.nombre = nombre;
		return this;
	}
	
	public ProductoTestDataBuilder conValor(Long valor) {
		this.valor = valor;
		return this;
	}
	
	public ProductoTestDataBuilder conDescuento(Long descuento) {
		this.descuento = descuento;
		return this;
	}
	
	public ProductoTestDataBuilder conFecha(Date fecha) {
		this.fecha = fecha;
		return this;
	}
	
	public ProductoTestDataBuilder conCedulaVEndedor(String cedulaVendedor) {
		this.cedulaVendedor = cedulaVendedor;
		return this;
	}
	
	public ProductoTestDataBuilder conNombreVendedor(String nombreVendedor) {
		this.nombreVendedor = nombreVendedor;
		return this;
	}
	
	public Producto build() {
		return new Producto(codigo, nombre, valor, descuento, fecha, cedulaVendedor, nombreVendedor);
	}
}
