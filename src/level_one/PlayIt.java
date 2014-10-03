package level_one;

public class PlayIt {
	private static final Deck deck = Deck.getInstance();
	
	public static void main(String[] args){
		
		play();
/*		Game game;
	    IDeck iDeck = deck;
	    
	    do {
	    	game = new Game();
	    } while (game.play(iDeck));
*/		
	}
	
	public static void play(){
	    Game game;
	    IDeck iDeck = deck;
	    
	    do {
	    	game = new Game();
	    } while (game.play(iDeck));
		
	}

}
