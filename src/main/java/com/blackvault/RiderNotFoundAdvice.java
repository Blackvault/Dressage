package com.blackvault;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
class RiderNotFoundAdvice {

	@ResponseBody
	@ExceptionHandler(RiderNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	String riderNotFoundHandler(RiderNotFoundException ex) {
		return ex.getMessage();
	}
}