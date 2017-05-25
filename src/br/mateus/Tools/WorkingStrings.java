package br.mateus.Tools;

public class WorkingStrings {
	private boolean bingo = false;
	
	public String compareStrings (String str1, String str2){

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
