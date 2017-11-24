package monopoly;

import java.util.ArrayList;
import java.util.Collections;

import gui.ShowFrame;

public class SpecialSquare extends Square{
//constructor 
	public SpecialSquare(String name, int position) {
		super(name, position);
		
	}
// implementing move function
	@Override
	public void executeMove(Player player) {
		// "Go" square
		if(getName().equals("Go")){
			// increment balance with 200$
			player.setBalance(player.getBalance()+200,null);
		}
		// go to jail square
		else if(getName().equals("Go To Jail")){
			// send player to jail
			player.setPosition(9);
			player.setInsideJail(true);
		}
		// luxury tax square
		else if(getName().equals("Luxury Tax")){
			// decrement balance with 100$
			player.setBalance(player.getBalance()-100,null);
		}
		// Income tax square
		else if(getName().equals("Income Tax")){
			// decrement balance with 200$
			player.setBalance(player.getBalance()-200,null);
		}
		// chance square
		else if(getName().equals("Chance")){
			// select a random card from list
			ArrayList<ChanceCard> cards = player.getGame().getChanceCards();
			Collections.shuffle(cards);
			ChanceCard card = cards.get(0);
			// show card
			new ShowFrame(this, "images/chance/"+card.getImageFileName());
			card.executeMove(player);
		}
		// community chest square
		else if(getName().equals("Community Chest")){
			// select a random card from list
			ArrayList<CommunityChestCard> cards = player.getGame().getCommunityCards();
			
			Collections.shuffle(cards);
			CommunityChestCard card = cards.get(0);
			// show card
			ShowFrame frame = new ShowFrame(this, "images/community/"+card.getImageFileName());
			player.getGame().setFrame(frame);
			card.executeMove(player);
		}
	}
}
