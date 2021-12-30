package br.com.meli.bootcamp.wave4.grupo9.desafio.spring.advice;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

/***
 * @author Leo
 */
@ControllerAdvice
public class PersistenceExceptionAdvice {

    @ExceptionHandler(value = NullPointerException.class)
    protected ResponseEntity<Object> handleNullPointer(NullPointerException ex, WebRequest request) {
        return ResponseEntity.badRequest().body("Estamos com problema para localizar o produto soicitado tente novamente mais tarde");
    }

    @ExceptionHandler(value = ArithmeticException.class)
    protected ResponseEntity<Object> erroCalculo(ArithmeticException ex, WebRequest request) {
        return ResponseEntity.badRequest().body("Error ao contabilizar valores");
    }

}
