package me.gizio.garapon4j.exception;

public class WrongPasswordException extends GaraponException {

	private static final long serialVersionUID = 6185208712324305380L;

	public WrongPasswordException(String substring){
		super(substring);
	}
}
