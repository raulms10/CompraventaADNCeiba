/**
 * 
 */
package co.com.ceiba.compraventa.infraestructura.error;

import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import co.com.ceiba.compraventa.dominio.excepcion.ExcepcionDescuento;
import co.com.ceiba.compraventa.dominio.excepcion.ExcepcionDiferenteValorPagado;
import co.com.ceiba.compraventa.dominio.excepcion.ExcepcionDuplicidad;
import co.com.ceiba.compraventa.dominio.excepcion.ExcepcionLongitudMaxima;
import co.com.ceiba.compraventa.dominio.excepcion.ExcepcionLunesViernes;
import co.com.ceiba.compraventa.dominio.excepcion.ExcepcionProductoComprado;
import co.com.ceiba.compraventa.dominio.excepcion.ExcepcionRango;
import co.com.ceiba.compraventa.dominio.excepcion.ExcepcionSabadoDomingo;
import co.com.ceiba.compraventa.dominio.excepcion.ExcepcionValorMinimo;
import co.com.ceiba.compraventa.dominio.excepcion.ExcepcionValorObligatorio;

/**
 * @author raul.martinez
 *
 */
@ControllerAdvice
public class ManejadorError extends ResponseEntityExceptionHandler {
    
    private static final Logger LOG = LoggerFactory.getLogger(ManejadorError.class);

    private static final String HA_OCURRIDO_ERROR_CONCTACTE_ADMINISTRADOR = "Ha ocurrido un error, por favor contactar al administrador.";

    private static final ConcurrentHashMap<String, Integer> CODIGOS_ESTADO = new ConcurrentHashMap<>();

    public ManejadorError() {
    	CODIGOS_ESTADO.put(ExcepcionDescuento.class.getSimpleName(), HttpStatus.BAD_REQUEST.value());
    	CODIGOS_ESTADO.put(ExcepcionDiferenteValorPagado.class.getSimpleName(), HttpStatus.BAD_REQUEST.value());
        CODIGOS_ESTADO.put(ExcepcionDuplicidad.class.getSimpleName(), HttpStatus.BAD_REQUEST.value());
        CODIGOS_ESTADO.put(ExcepcionLongitudMaxima.class.getSimpleName(), HttpStatus.BAD_REQUEST.value());
        CODIGOS_ESTADO.put(ExcepcionLunesViernes.class.getSimpleName(), HttpStatus.BAD_REQUEST.value());
        CODIGOS_ESTADO.put(ExcepcionProductoComprado.class.getSimpleName(), HttpStatus.BAD_REQUEST.value());
        CODIGOS_ESTADO.put(ExcepcionRango.class.getSimpleName(), HttpStatus.BAD_REQUEST.value());
        CODIGOS_ESTADO.put(ExcepcionSabadoDomingo.class.getSimpleName(), HttpStatus.BAD_REQUEST.value());
        CODIGOS_ESTADO.put(ExcepcionValorMinimo.class.getSimpleName(), HttpStatus.BAD_REQUEST.value());
        CODIGOS_ESTADO.put(ExcepcionValorObligatorio.class.getSimpleName(), HttpStatus.BAD_REQUEST.value());
    }

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Error> handleAllExceptions(Exception exception) {
        ResponseEntity<Error> resultado;

        String excepcionNombre = exception.getClass().getSimpleName();
        String mensaje = exception.getMessage();
        Integer codigo = CODIGOS_ESTADO.get(excepcionNombre);

        if (codigo != null) {
            Error error = new Error(excepcionNombre, mensaje);
            resultado = new ResponseEntity<>(error, HttpStatus.valueOf(codigo));
        } else {
        	LOG.error(excepcionNombre, exception);
            Error error = new Error(excepcionNombre, HA_OCURRIDO_ERROR_CONCTACTE_ADMINISTRADOR);
            resultado = new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return resultado;
    }
}
