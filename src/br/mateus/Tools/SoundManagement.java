package br.mateus.Tools;

import java.io.IOException;
import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JFrame;

public class SoundManagement {
	
	private boolean statePlay = false;
	
	private Clip clip;
	
	
	
	public void executeSelectedSound(String pathAudioFile){
		try {
	         // Open an audio input stream.
			//URL url = new URL("file:///D:/testes_training/001 From Struggle Comes Success.wav");
			URL url = new URL ("file:///"+pathAudioFile);
	        AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
	         // Get a sound clip resource.
	        clip = AudioSystem.getClip();
	         // Open audio clip and load samples from the audio input stream.
	        clip.open(audioIn);
	        
	        //clip.start();
	       
	      } catch (UnsupportedAudioFileException e) {
	    	  e.printStackTrace();
	      } catch (IOException e) {
	         e.printStackTrace();
	      } catch (LineUnavailableException e) {
	         e.printStackTrace();
	      }
   }
	
	public void playSound(int posIni, int posFinal) {
		if(statePlay == false){
			
			//clip.setFramePosition(510000);
			clip.setMicrosecondPosition(posIni * 1000);
			clip.start();
			statePlay = true;
			Thread thread = new Thread(){
				public void run(){
					while(true){
						if(clip.getMicrosecondPosition() >= posFinal * 1000)
							break;
					}
					stopClip();
				}
			};
			thread.start();
			
		}
	}
	
	public void stopClip(){
		clip.stop();
		System.out.println("Stopping in: "+clip.getFramePosition());
		clip.setFramePosition(0);
		statePlay = false;
		
		
	}
	

}
