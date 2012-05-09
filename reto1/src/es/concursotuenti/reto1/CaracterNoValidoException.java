package es.concursotuenti.reto1;

public class CaracterNoValidoException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public CaracterNoValidoException(char c){
		super("Caracter no válido: " + c);
	}

}
