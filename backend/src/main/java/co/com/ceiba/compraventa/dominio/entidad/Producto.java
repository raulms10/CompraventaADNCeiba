/**
 *
 */
package co.com.ceiba.compraventa.dominio.entidad;

import java.util.Date;

/**
 * 
 * @author raul.martinez
 * 
 */
public class Producto {

	private static final String EL_CODIGO_ES_DATO_OBLIGATORIO = "El c<F3>digo del producto es un dato obligatorio.";
	private static final String EL_NOMBRE_ES_DATO_OBLIGATORIO = "El nombre del producto es un dato obligatoio.";
	private static final String EL_VALOR_ES_DATO_OBLIGATORIO = "El valor del producto es un dato obligatoio.";
	private static final String EL_DESCUENTO_ES_DATO_OBLIGATORIO = "El descuento del producto es un dato obligatoio.";
	private static final String LA_FECHA_ES_DATO_OBLIGATORIO = "La fecha es un dato obligatorio.";
	private static final String LA_CEDULA_VENDEDOR_ES_DATO_OBLIGATORIO = "La cedula del vendedor es un dato obligatoio.";
	private static final String EL_NOMBRE_VENDEDOR_ES_DATO_OBLIGATORIO = "El nombre del vendedor es un dato obligatoio.";
	
	private static final String EL_CODIGO_DEBE_TENER_MAXIMO_CARACTERES = "El c<F3>digo del producto debe tener m<E1>ximo %s caracteres.";
	private static final String EL_NOMBRE_DEBE_TENER_MAXIMO_CARACTERES = "El nombre del producto debe tener m<E1>ximo %s caracteres.";
	private static final String LA_CEDULA_VENDEDOR_DEBE_TENER_MAXIMO_CARACTERES = "La c<E9>dula del vendedor debe tener m<E1>ximo %s caracteres.";
	private static final String EL_NOMBRE_VENDEDOR_DEBE_TENER_MAXIMO_CARACTERES = "El nombre del vendedor debe tener m<E1>ximo %s caracteres.";
	private static final String EL_DESCUENTO_DEBE_ESTAR_EN_EL_RANGO = "El porcentaje de descuento debe estar entre %s y %s";
	private static final String EL_VALOR_DEBE_SER_MAYOR_QUE = "El valor del producto debe ser mayor que %s";
	
	private static final int LONGITUD_MAXIMA_DE_CODIGO = 10;
	private static final int LONGITUD_MAXIMA_DE_NOMBRE = 30;
	private static final int LONGITUD_MAXIMA_DE_CEDULA_VENDEDOR = 12;
	private static final int LONGITUD_MAXIMA_DE_NOMBRE_VENDEDOR = 60;
	private static final Long MINIMO_VALOR_PERMITIDO = 0L;
	private static final Long MINIMO_DESCUENTO_PERMITIDO = 0L;
	private static final Long MAXIMO_DESCUENTO_PERMITIDO = 75L;

	private String codigo;
	private String nombre;
	private Long valor;
	private Long descuento;
	private Date fecha;
	private String cedulaVendedor;
	private String nombreVendedor;

	public Producto(String codigo, String nombre, Long valor, Long descuento, Date fecha, String cedulaVendedor, String nombreVendedor) {
		// Se valida los datos obligatorios
		ValidadorParametro.validarObligatorio(codigo, EL_CODIGO_ES_DATO_OBLIGATORIO);
		ValidadorParametro.validarObligatorio(nombre, EL_NOMBRE_ES_DATO_OBLIGATORIO);
		ValidadorParametro.validarObligatorio(valor, EL_VALOR_ES_DATO_OBLIGATORIO);
		ValidadorParametro.validarObligatorio(descuento, EL_DESCUENTO_ES_DATO_OBLIGATORIO);
		ValidadorParametro.validarObligatorio(fecha, LA_FECHA_ES_DATO_OBLIGATORIO);
		ValidadorParametro.validarObligatorio(cedulaVendedor, LA_CEDULA_VENDEDOR_ES_DATO_OBLIGATORIO);
		ValidadorParametro.validarObligatorio(nombreVendedor, EL_NOMBRE_VENDEDOR_ES_DATO_OBLIGATORIO);
		// Se valida las longitudes maximas
		ValidadorParametro.validarLongitudMaxima(codigo, LONGITUD_MAXIMA_DE_CODIGO, String.format(EL_CODIGO_DEBE_TENER_MAXIMO_CARACTERES, LONGITUD_MAXIMA_DE_CODIGO));
		ValidadorParametro.validarLongitudMaxima(nombre, LONGITUD_MAXIMA_DE_NOMBRE, String.format(EL_NOMBRE_DEBE_TENER_MAXIMO_CARACTERES, LONGITUD_MAXIMA_DE_NOMBRE));
		ValidadorParametro.validarLongitudMaxima(cedulaVendedor, LONGITUD_MAXIMA_DE_CEDULA_VENDEDOR, String.format(LA_CEDULA_VENDEDOR_DEBE_TENER_MAXIMO_CARACTERES, LONGITUD_MAXIMA_DE_CEDULA_VENDEDOR));
		ValidadorParametro.validarLongitudMaxima(nombreVendedor, LONGITUD_MAXIMA_DE_NOMBRE_VENDEDOR, String.format(EL_NOMBRE_VENDEDOR_DEBE_TENER_MAXIMO_CARACTERES, LONGITUD_MAXIMA_DE_NOMBRE_VENDEDOR));
		// Se valida los rangos y valores minimos
		ValidadorParametro.validarRango(descuento, MINIMO_DESCUENTO_PERMITIDO, MAXIMO_DESCUENTO_PERMITIDO, String.format(EL_DESCUENTO_DEBE_ESTAR_EN_EL_RANGO, MINIMO_DESCUENTO_PERMITIDO, MAXIMO_DESCUENTO_PERMITIDO));
		ValidadorParametro.validarValorMinimo(valor, MINIMO_VALOR_PERMITIDO, String.format(EL_VALOR_DEBE_SER_MAYOR_QUE, MINIMO_VALOR_PERMITIDO));
		
		this.codigo = codigo;
		this.nombre = nombre;
		this.valor = valor;
		this.descuento = descuento;
		this.fecha = new Date(fecha.getTime());
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
		return new Date(fecha.getTime());
	}

	public String getCedulaVendedor() {
		return cedulaVendedor;
	}

	public String getNombreVendedor() {
		return nombreVendedor;
	}
}
