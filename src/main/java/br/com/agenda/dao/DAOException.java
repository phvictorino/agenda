package br.com.agenda.dao;

public class DAOException extends Exception {

	private static final long serialVersionUID = 1L;

	//Construtor recebendo a causa
	public DAOException(String msg, Exception causa) {
		super(msg, causa);
	}

}
