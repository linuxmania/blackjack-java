package level_one;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Game {
	private List<Player> players = new ArrayList<Player>(0);
	private Dealer dealer = new Dealer();

	public boolean play(IDeck deck){

		if(getNumPlayers() == 0) 
			return false;

		deck.shuffle();

		deal(deck);
		
//		processPlayers(deck);

		int i = 1;
		for(Player p : players){
			p.process(deck, String.valueOf(i), dealer.reportShowing());
			i++;
		}
		
		processDealer(deck);

		reportResults();

		return true;
	}
	
	private void reportResults(){
		for(Player p : players){
			if(p.hasSplit){
				p.reportSplitResults(dealer.getHandValue() , 
						String.valueOf(players.indexOf(p) + 1));		
			} else { 			
				if(!p.busted && !p.blackjack){
					if(dealer.busted)
						p.win = true;
					else if(p.getHandValue() > dealer.getHandValue())
						p.win = true;
					else if(p.getHandValue() == dealer.getHandValue())
						p.tie = true;
					else p.lose = true;
				}
				System.out.println("");
				System.out.println("Player " + (players.indexOf(p) + 1));
				p.reportResults();
			}
		}
		System.out.println("");				
	}
	
	private void processDealer(IDeck deck){
		dealer.checkAces();
		System.out.println("");
		System.out.println("Dealer has:" );
		dealer.reportHand();
		while(!dealer.busted){
			if(dealer.takeHit()){
				dealer.addCard(deck.nextCard());
				dealer.reportNewCard();
				if(dealer.checkBusted())
					dealer.reportResults();
			} else break;
		}		
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
	private int getNumPlayers(){
		int i = 0;
		while(true){
			Scanner scanner = new Scanner(System.in);
			System.out.println("How many players?");
			try{
				i = scanner.nextInt();
				break;
			} catch (java.util.InputMismatchException e) {
				System.out.println("Invalid entry. Please enter a number between 0 and 5.");
			}
		}
		int num = i;
		while(i-- > 0)
			players.add(new Player());
		return num;
	}
	
	private void deal(IDeck deck){
		
		for(Player p : players){
			p.addCard(deck.nextCard());
		}
		dealer.addCard(deck.nextCard());
		for(Player p : players){
			p.addCard(deck.nextCard());
			System.out.println("");
			System.out.println("Player " + (players.indexOf(p) + 1));
			p.checkBusted();
			p.reportHand();
			if(p.getHandValue() == 21) p.blackjack = true;
			p.reportResults();
		}
		dealer.addCard(deck.nextCard());
		System.out.println("");
		dealer.reportInitialHand();
		
	}

}
