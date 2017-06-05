package br.mateus.Tools;

import javax.media.*;
import java.net.URL;
import java.util.ArrayList;

public class SoundMP3Manager {
	
	private Time time;
	
	private boolean statePlay = false;
	private Player player;
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
		
		//System.out.println("INI: "+tempo.get(0)+"\nEND: "+tempo.get(1));
		return tempo;
	}
	
	public void executeSelectedSound(String pathAudioFile){
		try {
	        // URL url = this.getClass().getClassLoader().getResource(filename);
	    	  //URL url = new URL ("file:///D:/video1.mp3");
	    	  //URL url = new URL (pathAudioFile);
	    	  URL url = new URL ("file:///"+pathAudioFile);
	    	  MediaLocator locator = new MediaLocator(url);
	    	  player = Manager.createPlayer(locator);
	    	  player.addControllerListener(new ControllerListener() {
	            public void controllerUpdate(ControllerEvent event) {
	               if (event instanceof EndOfMediaEvent) {
	                  player.stop();
	                  player.close();
	               }
	            }
	         });
	         player.realize();
	         //player.start();
	      } catch (Exception e) {
	         e.printStackTrace();
	      }
		
	}
	
	public void playSound(String soundTime) {
		tempo = formatStringTime(soundTime);
		long tEnd = tempo.get(1)*10000;
		long tIni = tempo.get(0)*10000;
		if(statePlay == false){
			//time = new Time(tempo.get(0) * 1000 * 1000);
			time = new Time(tIni * 100);
			System.out.println("Tempo ini: "+tempo.get(0)+" Tempo End: "+tempo.get(1));
			player.setMediaTime(time);
			player.start();
			statePlay = true;
			Thread thread = new Thread(){
				public void run(){
					while(true){
						//if(player.getMediaNanoseconds() >= tempo.get(1) * 1000 * 1000)
						if(player.getMediaNanoseconds() >= tEnd * 100)	
							break;
					}
					stopClip();//O problema está neste método. não está executando a faixa certa
				}
			};
			thread.start();
			
		}
	}
	
	public void stopClip(){
		player.stop();
		System.out.println("Stopping in: "+player.getMediaNanoseconds());
		//clip.setFramePosition(0);
		statePlay = false;
		
		
	}

}
