package br.mateus.Tools;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;


public class FileManagement {
	
	private String pathFile = null;
	private int numLinha;
	
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
		numLinha = 0;
	    
		try {
	    	FileReader arq = new FileReader(pathFile);
	    	BufferedReader lerArq = new BufferedReader(arq);
	 
	    	String linha = null; 
	    	
	    	do{
	    		linha = lerArq.readLine(); // lê linha por linha
	    		numLinha++;//conta a linha
	    		if(linha.equals(":>"+String.valueOf(numLine))){//aqui irei selecionar a linha que quero.
	    			for (int i = 0; i < 3; i++) {//Conteúdo do Array: (0 -faixa audio) (1 - texto 1) (2 - texto 2)
	    				contentFile.add(lerArq.readLine());
	    				numLinha++;
					}
	    			break;
	    		}  
	    	}while (linha != null); 
	    	
	    	arq.close();
	    } catch (IOException e) {
	        System.err.printf("Erro na abertura do arquivo: %s.\n",
	          e.getMessage());
	    }
	    return contentFile;
	 
	}
	
	public void recordStatus(boolean hitOrWrong){
		int contaLinha = 0;
		String linha = "VAI LA";
		String origin = null;
		try {
			FileReader arq = new FileReader(this.pathFile);
			BufferedReader lerArq = new BufferedReader(arq);
			StringBuilder sb = new StringBuilder();
			
			//while(contaLinha < numLinha){
			while((origin = lerArq.readLine()) != null){
				contaLinha++;
				
				if(contaLinha == numLinha){
					sb.append(linha+" \n");
				}
				else
					sb.append(origin+" \n");
			}
			System.out.println("ContaLinha: "+contaLinha+" numLinha: "+numLinha);
			//sb.append(linha);
			arq.close();
			new FileOutputStream(this.pathFile).write(sb.toString().getBytes());
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
    	
	}

}
