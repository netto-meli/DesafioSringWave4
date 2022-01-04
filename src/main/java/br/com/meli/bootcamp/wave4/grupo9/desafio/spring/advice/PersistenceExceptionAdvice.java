package br.com.meli.bootcamp.wave4.grupo9.desafio.spring.advice;

import br.com.meli.bootcamp.wave4.grupo9.desafio.spring.exception.CartManagementException;
import br.com.meli.bootcamp.wave4.grupo9.desafio.spring.exception.ErrorProcesamentoException;
import br.com.meli.bootcamp.wave4.grupo9.desafio.spring.exception.NotFoundExceptionProduct;
import br.com.meli.bootcamp.wave4.grupo9.desafio.spring.exception.RepositoryException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

/***
 * Classe Advice que manipula as exceções
 *
 * @author Fernando
 */
@ControllerAdvice
public class PersistenceExceptionAdvice {

	/*** Handler de erro
	 *
	 * @param ex exceção lançada
	 * @param request webRequest
	 * @return ResponseEntity com status code de erro e mensagem.
	 */
	@ExceptionHandler(value = CartManagementException.class)
	protected ResponseEntity<Object> handleCartManagementException(CartManagementException ex, WebRequest request) {
		String bodyOfResponse = ex.getMessage();
		return ResponseEntity.unprocessableEntity().body(bodyOfResponse);
	}

	/*** Handler de erro
	 *
	 * @param ex exceção lançada
	 * @param request webRequest
	 * @return ResponseEntity com status code de erro e mensagem.
	 */
	@ExceptionHandler(value = ErrorProcesamentoException.class)
	protected ResponseEntity<Object> handleErrorProcesamentoException(ErrorProcesamentoException ex, WebRequest request) {
		String bodyOfResponse = ex.getMessage();
		return ResponseEntity.unprocessableEntity().body(bodyOfResponse);
	}

	/*** Handler de erro
	 *
	 * @param ex exceção lançada
	 * @param request webRequest
	 * @return ResponseEntity com status code de erro e mensagem.
	 */
	@ExceptionHandler(value = NotFoundExceptionProduct.class)
	protected ResponseEntity<Object> erroNotFoundExceptionProduct(NotFoundExceptionProduct ex, WebRequest request) {
		String bodyOfResponse = ex.getMessage();
		return ResponseEntity.unprocessableEntity().body(bodyOfResponse);
	}

	/*** Handler de erro
	 *
	 * @param ex exceção lançada
	 * @param request webRequest
	 * @return ResponseEntity com status code de erro e mensagem.
	 */
	@ExceptionHandler(value = RepositoryException.class)
	protected ResponseEntity<Object> erroRepositoryException(RepositoryException ex, WebRequest request) {
		String bodyOfResponse = ex.getMessage();
		return ResponseEntity.unprocessableEntity().body(bodyOfResponse);
	}
}
