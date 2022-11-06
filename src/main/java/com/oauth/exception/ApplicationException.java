package com.sfa.exception;

public class ApplicationException extends RuntimeException{

	private static final long serialVersionUID = -1870614008805292128L;
	
	public ApplicationException() {
		super();
	}
	
	public ApplicationException(final String message) {
		super(message);
	}
	
	public ApplicationException(final String message, final Throwable cause) {
		super(message,cause);
	}
	
	public ApplicationException(final Throwable cause) {
		super(cause);
	}
}
