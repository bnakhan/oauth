package com.sfa.exception;

import com.sfa.model.RestResponse;
import com.sfa.utils.RestHelper;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler{

	@ExceptionHandler({ ApplicationException.class })
	@ResponseBody
	public ResponseEntity<RestResponse> handleEmptyListException(final Exception ex) {

		return RestHelper.responseError(ex.getMessage(), HttpStatus.BAD_REQUEST);
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		RestResponse restResponse = new RestResponse();
		ex.getBindingResult().getAllErrors().forEach((error) -> {
			restResponse.setError(error.getDefaultMessage());
		});
		restResponse.setStatus(false);

		return new ResponseEntity<>(restResponse, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler({ InvalidCredentialException.class })
	@ResponseBody
	public ResponseEntity<RestResponse> invalidCredentialException(final Exception ex, String msg) {
		RestResponse restResponse = new RestResponse();
		restResponse.setMessage(msg);
		return RestHelper.responseError(msg, HttpStatus.UNAUTHORIZED);
	}

	@ExceptionHandler({ BlockedCredentialsException.class })
	@ResponseBody
	public ResponseEntity<RestResponse> blockedCredentialsException(final Exception ex, String msg) {
		RestResponse restResponse = new RestResponse();
		restResponse.setMessage(msg);
		return RestHelper.responseError(msg, HttpStatus.UNAUTHORIZED);
	}

	@ExceptionHandler({ EmptyListException.class })
	@ResponseBody
	public ResponseEntity<RestResponse> emptyListException(final Exception ex, String msg) {
		RestResponse restResponse = new RestResponse();
		restResponse.setMessage(msg);
		return RestHelper.responseError(msg, HttpStatus.UNAUTHORIZED);
	}


}
