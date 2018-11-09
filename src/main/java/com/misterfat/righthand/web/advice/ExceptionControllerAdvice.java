package com.misterfat.righthand.web.advice;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.misterfat.framework.exception.GenericException;
import com.misterfat.framework.web.response.ResponseResult;

@ControllerAdvice
public class ExceptionControllerAdvice {

	private static final Logger logger = LoggerFactory.getLogger(ExceptionControllerAdvice.class);

	@ExceptionHandler
	@ResponseBody
	public ResponseResult<Object> handleException(Exception e, HttpServletResponse response) throws Exception {
		logger.error("Unified Exception", e);
		return ResponseResult.error(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
	}

	@ExceptionHandler
	@ResponseBody
	public ResponseResult<Object> handleMethodArgumentNotValidException(MethodArgumentNotValidException e,
			HttpServletResponse response) throws Exception {
		logger.error("Unified MethodArgumentNotValidException", e);
		return ResponseResult.error(HttpStatus.BAD_REQUEST.value(), e.getMessage());
	}

	@ExceptionHandler
	@ResponseBody
	public ResponseResult<Object> handleGenericException(GenericException ex, HttpServletResponse response)
			throws IOException {
		logger.warn("Unified GenericException", ex);
		return ResponseResult.error(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage());
	}

}