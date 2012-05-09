package es.concursotuenti.reto4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Reto4 {

	public static void main(String[] args) {
		try {
			CasoPrueba [] casosPrueba = leerCasosPrueba();
			
			for(int i = 0; i < casosPrueba.length; i++){
				new Thread(casosPrueba[i]).start();
			}
			
			for(int i = 0; i < casosPrueba.length; i++){
				System.out.println(casosPrueba[i].getLitrosNecesarios());
			}
		}
		catch (ArrayIndexOutOfBoundsException e) {
	    	System.out.println("El número de grupos no coincide con los grupos leidos");
	    }
		catch (NumberFormatException e) {
	    	System.out.println("Error al leer un número");
	    }
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static CasoPrueba [] leerCasosPrueba() throws NumberFormatException, IOException {
		String line;
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		
		int nCasosPrueba = 0;
		boolean primeraFila = true;
		int i = 0;
		String [] aux;
		CasoPrueba casoPrueba = null;
		CasoPrueba [] casosPrueba = new CasoPrueba [0];
		
		try {
			while ((line = br.readLine()) != null && !line.isEmpty()){
				if(primeraFila){
					nCasosPrueba = Integer.valueOf(line);
					casosPrueba = new CasoPrueba [nCasosPrueba];
					primeraFila = false;
				}
				else{
					aux = line.split(" ");
					
					if(casoPrueba == null){
						casoPrueba = new CasoPrueba(Integer.parseInt(aux[0].trim()), 
													Integer.parseInt(aux[1].trim()), 
													Integer.parseInt(aux[2].trim()));
					}
					else{
						casoPrueba.setGrupos(aux);
						casosPrueba[i] = casoPrueba;
						i++;
						casoPrueba = null;
					}
				}
			}
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
		
		return casosPrueba;
	}
}
