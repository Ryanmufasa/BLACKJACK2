package data;

import java.awt.HeadlessException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import main.Hall;

public class House extends JFrame{
	private Object[] bettingOptions;
	private int cardIdx;
	private int cardNum;
	private String suits;
	private ArrayList<House> shuffleDeck;
	private int drawCnt;
	
	public House () {
		shuffleDeck = new ArrayList<House>();
	}

	public House(int cardIdx, int cardNum, String suits) throws HeadlessException {
		this.cardIdx = cardIdx;
		this.cardNum = cardNum;
		this.suits = suits;
	}

	public int getCardIdx() {
		return cardIdx;
	}

	public void setCardIdx(int cardIdx) {
		this.cardIdx = cardIdx;
	}

	public int getCardNum() {
		return cardNum;
	}

	public void setCardNum(int cardNum) {
		this.cardNum = cardNum;
	}

	public String getSuits() {
		return suits;
	}

	public void setSuits(String suits) {
		this.suits = suits;
	}
	
	
	public ArrayList<House> cardSetterToTest() {
		ArrayList<House> factory = new ArrayList<House>();
		for(int i=0;i<52;i++) {
					House tc = new House(i+1,2,"spade");
					factory.add(tc);
		}
		return factory;
	}
	
	public ArrayList<House> cardSetter () { // 순서대로 정렬되어있는 덱, 공장초기화상태
		ArrayList<House> factory = new ArrayList<House>();
		String [] suit = {"spade","diamond","heart","clover"};
		int k=0;
		for(int i=0;i<suit.length;i++) {
			for(int j=1;j<=13;j++) {
					k++;
					House tc = new House(k,j,suit[i]);
					factory.add(tc);
			}
		}
		int cnt=0;
		for(House i : factory) {
			System.out.print(" / "+i.getCardIdx()+" "+i.getCardNum()+" "+i.getSuits());
			cnt++;
			if(cnt%5==0)
			System.out.println();
		}
		return factory;
	}

	public void cardSuffle(ArrayList<House> deck) { // Riffle Shuffle 방식.
		ArrayList<House> shuffleHand = deck; 						// 새 덱을 핸드에 옮김
		ArrayList<House> leftHand = new ArrayList<House>();
		ArrayList<House> rightHand = new ArrayList<House>();
		boolean flag=true;
		int lhCnt = 25;
		int rhCnt = 25;
		Scanner sc = new Scanner(System.in);
		
		
		for(int k=1;k<=10;k++) {											/////// 아래 과정을 k+1만큼 반복
			for(int i=0;i<shuffleHand.size();i++) {						// 반으로 갈라 왼손과 오른손에 들고
			if(i<(shuffleHand.size()/2)) {
				leftHand.add(shuffleHand.get(i));
			}else {
				rightHand.add(shuffleHand.get(i));
			}
			}
		
		shuffleHand.clear();
			while(lhCnt>=0 || rhCnt>=0) {
			int rIdx = (int)(Math.ceil(Math.random()*3));				// 양손에 든 카드를 번갈아가며 
																		// 1~3장씩 랜덤하게 가운데에 모음
				for(int i=1;i<=rIdx;i++) {
					if(flag && lhCnt>=0) {
						shuffleHand.add(leftHand.get(lhCnt));
						leftHand.remove(lhCnt);
						lhCnt--;
						if(lhCnt<0) {
							break;
						}
					}else if(!flag && rhCnt>=0) {
						shuffleHand.add(rightHand.get(rhCnt));
						rightHand.remove(rhCnt);
						rhCnt--;
						if(rhCnt<0) {
							break;
						}
					}
				}
				flag = !flag;
			}
		lhCnt = 25;
		rhCnt = 25;
		}
		this.shuffleDeck = shuffleHand;										// 섞기가 완료된 카드 덱을 보관
		int cnt=0;
			for(House i : shuffleDeck) {
				System.out.print(" / "+i.getCardNum()+" "+i.getSuits());
				cnt++;
				if(cnt%5==0)
				System.out.println();
			}
		drawCnt=0;
	}

	public ArrayList<House> cardDrawP(ArrayList<House> playerHand) {
		playerHand.add(shuffleDeck.get(drawCnt));
		drawCnt++;
		return playerHand;
	}

	public ArrayList<House> cardDrawD(ArrayList<House> dealerHand) {
		dealerHand.add(shuffleDeck.get(drawCnt));
		drawCnt++;
		return dealerHand;
	}

	public int scoreCnt(ArrayList<House> hand) {
		int score=0;
		int aceCnt=0;
		int aceScore=0;
		for(House i : hand) {
			switch(i.getCardNum()) {
			case 1 :
				aceCnt++;
				break;
			case 2 :
				score +=2;
				break;
			case 3 :
				score +=3;
				break;
			case 4 :
				score +=4;
				break;
			case 5 :
				score +=5;
				break;
			case 6 :
				score +=6;
				break;
			case 7 :
				score +=7;
				break;
			case 8 :
				score +=8;
				break;
			case 9 :
				score +=9;
				break;
			case 10 :
			case 11 :
			case 12 :
			case 13 :
				score +=10;
				break;
			}
		}
		if(aceCnt>0) {
		aceScore = score<=(11-aceCnt) ? 10+aceCnt : 0+aceCnt;
		}
		score += aceScore;
		return score;
	}

	public boolean splitLogic(ArrayList<House> playerHand) {
		boolean judge;
		judge = playerHand.get(0).getCardNum() == playerHand.get(1).getCardNum();
		return judge;
	}

	public int evenMoneyCheck(int bet, int dealerScore) {
		int gainMoney=0;
		int answer = JOptionPane.showConfirmDialog(null , 
		"블랙잭이 나왔지만, 딜러도 블랙잭일 수 있습니다. 이븐머니(배팅금액 1배지급)를 선택하시겠습니까? ",
		"보험금 배팅",	JOptionPane.YES_NO_OPTION);
		if(answer==0) {
			System.out.println("이븐머니를 선택하셔서 일반 승리수당만큼만 지급합니다.");
			gainMoney=bet*2;
		}else if(dealerScore==21) {
			System.out.println("딜러카드 오픈.");
			System.out.println("딜러 블랙잭! 무승부입니다.");
			gainMoney=bet;
		}else {
			System.out.println("딜러카드 오픈.");
			System.out.println("딜러는 블랙잭이 아닙니다. 플레이어 블랙잭 승리!");
			gainMoney=(int)(bet*2.5);
		}
		return gainMoney;
	}

	public int insuranceCheck(int bet, int dealerScore) {
		int gainMoney=0;
		int answer = JOptionPane.showConfirmDialog(null , 
				"딜러의패가 블랙잭인것에 배팅금액의 반을 보험금으로 거시겠습니까?\n(블랙잭인경우 보험금의 2배 지급)",
				"보험금 배팅",	JOptionPane.YES_NO_OPTION);
		if(answer==0 && dealerScore==21) {
			gainMoney = 0;
			System.out.println("딜러카드 오픈.");
			System.out.println("딜러 블랙잭으로 플레이어 패배");
			System.out.println("보험금의 두배가 지급됩니다.");
		}else if(answer==0 && dealerScore<21) {
			gainMoney = bet/2;
			System.out.println("딜러는 블랙잭이 아닙니다.");
			System.out.println("보험금을 잃고 게임이 계속됩니다.");
		}else {
			gainMoney = -1;
			System.out.println("보험금을 선택하지않아 게임이 계속됩니다.");
		}
		return gainMoney;
	}

	public boolean loop2Check(ArrayList<House>[] playerHand) {
		int sum=0, cnt=0;
		for(ArrayList<House> i : playerHand) {
			if(i!=null) {
				cnt++;
				sum+=i.size();
			}
		}
		if(sum/cnt==2) {
		return true;
		}else {
			return false;
		}
	}

	public boolean findOut(ArrayList<House> playerHand, int playerScore) {
		if(playerHand.size()==6 || playerScore>=21) {
			return false;
		}
		return true;
	}

	public void dispField(ArrayList<House>[] playerHand, ArrayList<House> dealerHand, int[] playerScore, int budget,
			 int startBudget, int insuranceBet, boolean hiddenOpen) {
			for(int i=0;i<playerHand.length;i++) { 
				if(playerHand[i]!=null) {
				System.out.println();
				System.out.println("***** "+(i+1)+"번 핸드*****");
				for(House j : playerHand[i]) {
					System.out.println(j.getCardNum()+" / "+j.getSuits());
				}
				System.out.println("플레이어 점수 : "+playerScore[i]);
				System.out.println();
				System.out.println("***** 딜러핸드 *****");
				if(!hiddenOpen) {
				System.out.println(dealerHand.get(1).getCardNum()+" "+
						dealerHand.get(1).getSuits());
				}else {
					for(House j : dealerHand) {
						System.out.println(j.getCardNum()+" / "+j.getSuits());	
					}
					System.out.println("딜러 점수 : "+scoreCnt(dealerHand));
				}
				
				System.out.println();
				System.out.println("현재 배팅금액 : " + (startBudget-budget));
				System.out.println("보험 배팅금액 : " + insuranceBet);
				System.out.println("예산 : " + budget);
				}
			}
		}

	public int scoreComparing(ArrayList<House>[] playerHand, int[] playerScore, int dealerScore, 
								boolean[] doubleDown) {
		int idx=0;
		for(int i=0;i<playerHand.length;i++) { 
			if(playerHand[i]!=null && playerScore[i]>dealerScore) {
				System.out.println("플레이어의 "+(i+1)+"번 핸드 승리! 배팅금액만큼의 수당을 획득합니다.");
				idx += 2;
				if(doubleDown[i]) {
					idx += 2;
				}
			}else if(playerHand[i]!=null && playerScore[i]==dealerScore) {
				System.out.println("플레이어의 "+(i+1)+"번 핸드 와 딜러의 점수가 같아 무승부! 배팅금액을 반환합니다.");
				idx++;
				if(doubleDown[i]) {
					idx ++;
				}
			}else if(playerHand[i]!=null && playerScore[i]<dealerScore){
				System.out.println("플레이어의 "+(i+1)+"번 핸드 패배... 배팅금액을 잃었습니다.");
			}
		}

		return idx;
	}

	public void dealerAddCard(ArrayList<House> dealerHand) {
		for(House j : dealerHand) {
			System.out.println("딜러핸드 "+j.getCardNum()+" "+j.getSuits());	
		}
		System.out.println();
		System.out.println("딜러 점수 : "+scoreCnt(dealerHand));
	}

	public int choiceBetting(int budget) {
		int bet=0;
		int answer=3;
		bettingOptions = new String [] {"10","20","30","40","50","60","70","80","90","100"};
		try {
			System.out.println("현재 보유금액 : "+budget); 
			bet = (Integer.parseInt((String) JOptionPane.showInputDialog(null,"금액을 선택해주세요", "배팅금액 선택",JOptionPane.QUESTION_MESSAGE,
					null,bettingOptions,bettingOptions[0])));
			}catch(NullPointerException e){
				System.out.println("null");
			}catch(Exception e1) {
				answer = JOptionPane.showConfirmDialog(null , ""
						+ "배팅을 취소하셨습니다. 게임을 종료하고 메인화면으로 가시겠습니까?",
														"게임종료 확인",JOptionPane.YES_NO_OPTION);
				System.out.println(answer);
			}
		if(answer==0) {
			return 0;
		}else if(answer==1 || answer==-1){
			return -1;
		}else {
			return bet;
		}
	}

	public void dispIndexField(int i, ArrayList<House> playerHand, ArrayList<House> dealerHand, int playerScore, int budget,
			int bet, int startBudget, int insuranceBet, boolean hiddenOpen) {
			if(playerHand!=null) {
			System.out.println();
			System.out.println("***** "+(i+1)+"번 핸드*****");
			for(House j : playerHand) {
				System.out.println(j.getCardNum()+" / "+j.getSuits());
			}
			System.out.println("플레이어 점수 : "+playerScore);
			System.out.println();
			System.out.println("***** 딜러핸드 *****");
			if(!hiddenOpen) {
			System.out.println(dealerHand.get(1).getCardNum()+" "+
					dealerHand.get(1).getSuits());
			}else {
				for(House j : dealerHand) {
					System.out.println(j.getCardNum()+" / "+j.getSuits());	
				}
				System.out.println("딜러 점수 : "+scoreCnt(dealerHand));
			}
			
			System.out.println();
			System.out.println("현재 배팅금액 : " + (startBudget-budget));
			System.out.println("보험 배팅금액 : " + insuranceBet);
			System.out.println("예산 : " + budget);
			}
	}


	



}
