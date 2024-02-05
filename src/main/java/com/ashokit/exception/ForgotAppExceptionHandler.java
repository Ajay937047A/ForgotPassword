package com.ashokit.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ForgotAppExceptionHandler {
	
	@ExceptionHandler(value = ForgotAppException.class)
	public ResponseEntity<AppError> handleForgotException(ForgotAppException forgotAppException){
		AppError appError=new AppError();
		appError.setErrorCode("FORGOT0001");
		appError.setErrorMSG(forgotAppException.getMessage());
		ResponseEntity<AppError> entity=new ResponseEntity<AppError>(appError, HttpStatus.INTERNAL_SERVER_ERROR);
		return entity;
	}
}
