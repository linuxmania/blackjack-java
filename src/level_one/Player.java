package level_one;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Player {
	public List<Card> hand = new ArrayList<Card>(0);
	public boolean blackjack,busted,win,lose,tie,aces,hasSplit = false;
	private Scanner scanner = new Scanner(System.in);
	private List<Player> splitPlayers = new ArrayList<Player>(0);
	
	public boolean takeHit(){
		System.out.println("Hit? (y/n)");
		String s = scanner.next();
		if(s.equals("y"))
			return true;
		return false;
	}

	private boolean canAndWantsSplit(String num, String dealerShow){
		if(!aces){
			if(hand.get(0).value != hand.get(1).value || 
				hand.get(0).order != hand.get(1).order)
			{
				return false;
			}
		}
		
		String s = "\nPlayer " + num + ": Splittable Hand!\n" + 
			getHandReport() + 
			"Dealer showing " + dealerShow +
			"\nSplit them (y/n)?" ;
		System.out.println(s);
		s = scanner.next();
		if(s.equals("y"))
			return true;
		return false;
	}	

	private void processSplit(IDeck deck,String num,String dealerShow){
		splitPlayers.add(new Player());
		splitPlayers.add(new Player());

		splitPlayers.get(0).hand.add(hand.get(0));
		splitPlayers.get(1).hand.add(hand.get(1));

		splitPlayers.get(0).hand.add(deck.nextCard());
		splitPlayers.get(1).hand.add(deck.nextCard());

		splitPlayers.get(0).process(deck, (num + ".1") ,dealerShow);
		splitPlayers.get(1).process(deck,(num + ".2"),dealerShow);		
	}	
/*
	private void processPlayers(IDeck deck){
		for(Player p : players){
			if(p.blackjack)
				continue;
			System.out.println("");
			System.out.println("Player " + (players.indexOf(p) + 1));
			p.reportHand();
			while(!p.busted && p.getHandValue() != 21){
				if(p.takeHit()){
					p.addCard(deck.nextCard());
					if(p.checkBusted()){
						p.reportNewCard();
						p.reportResults();
					} else p.reportNewCard();
				} else break;
			}
		}		
	}
*/	
	
	public void process(IDeck deck, String num, String dealerShow){
		checkAces();
		if(getHandValue() == 21){
			blackjack = true;
			reportResults();
		} else if(this.canAndWantsSplit(num,dealerShow)){	
			this.processSplit(deck,num,dealerShow);
			this.hasSplit = true;
		} else {	
			while(this.getHandValue() < 21){
				String s = "\nPlayer " + num + ":\n"  
						+  getHandReport() 
					+ "Dealer showing " + dealerShow ;
//					"\nClick 'OK' for hit, 'Cancel' to stay.";
				System.out.println(s);				
				if(takeHit()){
					addCard(deck.nextCard());
					if(checkBusted()){
						s = "Player " + num + ": Value: " + getHandValue() + "\n" 
							+  getHandReport() 
							+ "Sorry you're busted.";
						System.out.println(s);				
						break;
					} else if(getHandValue() == 21){
						s = "Player " + num + ": Value: " + this.getHandValue() + "\n" 
							+  getHandReport() 
							+ "21!";
						System.out.println(s);				
						break;
					}	
				}
				else break;
			}
		}
	}

	public void checkAces(){
		if(hand.get(0).order == 1 && hand.get(1).order == 1){
			aces = true;
			hand.get(0).value = 1;
		}
	}	

	public void reportSplitResults(int dealerValue, String num){
		String s = "";
		for (Player p :  splitPlayers) {
			if(p.hasSplit){
				p.reportSplitResults(dealerValue, num + "." + (splitPlayers.indexOf(p)+1));		
			} else { 
				if(!p.busted && !p.blackjack){
					if(dealerValue > 21)
						p.win = true;
					else if(p.getHandValue() > dealerValue)
						p.win = true;
					else if(p.getHandValue() == dealerValue)
						p.tie = true;
					else p.lose = true;
				}
				s += "Player #" + num + "." + (splitPlayers.indexOf(p)+1) + 
					": " + p.getResults(dealerValue) + "\n";
			}
		}
		if(s != "") System.out.println(s);
	}

	public String getResults(int dealerValue){
		if(busted)
			return "Sorry you're busted.";
		else if(blackjack)
			return "Blackjack! Collect your dough.";
		else if(win) {
			if(dealerValue > 21)
				return "Winner " + getHandValue() + " - dealer bust";
			else
				return "Winner " + getHandValue() + " - " + dealerValue;		
		}	
		else if(lose)
			return "Loser " + getHandValue() + " - " + dealerValue;
		else if(tie)
			return "Tie " + getHandValue() + " - " + dealerValue;
		return "Undefined result.";
	}	
	
	int getHandValue(){
		int handValue = 0;
		for(Card c : hand){
			handValue += c.value;
		}		
		return handValue;
	}
	
	public boolean checkBusted(){
		if(getHandValue() > 21){
			busted = true;
			for(Card c : hand){
				if(c.order == 1 && c.value == 11){
					c.value = 1;
					busted = false;
					break;
				}	
			}	
		}
		return busted;
	}
	
	public void addCard(Card card){
		hand.add(card);
	}
	
	public void reportResults(){
		if(busted)
			System.out.println("Sorry you're busted.");
		else if(blackjack)
			System.out.println("Blackjack! Collect your dough.");
		else if(win)
			System.out.println("Winner.");
		else if(lose)
			System.out.println("Loser.");
		else if(tie)
			System.out.println("Tie.");
	}

	public void reportNewCard(){
		Card c = hand.get(hand.size() -1);
		System.out.println("Card " + hand.size() + ": " + c.name + " of " + c.sSuit);
		System.out.println("Value: " + getHandValue());		
	}
	
	public void reportHand(){
		int i = 1;
		int value = 0;
		for(Card c : hand){
			System.out.println("Card " + i++ + ": " + c.name + " of " + c.sSuit);
			value += c.value;
		}
		System.out.println("Value: " + value);		
	}

	private String getHandReport(){
		int i = 1;
		int value = 0;
		String s = "";
		for(Card c : hand){
			s += "Card " + i++ + ": " + c.name + " of " + c.sSuit + "\n";
//			System.out.println("Card " + i++ + ": " + c.name + " of " + c.sSuit);
			value += c.value;
		}
		s += "Value: " + value + "\n";
		return s;
	}
}
