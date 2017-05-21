package br.mateus.Tools;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


public class FileManagement {
	
	private String pathFile = null;
	
	public ArrayList<String> init(String pathFile){
		this.pathFile = pathFile+".txt";
		
		ArrayList<String> headerFile = new ArrayList<String>();
		System.out.printf("\nConteúdo do arquivo texto:\n");
	    try {
	    	FileReader arq = new FileReader(this.pathFile);
	    	BufferedReader lerArq = new BufferedReader(arq);
	    	
	    	String linha = null; 
	    	
	    	do{
	    		linha = lerArq.readLine(); // lê linha por linha
	    		if(linha.equals(":>HEADER")){//aqui irei selecionar a linha que quero.
	    			for (int i = 0; i < 4; i++) {
	    				headerFile.add(lerArq.readLine());
					}
	    			break;
	    		}  
	    	}while (linha != null); 
	    	
	    	arq.close();
	    } catch (IOException e) {
	        System.err.printf("Erro na abertura do arquivo: %s.\n",
	          e.getMessage());
	    }
	    return headerFile;
		
	}
	
	public ArrayList<String> getContentFile(int numLine){
		System.out.println("Chega: "+String.valueOf(numLine));
		ArrayList<String> contentFile = new ArrayList<String>();
	    
		try {
	    	FileReader arq = new FileReader(pathFile);
	    	BufferedReader lerArq = new BufferedReader(arq);
	 
	    	String linha = null; 
	    	
	    	do{
	    		linha = lerArq.readLine(); // lê linha por linha
	    		if(linha.equals(":>"+String.valueOf(numLine))){//aqui irei selecionar a linha que quero.
	    			for (int i = 0; i < 4; i++) {
	    				contentFile.add(lerArq.readLine());
					}
	    			break;
	    		}  
	    	}while (linha != null); 
	      		//System.out.printf("%s\n", linha);
	    	arq.close();
	    } catch (IOException e) {
	        System.err.printf("Erro na abertura do arquivo: %s.\n",
	          e.getMessage());
	    }
	    return contentFile;
	 
	}

}
