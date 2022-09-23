package com.inadaptados.escuela.dao;

public class DataAccessException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DataAccessException(String message) {
		super(message);
	}
	
	public DataAccessException(String message, Exception e) {
		super(message, e);
	}
}
