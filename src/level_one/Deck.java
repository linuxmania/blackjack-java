package level_one;

import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Random;

public class Deck implements IDeck{
	public ArrayList<Card> cards;
	private Common.Suit suit;
    private static Deck instance = null;
    private ListIterator<Card> deckIterator;
    
	private Deck(){
		cards = new ArrayList<Card>(0);
		for(int i=0; i<52; i++){
			if(i<13) suit = Common.Suit.diamonds;
			else if(i<26) suit = Common.Suit.clubs;
			else if(i<39) suit = Common.Suit.hearts;
			else suit = Common.Suit.spades;
			
			cards.add(new Card((i%13) + 1, suit));
		}
		deckIterator = (ListIterator<Card>)cards.listIterator();
	}
	
    public static synchronized Deck getInstance() {
        if (instance == null) {
            instance = new Deck();
        }
        return instance;
    }
    
	public void shuffle(){
		Random random = new Random();
		@SuppressWarnings("unchecked")
		ArrayList<Card> origDeck = (ArrayList<Card>)cards.clone();
		cards.clear();
		while(origDeck.size() > 0){
			int num = random.nextInt(origDeck.size());
			cards.add(origDeck.get(num));
			origDeck.remove(num);			
		}
		deckIterator = (ListIterator<Card>)cards.listIterator();
	}

	public Card nextCard(){
		return(deckIterator.next());
	}
	
}
