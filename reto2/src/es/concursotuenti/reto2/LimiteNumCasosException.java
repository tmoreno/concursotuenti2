package es.concursotuenti.reto2;

public class LimiteNumCasosException extends Exception {
	private static final long serialVersionUID = 1L;
	
	public LimiteNumCasosException(){
		super("El número de casos tiene que estar entre 1 y 2000, ambos inclusive");
	}
}
