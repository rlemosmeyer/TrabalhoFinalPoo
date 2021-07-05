package com.poo.controle;

public class ControleExcption extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ControleExcption(String message, Throwable cause) {
		super(message, cause);
	}

	public ControleExcption(String message) {
		super(message);
	}

}
