package main;

import java.awt.EventQueue;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;
import javax.swing.SwingWorker;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;

import data.House;

import java.awt.CheckboxGroup;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.awt.GridLayout;
import java.awt.Label;

public class Hall extends JFrame {
	//작성자 이명진
	
	static private JLabel contentPane;
	static private JPanel playerField;
	static private JPanel dealerField;
	static private JLayeredPane jlp;
	static private JLabel [] dealerCard;
	static private ArrayList<House> dealerHand;
	static private int dealerScore=0;
	static private JLabel dealerScoreBoard;
	static private boolean dealerBurst=false;
	
	static private int budget;
	static private int startBudget;
	static private int bet=0;
	static private int insuranceBet=0;
	static private int splitCnt;
	
	static private ArrayList<House> [] playerHand;
	static private JPanel [] playerFieldPanel;
	static private JPanel [] playerCardPanel;
	static private JLabel [][] playerCard;
	static private Border border;
	static private JPanel [] playerbuttonPanel;
	
	static private JLabel [] scoreMonitorA;
	static private JLabel [] scoreMonitorB;
	static private String sort="점수";
	static private JLabel [] playMonitor;
	static private JButton [] hit;
	static private JButton [] stay;
	static private JButton [] DD;
	static private int [] playerScore;
	
	static private int sixCardCnt;
	static private boolean [] canSplit;
	static private boolean aceSplit;
	static private boolean blackJack;
	static private boolean [] sixCard;
	
	static private boolean [] doubleDown;
	static private boolean [] burst;
	static private boolean [] condition;
	static private boolean [] result;
	static private boolean fResult=false;
	static private boolean playable=false;
	static private boolean online=false; // 
	static private boolean fieldcheck1=false; // 다른 플레이어 필드정보 받아오기위한 flag
	static private boolean fieldcheck2=false; // 다른 플레이어 필드정보 받아오기위한 flag
	static private int burstCnt;
	
	static private int doubleDownCnt;
	static private FlowLayout flowForm;
	static private boolean signal = false;
	static private boolean flag = false;
	static private boolean flag2 = false;
	static private boolean flag3 = false;
	static private boolean flag4 = false;
	static private boolean hiddenOpen=false;
	
	static private JPanel leftSide;
	static private JPanel rightSide;
	static private JLabel information;
	static private JPanel informationPanel;
	static private JPanel choicePanel;
	static private JLabel signalOff;
	static private JLabel signalOn;
	static private JLabel timer;
	
	static private JPanel ready;
	static private JLabel readyConfirm;
	static private JLabel readyMessege;
	static private JButton readyButtonA;
	static private JButton readyButtonB;
	
	static private JPanel evenMoney;
	static private JLabel evenMoneyMessege;
	static private JButton evenMoneyButtonA;
	static private JButton evenMoneyButtonB;
	
	static private JPanel insure;
	static private JLabel insureMessege;
	static private JButton insureButtonA;
	static private JButton insureButtonB;
	
	static private String [] bettingOptions;
	static private JPanel betting;
	static private JLabel bettingMessege;
	static private JComboBox bettingChoice;
	static private JButton bettingButtonA;
	static private JButton bettingButtonB;
	
	static private JPanel split;
	static private JPanel split2;
	static private boolean splitCheck=false;
	static private JLabel splitMessege;
	static private JLabel splitMessege2;
	static private JRadioButton splitChoice1;
	static private JRadioButton splitChoice2;
	static private JRadioButton splitChoice3;
	static private JButton splitButtonA;
	static private JButton splitButtonB;
	static private ButtonGroup splitChoice;
	
	static private File flipFile;
	static private AudioInputStream flipAudio;
	static private Clip flipClip;
	static private File dealFile;
	static private AudioInputStream dealAudio;
	static private Clip dealClip;
	static private File winFile;
	static private AudioInputStream winAudio;
	static private Clip winClip;
	static private File loseFile;
	static private AudioInputStream loseAudio;
	static private Clip loseClip;
	static private File drawFile;
	static private AudioInputStream drawAudio;
	static private Clip drawClip;
	
	static private JPanel select;
	static private JLabel selectMessege;
	
	static private JPanel conclusion;
	static private JLabel conclusionMessege;
	
	static private JLabel test;
	
	House tc;
	ArrayList<House> pureDeck;
	
	Thread t1;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JPanel panel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		int temp=0;
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Hall frame = new Hall(temp);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public Hall() {
		int temp=0;
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Hall frame = new Hall(temp);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public Hall(int temp) throws Exception {
		settingTable();

		
		tc = new House();
		border = BorderFactory.createLineBorder(Color.DARK_GRAY,2);
		
		flipFile = new File("src/sound/flip.wav");
		flipAudio = AudioSystem.getAudioInputStream(flipFile);
        flipClip = AudioSystem.getClip();
        flipClip.open(flipAudio);
        
        dealFile = new File("src/sound/deal.wav");
        dealAudio = AudioSystem.getAudioInputStream(dealFile);
        dealClip = AudioSystem.getClip();
        dealClip.open(dealAudio);
        
        winFile = new File("src/sound/win.wav");
        winAudio = AudioSystem.getAudioInputStream(dealFile);
        winClip = AudioSystem.getClip();
        winClip.open(winAudio);
        
        drawFile = new File("src/sound/draw.wav");
        drawAudio = AudioSystem.getAudioInputStream(dealFile);
        drawClip = AudioSystem.getClip();
        drawClip.open(drawAudio);
        
        loseFile = new File("src/sound/lose.wav");
        loseAudio = AudioSystem.getAudioInputStream(dealFile);
        loseClip = AudioSystem.getClip();
        loseClip.open(loseAudio);
		
		pureDeck = tc.cardSetter(); // 정상카드 덱
//		pureDeck = tc.cardSetterToTest(); // 기능 테스트용 커스텀 카드 덱
		
		budget = 500; // 게임 시작 예산

		
		resetIndex();
		monitorUpdate();
		readyCheck(5);
		readyServerCheck();
}
	
	public void tableSet(JComponent contentPane2) throws Exception {
		settingTable();

		
		tc = new House();
		border = BorderFactory.createLineBorder(Color.DARK_GRAY,2);
		
		flipFile = new File("src/sound/flip.wav");
		flipAudio = AudioSystem.getAudioInputStream(flipFile);
        flipClip = AudioSystem.getClip();
        flipClip.open(flipAudio);
        
        dealFile = new File("src/sound/deal.wav");
        dealAudio = AudioSystem.getAudioInputStream(dealFile);
        dealClip = AudioSystem.getClip();
        dealClip.open(dealAudio);
        
        winFile = new File("src/sound/win.wav");
        winAudio = AudioSystem.getAudioInputStream(dealFile);
        winClip = AudioSystem.getClip();
        winClip.open(winAudio);
        
        drawFile = new File("src/sound/draw.wav");
        drawAudio = AudioSystem.getAudioInputStream(dealFile);
        drawClip = AudioSystem.getClip();
        drawClip.open(drawAudio);
        
        loseFile = new File("src/sound/lose.wav");
        loseAudio = AudioSystem.getAudioInputStream(dealFile);
        loseClip = AudioSystem.getClip();
        loseClip.open(loseAudio);
		
		pureDeck = tc.cardSetter(); // 정상카드 덱
//		pureDeck = tc.cardSetterToTest(); // 기능 테스트용 커스텀 카드 덱
		
		budget = 500; // 게임 시작 예산

		
		resetIndex();
		monitorUpdate();
		readyCheck(5);
		readyServerCheck();
	}


	public void readyCheck(int i) throws InterruptedException {
		ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(2);
			Runnable runnable = new Runnable() {
			int j=i;
			int k=0;
				@Override
				public void run() {
					choicePanel.setVisible(true);
					ready.setVisible(true);
					timer.setText(""+j);
					j--;
					if(j <0) {
						choicePanel.setVisible(false);
						ready.setVisible(false);
						readyConfirm.setVisible(false);
						flag2 = true;
							if(flag) {
								System.out.println("게임 시작");
							}else {
								System.out.println("레디 안해서 강퇴!");
								dispose();
							}
							System.out.println("스케쥴러셧다운");
							scheduler.shutdown();
						}
					}
				};
				scheduler.scheduleAtFixedRate(runnable, 0, 1, TimeUnit.SECONDS);
		}
	public void readyServerCheck() {
		SwingWorker<Void,Void> worker = new SwingWorker<Void, Void>(){

			@Override
			protected Void doInBackground() throws Exception {
				while(!flag || !flag2) {
					System.out.println("레디 신호대기중");
					Thread.sleep(500);
				}
				if(signal) {
				System.out.println("수신완료, 배팅창 출력");
				flag=false;
				flag2=false;
				bettingCheck(30);
				return null;
				}else {
					return null;
				}
			}
			
			protected void done() {
				if(signal) {
				bettingServerCheck();
				}else {
					
				}
			}
		};worker.execute();
		
	}

	
	protected void bettingCheck(int i) {
		ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
		Runnable runnable = new Runnable() {
		int j=i;
			@Override
			public void run() {
				choicePanel.setVisible(true);
				betting.setVisible(true);
				timer.setText(""+j);
				j--;
				if(j <0 || flag) {
					choicePanel.setVisible(false);
					betting.setVisible(false);
						if(flag) {
							bet = Integer.parseInt(bettingChoice.getSelectedItem().toString());
							budget -= bet;
							monitorUpdate();
							System.out.println("배팅금액 선택이 완료되었습니다.");
						}else {
							flag=true;
							bet = 10;
							budget -= bet;
							monitorUpdate();
							System.out.println("배팅금액을 선택하지않으셔서 최소배팅금액으로 선택되었습니다.");
						}
						flag2 = true;
						scheduler.shutdown();
					}
				}
			};
			scheduler.scheduleAtFixedRate(runnable, 0, 1, TimeUnit.SECONDS);
		
	}
	protected void bettingServerCheck() {
		SwingWorker<Void,Void> worker = new SwingWorker<Void, Void>(){

			@Override
			protected Void doInBackground() throws Exception {
				while(!flag || !flag2) {
					if(!signal) {
						break;
					}
					System.out.println("배팅 신호대기중");
					Thread.sleep(500);
				}
				System.out.println("배팅금액 선택완료, 카드 배분");
				flag=false;
				flag2=false;
				return null;
			}
			protected void done() {
				if(signal) {
				tc.cardSuffle(pureDeck);
				try {
					drawCardCheck(tc);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				drawCardServerCheck();
				}
			}
		};worker.execute();
	}
	
	
	private void drawCardCheck(House tc) throws InterruptedException {
		ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
		Runnable runnable = new Runnable() {
			@Override
			public void run() {
				if(playerHand[0].size()==dealerHand.size()) {
					playerHand[0] = tc.cardDrawP(playerHand[0]);
					PCardLabel(0);
				}else if(playerHand[0].size()>dealerHand.size()) {
					dealerHand = tc.cardDrawD(dealerHand);
					DCardLabel();
				}
				dealClip.setFramePosition(480000);
				dealClip.loop(1);
				if(playerHand[0].size()==2 && dealerHand.size()==2) {
					flag2 = true;
					flag = true;
					canSplit[0] = tc.splitLogic(playerHand[0]); // 스플릿 가능여부 체크
					playerScore[0] = tc.scoreCnt(playerHand[0]); // 플레이어 핸드별 스코어 계산
					dealerScore = tc.scoreCnt(dealerHand); 
					scoreUpdate();
					scheduler.shutdown();
					}
				}
			};
			scheduler.scheduleAtFixedRate(runnable, 0, 700, TimeUnit.MILLISECONDS);	
	}
	protected void drawCardServerCheck() {
		SwingWorker<Void,Void> worker = new SwingWorker<Void, Void>(){
			@Override
			protected Void doInBackground() throws Exception {
				while(!flag || !flag2) {
					System.out.println("카드배분 신호대기중");
					Thread.sleep(500);
				}
				flag=false;
				flag2=false;
				evenMoney.setVisible(false);
				return null;
			}
			protected void done() {
				blackJackCheck();
				playerField.revalidate();
				playerField.repaint();
				fResultCheck();
				playableCheck();
				if(!playable){
					if(!fResult) {
						timer.setVisible(false);
						choicePanel.setVisible(true);
						signalOff.setVisible(true);
//						conclutionFlagCheck();
						conclusionCheck(10);
						conclusionServerCheck();
					}else {
						timer.setVisible(false);
						choicePanel.setVisible(true);
						signalOn.setVisible(true);
//						cardOpenFlagCheck();
						try {
							Thread.sleep(500);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						dealerHiddenOpen();
						try {
							Thread.sleep(500);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						dealerBlackJackCheck(10);
						dealerBlackJackServerCheck();
					}
				}else if(splitCnt==0 && dealerHand.get(0).getCardNum()==1){
					if(playerScore[0]==21) {
						evenMoneyCheck(30);
						evenMoneyServerCheck();
					}else if(budget>bet/2){
						insureCheck(30);
						insureServerCheck();
					}
				}else {
					splitableCheck();
					if(splitCheck && budget>bet) {
						split.setVisible(true);
					}else {
						split2.setVisible(true);
						flag = true;
					}
					splitCheckOut(30);
					splitServerCheck();
				}
			}
		};worker.execute();
	}




	protected void evenMoneyCheck(int i) {
		ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
		Runnable runnable = new Runnable() {
		int j=i;
			@Override
			public void run() {
				choicePanel.setVisible(true);
				evenMoney.setVisible(true);
				timer.setText(""+j);
				j--;
				if(j <0 || flag) {
					flag2 = true;
					flag = true;
					choicePanel.setVisible(false);
					evenMoney.setVisible(false);
					scheduler.shutdown();
					}
				}
			};
			scheduler.scheduleAtFixedRate(runnable, 0, 1, TimeUnit.SECONDS);
	}
	protected void evenMoneyServerCheck() {
		SwingWorker<Void,Void> worker = new SwingWorker<Void, Void>(){
			@Override
			protected Void doInBackground() throws Exception {
				while(!flag || !flag2) {
					System.out.println("이븐 신호대기중");
					Thread.sleep(500);
				}
				flag=false;
				flag2=false;
				evenMoney.setVisible(false);
				return null;
			}
			protected void done() {
				if(signal) {
					System.out.println("이븐머니 미선택");
					timer.setVisible(false);
					choicePanel.setVisible(true);
					signalOn.setVisible(true);
					condition[0] = false;
//					cardOpenFlagCheck();
					dealerBlackJackCheck(10);
					dealerBlackJackServerCheck();
				}else {
					budget += bet*2;
					monitorUpdate();
					timer.setVisible(false);
					choicePanel.setVisible(true);
					signalOff.setVisible(true);
					condition[0] = false;
					result[0] = false;
//					conclutionFlagCheck();
					conclusionCheck(10);
					conclusionServerCheck();
				}
			}
		};worker.execute();
		
	}
	
	
	protected void insureCheck(int i) {
		ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
		Runnable runnable = new Runnable() {
		int j=i;
			@Override
			public void run() {
				choicePanel.setVisible(true);
				insure.setVisible(true);
				timer.setText(""+j);
				j--;
				if(j <0 || flag) {
					flag2 = true;
					choicePanel.setVisible(false);
					insure.setVisible(false);
					scheduler.shutdown();
					}
				}
			};
			scheduler.scheduleAtFixedRate(runnable, 0, 1, TimeUnit.SECONDS);
	}
	protected void insureServerCheck() {
		SwingWorker<Void,Void> worker = new SwingWorker<Void, Void>(){

			@Override
			protected Void doInBackground() throws Exception {
				while(!flag || !flag2) {
					System.out.println("인슈 신호대기중");
					Thread.sleep(500);
				}
				System.out.println("보험확인 완료");
				flag=false;
				flag2=false;
				return null;
			}
			protected void done() {
				splitableCheck();
				if(splitCheck && budget>bet) {
					split.setVisible(true);
				}else {
					split2.setVisible(true);
					flag = true;
				}
				splitCheckOut(30);
				splitServerCheck();
			}
		};worker.execute();
	}

	
	protected void splitCheckOut(int i) {
		ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
		Runnable runnable = new Runnable() {
		int j=i;
			@Override
			public void run() {
				choicePanel.setVisible(true);
				System.out.println(canSplit[0]==true);
				System.out.println(canSplit[1]==true);
				System.out.println(canSplit[2]==true);
				if(canSplit[0]) 
					splitChoice1.setEnabled(true);
				else 
					splitChoice1.setEnabled(false);
				
				if(canSplit[1]) 
					splitChoice2.setEnabled(true);
				else 
					splitChoice2.setEnabled(false);
				
				if(canSplit[2]) 
					splitChoice3.setEnabled(true);
				else 
					splitChoice3.setEnabled(false);
				
				timer.setText(""+j);
				j--;
				if(j <0 || flag) {
					flag2 = true;
					choicePanel.setVisible(false);
					split.setVisible(false);
					split2.setVisible(false);
					scheduler.shutdown();
					}
				}
			};
			scheduler.scheduleAtFixedRate(runnable, 0, 1, TimeUnit.SECONDS);
	}
	protected void splitServerCheck() {
		SwingWorker<Void,Void> worker = new SwingWorker<Void, Void>(){

			@Override
			protected Void doInBackground() throws Exception {
				while(!flag || !flag2) {
					System.out.println("스플릿 신호대기중");
					Thread.sleep(500);
				}
				System.out.println("스플릿확인 완료, 플레이어 행동 선택");
				flag=false;
				flag2=false;
				return null;
			}
			protected void done() {
				for(int i=0;i<=splitCnt;i++) {
					hit[i].setEnabled(true);
					stay[i].setEnabled(true);
					DD[i].setEnabled(true);
				}
				selectCheck(60);
				selectServerCheck();
			}
		};worker.execute();
	}
	
	
	protected void selectCheck(int i) {
		ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
		Runnable runnable = new Runnable() {
		int j=i;
			@Override
			public void run() {
				choicePanel.setVisible(true);
				select.setVisible(true);
				timer.setText(""+j);
				j--;
				if(j <0 || flag) {
					flag2 = true;
					flag = true;
					condition[0] = false;
					condition[1] = false;
					condition[2] = false;
					condition[3] = false;
					choicePanel.setVisible(false);
					select.setVisible(false);
					scheduler.shutdown();
					}
				}
			};
			scheduler.scheduleAtFixedRate(runnable, 0, 1, TimeUnit.SECONDS);
	}
	protected void selectServerCheck() {
		SwingWorker<Void,Void> worker = new SwingWorker<Void, Void>(){

			@Override
			protected Void doInBackground() throws Exception {
				while(!flag || !flag2) {
					System.out.println("선택 신호대기중");
					Thread.sleep(500);
				}
				System.out.println("플레이어 행동 선택 확인");
				flag=false;
				flag2=false;
				return null;
			}
			protected void done() {
				fResultCheck();
				if(fResult) {
					dealerScoreBoard.setText(""+dealerScore);
					dealerScoreBoard.setVisible(true);
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					dealerHiddenOpen();
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					dealerBlackJackCheck(3);
					dealerBlackJackServerCheck();
				}else {
					signal = false;
					timer.setVisible(false);
					choicePanel.setVisible(true);
					signalOff.setVisible(true);
//					conclutionFlagCheck();
					conclusionCheck(10);
					conclusionServerCheck();
				}
			}
		};worker.execute();
	}


	protected void dealerBlackJackCheck(int i) {
		ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(2);
		Runnable runnable = new Runnable() {
		int j=i;
			@Override
			public void run() {
				if(dealerScore==21 && dealerHand.size()==2) {
					if(blackJack && result[0]) {
						System.out.println("딜러와 플레이어 모두 블랙잭입니다!! Push(무승부)로 배팅금액이 반환됩니다.");
						budget += bet;
						blackJack = false;
						result[0] = false;
						monitorUpdate();
						signal=false;
					}else{
						if(insuranceBet>0) {
							System.out.println("딜러 블랙잭으로 인슈어런스 배팅금액의 2배가 지급됩니다.");
							budget += insuranceBet*2;
							monitorUpdate();
						}
						for(int i=0;i<playerHand.length;i++) {
							if(result[i]) {
								System.out.println("딜러 블랙잭! 플레이어 패배로 배팅금액을 잃습니다.");
								if(sixCard[i]) {
									sixCardCnt --;
									sixCard[i] = false;
								}
								if(doubleDown[i]) {
									doubleDown[i] = false;
									doubleDownCnt--;
								}
								result[i]=false;
								monitorUpdate();
							}
						}
						flag = true;
						flag2 = true;
						scheduler.shutdown();
					}
				}else if(dealerScore!=21 && dealerHand.size()==2) {
					if(blackJack && result[0]) {
							System.out.println("플레이어 블랙잭!! 배팅금액의 1.5배를 지급합니다.");
							budget += (int)(bet*2.5);
							blackJack = false;
							result[0] = false;
							monitorUpdate();
							signal=false;
						}else if(sixCardCnt>0) {
						for(int i=0;i<sixCard.length;i++) {
							if(sixCard[i] && result[i]) {
								System.out.println("플레이어의 "+(i+1)+"번 핸드 식스카드찰리로 승리!! 배팅금액만큼의 수당을 지급합니다.");
								if(doubleDown[i]) {
									budget += bet*4;
									doubleDown[i] = false;
									doubleDownCnt--;
									System.out.println("최종 획득 금액 : "+(bet*2));
								}else {
								budget += bet*2;
								System.out.println("최종 획득 금액 : "+bet);
								}
								sixCardCnt --;
								sixCard[i] = false;
								result[i] = false;
								monitorUpdate();
							}
						}
					}
					flag = true;
					flag2 = true;
					scheduler.shutdown();
				}
			}
		};
		scheduler.scheduleAtFixedRate(runnable, 0, 1, TimeUnit.SECONDS);
	}
	protected void dealerBlackJackServerCheck() {
		SwingWorker<Void,Void> worker = new SwingWorker<Void, Void>(){

			@Override
			protected Void doInBackground() throws Exception {
				while(!flag || !flag2) {
					System.out.println("딜러 블랙잭체크 신호대기중");
					Thread.sleep(500);
				}
				System.out.println("딜러 블랙잭 확인 완료");
				flag=false;
				flag2=false;
				return null;
			}
			protected void done() {
				fResultCheck();
				if(fResult) {
					dealerCardCheck(5);
					dealerCardServerCheck();
				}else {
					signal = false;
					timer.setVisible(false);
					choicePanel.setVisible(true);
					signalOff.setVisible(true);
//					conclutionFlagCheck();
					conclusionCheck(10);
					conclusionServerCheck();
				}
				
			}
		};worker.execute();
	}

	
	protected void dealerCardCheck(int i) {
		ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(2);
		Runnable runnable = new Runnable() {
			int j=i;
				@Override
				public void run() {
					if(j<5) {
					if(dealerScore<=16 && dealerHand.size()<6 && fResult){
						dealerHand = tc.cardDrawD(dealerHand);
						DCardLabel();
						dealClip.setFramePosition(480000);
						dealClip.loop(1);
						dealerScore = tc.scoreCnt(dealerHand); 
						dealerScoreBoard.setText(""+dealerScore);
						dealerScoreBoard.revalidate();
						dealerScoreBoard.repaint();
					}else if(dealerScore>21 || dealerHand.size()>5){
						dealerBurst = true;
						flag2=true;
						scheduler.shutdown();
					}else {
						flag=true;
						flag2 = true;
						scheduler.shutdown();
						}
					}
					j--;
					}
				};
			scheduler.scheduleAtFixedRate(runnable, 0, 1, TimeUnit.SECONDS);
	}
	protected void dealerCardServerCheck() {
		SwingWorker<Void,Void> worker = new SwingWorker<Void, Void>(){

			@Override
			protected Void doInBackground() throws Exception {
				while(!flag2) {
					System.out.println("딜러카드체크 신호대기중");
					Thread.sleep(500);
				}
				System.out.println("딜러카드체크 확인 완료");
				flag=false;
				flag2=false;
				return null;
			}
			protected void done() {
				dealerCardCnt(10);
				dealerCardServerCnt();
			}
		};worker.execute();
	}
	protected void dealerCardCnt(int i) {
		ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(2);
		Runnable runnable = new Runnable() {
			@Override
			public void run() {
				if(dealerBurst){
					System.out.println("딜러 버스트로 버스트된 핸드를 제외한 나머지 핸드의 배팅금액만큼의 수당을 지급합니다.");
					for(int i=0; i<result.length;i++) {
						if(result[i]) {
							if(doubleDown[i]) {
								budget += bet*2;
								doubleDown[i] = false;
								doubleDownCnt--;
							}
							budget += bet*2;
						}
						result[i] = false;
						monitorUpdate();
					}
				}else{
					for(int i=0 ; i<result.length;i++) {
						if(result[i]) {
							if(dealerScore>playerScore[i]) {
								System.out.println("플레이어 패배, 배팅금액을 잃습니다.");
							}else if(dealerScore==playerScore[i]){
								System.out.println("무승부, 배팅금액이 반환됩니다.");
								if(doubleDown[i]) {
									budget += bet;
									doubleDown[i] = false;
									doubleDownCnt--;
								}
								budget += bet;
							}else if(dealerScore<playerScore[i]) {
								System.out.println("플레이어 승리. 배팅금액만큼 수당이 지급됩니다.");
								if(doubleDown[i]) {
									budget += bet*2;
									doubleDown[i] = false;
									doubleDownCnt--;
								}
								budget += bet*2;
							}
						}
						result[i] = false;
						monitorUpdate();
					}
				}
				fResultCheck();
				if(!fResult) {
					flag = true;
					flag2 = true;
					choicePanel.setVisible(false);
					timer.setVisible(true);
					signalOff.setVisible(false);
					signalOn.setVisible(false);
					scheduler.shutdown();
				}
				}
			};
			scheduler.scheduleAtFixedRate(runnable, 0, 1, TimeUnit.SECONDS);
		}
	protected void dealerCardServerCnt() {
		SwingWorker<Void,Void> worker = new SwingWorker<Void, Void>(){
			
			@Override
			protected Void doInBackground() throws Exception {
				while(!flag2) {
					System.out.println("딜러체크 신호대기중");
					Thread.sleep(500);
				}
				System.out.println("딜러카드 확인 완료");
				flag=false;
				flag2=false;
				return null;
			}
			protected void done() {
				conclusionCheck(10);
				if(budget - startBudget>=0) {
					winClip.setFramePosition(2221473);
			        winClip.loop(1);
				}else if(budget - startBudget==0) {
					drawClip.setFramePosition(1071020);
					drawClip.loop(1);
				}else {
					loseClip.setFramePosition(3888004);
					loseClip.loop(1);
				}
				conclusionServerCheck();
				
			}
		};worker.execute();
	}
	
	
	protected void conclusionCheck(int i) {
		ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
		Runnable runnable = new Runnable() {
		int j=i;
			@Override
			public void run() {
				conclusionMessege.setText("최종 획득 금액 : "+(budget - startBudget));
				choicePanel.setVisible(true);
				conclusion.setVisible(true);
				timer.setVisible(true);
				signalOff.setVisible(false);
				timer.setText(""+j);
				j--;
				if(j <0) {
					flag2 = true;
					choicePanel.setVisible(false);
					conclusion.setVisible(false);
					scheduler.shutdown();
					}
				}
			};
			scheduler.scheduleAtFixedRate(runnable, 0, 1, TimeUnit.SECONDS);
	}
	protected void conclusionServerCheck() {
		SwingWorker<Void,Void> worker = new SwingWorker<Void, Void>(){

			@Override
			protected Void doInBackground() throws Exception {
				while(!flag2) {
					System.out.println("결과 신호대기중");
					Thread.sleep(500);
				}
				System.out.println("결과확인 완료");
				flag=false;
				flag2=false;
				return null;
			}
			protected void done() {
				dealerScoreBoard.setVisible(false);
				readyMessege.setVisible(true);
				resetIndex();
				monitorUpdate();
				try {
					readyCheck(5);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				readyServerCheck();
				
			}
		};worker.execute();
	}

	
	protected void cardOpenFlagCheck() {
		SwingWorker<Void,Void> worker = new SwingWorker<Void, Void>(){
			@Override
			protected Void doInBackground() throws Exception {
				while(!flag3) {
					System.out.println("카드 오픈까지 대기중");
					Thread.sleep(500);
				}
				flag3=false;
				return null;
			}
			
			protected void done() {
				
			}
		};worker.execute();
	}
	
	
	protected void conclutionFlagCheck() {
		SwingWorker<Void,Void> worker = new SwingWorker<Void, Void>(){
			@Override
			protected Void doInBackground() throws Exception {
				while(!flag4) {
					System.out.println("결과확인까지 대기중");
					Thread.sleep(500);
				}
				flag4=false;
				return null;
			}
			
			protected void done() {
				
			}
		};worker.execute();
	}


	
//======================= 위는 게임 진행 메소드, 아래는 논리 메소드 ======================================	
	
	
	protected void playableCheck() {
		playable = false;
		for(boolean i : condition) 
			playable = playable || i;
		System.out.print("playable : ");
		System.out.println(playable==true);
	}
	
	
	protected void fResultCheck() {
		fResult = false;
		for(boolean i : result) 
			fResult = fResult || i;
		System.out.print("fResult : ");
		System.out.println(fResult==true);
	}
	
	
	protected void splitableCheck() {
		splitCheck = false;
		for(boolean i : canSplit)
			splitCheck = splitCheck || i;
	}
	

	protected void blackJackCheck() {
		if(splitCnt==0 && playerScore[0]==21) { // 플레이어 블랙잭 체크, 이후 단계에 필요
			blackJack = true; // 블랙잭상태 활성화
			scoreUpdate();
			condition[0] = false; //블랙잭인경우 이후 행동선택 불필요. 행동 종료처리
		}
		if(blackJack && dealerHand.get(1).getCardNum()<10 && dealerHand.get(1).getCardNum() != 1) {
			// 플레이어가 블랙잭인데 딜러 오픈카드가 10 미만이면서 ace가 아닌경우 
			// 	딜러는 블랙잭일 확률이 없으므로 플레이어 즉시 승리처리 
			System.out.println("플레이어 블랙잭!! 배팅금액의 1.5배를 지급합니다.");
			budget += (int)(bet*2.5);
			condition[0] = false;
			result[0] = false;
			monitorUpdate();
			
		}
	}


	protected void burstCheck() {
		int a=0, b=0;
		for(int i=0 ; i<playerHand.length ;i++) {
			if(playerHand[i]!=null)
				a++;
			if(burst[i]==true) 
				b++;
			if(a==b) {
				signal = false;
				timer.setVisible(false);
				choicePanel.setVisible(true);
				signalOff.setVisible(true);
			}
		}
		
	}


	private void monitorUpdate() {
		for(int i=0 ; i<playMonitor.length;i++) {
			if(playMonitor[i]!=null) {
				if(!result[i]) {
					playMonitor[i].setText("<html>"+
							"현재예산 : " + budget + "만원<br>"+
							"배팅금액 : " + 0 + "만원<br>" +
							"</html>");
				}else {
					playMonitor[i].setText("<html>"+
					"현재예산 : " + budget + "만원<br>"+
					"배팅금액 : " + (bet+(doubleDown[i] ? bet/2 : 0)+insuranceBet) + "만원<br>" +
					"</html>");
				}
				playMonitor[i].revalidate();
				playMonitor[i].repaint();
			}
		}
	}
	
	
	private void scoreUpdate() {
		for(int i=0;i<scoreMonitorA.length;i++) {
			if(scoreMonitorA[i]!=null) {
				if(blackJack) {
					scoreMonitorA[i].setForeground(Color.decode("#FFD700"));
					scoreMonitorA[i].setBackground(Color.BLACK);
					scoreMonitorB[i].setForeground(Color.decode("#FFD700"));
					scoreMonitorB[i].setBackground(Color.BLACK);
					sort="Black Jack";
				}else if(burst[i]) {
					scoreMonitorA[i].setForeground(Color.decode("#FF0000"));
					scoreMonitorA[i].setBackground(Color.GRAY);
					scoreMonitorB[i].setForeground(Color.decode("#FF0000"));
					scoreMonitorB[i].setBackground(Color.GRAY);
					sort="Burst";
				}else if(sixCard[i]) {
					scoreMonitorA[i].setForeground(Color.decode("#9400D3"));
					scoreMonitorA[i].setBackground(Color.BLACK);
					scoreMonitorB[i].setForeground(Color.decode("#9400D3"));
					scoreMonitorB[i].setBackground(Color.BLACK);
					sort="Six Card Charlie";
				}else {
					sort="점수";
				}
				scoreMonitorA[i].setText(sort);
				scoreMonitorA[i].revalidate();
				scoreMonitorA[i].repaint();
				scoreMonitorB[i].setText(""+playerScore[i]);
				scoreMonitorB[i].revalidate();
				scoreMonitorB[i].repaint();
			}
		}
	}

	
	private void splitCheck1(int k) {
				if(playerHand[k].get(0).getCardNum()==1) {
					aceSplit=true;
				}
				splitCnt++;
				budget-= bet;
				playerHand[splitCnt] = new ArrayList<House>(); 
				setPlayerPanels(splitCnt);
				condition[splitCnt] = true;
				result[splitCnt] = true;
				playerHand[splitCnt].add(playerHand[k].get(1));
				PCardLabel(splitCnt);
				playerCard[k][1].setIcon(null);
				playerCard[k][1].setBorder(null);
				playerHand[k].remove(1);
	}
	
	protected void splitCardDeal(int k) {
		ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(2);
		Runnable runnable = new Runnable() {
			int j=1;
				@Override
				public void run() {
						if(playerHand[k].size()==1 && j==0){
							tc.cardDrawP(playerHand[k]);
							PCardLabel(k);
							dealClip.setFramePosition(480000);
							dealClip.loop(1);
						}else if(playerHand[splitCnt].size()==1 && j==0){
							tc.cardDrawP(playerHand[splitCnt]);	
							PCardLabel(splitCnt);
							dealClip.setFramePosition(480000);
							dealClip.loop(1);
						}else if(playerHand[k].size()==2 && playerHand[splitCnt].size()==2){
							splitCheck2(k);
							scheduler.shutdown();
						}else {
						j--;
						}
					}
				};
			scheduler.scheduleAtFixedRate(runnable, 0, 1, TimeUnit.SECONDS);
	}
	
	private void splitCheck2(int k) {
		if(playerHand[k].get(0).getCardNum()==1) {
			aceSplit=true;
		}
		canSplit[k] = tc.splitLogic(playerHand[k]); 
		playerScore[k] = tc.scoreCnt(playerHand[k]); 
		canSplit[splitCnt] = tc.splitLogic(playerHand[splitCnt]); 
		playerScore[splitCnt] = tc.scoreCnt(playerHand[splitCnt]); 
		splitCheck=false;
		for(boolean i : canSplit)
			splitCheck = splitCheck || i;
		if(splitCheck && budget>bet && splitCnt<3 && !aceSplit) {
			splitButtonA.setEnabled(true);
			splitButtonB.setEnabled(true);
			split.revalidate();
			split.repaint();
		}else {
			split.setVisible(false);
			split2.setVisible(true);
			flag = true;
		}
	}
	


	
	private void PCardLabel(int i) {
		System.out.println("카드라벨링 진행");
		int idx1 = playerHand[i].size()-1;
		int idx2 = playerHand[i].get(idx1).getCardIdx();
		
		playerCard[i][idx1] = new JLabel();
		playerCard[i][idx1].setBounds(idx1*32,0,140,200);
		playerCard[i][idx1].setLayout(null);
		if(playerHand[i].get(idx1)!=null) {
		playerCard[i][idx1].setIcon(new ImageIcon(Hall.class.getResource("/image/"+idx2+".png")));
		playerCard[i][idx1].setVisible(true);
		playerCard[i][idx1].setBorder(border);
		playerCard[i][idx1].revalidate();
		playerCard[i][idx1].repaint();
		}
		System.out.println(idx2);
		playerCardPanel[i].add(playerCard[i][idx1]);
		playerCardPanel[i].revalidate();
		playerCardPanel[i].repaint();
	}
	
	private void DCardLabel() {
		int idx1 = dealerHand.size()-1;
		int idx2 = dealerHand.get(idx1).getCardIdx();
		
		dealerCard[idx1] = new JLabel();
		dealerCard[idx1].setPreferredSize(new Dimension(140,200));
		dealerCard[idx1].setLayout(null);
		dealerCard[idx1].setBorder(border);
		if(idx1==1) {
			dealerCard[idx1].setIcon(new ImageIcon(Hall.class.getResource("/image/red.png")));
		}else {
		dealerCard[idx1].setIcon(new ImageIcon(Hall.class.getResource("/image/"+idx2+".png")));
		}
		dealerField.add(dealerCard[idx1]);
		dealerField.revalidate();
		dealerField.repaint();
	}
	
	private void dealerHiddenOpen() {
		dealerCard[1].setIcon(new ImageIcon(
				Hall.class.getResource("/image/"+dealerHand.get(1).getCardIdx()+".png")));
		
		dealerCard[1].revalidate();
		dealerCard[1].repaint();
		dealerField.revalidate();
		flipClip.setFramePosition(480000);
        flipClip.loop(1);
		dealerField.repaint();
	}

	
// =================== 위는 논리 메소드, 아래는 게임 화면 구현 및 초기화 메소드 =============================================
	
	
	private void resetIndex() {
		dealerField.removeAll();
		dealerField.revalidate();
		dealerField.repaint();
		playerField.removeAll();
		playerField.revalidate();
		playerField.repaint();
		playerHand = new ArrayList [4]; // 플레이어핸드 배열 리셋
		for(int i=0;i<playerHand.length;i++) { // 플레이어 핸드 리셋
			if(playerHand[i]!=null) {
				playerHand[i].clear();
			}
		}
		if(dealerHand!=null) {
		dealerHand.clear(); // 딜러 핸드 리셋
		}
		playerFieldPanel = new JPanel [4]; 	// 플레이어 필드 패널 리셋
		for(int i=0;i<playerFieldPanel.length;i++)  {
			if(playerFieldPanel[i]!=null) {
				playerFieldPanel[i].removeAll();
				playerFieldPanel[i].revalidate();
				playerFieldPanel[i].repaint();
			}
		}
		playerCardPanel = new JPanel [4];	// 플레이어 카드 패널 리셋
		for(int i=0;i<playerCardPanel.length;i++)  {
			if(playerCardPanel[i]!=null) {
				playerCardPanel[i]=null;
				playerCardPanel[i].revalidate();
				playerCardPanel[i].repaint();
			}
		}
		playerCard = new JLabel [4][6];
		for(int i=0;i<playerCard.length;i++)  {
			if(playerCard[i]!=null) {
				for(int j=0;j<playerCard[i].length;j++) {
					if(playerCard[i][j]!=null) {
						playerCard[i][j] = null;
						playerCard[i][j].revalidate();
						playerCard[i][j].repaint();
					}
				}
			}	
		}
		dealerCard = new JLabel [6];	// 플레이어 점수 패널 리셋
		for(int i=0;i<dealerCard.length;i++) {  
			if(dealerCard[i]!=null) {
				dealerCard[i]=null;
				dealerCard[i].revalidate();
				dealerCard[i].repaint();
			}
		}
		playMonitor = new JLabel [4];
		for(int i=0;i<playMonitor.length;i++)  {
			if(playMonitor[i]!=null) {
				playMonitor[i]=null;
				playMonitor[i].revalidate();
				playMonitor[i].repaint();
			}
		}
		scoreMonitorA = new JLabel [4];
		for(int i=0;i<scoreMonitorA.length;i++)  {
			if(scoreMonitorA[i]!=null) {
				scoreMonitorA[i]=null;
				scoreMonitorA[i].revalidate();
				scoreMonitorA[i].repaint();
			}
		}
		scoreMonitorB = new JLabel [4];
		for(int i=0;i<scoreMonitorB.length;i++)  {
			if(scoreMonitorB[i]!=null) {
				scoreMonitorB[i]=null;
				scoreMonitorB[i].revalidate();
				scoreMonitorB[i].repaint();
			}
		}
		playerbuttonPanel = new JPanel [4];	// 플레이어 버튼 패널 리셋
		hit = new JButton [4]; 	// 플레이어 버튼 리셋
		stay = new JButton [4]; // 플레이어 버튼 리셋
		DD = new JButton [4]; 	// 플레이어 버튼 리셋
		result = new boolean [4]; // 결과확인 리셋
		canSplit = new boolean [4]; // 핸드별 스플릿 가능여부 체크 리셋
		playerScore = new int [4]; // 플레이어 핸드별 점수 리셋
		burst = new boolean [4]; // 핸드별 버스트 체크 리셋
		sixCard = new boolean [4]; // 식스카드찰리 체크 리셋
		doubleDown = new boolean [4]; // 더블다운 체크 리셋
		condition = new boolean [4]; // 행동가능여부 체크 리셋
		aceSplit = false; // 에이스 스플릿 체크 리셋
		blackJack = false; // 블랙잭 체크 리셋
		doubleDownCnt = 0; // 총 더블다운 카운트 리셋
		sixCardCnt = 0; // 총 식스카드찰리 카운트 리셋
		splitCnt = 0; // 총 스플릿핸드 카운트 리셋
		burstCnt = 0; // 총 버스트핸드 카운트 리셋
		bet=0; // 게임시작 시 배팅금액 리셋
		insuranceBet=0; // 총 보험금 배팅금액 리셋
		startBudget = budget; // 게임시작 전 예산 금액 리셋
		playerHand[splitCnt] = new ArrayList<House>(); // 새 플레이어 핸드 생성
		condition[splitCnt] = true; // 새 플레이어핸드의 행동가능여부 활성화
		result[splitCnt] = true; // 새 플레이어핸드의 결과확인여부 활성화
		dealerHand = new ArrayList<House>(); // 새 딜러 핸드 생성
		setPlayerPanels(splitCnt);
	}

	private void setPlayerPanels(int splitCnt) {
		playerFieldPanel[splitCnt] = new JPanel();
		playerFieldPanel[splitCnt].setPreferredSize(new Dimension(300,300));
		playerFieldPanel[splitCnt].setLayout(null);
		playerFieldPanel[splitCnt].setOpaque(false);
		playerFieldPanel[splitCnt].setVisible(true);
		playerField.add(playerFieldPanel[splitCnt]);
		
		playerCardPanel[splitCnt] = new JPanel();
		playerCardPanel[splitCnt].setBounds(0,0,300,200);
		playerCardPanel[splitCnt].setLayout(null);
		playerCardPanel[splitCnt].setOpaque(false);
		playerFieldPanel[splitCnt].add(playerCardPanel[splitCnt]);
		
		playerbuttonPanel[splitCnt] = new JPanel();
		playerbuttonPanel[splitCnt].setBounds(0,200,300,100);
		playerbuttonPanel[splitCnt].setBackground(Color.decode("#2e8b57"));
		playerbuttonPanel[splitCnt].setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		playerbuttonPanel[splitCnt].setLayout(null);
		playerbuttonPanel[splitCnt].setOpaque(true);
		playerFieldPanel[splitCnt].add(playerbuttonPanel[splitCnt]);
		
		hit[splitCnt] = new JButton("Hit");
		hit[splitCnt].setFont(new Font("굴림", Font.PLAIN, 15));
		hit[splitCnt].setForeground(Color.decode("#FFD700"));
		hit[splitCnt].setBackground(Color.BLACK);
		hit[splitCnt].setHorizontalAlignment(SwingConstants.CENTER);
		hit[splitCnt].setBounds(5,5,90,25);
		hit[splitCnt].setEnabled(false);
		playerbuttonPanel[splitCnt].add(hit[splitCnt]);
		hit[splitCnt].addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				playerHand[splitCnt] = tc.cardDrawP(playerHand[splitCnt]);
				PCardLabel(splitCnt);
				dealClip.setFramePosition(480000);
				dealClip.loop(1);
				playerScore[splitCnt] = tc.scoreCnt(playerHand[splitCnt]);
				scoreUpdate();
				if(playerScore[splitCnt]>21) {
					System.out.println("플레이어 버스트로 패배, 배팅금액을 잃습니다.");
					burst[splitCnt] = true;
					scoreUpdate();
					result[splitCnt] = false;
				}else if(playerHand[splitCnt].size()==6 && !burst[splitCnt]) {
					System.out.println("식스카드 찰리!");
					sixCard[splitCnt] = true;
					scoreUpdate();
					sixCardCnt++;
				}
				if(burst[splitCnt] || sixCard[splitCnt] || playerScore[splitCnt]==21 || doubleDown[splitCnt]) {
					if(sixCard[splitCnt] && dealerHand.get(0).getCardNum()<10 && dealerHand.get(0).getCardNum()!=1) {
						System.out.println("식스카드 찰리로 "+(splitCnt+1)+"번 핸드 승리, 배팅금액만큼의 수당이 지급됩니다.");
						budget += bet*2;
						sixCardCnt--;
						result[splitCnt]=false;
						monitorUpdate();
					}
				System.out.println("더이상 할 수 있는 행동이 없어 결과를 기다립니다.");
				condition[splitCnt] = false;
				hit[splitCnt].setEnabled(false);
				stay[splitCnt].setEnabled(false);
				DD[splitCnt].setEnabled(false);
					playableCheck();
					if(!playable) {
						flag=true;
					}
				}
				playerFieldPanel[splitCnt].revalidate();
				playerFieldPanel[splitCnt].repaint();
			}
		});
		stay[splitCnt] = new JButton("Stay");
		stay[splitCnt].setFont(new Font("굴림", Font.PLAIN, 15));
		stay[splitCnt].setForeground(Color.decode("#FFD700"));
		stay[splitCnt].setBackground(Color.BLACK);
		stay[splitCnt].setHorizontalAlignment(SwingConstants.CENTER);
		stay[splitCnt].setBounds(5,35,90,25);
		stay[splitCnt].setEnabled(false);
		playerbuttonPanel[splitCnt].add(stay[splitCnt]);
		stay[splitCnt].addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				condition[splitCnt] = false;
				hit[splitCnt].setEnabled(false);
				stay[splitCnt].setEnabled(false);
				DD[splitCnt].setEnabled(false);
				playerFieldPanel[splitCnt].revalidate();
				playerFieldPanel[splitCnt].repaint();
				playableCheck();
				if(!playable) {
					flag=true;
				}
			}
		});
		// double down button
		DD[splitCnt] = new JButton("Double Down");
		DD[splitCnt].setFont(new Font("굴림", Font.PLAIN, 15));
		DD[splitCnt].setForeground(Color.decode("#FFD700"));
		DD[splitCnt].setBackground(Color.BLACK);
		DD[splitCnt].setHorizontalAlignment(SwingConstants.CENTER);
		DD[splitCnt].setBounds(5,65,90,25);
		DD[splitCnt].setEnabled(false);
		playerbuttonPanel[splitCnt].add(DD[splitCnt]);
		DD[splitCnt].addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				doubleDown[splitCnt] = true;
				doubleDownCnt++;
				budget -= bet;
				playerHand[splitCnt] = tc.cardDrawP(playerHand[splitCnt]);
				PCardLabel(splitCnt);
				dealClip.setFramePosition(480000);
				dealClip.loop(1);
				playerScore[splitCnt] = tc.scoreCnt(playerHand[splitCnt]);
				scoreUpdate();
				if(playerScore[splitCnt]>21) {
					System.out.println("플레이어 버스트로 패배, 배팅금액을 잃습니다.");
					burst[splitCnt] = true;
					scoreUpdate();
					result[splitCnt] = false;
				}else if(playerHand[splitCnt].size()==6 && !burst[splitCnt]) {
					System.out.println("식스카드 찰리!");
					sixCard[splitCnt] = true;
					scoreUpdate();
					sixCardCnt++;
				}
				if(burst[splitCnt] || sixCard[splitCnt] || playerScore[splitCnt]==21 || doubleDown[splitCnt]) {
					if(sixCard[splitCnt] && dealerHand.get(0).getCardNum()<10 && dealerHand.get(0).getCardNum()!=1) {
						System.out.println("식스카드 찰리로 "+(splitCnt+1)+"번 핸드 승리, 배팅금액만큼의 수당이 지급됩니다.");
						budget += bet*4;
						doubleDownCnt--;
						sixCardCnt--;
						result[splitCnt]=false;
						monitorUpdate();
					}
				System.out.println("더이상 할 수 있는 행동이 없어 결과를 기다립니다.");
				condition[splitCnt] = false;
				hit[splitCnt].setEnabled(false);
				stay[splitCnt].setEnabled(false);
				DD[splitCnt].setEnabled(false);
					playableCheck();
					if(!playable) {
						flag=true;
					}
				}
				playerFieldPanel[splitCnt].revalidate();
				playerFieldPanel[splitCnt].repaint();
			}
		});
		scoreMonitorA[splitCnt] = new JLabel();
		scoreMonitorA[splitCnt].setFont(new Font("굴림", Font.BOLD, 20));
		scoreMonitorA[splitCnt].setForeground(Color.decode("#0000FF"));
		scoreMonitorA[splitCnt].setHorizontalAlignment(SwingConstants.LEFT);
		scoreMonitorA[splitCnt].setBounds(100, 5, 170, 30);
		playerbuttonPanel[splitCnt].add(scoreMonitorA[splitCnt]);
		
		scoreMonitorB[splitCnt] = new JLabel();
		scoreMonitorB[splitCnt].setFont(new Font("굴림", Font.BOLD, 20));
		scoreMonitorB[splitCnt].setForeground(Color.decode("#0000FF"));
		scoreMonitorB[splitCnt].setHorizontalAlignment(SwingConstants.CENTER);
		scoreMonitorB[splitCnt].setBounds(270, 5, 30, 30);
		playerbuttonPanel[splitCnt].add(scoreMonitorB[splitCnt]);
		
		playMonitor[splitCnt] = new JLabel();
		playMonitor[splitCnt].setFont(new Font("굴림", Font.BOLD, 20));
		playMonitor[splitCnt].setForeground(Color.decode("#CD1039"));
		playMonitor[splitCnt].setHorizontalAlignment(SwingConstants.LEFT);
		playMonitor[splitCnt].setBounds(100, 40, 200, 55);
		playerbuttonPanel[splitCnt].add(playMonitor[splitCnt]);

	}
	
	private void settingTable() {
		setBounds(100, 100, 1300, 730); // 1284 686 // 642 343
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		
		
		contentPane = new JLabel();
		contentPane.setBackground(new Color(0, 150, 0));
		contentPane.setIcon(new ImageIcon(Hall.class.getResource("/image/backgroundred.png")));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setVisible(true);
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		
		dealerScoreBoard = new JLabel();
		dealerScoreBoard.setForeground(Color.WHITE);
		dealerScoreBoard.setFont(new Font("굴림", Font.PLAIN, 99));
		dealerScoreBoard.setHorizontalAlignment(SwingConstants.CENTER);
		dealerScoreBoard.setBackground(new Color(0,50,50));
		dealerScoreBoard.setBounds(0, 0, 200 , 200);
		dealerScoreBoard.setVisible(false);
		dealerScoreBoard.setAlignmentX(CENTER_ALIGNMENT);
		contentPane.add(dealerScoreBoard);
		
		dealerField = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) dealerField.getLayout();
		flowLayout_1.setVgap(0);
		flowLayout_1.setHgap(0);
		flowLayout_1.setAlignOnBaseline(true);
		dealerField.setBounds(200, 0, 840, 200);
		dealerField.setBackground(new Color(0,100,0));
		dealerField.setAlignmentX(CENTER_ALIGNMENT);
		dealerField.setVisible(true);
		dealerField.setOpaque(false);
		contentPane.add(dealerField);
		
		playerField = new JPanel();
		FlowLayout flowLayout = (FlowLayout) playerField.getLayout();
		playerField.setBounds(0, 386, 1284, 300);
		playerField.setOpaque(false);
		playerField.setAlignmentX(CENTER_ALIGNMENT);
		contentPane.add(playerField);
		
		leftSide = new JPanel();
		leftSide.setBackground(new Color(0,50,50));
		leftSide.setBounds(0, 200, 300, 186);
		leftSide.setAlignmentX(CENTER_ALIGNMENT);
		leftSide.setOpaque(false);
		contentPane.add(leftSide);
		leftSide.setLayout(null);
		
		rightSide = new JPanel();
		rightSide.setBackground(new Color(0,50,50));
		rightSide.setBounds(984, 200, 300, 186);
		rightSide.setAlignmentX(CENTER_ALIGNMENT);
		rightSide.setOpaque(false);
		contentPane.add(rightSide);
		rightSide.setLayout(null);
		
		information = new JLabel();
		information.setForeground(Color.WHITE);
		information.setHorizontalAlignment(SwingConstants.CENTER);
		information.setBackground(new Color(0,50,50,122));
		information.setBounds(300, 200, 684 , 186);
		information.setAlignmentX(CENTER_ALIGNMENT);
		contentPane.add(information);
		
		choicePanel = new JPanel();
		choicePanel.setBackground(new Color(10,10,10));
		choicePanel.setBounds(340, 200, 604 , 186);
		choicePanel.setAlignmentX(CENTER_ALIGNMENT);
		contentPane.add(choicePanel);
		choicePanel.setOpaque(true);
		choicePanel.setLayout(null);
		choicePanel.setVisible(false);
		
		signalOff = new JLabel("플레이가 종료되어 다른 플레이어를 기다리고있습니다.");
		signalOff.setBackground(new Color(150,150,150));
		signalOff.setForeground(Color.BLACK);
		signalOff.setAlignmentX(CENTER_ALIGNMENT);
		signalOff.setFont(new Font("굴림", Font.PLAIN, 15));
		signalOff.setBounds(0, 0, 604 , 186);
		signalOff.setVisible(false);
		choicePanel.add(signalOff);
		
		signalOn = new JLabel("더이상 선택사항이 없어 결과를 기다립니다.");
		signalOn.setBackground(new Color(150,150,150));
		signalOn.setForeground(Color.BLACK);
		signalOn.setAlignmentX(CENTER_ALIGNMENT);
		signalOn.setFont(new Font("굴림", Font.PLAIN, 15));
		signalOn.setBounds(0, 0, 604 , 186);
		signalOn.setVisible(false);
		choicePanel.add(signalOn);
		
		timer = new JLabel();
		timer.setHorizontalAlignment(SwingConstants.CENTER);
		timer.setBackground(new Color(0,50,50));
		timer.setForeground(Color.WHITE);
		timer.setFont(new Font("굴림", Font.PLAIN, 99));
		timer.setBounds(0, 0, 186 , 186);
		timer.setAlignmentX(CENTER_ALIGNMENT);
		timer.setVisible(true);
		choicePanel.add(timer);
		timer.setOpaque(true);
		
		
		ready = new JPanel();
		ready.setBackground(new Color(150,150,150));
		ready.setForeground(Color.BLACK);
		ready.setLayout(null);
		ready.setFont(new Font("굴림", Font.PLAIN, 15));
		ready.setBounds(186, 0, 418 , 186);
		ready.setVisible(false);
		choicePanel.add(ready);
		
		readyConfirm = new JLabel();
		readyConfirm.setText("레디완료!!");
		readyConfirm.setHorizontalAlignment(SwingConstants.CENTER);
		readyConfirm.setBackground(new Color(0,50,50));
		readyConfirm.setForeground(Color.WHITE);
		readyConfirm.setFont(new Font("굴림", Font.PLAIN, 15));
		readyConfirm.setBounds(0, 0, 418 , 156);
		readyConfirm.setAlignmentX(CENTER_ALIGNMENT);
		ready.add(readyConfirm);
		readyConfirm.setVisible(false);
		
		readyMessege = new JLabel();
		readyMessege.setText("레디하시겠습니까?");
		readyMessege.setHorizontalAlignment(SwingConstants.CENTER);
		readyMessege.setBackground(new Color(0,50,50));
		readyMessege.setForeground(Color.WHITE);
		readyMessege.setFont(new Font("굴림", Font.PLAIN, 15));
		readyMessege.setBounds(0, 0, 418 , 156);
		readyMessege.setAlignmentX(CENTER_ALIGNMENT);
		ready.add(readyMessege);
		
		readyButtonA = new JButton("레디");
		readyButtonA.setBounds(0, 156, 209 , 30);
		ready.add(readyButtonA);
		readyButtonA.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				readyMessege.setVisible(false);
				readyConfirm.setVisible(true);
				signal = true;
				flag=true;
			}
		});
		readyButtonB = new JButton("게임종료");
		readyButtonB.setBounds(209, 156, 209 , 30);
		ready.add(readyButtonB);
		readyButtonB.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				flag=true;
				signal = false;
				dispose();
			}
		});
		
		betting = new JPanel();
		betting.setBackground(new Color(150,150,150));
		betting.setForeground(Color.BLACK);
		betting.setLayout(null);
		betting.setFont(new Font("굴림", Font.PLAIN, 15));
		betting.setBounds(186, 0, 418 , 186);
		betting.setVisible(false);
		choicePanel.add(betting);
		
		bettingMessege = new JLabel();
		bettingMessege.setText("배팅금액을 선택해주세요");
		bettingMessege.setHorizontalAlignment(SwingConstants.CENTER);
		bettingMessege.setBackground(new Color(0,50,50));
		bettingMessege.setForeground(Color.WHITE);
		bettingMessege.setFont(new Font("굴림", Font.PLAIN, 15));
		bettingMessege.setBounds(0, 0, 418 , 106);
		bettingMessege.setAlignmentX(CENTER_ALIGNMENT);
		betting.add(bettingMessege);
		
		bettingOptions = new String [] {"10","20","30","40","50","60","70","80","90","100"};
		bettingChoice = new JComboBox(bettingOptions);
		bettingChoice.setSelectedIndex(0);
		bettingChoice.setMaximumRowCount(10);
		bettingChoice.setBounds(0,106,418,50);
		betting.add(bettingChoice);
		
		bettingButtonA = new JButton("선택완료");
		bettingButtonA.setBounds(0, 156, 209 , 30);
		betting.add(bettingButtonA);
		bettingButtonA.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				flag=true;
			}
		});
		bettingButtonB = new JButton("게임종료");
		bettingButtonB.setBounds(209, 156, 209 , 30);
		betting.add(bettingButtonB);
		bettingButtonB.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				flag=false;
				signal=false;
				dispose();
			}
		});
		
		
		evenMoney = new JPanel();
		evenMoney.setBackground(new Color(150,150,150));
		evenMoney.setForeground(Color.BLACK);
		evenMoney.setLayout(null);
		evenMoney.setFont(new Font("굴림", Font.PLAIN, 15));
		evenMoney.setBounds(186, 0, 418 , 186);
		evenMoney.setVisible(false);
		choicePanel.add(evenMoney);
		
		evenMoneyMessege = new JLabel();
		evenMoneyMessege.setText("블랙잭이 나왔지만, 딜러도 블랙잭일 수 있습니다. 이븐머니(배팅금액 1배지급)를 선택하시겠습니까?");
		evenMoneyMessege.setHorizontalAlignment(SwingConstants.CENTER);
		evenMoneyMessege.setBackground(new Color(0,50,50));
		evenMoneyMessege.setForeground(Color.WHITE);
		evenMoneyMessege.setFont(new Font("굴림", Font.PLAIN, 15));
		evenMoneyMessege.setBounds(0, 0, 418 , 156);
		evenMoneyMessege.setAlignmentX(CENTER_ALIGNMENT);
		evenMoney.add(evenMoneyMessege);
		
		evenMoneyButtonA = new JButton("이븐머니 선택");
		evenMoneyButtonA.setBounds(0, 156, 209 , 30);
		evenMoney.add(evenMoneyButtonA);
		evenMoneyButtonA.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println("이븐머니 선택, 배팅금액 1배에 해당하는 승리수당 지급");
				signal = false;
				flag=true;
			}
		});
		evenMoneyButtonB = new JButton("이븐머니 취소");
		evenMoneyButtonB.setBounds(209, 156, 209 , 30);
		evenMoney.add(evenMoneyButtonB);
		evenMoneyButtonB.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				flag=true;
			}
		});
		
		
		insure = new JPanel();
		insure.setBackground(new Color(150,150,150));
		insure.setForeground(Color.BLACK);
		insure.setLayout(null);
		insure.setFont(new Font("굴림", Font.PLAIN, 15));
		insure.setBounds(186, 0, 418 , 186);
		insure.setVisible(false);
		choicePanel.add(insure);
		
		insureMessege = new JLabel();
		insureMessege.setText("딜러의패가 블랙잭인것에 배팅금액의 반을 보험금으로 거시겠습니까?\\n(블랙잭인경우 보험금의 2배 지급)");
		insureMessege.setHorizontalAlignment(SwingConstants.CENTER);
		insureMessege.setBackground(new Color(0,50,50));
		insureMessege.setForeground(Color.WHITE);
		insureMessege.setFont(new Font("굴림", Font.PLAIN, 15));
		insureMessege.setBounds(0, 0, 418 , 156);
		insureMessege.setAlignmentX(CENTER_ALIGNMENT);
		insure.add(insureMessege);
		
		insureButtonA = new JButton("보험금 배팅하기");
		insureButtonA.setBounds(0, 156, 209 , 30);
		insure.add(insureButtonA);
		insureButtonA.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				insuranceBet += bet/2;
				budget -= insuranceBet;
				monitorUpdate();
				flag=true;
			}
		});
		insureButtonB = new JButton("보험금 배팅하지않기");
		insureButtonB.setBounds(209, 156, 209 , 30);
		insure.add(insureButtonB);
		insureButtonB.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				flag=true;
			}
		});
		
		split = new JPanel();
		split.setBackground(new Color(150,150,150));
		split.setForeground(Color.BLACK);
		split.setLayout(null);
		split.setFont(new Font("굴림", Font.PLAIN, 15));
		split.setBounds(186, 0, 418 , 186);
		split.setVisible(false);
		choicePanel.add(split);
		
		splitMessege = new JLabel();
		splitMessege.setText("스플릿을 진행할 핸드를 선택해주세요");
		splitMessege.setHorizontalAlignment(SwingConstants.CENTER);
		splitMessege.setBackground(new Color(0,50,50));
		splitMessege.setForeground(Color.WHITE);
		splitMessege.setFont(new Font("굴림", Font.PLAIN, 15));
		splitMessege.setBounds(0, 0, 418 , 106);
		splitMessege.setAlignmentX(CENTER_ALIGNMENT);
		split.add(splitMessege);
		
		split2 = new JPanel();
		split2.setBackground(new Color(150,150,150));
		split2.setForeground(Color.BLACK);
		split2.setLayout(null);
		split2.setFont(new Font("굴림", Font.PLAIN, 15));
		split2.setBounds(186, 0, 418 , 186);
		split2.setVisible(false);
		choicePanel.add(split2);
		
		splitMessege2 = new JLabel();
		splitMessege2.setText("다른분들의 선택을 기다리고있습니다.");
		splitMessege2.setHorizontalAlignment(SwingConstants.CENTER);
		splitMessege2.setBackground(new Color(0,50,50));
		splitMessege2.setForeground(Color.WHITE);
		splitMessege2.setFont(new Font("굴림", Font.PLAIN, 15));
		splitMessege2.setBounds(0, 0, 418 , 186);
		splitMessege2.setAlignmentX(CENTER_ALIGNMENT);
		split2.add(splitMessege2);
		
		splitChoice = new ButtonGroup();
		
		splitChoice1 = new JRadioButton("1번 핸드");
		splitChoice1.setBounds(39, 116, 80, 30);
		split.add(splitChoice1);
		
		splitChoice2 = new JRadioButton("2번 핸드");
		splitChoice2.setBounds(169, 116, 80, 30);
		split.add(splitChoice2);
		
		splitChoice3 = new JRadioButton("3번 핸드");
		splitChoice3.setBounds(299, 116, 80, 30);
		split.add(splitChoice3);
		
		splitChoice.add(splitChoice1);
		splitChoice.add(splitChoice2);
		splitChoice.add(splitChoice3);
		
		splitButtonA = new JButton("선택완료");
		splitButtonA.setBounds(0, 156, 209 , 30);
		split.add(splitButtonA);
		splitButtonA.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				splitButtonA.setEnabled(false);
				splitButtonB.setEnabled(false);
				int k=-1;
				if(splitChoice1.isSelected())
					k=0;
				if(splitChoice2.isSelected())
					k=1;
				if(splitChoice3.isSelected())
					k=2;
				if(k>=0)				
				splitCheck1(k);
				monitorUpdate();
				splitCardDeal(k);
			}
		});
		splitButtonB = new JButton("스플릿 취소");
		splitButtonB.setBounds(209, 156, 209 , 30);
		split.add(splitButtonB);
		splitButtonB.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				split.setVisible(false);
				split2.setVisible(true);
				flag=true;
			}
		});
		
		
		select = new JPanel();
		select.setBackground(new Color(150,150,150));
		select.setForeground(Color.BLACK);
		select.setLayout(null);
		select.setFont(new Font("굴림", Font.PLAIN, 15));
		select.setBounds(186, 0, 418 , 186);
		select.setVisible(false);
		choicePanel.add(select);
		
		selectMessege = new JLabel();
		selectMessege.setText("각 핸드의 행동을 선택해주세요");
		selectMessege.setHorizontalAlignment(SwingConstants.CENTER);
		selectMessege.setBackground(new Color(0,50,50));
		selectMessege.setForeground(Color.WHITE);
		selectMessege.setFont(new Font("굴림", Font.PLAIN, 15));
		selectMessege.setBounds(0, 0, 418 , 186);
		selectMessege.setAlignmentX(CENTER_ALIGNMENT);
		select.add(selectMessege);
		
		conclusion = new JPanel();
		conclusion.setBackground(new Color(150,150,150));
		conclusion.setForeground(Color.BLACK);
		conclusion.setLayout(null);
		conclusion.setFont(new Font("굴림", Font.PLAIN, 15));
		conclusion.setBounds(186, 0, 418 , 186);
		conclusion.setVisible(false);
		choicePanel.add(conclusion);
		
		conclusionMessege= new JLabel();
		conclusionMessege.setHorizontalAlignment(SwingConstants.CENTER);
		conclusionMessege.setBackground(new Color(0,50,50));
		conclusionMessege.setForeground(Color.WHITE);
		conclusionMessege.setFont(new Font("굴림", Font.PLAIN, 15));
		conclusionMessege.setBounds(0, 0, 418 , 186);
		conclusionMessege.setAlignmentX(CENTER_ALIGNMENT);
		conclusion.add(conclusionMessege);
	}
}
