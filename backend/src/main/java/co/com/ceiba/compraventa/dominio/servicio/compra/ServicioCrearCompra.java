/**
 * 
 */
package co.com.ceiba.compraventa.dominio.servicio.compra;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import co.com.ceiba.compraventa.dominio.excepcion.ExcepcionDiferenteValorPagado;
import co.com.ceiba.compraventa.dominio.excepcion.ExcepcionDuplicidad;
import co.com.ceiba.compraventa.dominio.modelo.Compra;
import co.com.ceiba.compraventa.dominio.modelo.ValidadorDescuento;
import co.com.ceiba.compraventa.dominio.repositorio.RepositorioCompra;

/**
 * @author raul.martinez
 *
 */
public class ServicioCrearCompra {

	private static final String EL_PRODUCTO_HA_SIDO_VENDIDO = "El producto ya ha sido vendido.";
	private static final String EL_VALOR_PAGADO_DIFERENTE_A_VALOR = "El valor pagado es diferente al valor del producto";
	private static final String EL_VALOR_PAGADO_NO_ES_DESCUENTO = "El valor pagado no corresponde al descuento aplicado al producto";
	
	private RepositorioCompra repositorioCompra;

	public ServicioCrearCompra(RepositorioCompra repositorioCompra) {
		this.repositorioCompra = repositorioCompra;
	}

	public void ejecutar(Compra compra) {
		validarDescuentoViernesDeValorPagado(compra.getFechaCompra(), compra.getValorPagado(), compra.getProducto().getValor(), compra.getProducto().getDescuento());
		validarExistenciaPrevia(compra);
		this.repositorioCompra.crear(compra);
	}
	
	private void validarExistenciaPrevia(Compra compra) {
		boolean existe = this.repositorioCompra.existe(compra);
		if (existe) {
			throw new ExcepcionDuplicidad(EL_PRODUCTO_HA_SIDO_VENDIDO);
		}
	}
	
	private void validarDescuentoViernesDeValorPagado(Date fechaCompra, Long valorPagado, Long valor, Long descuento) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(fechaCompra);
		int numeroDia = calendar.get(Calendar.DAY_OF_WEEK);
		boolean esViernes = numeroDia == Calendar.FRIDAY;
		if (esViernes) {
			ValidadorDescuento.validarDescuentoLosViernes(valorPagado, valor, descuento, EL_VALOR_PAGADO_NO_ES_DESCUENTO);
		} else {
			boolean esMismoValor = valorPagado.equals(valor);
			if (!esMismoValor) {
				throw new ExcepcionDiferenteValorPagado(EL_VALOR_PAGADO_DIFERENTE_A_VALOR);
			}
		}
	}
}
