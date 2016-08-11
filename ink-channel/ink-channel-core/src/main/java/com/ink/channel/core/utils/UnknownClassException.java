package com.ink.channel.core.utils;

public class UnknownClassException  extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public UnknownClassException() {
		super();
	}

	/**
	 * Creates a new <i>UnknownClassException</i>.
	 *
	 */
	public UnknownClassException(String message) {
		super(message);
	}

	/**
	 * Creates a new <i>UnknownClassException</i>.
	 *
	 */
	public UnknownClassException(Throwable cause) {
		super(cause);
	}

	/**
	 * Creates a new <i>UnknownClassException</i>.
	 */
	public UnknownClassException(String message, Throwable cause) {
		super(message, cause);
	}
}
