package com.univpm.TweetAnalyzer.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.univpm.TweetAnalyzer.model.ErrorResponse;

/**
 * In questa Classe vengono Gestite le Eccezioni Personalizzate
 */

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler{
	
	/**
	 * Questo Metodo inizializza un Oggetto di tipo Error mediante una Stringa.
	 * @param ex Contiene l'Eccezione che è stata lanciata.
	 * @param SolutionDetails contiene informazioni su come evitare un nuovo lancio dell'Eccezione.
	 * @return un Oggetto Error con Informazioni riguardanti il lancio di un'Eccezione.
	 */
	
	private static ErrorResponse ErrorResponseInitialization(Exception ex, String SolutionDetails) {
		Map<String, Object> Details= new HashMap<>();
		String Message = ex.getLocalizedMessage();

		Details.put("ExceptionType", ex.getClass().getSimpleName());
		Details.put("SolutionDetails", SolutionDetails);
		ErrorResponse Error = new ErrorResponse(Message, Details);
		return Error;
	}
	
	/**
	 * Questo Metodo inizializza un Oggetto di tipo Error mediante una Mappa.
	 * @param ex Contiene l'Eccezione che è stata lanciata.
	 * @param SolutionDetails contiene informazioni su come evitare un nuovo lancio dell'Eccezione.
	 * @return un Oggetto Error con Informazioni riguardanti il lancio di un'Eccezione.
	 */
	
	private static ErrorResponse ErrorResponseInitialization(Exception ex, Map<String, Object> SolutionDetails) {
		Map<String, Object> Details= new HashMap<>();
		String Message = ex.getLocalizedMessage();
		
		Details.put("ExceptionType", ex.getClass().getSimpleName());
		Details.put("SolutionsDetails", SolutionDetails);
		ErrorResponse Error = new ErrorResponse(Message, Details);
		return Error;
	}
	
	/**
	 * Questo Metodo viene chiamato al lancio di FilterNotFoundException.
	 * @param ex contiene una DuplicateFilterException.
	 * @return un Oggetto Error con informazioni riguardanti l'Eccezione e un Codice di Stato HTTP.
	 */
	
	@ExceptionHandler(DuplicateFilterException.class)
	public final ResponseEntity<ErrorResponse> handleFilterNotFoundException(DuplicateFilterException ex){
		ErrorResponse Error = ErrorResponseInitialization(ex, "Use An Allowed Filter");
		return new ResponseEntity<>(Error, HttpStatus.BAD_REQUEST);
	}
	
	/**
	 * Questo Metodo viene chiamato al lancio di IllegalFilterKeyException.
	 * @param ex contiene una IllegalFilterKeyException.
	 * @return un Oggetto Error con informazioni riguardanti l'Eccezione e un Codice di Stato HTTP.
	 */
	
	@ExceptionHandler(IllegalFilterKeyException.class)
	public final ResponseEntity<ErrorResponse> handleIllegalFilterKeyException(IllegalFilterKeyException ex){
		ErrorResponse Error = ErrorResponseInitialization(ex, "Use a Recognizable Filter Key");
		return new ResponseEntity<>(Error, HttpStatus.EXPECTATION_FAILED);
	}
	
	/**
	 * Questo Metodo viene chiamato al lancio di IllegalFilterValueException.
	 * @param ex contiene una IllegalFilterValueException.
	 * @return un Oggetto Error con informazioni riguardanti l'Eccezione e un Codice di Stato HTTP.
	 */
	
	@ExceptionHandler(IllegalFilterValueException.class)
	public final ResponseEntity<ErrorResponse> handleIllegalFilterValueException(IllegalFilterValueException ex, WebRequest request){
		Map<String, Object> SolutionDetails = new HashMap<>();
		SolutionDetails.put("1", "Use a Recognizable Filter Value");
		SolutionDetails.put("2", "Change Filter Key");
		
		ErrorResponse Error = ErrorResponseInitialization(ex, SolutionDetails);
		return new ResponseEntity<>(Error, HttpStatus.EXPECTATION_FAILED);
	}
	
	/**
	 * Questo Metodo viene chiamato al lancio di IllegalTimeException.
	 * @param ex contiene una IllegalTimeException.
	 * @return un Oggetto Error con informazioni riguardanti l'Eccezione e un Codice di Stato HTTP.
	 */
	
	@ExceptionHandler(IllegalTimeException.class)
	public final ResponseEntity<ErrorResponse> handleIllegalTimeException(IllegalTimeException ex, WebRequest request){
		Map<String, Object> SolutionDetails = new HashMap<>();
		SolutionDetails.put("Example", "Minutes Scope more-than OR equal to 0 AND less-than OR equal to 59");
		SolutionDetails.put("Sol", "Use An Allowable Date");
		
		ErrorResponse Error = ErrorResponseInitialization(ex, SolutionDetails);
		return new ResponseEntity<>(Error, HttpStatus.BAD_REQUEST);
	}
	
	/**
	 * Questo Metodo viene chiamato al lancio di IllegalFilterValueSizeException.
	 * @param ex contiene una IllegalFilterValueSizeException.
	 * @return un Oggetto Error con informazioni riguardanti l'Eccezione e un Codice di Stato HTTP.
	 */
	
	@ExceptionHandler(IllegalFilterValueSizeException.class)
	public final ResponseEntity<ErrorResponse> handleIllegalFilterValueSizeException(IllegalFilterValueSizeException ex, WebRequest request){
		Map<String, Object> SolutionDetails = new HashMap<>();
		SolutionDetails.put("Example", "Change English into en");
		SolutionDetails.put("Sol", "Change Filter Key");
		
		ErrorResponse Error = ErrorResponseInitialization(ex, SolutionDetails);
		return new ResponseEntity<>(Error, HttpStatus.BAD_REQUEST);
	}
	
	/**
	 * Questo Metodo viene chiamato al lancio di StatisticsNotAppliedException.
	 * @param ex contiene una StatisticsNotAppliedException.
	 * @return un Oggetto Error con informazioni riguardanti l'Eccezione e un Codice di Stato HTTP.
	 */
	
	@ExceptionHandler(StatisticsNotAppliedException.class)
	public final ResponseEntity<ErrorResponse> handleStatisticsNotAppliedException(StatisticsNotAppliedException ex, WebRequest request){
		Map<String, Object> SolutionDetails = new HashMap<>();
		SolutionDetails.put("1", "Use More Common Hashtag");
		SolutionDetails.put("2", "If You Are Using a Filter, Change It");
		
		ErrorResponse Error = ErrorResponseInitialization(ex, SolutionDetails);
		return new ResponseEntity<>(Error, HttpStatus.BAD_REQUEST);
	}
	
}
