package br.mateus.Tools;

public enum EnumContentArrayPhrase {
	
	PIECE_AUDIO(0),
	TEXT1(1),
	TEXT2(2);
	
	private int contentArray;
	
	private EnumContentArrayPhrase(int contentArray) {
		this.contentArray = contentArray;
	}
	
	public int getContentArray(){
		return contentArray;
	}

}
