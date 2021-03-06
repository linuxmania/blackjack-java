/***************************
Copyleft [2014] [Daniel Spicer]
Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at
http://www.apache.org/licenses/LICENSE-2.0
Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
****************************/

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
