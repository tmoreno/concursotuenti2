package es.concursotuenti.reto1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Reto1 {

	private static Map<Character, Tecla> teclado;

	public static void main(String[] args) {
		int numMarcaciones = 0;
		boolean primeraFila = true;
		
		List<char []> marcaciones = new ArrayList<char []>();
		
		String line;
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
	    
	    try {
			while ((line = br.readLine()) != null && !line.isEmpty()){
				if(primeraFila){
					numMarcaciones = Integer.valueOf(line);
					primeraFila = false;
				}
				else{
					marcaciones.add(line.toCharArray());
				}
			}
			
			teclado = initTeclado();
			
			for(int i = 0; i < marcaciones.size() && i < numMarcaciones; i++){
				System.out.println(calcularTiempoMarcacion(marcaciones.get(i)));
			}
		}
	    catch (NumberFormatException e) {
	    	System.out.println("El número de marcaciones no es un número");
	    }
	    catch (CaracterNoValidoException e) {
	    	System.out.println(e.getMessage());
	    }
	    catch (IOException e) {
			e.printStackTrace();
		}
	    finally {
	    	if(br != null) {
	    		try {
					br.close();
				} 
	    		catch (Throwable e) {
					e.printStackTrace();
				}
	    	}
	    	
	    	if(isr != null) {
	    		try {
					isr.close();
				} 
	    		catch (Throwable e) {
					e.printStackTrace();
				}
	    	}
	    }
	}
	
	private static int calcularTiempoMarcacion(char [] marcacion) throws CaracterNoValidoException{
		Tecla inicio = new Tecla(3, 1, 1);
		Tecla bloqMayusculasTecla = new Tecla(3, 2, 1);
		Tecla fin = null;
		
		int tiempo = 0;
		boolean bloqMayusculas = false;

		for(char c : marcacion){
			if(Character.isUpperCase(c) && !bloqMayusculas || Character.isLowerCase(c) && bloqMayusculas){
				tiempo += inicio.tiempo(bloqMayusculasTecla) + Tecla.PULSAR_TECLA;
				bloqMayusculas = !bloqMayusculas;
				
				inicio = bloqMayusculasTecla;
			}
			
			fin = teclado.get(Character.toLowerCase(c));
			
			if(fin == null){
				throw new CaracterNoValidoException(c);
			}
			
			tiempo += inicio.tiempo(fin) + fin.getPulsaciones() * Tecla.PULSAR_TECLA;
			
			inicio = fin;
		}
		
		return tiempo;
	}

	private static Map<Character, Tecla> initTeclado() {
		Map<Character, Tecla> teclado = new HashMap<Character, Tecla>();
		
		teclado.put(' ', new Tecla(0, 0, 1));
		teclado.put('1', new Tecla(0, 0, 2));
		
		teclado.put('a', new Tecla(0, 1, 1));
		teclado.put('b', new Tecla(0, 1, 2));
		teclado.put('c', new Tecla(0, 1, 3));
		teclado.put('2', new Tecla(0, 1, 4));
		
		teclado.put('d', new Tecla(0, 2, 1));
		teclado.put('e', new Tecla(0, 2, 2));
		teclado.put('f', new Tecla(0, 2, 3));
		teclado.put('3', new Tecla(0, 2, 4));
		
		teclado.put('g', new Tecla(1, 0, 1));
		teclado.put('h', new Tecla(1, 0, 2));
		teclado.put('i', new Tecla(1, 0, 3));
		teclado.put('4', new Tecla(1, 0, 4));
		
		teclado.put('j', new Tecla(1, 1, 1));
		teclado.put('k', new Tecla(1, 1, 2));
		teclado.put('l', new Tecla(1, 1, 3));
		teclado.put('5', new Tecla(1, 1, 4));
		
		teclado.put('m', new Tecla(1, 2, 1));
		teclado.put('n', new Tecla(1, 2, 2));
		teclado.put('o', new Tecla(1, 2, 3));
		teclado.put('6', new Tecla(1, 2, 4));
		
		teclado.put('p', new Tecla(2, 0, 1));
		teclado.put('q', new Tecla(2, 0, 2));
		teclado.put('r', new Tecla(2, 0, 3));
		teclado.put('s', new Tecla(2, 0, 4));
		teclado.put('7', new Tecla(2, 0, 5));
		
		teclado.put('t', new Tecla(2, 1, 1));
		teclado.put('u', new Tecla(2, 1, 2));
		teclado.put('v', new Tecla(2, 1, 3));
		teclado.put('8', new Tecla(2, 1, 4));
		
		teclado.put('w', new Tecla(2, 2, 1));
		teclado.put('x', new Tecla(2, 2, 2));
		teclado.put('y', new Tecla(2, 2, 3));
		teclado.put('z', new Tecla(2, 2, 4));
		teclado.put('9', new Tecla(2, 2, 5));
		
		teclado.put('0', new Tecla(3, 1, 1));
		
		return teclado;
	}
}
