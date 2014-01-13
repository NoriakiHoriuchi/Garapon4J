package me.gizio.garapon4j.auth;

import me.gizio.garapon4j.exception.GaraponException;

public interface GaraponAuth {

	boolean execute();

	boolean executeWebAuth() throws GaraponException;

	boolean executeTerminalAuth() throws GaraponException;

}
