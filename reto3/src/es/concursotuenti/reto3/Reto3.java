package es.concursotuenti.reto3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Reto3 {

	public static void main(String[] args) {
		
		List<Integer> stocks = new ArrayList<Integer>();
		List<Integer> stocksOrdenados = new ArrayList<Integer>();
		
		String line;
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
	    
	    try {
			while ((line = br.readLine()) != null && !line.isEmpty()){
				stocks.add(Integer.parseInt(line));
				stocksOrdenados.add(Integer.parseInt(line));
			}
			
			if(stocks.size() == 0){
				System.out.println("0 0 0");
				return;
			}
			
			Collections.sort(stocksOrdenados);
			
			int ganancia = 0;
			int gananciaMaxima = 0;
			int tiempoIni = 0;
			int tiempoFin = 0;
			
			int indexStock = 0;
			int indexStockMax = 0;
			int indexStockOrdenado = stocksOrdenados.size() - 1;
			int stock = 0;
			int stockMax = stocksOrdenados.get(indexStockOrdenado);
			for(;indexStock < stocks.size(); indexStock++){
				stock = stocks.get(indexStock);
				
				if(stock >= stockMax){
					indexStockOrdenado--;
					
					if(indexStockOrdenado > 0){
						stockMax = stocksOrdenados.get(indexStockOrdenado);
					}
					
					continue;
				}
				
				indexStockMax = obtenerIndice(stocks, stockMax);
				if(indexStockMax < indexStock){
					continue;
				}
				
				ganancia = stocks.get(indexStockMax) - stock;
				
				if(ganancia > gananciaMaxima){
					gananciaMaxima = ganancia;
					tiempoIni = indexStock * 100;
					tiempoFin = indexStockMax * 100;
				}
			}
			
			System.out.println(tiempoIni + " " + tiempoFin + " " + gananciaMaxima);
		}
	    catch (NumberFormatException e) {
	    	System.out.println("Error al leer un número");
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

	private static int obtenerIndice(List<Integer> stocks, int b) {
		for(int i = 0; i < stocks.size(); i++){
			if(b == stocks.get(i)){
				return i;
			}
		}
		
		return 0;
	}
}
