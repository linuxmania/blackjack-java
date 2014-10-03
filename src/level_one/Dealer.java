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
