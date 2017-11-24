package monopoly;

import java.util.ArrayList;

public class ChanceCard extends Card {

	// attributes like go back three spaces or not, amount of change, collect from or pay each player or not, next position,
	// go to jail or not, jail free card, text inside the card, pay per house and hotel or not
	private boolean goBackThreeSpace=false;
	private boolean goToJail = false;
	private String text ;
	private int amount;
	private String imageFileName;
	private int nextIndex;
	private boolean payEachPlayer;
	private boolean perHouseHotel;
	private boolean jailFreeCard;
	private boolean nearestRailway;
	// constructor using attributes
	public ChanceCard(String name, int index, int amount,boolean goToJail, boolean payEachPlayer, boolean goBackThreeSpace, String imageFileName , String text) {
		super(name, index);
		nextIndex = index;
		this.goBackThreeSpace = goBackThreeSpace;
		this.imageFileName = imageFileName;
		this.amount = amount;
		this.text = text;
		this.goToJail= goToJail;
		this.payEachPlayer = payEachPlayer;
		this.goBackThreeSpace = goBackThreeSpace;
		
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
		// set position to three spaces behind
		else if(goBackThreeSpace){
			Square nextSquare = player.getGame().getSquares()[player.getPosition()-3];
			player.setPosition(player.getPosition()-3);
			
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
			// pay 25$ per house and 100$ per hotel
			player.setBalance(player.getBalance() - 25*numberOfHouse -100* numberOfHotel,null);
		}
		// if paying each player is asked then transfer amount from 
		// each other player to player balance
		else if(payEachPlayer){
			Game game = player.getGame();
			for(Player otherPlayer: game.getPlayers()){
				if(!otherPlayer.equals(player)){
					otherPlayer.setBalance(otherPlayer.getBalance()+amount,null);
					player.setBalance(player.getBalance()- amount,player);
				}
			}
		}
		// send player to nearest railway and pay owner double or give him chance to buy
		else if(nearestRailway){
			int position = player.getPosition();
			int nextPosition=0;
			if(position>34 || position<4){
				nextPosition = 4;
			}
			else if(position>4 || position<14){
				nextPosition = 14;
			}
			else if(position>14 || position<24){
				nextPosition = 24;
			}
			else {
				nextPosition = 34;
			}
			player.setPosition(nextPosition);
			
		}
		//change amount
		else{
			player.setBalance(player.getBalance()+ amount,null);
		}
		
	}
// getters and setters
	public boolean isNearestRailway() {
		return nearestRailway;
	}

	public void setNearestRailway(boolean nearestRailway) {
		this.nearestRailway = nearestRailway;
	}

	public boolean isPerHouseHotel() {
		return perHouseHotel;
	}

	public void setPerHouseHotel(boolean perHouseHotel) {
		this.perHouseHotel = perHouseHotel;
	}

	public boolean isGoBackThreeSpace() {
		return goBackThreeSpace;
	}


	public void setGoBackThreeSpace(boolean goBackThreeSpace) {
		this.goBackThreeSpace = goBackThreeSpace;
	}


	public boolean isGoToJail() {
		return goToJail;
	}


	public void setGoToJail(boolean goToJail) {
		this.goToJail = goToJail;
	}


	public String getText() {
		return text;
	}


	public void setText(String text) {
		this.text = text;
	}


	public int getAmount() {
		return amount;
	}


	public void setAmount(int amount) {
		this.amount = amount;
	}


	public String getImageFileName() {
		return imageFileName;
	}


	public void setImageFileName(String imageFileName) {
		this.imageFileName = imageFileName;
	}


	public int getNextIndex() {
		return nextIndex;
	}


	public void setNextIndex(int nextIndex) {
		this.nextIndex = nextIndex;
	}


	public boolean isPayEachPlayer() {
		return payEachPlayer;
	}


	public void setPayEachPlayer(boolean payEachPlayer) {
		this.payEachPlayer = payEachPlayer;
	}


	public boolean isJailFreeCard() {
		return jailFreeCard;
	}


	public void setJailFreeCard(boolean jailFreeCard) {
		this.jailFreeCard = jailFreeCard;
	}

}
