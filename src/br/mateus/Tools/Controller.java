package br.mateus.Tools;

import java.util.ArrayList;

import br.mateus.View.UnicWindow;

public class Controller {
	private UnicWindow view;
	private SoundManagement soundManager = new SoundManagement();
	private FileManagement fileManager = new FileManagement();
	private WorkingStrings workingStrings = new WorkingStrings();
	
	public void init(){
		//TODO:
		view = new UnicWindow(this, "Training Phrases");
		view.init();
	}
	
	public void openSoundFile(String pathSound){
		soundManager.executeSelectedSound(pathSound);
	}
	
	public void playSound(int posIni, int posFinal){
		soundManager.playSound(posIni, posFinal);
	}
	
	public ArrayList<String> getAmounthPhraseInFile(String nameFile){
		return fileManager.init(nameFile);
	}
	
	public ArrayList<String> getContentFile(int idPhrase){
		return fileManager.getContentFile(idPhrase);
	}
	
	public String ctrlCompareStrings(String str1, String str2){
		return workingStrings.compareStrings(str1, str2);
	}
	
	public boolean getStatusAnswer(){
		return workingStrings.getBingo();
	}

}
