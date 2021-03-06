package br.mateus.Tools;

import java.util.ArrayList;

import br.mateus.View.UnicWindow;

public class Controller {
	private UnicWindow view;
	private SoundManagement soundManager = new SoundManagement();
	private SoundMP3Manager soundMP3 = new SoundMP3Manager();
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
	
	public void openSoundMP3(String pathSound){
		soundMP3.executeSelectedSound(pathSound);
	}
	
	public void playSound(String duringTime){
		soundManager.playSound(duringTime);
	}
	
	public void playMP3Sound(String duringTime){
		soundMP3.playSound(duringTime);
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
	
	public void setStatusPhrase (boolean hitOrWrong){
		fileManager.recordStatus(hitOrWrong);
	}

}
