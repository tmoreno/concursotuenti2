package es.concursotuenti.reto2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class Reto2 {

	public static void main(String[] args) {
		int datasetSize = 0;
		boolean primeraFila = true;
		
		List<BigInteger> dataset = new ArrayList<BigInteger>();
		
		String line;
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
	    
	    try {
			while ((line = br.readLine()) != null && !line.isEmpty()){
				if(primeraFila){
					datasetSize = Integer.valueOf(line);
					primeraFila = false;
				}
				else{
					dataset.add(new BigInteger(line));
				}
			}
			
			if(datasetSize < 1 || datasetSize > 2000 || 
			   dataset.size() < 1 || dataset.size() > 2000){
				throw new LimiteNumCasosException();
			}
			
			for(int i = 0; i < dataset.size() && i < datasetSize; i++){
				System.out.println("Case #" + (i + 1) + ": " + maxNumUnos(dataset.get(i)));
			}
		}
	    catch (NumberFormatException e) {
	    	System.out.println("Error al leer un número");
	    }
	    catch (LimiteNumCasosException e){
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

	private static int maxNumUnos(BigInteger n) {
		int numUnos = 0;
		boolean acarreo = false;
		
		char [] binario = n.toString(2).toCharArray();
		
		for(int i = binario.length - 1; i >= 0 ; i--){
			if(binario[i] == '1'){
				if(!acarreo){
					numUnos += 1;
					acarreo = false;
				}
				else{
					if(i != 0){
						numUnos += 2;
						acarreo = true;
					}
				}
			}
			else{
				if(acarreo){
					numUnos += 1;
				}
				else{
					numUnos += 2;
				}
				
				acarreo = true;
			}
		}
		
		return numUnos;
	}
}
