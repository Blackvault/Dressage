package com.blackvault;

public class RiderNotFoundException extends RuntimeException {

	/**
	* 
	*/
	private static final long serialVersionUID = 1L;

	public RiderNotFoundException(Long id) {
		super("Could not find Rider " + id);
	}

}
