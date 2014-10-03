package level_one;

import level_one.Common.Suit;

public class Card {
	private Common.Suit suit;
	public int value;
	public int order;
	public String name;
	public String sSuit;

	Card(int order, Common.Suit suit){
		this.order = order;
		this.suit = suit;
		
		switch(suit){
			case diamonds:
				sSuit = "Diamonds";
				break;
			case clubs:
				sSuit = "Clubs";
				break;
			case spades:
				sSuit = "Spades";
				break;
			case hearts:
				sSuit = "Hearts";
				break;
		}
		
		switch(order){
			case 1:
				name = "Ace";
				value = 11;
				break;
			case 2:
				name = "Two";
				value = 2;
				break;
			case 3:
				name = "Three";
				value = 3;
				break;
			case 4:
				name = "Four";
				value = 4;
				break;
			case 5:
				name = "Five";
				value = 5;
				break;
			case 6:
				name = "Six";
				value = 6;
				break;
			case 7:
				name = "Seven";
				value = 7;
				break;
			case 8:
				name = "Eight";
				value = 8;
				break;
			case 9:
				name = "Nine";
				value = 9;
				break;
			case 10:
				name = "Ten";
				value = 10;
				break;
			case 11:
				name = "Jack";
				value = 10;
				break;
			case 12:
				name = "Queen";
				value = 10;
				break;
			case 13:
				name = "King";
				value = 10;
				break;
		
		}
	}
}
