package br.mateus.View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import br.mateus.Tools.Controller;

public class UnicWindow extends JFrame{
	
	final static boolean shouldFill = true;
	final static boolean shouldWeightX = true;
	
	private int vTop = 6;//10
	private int vLeft = 1;//5
	private int vRight = 1;//5
	private int vBottom = 6;//10
	private int multBorda = 20;
	
	private JMenuBar menu;
	private JMenu menuAbout;
	
	//Paineis....
	private JPanel painelGeral;
	private JPanel painelCentral;
	private JPanel painelSuperior;
	private JPanel painelInferior;
	
	private JPanel painelSupSup;
	private JPanel painelSupInf;
	
	//Painel Superior
	private JButton importFile = new JButton("Import File");
	private JTextField textPathFile = new JTextField(30);
	//private JLabel textPathFile = new JLabel("");
	private JLabel labelAudioFile = new JLabel("Audio File");
	private JLabel labelMode = new JLabel("Mode: ");
	private JRadioButton radioSequence = new JRadioButton("Sequence");
	private JRadioButton radioRandom = new JRadioButton("Random");
	private ButtonGroup group = new ButtonGroup();
	private JCheckBox checkShowTraslation = new JCheckBox("Show translation");
	
	//Painel Central
	private JButton startTraining = new JButton("Start Training");
	private JButton play = new JButton("PLAY");
	private JButton stop = new JButton("STOP");
	private JButton send = new JButton("SEND");
	private JButton buttonNext = new JButton("Next");
	private JLabel labelPhrase = new JLabel("Phares Nº ");
	private JLabel labelTextPhrase = new JLabel("00");
	//private JLabel j1 = new JLabel("");
	private JTextField textSentence = new JTextField(30);
	
	private JTextArea areaSentence = new JTextArea();
	private JLabel textCorrection = new JLabel("Correction");
	private JLabel textTranslation = new JLabel("Translation");
	
	//Painel Inferior
	private JLabel labelStudied = new JLabel("Studied: 0 de 0");
	private JLabel labelScore = new JLabel ("HITS: 00 WRONGS: 00");
	
	private int totalPhrasesStudied = 0;
	private int acertos = 0;
	private int wrongs = 0;
	
	private Controller ctrl;
	private String pathAudio;
	private boolean machineTraining = false;
	
	private ArrayList<String> headerFile = new ArrayList<String>();
	private ArrayList<String> phraseStudying = new ArrayList<String>();
	
	private ArrayList<Integer> sequenceStudy = new ArrayList<Integer>();
	
	public UnicWindow(Controller ctrl, String titulo){
		super(titulo);
		this.ctrl = ctrl;
	}
	
	public void init(){
		super.setSize(620, 420);
		super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		super.setContentPane(getPainelGeral());
		super.setJMenuBar(getMenu());
		super.setVisible(true);
		//super.setResizable(false);//Desabilitar o Maximizar
	}
	
	private JMenuBar getMenu(){
			
		if(menu == null){
			menu = new JMenuBar();
			menu.add(getMenuAbout());
		}
		return menu;
	}
	
	public JMenu getMenuAbout() {
		if(menuAbout == null){
			menuAbout = new JMenu("Help");
			JMenuItem about = new JMenuItem("About");
			menuAbout.add(about);
			
			about.addActionListener(new ActionListener() {
				
				public void actionPerformed(ActionEvent arg0) {
					System.out.println("clicked");
					JOptionPane.showMessageDialog(UnicWindow.this, "Software para auxiliar no Aprendizado de língua Estrangeira!\n"
							+ "Beta Version 1.0 - @2017\nUPDATE 20170519-08:52\n\nDeveloped By Mateus Ferreira de Souza\n"
							+"\nseraomateus@hotmail.com");
					
				}
			});
		}
		return menuAbout;
	}
	
	private JPanel getPainelGeral(){
		
		if(painelGeral == null){
			painelGeral = new JPanel();
			BorderLayout layout = new BorderLayout();
			painelGeral.setLayout(layout);
			
			painelGeral.add(getPainelSuperior(), BorderLayout.NORTH);
			painelGeral.add(getPainelCentral(), BorderLayout.CENTER);
			painelGeral.add(getPainelInferior(), BorderLayout.SOUTH);
		}
		return painelGeral;
	}
		//PAINEL SUPERIOR......
		private JPanel getPainelSuperior(){
			if(painelSuperior == null){
				painelSuperior = new JPanel();
				painelSuperior.setLayout(new GridBagLayout());
				GridBagConstraints c = new GridBagConstraints();
				
				radioRandom.setSelected(true);
				group.add(radioSequence);
				group.add(radioRandom);
				checkShowTraslation.setSelected(true);
				//textPathFile.setEnabled(false);
				textPathFile.setEditable(false);
				
				if (shouldFill) {
					//natural height, maximum width
					c.fill = GridBagConstraints.HORIZONTAL;
				}
				if (shouldWeightX) {
					c.weightx = 0.20;
				}
				
				c.fill = GridBagConstraints.HORIZONTAL;
				c.insets = new Insets(vTop,vLeft * multBorda,vBottom,vRight);
				//c.weightx = 0.25;
				c.gridx = 0;
				c.gridy = 0;
				//c.gridwidth = 4;
				painelSuperior.add(importFile,c);
				
				c.fill = GridBagConstraints.HORIZONTAL;
				c.insets = new Insets(vTop,vLeft * multBorda,vBottom,vRight);
				//c.weightx = 0.25;
				c.gridx = 1;
				c.gridy = 0;
				c.gridwidth = 2;
				painelSuperior.add(textPathFile,c);
				
				c.gridwidth = 1;
				c.fill = GridBagConstraints.HORIZONTAL;
				c.insets = new Insets(vTop,vLeft * multBorda,vBottom,vRight);
				c.gridx = 3;
				c.gridy = 0;
				//c.gridwidth = 4;
				painelSuperior.add(labelAudioFile,c);
				
				c.fill = GridBagConstraints.HORIZONTAL;
				c.insets = new Insets(vTop,vLeft * multBorda,vBottom,vRight);
				c.gridx = 0;
				c.gridy = 1;
				//c.gridwidth = 4;
				painelSuperior.add(labelMode,c);
				
				c.fill = GridBagConstraints.HORIZONTAL;
				c.insets = new Insets(vTop,vLeft * multBorda,vBottom,vRight);
				c.gridx = 1;
				c.gridy = 1;
				//c.gridwidth = 4;
				painelSuperior.add(radioSequence,c);
				
				c.fill = GridBagConstraints.HORIZONTAL;
				c.insets = new Insets(vTop,vLeft * multBorda,vBottom,vRight);
				c.gridx = 2;
				c.gridy = 1;
				//c.gridwidth = 4;
				painelSuperior.add(radioRandom,c);
				
				c.fill = GridBagConstraints.HORIZONTAL;
				c.insets = new Insets(vTop,vLeft * multBorda,vBottom,vRight);
				c.gridx = 3;
				c.gridy = 1;
				//c.gridwidth = 4;
				painelSuperior.add(checkShowTraslation,c);
				
				c.fill = GridBagConstraints.HORIZONTAL;
				c.insets = new Insets(vTop,vLeft * multBorda,vBottom,vRight);
				c.gridx = 0;
				c.gridy = 2;
				c.gridwidth = 4;
				painelSuperior.add(new JSeparator(SwingConstants.HORIZONTAL), c);
				
				//Acões.....
				importFile.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent arg0) {
						System.out.println("Escolher arquivo tipo musica");
						JFileChooser arquivo = new JFileChooser();
						arquivo.setFileSelectionMode(JFileChooser.FILES_ONLY);
						
						if(arquivo.showOpenDialog(arquivo) == JFileChooser.APPROVE_OPTION){ 
				              pathAudio = arquivo.getSelectedFile().getPath();
				              System.out.println("Path: "+pathAudio);
				              textPathFile.setText(arquivo.getSelectedFile().getName());
				              ctrl.openSoundFile(pathAudio);				              
				              startTraining.setEnabled(true);
				              
				              //Obtendo o nome do arquivo sem a extensão (Lembrando que a extensão é .wav)
				              //String temp = arquivo.getSelectedFile().getName();
				              String nameFileText = pathAudio.substring(0,pathAudio.length()- 4);
				              System.out.println("TESTANDO: "+nameFileText);
				              headerFile = ctrl.getAmounthPhraseInFile(nameFileText);
				              
				              //Gera a sequencia
				              for(int i = 1; i <= Integer.parseInt(headerFile.get(1)); i++){
				            	  sequenceStudy.add(i);
				              }
    
				          }
					}
				});
				
			}
			
			return painelSuperior;
		}
		
		//PAINEL CENTRAL........
		private JPanel getPainelCentral(){
			if (painelCentral == null){
				painelCentral = new JPanel();
				painelCentral.setLayout(new GridBagLayout());
				GridBagConstraints c = new GridBagConstraints();
				
				buttonNext.setEnabled(false);
				
				enableTraining();
				
				if (shouldFill) {
					//natural height, maximum width
					c.fill = GridBagConstraints.HORIZONTAL;
				}
				if (shouldWeightX) {
					c.weightx = 0.20;
				}
				
				
				c.fill = GridBagConstraints.HORIZONTAL;
				c.insets = new Insets(vTop,vLeft * multBorda,vBottom,vRight);
				//c.weightx = 0.25;
				c.gridx = 0;
				c.gridy = 0;
				c.gridwidth = 4;
				painelCentral.add(startTraining,c);
				
				c.gridwidth = 1;
				c.fill = GridBagConstraints.HORIZONTAL;
				c.insets = new Insets(vTop,vLeft,vBottom,vRight);
				c.gridx = 0;
				c.gridy = 1;
				painelCentral.add(labelPhrase,c);
				
				c.fill = GridBagConstraints.HORIZONTAL;
				c.insets = new Insets(vTop,vLeft,vBottom,vRight);
				c.gridx = 1;
				c.gridy = 1;
				painelCentral.add(labelTextPhrase,c);
				
				c.fill = GridBagConstraints.HORIZONTAL;
				c.insets = new Insets(vTop,vLeft,vBottom,vRight);
				c.gridx = 2;
				c.gridy = 1;
				painelCentral.add(play,c);
				
				c.fill = GridBagConstraints.HORIZONTAL;
				c.insets = new Insets(vTop,vLeft,vBottom,vRight);
				c.gridx = 3;
				c.gridy = 1;
				painelCentral.add(stop,c);
				
				/*c.fill = GridBagConstraints.HORIZONTAL;
				c.insets = new Insets(vTop,vLeft,vBottom,vRight);
				c.gridx = 4;
				c.gridy = 1;
				painelCentral.add(j1,c);*/
				
				c.fill = GridBagConstraints.HORIZONTAL;
				c.insets = new Insets(vTop,vLeft,vBottom,vRight);
				c.gridx = 0;
				c.gridy = 2;
				c.gridwidth = 3;
				//painelCentral.add(textSentence,c);
				painelCentral.add(getTextSentence(), c);
				
				c.gridwidth = 1;
				c.fill = GridBagConstraints.HORIZONTAL;
				c.insets = new Insets(vTop,vLeft,vBottom,vRight);
				c.gridx = 3;
				c.gridy = 2;
				painelCentral.add(send,c);
				
				c.fill = GridBagConstraints.HORIZONTAL;
				c.insets = new Insets(vTop,vLeft,vBottom,vRight);
				c.gridx = 0;
				c.gridy = 3;
				c.gridwidth = 2;
				painelCentral.add(textCorrection,c);
				
				c.gridwidth = 1;
				c.fill = GridBagConstraints.HORIZONTAL;
				c.insets = new Insets(vTop,vLeft,vBottom,vRight);
				c.gridx = 3;
				c.gridy = 3;
				painelCentral.add(buttonNext,c);
				
				c.fill = GridBagConstraints.HORIZONTAL;
				c.insets = new Insets(vTop,vLeft,vBottom,vRight);
				c.gridx = 0;
				c.gridy = 4;
				c.gridwidth = 3;
				painelCentral.add(textTranslation,c);
				
				c.fill = GridBagConstraints.HORIZONTAL;
				c.insets = new Insets(vTop,vLeft,vBottom,vRight);
				c.gridx = 0;
				c.gridy = 5;
				c.gridwidth = 4;
				painelCentral.add(new JSeparator(SwingConstants.HORIZONTAL),c);
				
				startTraining.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						machineTraining = true;
						enableTraining();
						startTraining.setEnabled(false);
						
						if(radioSequence.isSelected()){
							Collections.shuffle(sequenceStudy);
							phraseStudying = ctrl.getContentFile(sequenceStudy.get(1));
							//Melhorar o contador 
							labelTextPhrase.setText("01");
						}
						else{//Ainda não implementado por isso iqual ao outro.
							phraseStudying = ctrl.getContentFile(1);
							labelTextPhrase.setText("01");
						}
						
						//System.out.println("FAF? "+phraseStudying.get(1));
						labelStudied.setText("Studied: 0 de "+headerFile.get(1));//Obtem a qtd de frases, baseado no header.
						
					}
				});
				
				play.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						//Aqui iremos procurar no arquivo para obter o pedaço a ser tocado e também
						//o texto e a tradução.
						buttonNext.setEnabled(false);
						//Pensar em mudar para string(1 string ao invés de 2 valores), e fazer a arrumação lá na classe
						//ctrl.playSound(Integer.parseInt(phraseStudying.get(0)), Integer.parseInt(phraseStudying.get(1)));
						ctrl.playSound(phraseStudying.get(0));
					}
				});
				
				send.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						buttonNext.setEnabled(true);
						toogleButtonState(false);
						
						//Atualizar display.
						totalPhrasesStudied++;
						//labelStudied.setText("Studied: "+totalPhrasesStudied+" de "+headerFile.get(1));
			
						textCorrection.setText(ctrl.ctrlCompareStrings(phraseStudying.get(1), areaSentence.getText()));
						setScoreAndDisplay(0);
						if(checkShowTraslation.isSelected()){//show de translation
							textTranslation.setText(phraseStudying.get(2));
						}
						
					}
				});
				
				buttonNext.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						System.out.println("Total: "+totalPhrasesStudied);
						if(totalPhrasesStudied+1 <= Integer.parseInt(headerFile.get(1))){
							buttonNext.setEnabled(false);//Desabilita o botão
							clearTraining();
							toogleButtonState(true);
							if(radioSequence.isSelected()){
								phraseStudying = ctrl.getContentFile(totalPhrasesStudied + 1);
								//Melhorar o contador 
								//labelTextPhrase.setText(String.valueOf(totalPhrasesStudied + 1));
								setScoreAndDisplay(1);
							}
							
						}
						else{//Reset 
							clearTraining();
							toogleButtonState(false);
							startTraining.setEnabled(true);
							buttonNext.setEnabled(false);
							toogleState(false);
							totalPhrasesStudied = 0;
							setScoreAndDisplay(2);
						}
					}
				});
			}
			
			return painelCentral;
		}
		
		private void toogleButtonState(boolean state){
			play.setEnabled(state);
			stop.setEnabled(state);
			send.setEnabled(state);
		}
		
		private void clearTraining(){
			areaSentence.setText("");
			textCorrection.setText("Correction");
			textTranslation.setText("Translation");
		}
		
		private void toogleState(boolean state){
			play.setEnabled(state);
			stop.setEnabled(state);
			areaSentence.setEnabled(state);
			send.setEnabled(state);
			buttonNext.setEnabled(state);
		}
		
		private void enableTraining(){
			startTraining.setEnabled(machineTraining);
			play.setEnabled(machineTraining);
			stop.setEnabled(machineTraining);
			textSentence.setEnabled(machineTraining);
			send.setEnabled(machineTraining);
			areaSentence.setEnabled(machineTraining);
		}
		
		private JTextArea getTextSentence(){
			areaSentence.setAlignmentX(CENTER_ALIGNMENT);
			areaSentence.setColumns(20);
			areaSentence.setLineWrap(true);
			areaSentence.setRows(2);
			
			return areaSentence;
		}
		
		//PAINEL INFERIOR........
		private JPanel getPainelInferior(){
			if(painelInferior == null){
				painelInferior = new JPanel();
				painelInferior.add(labelStudied);
				painelInferior.add(labelScore);
			}
			
			return painelInferior;
		}
		
		private void setScoreAndDisplay(int value){
			System.out.println("status: "+ctrl.getStatusAnswer());
			switch(value){
				case 0://button send
					labelStudied.setText("Studied: "+totalPhrasesStudied+" de "+headerFile.get(1));
					if(ctrl.getStatusAnswer())
						labelScore.setText("HITS: "+ ++acertos+" WRONGS: "+wrongs);
					else
						labelScore.setText("HITS: "+acertos+" WRONGS: "+ ++wrongs);
					break;
					
				case 1://button next
					labelTextPhrase.setText(String.valueOf(totalPhrasesStudied + 1));
					break;
					
				case 2://Clear score
					labelStudied.setText("Studied: 0 de 0");
					labelScore.setText("HITS: 00 WRONGS: 00");
					labelTextPhrase.setText("00");
					break;
			}
			
		}

}
