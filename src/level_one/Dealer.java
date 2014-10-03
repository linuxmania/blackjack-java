package level_one;

public class Dealer extends Player{
	
	public void reportInitialHand(){
		Card c = hand.get(1);
		System.out.println("Dealer shows " + c.name + " of " + c.sSuit);		
	}

	public String reportShowing(){
		return "Dealer shows " + hand.get(1).name + " of " + hand.get(1).sSuit;		
	}

	public boolean takeHit(){
		if(getHandValue() < 17) return true;
		return false;
	}

	public void reportResults(){
		if(busted)
			System.out.println("Dealer is busted, Everyone's a winner!");
	}

}
