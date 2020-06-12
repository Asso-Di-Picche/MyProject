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

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler{
	
	private static ErrorResponse ErrorResponseInitialization(Exception ex, String SolutionDetails) {
		Map<String, Object> Details= new HashMap<>();
		String Message = ex.getLocalizedMessage();

		Details.put("ExceptionType", ex.getClass().getSimpleName());
		Details.put("SolutionDetails", SolutionDetails);
		ErrorResponse Error = new ErrorResponse(Message, Details);
		return Error;
	}
	
	private static ErrorResponse ErrorResponseInitialization(Exception ex, Map<String, Object> SolutionDetails) {
		Map<String, Object> Details= new HashMap<>();
		
		String Message = ex.getLocalizedMessage();
		Details.put("ExceptionType", ex.getClass().getSimpleName());
		Details.put("SolutionsDetails", SolutionDetails);
		ErrorResponse Error = new ErrorResponse(Message, Details);
		return Error;
	}
	
	@ExceptionHandler(FilterNotFoundException.class)
	public final ResponseEntity<ErrorResponse> handleFilterNotFoundException(FilterNotFoundException ex, WebRequest request){
		ErrorResponse Error = ErrorResponseInitialization(ex, "Use An Allowed Filter");
		return new ResponseEntity<>(Error, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(IllegalFilterKeyException.class)
	public final ResponseEntity<ErrorResponse> handleIllegalFilterKeyException(IllegalFilterKeyException ex, WebRequest request){
		ErrorResponse Error = ErrorResponseInitialization(ex, "Use a Recognizable Filter Key");
		return new ResponseEntity<>(Error, HttpStatus.EXPECTATION_FAILED);
	}
	
	@ExceptionHandler(IllegalFilterValueException.class)
	public final ResponseEntity<ErrorResponse> handleIllegalFilterValueException(IllegalFilterValueException ex, WebRequest request){
		Map<String, Object> SolutionDetails = new HashMap<>();
		SolutionDetails.put("1", "Use a Recognizable Filter Value");
		SolutionDetails.put("2", "Change Filter Key");
		
		ErrorResponse Error = ErrorResponseInitialization(ex, SolutionDetails);
		return new ResponseEntity<>(Error, HttpStatus.EXPECTATION_FAILED);
	}
	
	@ExceptionHandler(IllegalTimeException.class)
	public final ResponseEntity<ErrorResponse> handleIllegalTimeException(IllegalTimeException ex, WebRequest request){
		Map<String, Object> SolutionDetails = new HashMap<>();
		SolutionDetails.put("Example", "Minutes Scope more-than OR equal to 0 AND less-than OR equal to 59");
		SolutionDetails.put("Sol", "Use An Allowable Date");
		
		ErrorResponse Error = ErrorResponseInitialization(ex, SolutionDetails);
		return new ResponseEntity<>(Error, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(IllegalFilterValueSizeException.class)
	public final ResponseEntity<ErrorResponse> handleIllegalFilterValueSizeException(IllegalFilterValueSizeException ex, WebRequest request){
		Map<String, Object> SolutionDetails = new HashMap<>();
		SolutionDetails.put("Example", "Change English into en");
		SolutionDetails.put("Sol", "Change Filter Key");
		
		ErrorResponse Error = ErrorResponseInitialization(ex, SolutionDetails);
		return new ResponseEntity<>(Error, HttpStatus.BAD_REQUEST);
	}
	
}
