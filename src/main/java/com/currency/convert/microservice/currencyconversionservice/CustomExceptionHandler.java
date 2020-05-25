package com.currency.convert.microservice.currencyconversionservice;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler{

	
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handleAllExceptions(Exception ex,WebRequest request) {
		// TODO Auto-generated method stub
		List<String> details=new ArrayList<String>();
		details.add(ex.getLocalizedMessage());
		
		MessageDetail error=new MessageDetail("Server Error", details,HttpStatus.INTERNAL_SERVER_ERROR);
		return new ResponseEntity<Object>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<Object> constraintViolationException(ConstraintViolationException e) throws IOException{
		List<String> details=new ArrayList<String>();

		//for(ObjectError error:e.getMessage()) {
			details.add(e.getMessage());
		//}
		MessageDetail error=new MessageDetail("Validation failed", details,HttpStatus.BAD_REQUEST);
		return new ResponseEntity<Object>(error, HttpStatus.BAD_REQUEST);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		// TODO Auto-generated method stub
		List<String> details=new ArrayList<String>();

		for(ObjectError error:ex.getBindingResult().getAllErrors()) {
			details.add(error.getDefaultMessage());
		}
		MessageDetail error=new MessageDetail("Validation failed", details,HttpStatus.BAD_REQUEST);
		return new ResponseEntity<Object>(error, HttpStatus.BAD_REQUEST);
	}
	
	
}
