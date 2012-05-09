package es.concursotuenti.reto1;

public class Tecla {
	
	private static final int MOVER_ARRIBA_ABAJO = 300;
	private static final int MOVER_IZQUIERDA_DERECHA = 200;
	private static final int MOVER_DIAGONAL = 350;
	private static final int MOVER_MISMA_TECLA = 500;
	public static final int PULSAR_TECLA = 100;

	private int fila;
	private int columna;
	private int pulsaciones;
	
	public Tecla (int fila, int columna, int pulsaciones){
		this.fila = fila;
		this.columna = columna;
		this.pulsaciones = pulsaciones;
	}
	
	public int getPulsaciones() {
		return pulsaciones;
	}

	public int tiempo(Tecla c){
		int tiempo = 0;
		
		int filaDiff = Math.abs(fila - c.fila);
		int columnaDiff = Math.abs(columna - c.columna);
		
		if(fila == c.fila && columna == c.columna){
			tiempo = MOVER_MISMA_TECLA;
		}
		else if(fila == c.fila){
			tiempo = columnaDiff * MOVER_IZQUIERDA_DERECHA;
		}
		else if(columna == c.columna){
			tiempo = filaDiff * MOVER_ARRIBA_ABAJO;
		}
		else if(filaDiff == columnaDiff) {
			tiempo = filaDiff * MOVER_DIAGONAL;
		}
		else if(filaDiff == 1) {
			tiempo = MOVER_DIAGONAL + (columnaDiff - 1) * MOVER_IZQUIERDA_DERECHA;
		}
		else if(columnaDiff == 1) {
			tiempo = MOVER_DIAGONAL + (filaDiff - 1) * MOVER_ARRIBA_ABAJO;
		}
		else if(filaDiff == 2) {
			tiempo = 2 * MOVER_DIAGONAL + (columnaDiff - 2) * MOVER_IZQUIERDA_DERECHA;
		}
		else if(columnaDiff == 2) {
			tiempo = 2 * MOVER_DIAGONAL + (filaDiff - 2) * MOVER_ARRIBA_ABAJO;
		}
		
		return tiempo;
	}
}
