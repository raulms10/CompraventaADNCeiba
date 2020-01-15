/**
 * 
 */
package co.com.ceiba.compraventa.dominio.modelo;

import java.util.Date;

/**
 * @author raul.martinez
 *
 */
public class Compra {
	
	private Long idCompra;
	private String cedulaComprador;
	private String nombreComprador;
	private Date fechaCompra;
	private Long valorPagado;
	private Producto producto;
	
	private static final String LA_CEDULA_COMPRADOR_ES_DATO_OBLIGATORIO = "La c<E9>dula del comprador es un dato obligatoio.";
	private static final String EL_NOMBRE_COMPRADOR_ES_DATO_OBLIGATORIO = "El nombre del comprador es un dato obligatoio.";
	private static final String LA_FECHA_COMPRA_ES_DATO_OBLIGATORIO = "La fecha de compra es un dato obligatorio.";
	private static final String EL_VALOR_PAGADO_ES_DATO_OBLIGATORIO = "El valor pagado es un dato obligatorio.";
	private static final String EL_PRODUCTO_ES_DATO_OBLIGATORIO = "El producto es un dato obligatorio.";
	
	private static final String LA_CEDULA_COMPRADOR_DEBE_TENER_MAXIMO_CARACTERES = "La c<E9>dula del comprador debe tener m<E1>ximo %s caracteres.";
	private static final String EL_NOMBRE_COMPRADOR_DEBE_TENER_MAXIMO_CARACTERES = "El nombre del comprador debe tener m<E1>ximo %s caracteres.";
	
	private static final int LONGITUD_MAXIMA_DE_CEDULA_COMPRADOR = 12;
	private static final int LONGITUD_MAXIMA_DE_NOMBRE_COMPRADOR = 60;
	
	public Compra(Long idCompra, String cedulaComprador, String nombreComprador, Date fechaCompra, Long valorPagado, Producto producto) {
		//Se valida los datos obligatorios
		ValidadorParametro.validarObligatorio(cedulaComprador, LA_CEDULA_COMPRADOR_ES_DATO_OBLIGATORIO);
		ValidadorParametro.validarObligatorio(nombreComprador, EL_NOMBRE_COMPRADOR_ES_DATO_OBLIGATORIO);
		ValidadorParametro.validarObligatorio(fechaCompra, LA_FECHA_COMPRA_ES_DATO_OBLIGATORIO);
		ValidadorParametro.validarObligatorio(valorPagado, EL_VALOR_PAGADO_ES_DATO_OBLIGATORIO);
		ValidadorParametro.validarObligatorio(producto, EL_PRODUCTO_ES_DATO_OBLIGATORIO);
		// Se valida las longitudes maximas
		ValidadorParametro.validarLongitudMaxima(cedulaComprador, LONGITUD_MAXIMA_DE_CEDULA_COMPRADOR, String.format(LA_CEDULA_COMPRADOR_DEBE_TENER_MAXIMO_CARACTERES, LONGITUD_MAXIMA_DE_CEDULA_COMPRADOR));
		ValidadorParametro.validarLongitudMaxima(nombreComprador, LONGITUD_MAXIMA_DE_NOMBRE_COMPRADOR, String.format(EL_NOMBRE_COMPRADOR_DEBE_TENER_MAXIMO_CARACTERES, LONGITUD_MAXIMA_DE_NOMBRE_COMPRADOR));
		
		this.idCompra = idCompra;
		this.cedulaComprador = cedulaComprador;
		this.nombreComprador = nombreComprador;
		this.fechaCompra = new Date(fechaCompra.getTime());
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
		return new Date(fechaCompra.getTime());
	}
	
	public Long getValorPagado() {
		return valorPagado;
	}

	public Producto getProducto() {
		return producto;
	}
}
