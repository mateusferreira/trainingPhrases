package br.mateus.Tools;

public class WorkingStrings {
	private boolean bingo = false;
	
	public String compareStrings (String str1, String str2){
		
		//Retirar espaços no início ou fim da string.
		str1 = str1.trim();
		str2 = str2.trim();
		
		//Sobre o replace abaixo.
		//ajuda se tiver espaços extras entre palavras, mas cria problemas do tipo:
		//aceita (every body) ao invés de everybody. Aceita m or nin g  ao invés de morning
		//Pensar maneira mais inteligente para solucionar isso.
		str1 = str1.replaceAll("\\s+", "");//Retira os espaços vazios da string
		str2 = str2.replaceAll("\\s+", "");//Retira os espaços vazios da string
		

		if(str1.toUpperCase().equals(str2.toUpperCase())){
			//System.out.println("VERY WEEL!...");
			bingo = true;
			return "    !!!VERY WEEL!!!       GOOD WORK!!";
		}
		
		else{//Ficar atento com o problema da codifica��o - UTF-8
			System.out.println("Len1: "+str1.length()+"Len2: "+str2.length());
			System.out.println("STR1: "+str1.toUpperCase());
			System.out.println("STR2: "+str2.toUpperCase());
			bingo = false;
			return str1;
		}
		
	}
	
	public boolean getBingo(){
		return bingo;
	}

}
