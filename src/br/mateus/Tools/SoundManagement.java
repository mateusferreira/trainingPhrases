package br.mateus.Tools;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JFrame;

/*Tentar tocar mp3
 * https://odoepner.wordpress.com/2013/07/19/play-mp3-or-ogg-using-javax-sound-sampled-mp3spi-vorbisspi/
 * https://stackoverflow.com/questions/6045384/playing-mp3-and-wav-in-java
 * 
 * Ver o último topico dá página.
 * https://www3.ntu.edu.sg/home/ehchua/programming/java/J8c_PlayingSound.html
 */

public class SoundManagement {
	
	private boolean statePlay = false;
	
	private Clip clip;
	private ArrayList<Integer> tempo = new ArrayList<Integer>();
	
	private ArrayList<Integer> formatStringTime(String str){
		//00:00:53,000-->00:01:00,680
		//0123456789
		String string;
		int num;
		
		string = str.replaceAll("\\s+", "");//Retira os espaços vazios da string
		System.out.println("TESTE: "+string);
		
		for(int i = 0, j = 0; i < 2; i++, j+=15){
			//Horas 
			num = 3600 * Integer.parseInt(string.substring(0+j, 2+j));
			
			num += 60 * Integer.parseInt(string.substring(3+j, 5+j));
			
			num += Integer.parseInt(string.substring(6+j, 8+j));
			
			num =  (num * 1000) + Integer.parseInt(string.substring(9+j, 12+j));
			
			//System.out.println("TESTE SUB: "+num);
			tempo.add(i, num);
		}
		
		return tempo;
	}
	
	
	
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
	
	public void playSound(String soundTime) {
		tempo = formatStringTime(soundTime);
		
		if(statePlay == false){
			
			//clip.setFramePosition(510000);
			clip.setMicrosecondPosition(tempo.get(0) * 1000);
			clip.start();
			statePlay = true;
			Thread thread = new Thread(){
				public void run(){
					while(true){
						if(clip.getMicrosecondPosition() >= tempo.get(1) * 1000)
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
