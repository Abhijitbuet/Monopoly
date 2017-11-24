package monopoly;

import java.util.ArrayList;

public class CommunityChestCard extends Card{
	// attributes like amount of change, collect from each player or not, next position,
	// go to jail or not, jail free card, text inside the card, pay per house and hotel or not
	private int amount;
	private boolean collectFromEachPlayer;
	private int nextIndex;
	private String imageFileName;
	private boolean goToJail;
	private boolean jailFreeCard=false;
	private String text ;
	private boolean perHouseHotel;
// constructor using attributes
	public CommunityChestCard(String name, int index, int amount, boolean collectFromEachPlayer, String imageFileName , String text) {
		super(name, index);
		nextIndex = index;
		this.collectFromEachPlayer = collectFromEachPlayer;
		this.imageFileName = imageFileName;
		this.amount = amount;
		this.text = text;
		
	}
// implementing move function
	@Override
	public void executeMove(Player player) {
		// if next index is valid then send player to next index
		if(nextIndex!=-1){
			player.setPosition(nextIndex);
			Square nextSquare = player.getGame().getSquares()[nextIndex];
			nextSquare.executeMove(player);
		}
		// send player to jail
		else if(goToJail){
			player.setPosition(9);
			player.setInsideJail(true);
		}
		// give player a jail free card
		else if(jailFreeCard){
			player.setNumberOfJailFreeCard(player.getNumberOfJailFreeCard()+1);
		}
		// if paying per house and hotel is asked 
		// then calculate first due amount and then pay
		else if(perHouseHotel){
			int numberOfHouse = 0;
			int numberOfHotel = 0;
			// count number of house and hotel of the player
			ArrayList<Square> squares = player.getOwnedSquares();
			for(Square square: squares){
				if(square instanceof Property){
					Property property  = (Property) square;
					numberOfHotel+= property.getNumberOfHotel();
					numberOfHouse = property.getNumberOfHouse();
				}
			}
			// pay 40$ per house and 115$ per hotel
			player.setBalance(player.getBalance() + 40*numberOfHouse + 115* numberOfHotel,null);
		}
		// if collecting form each player is asked then transfer amount from 
		// each other player to player balance
		else if(collectFromEachPlayer){
			Game game = player.getGame();
			for(Player otherPlayer: game.getPlayers()){
				if(!otherPlayer.equals(player)){
					otherPlayer.setBalance(otherPlayer.getBalance()-amount,player);
					player.setBalance(player.getBalance()+ amount,null);
				}
			}
		}
		// give player a jail free card
		else if(jailFreeCard){
			player.setNumberOfJailFreeCard(player.getNumberOfJailFreeCard()+1);
		}
		// change balance of player
		else{
			player.setBalance(player.getBalance()+ amount,null);
		}
		
	}
// getters and setters
	public boolean isPerHouseHotel() {
		return perHouseHotel;
	}

	public void setPerHouseHotel(boolean perHouseHotel) {
		this.perHouseHotel = perHouseHotel;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public boolean isCollectFromEachPlayer() {
		return collectFromEachPlayer;
	}

	public void setCollectFromEachPlayer(boolean collectFromEachPlayer) {
		this.collectFromEachPlayer = collectFromEachPlayer;
	}

	public int getNextIndex() {
		return nextIndex;
	}

	public void setNextIndex(int nextIndex) {
		this.nextIndex = nextIndex;
	}

	public String getImageFileName() {
		return imageFileName;
	}

	public void setImageFileName(String imageFileName) {
		this.imageFileName = imageFileName;
	}

	public boolean isGoToJail() {
		return goToJail;
	}

	public void setGoToJail(boolean goToJail) {
		this.goToJail = goToJail;
	}

	public boolean isJailFreeCard() {
		return jailFreeCard;
	}

	public void setJailFreeCard(boolean jailFreeCard) {
		this.jailFreeCard = jailFreeCard;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

}
