package monopoly;

import java.util.*;

import javax.swing.JOptionPane;

public class Player {
	//reference of game object
	private Game game;
	//name of player
	private String name;
	// position on the board
	private int position;
	// available balance
	private double balance=0;
	// inside jail or not
	private boolean insideJail=false;
	// number of owned railway
	private int numberOfRailway=0;
	// number of owned utility
	private int numberOfUtility=0;
	// number of jail free card
	private int numberOfJailFreeCard = 0;
	// number of times rolled for double
	private int rollForDoubleCount = 0;
	// list of squares owned
	private ArrayList<Square>ownedSquares= new ArrayList<Square>();
	// list of color set (color set means all properties of t hat color is owned
	private ArrayList<String>colorSets = new ArrayList<String>();
	// map of a colors, how many squares of each color owned is listed here
	private HashMap <String,Integer>colorMap = new HashMap<String,Integer>();

	

// constructor using name	
	public Player(String name) {		
		this.name = name;
		this.balance =1500;
		this.position=39;
	
		
	}
	// buy a square
	public void buySquare(Square square){
		//check if already owned
		if(ownedSquares.contains(square)){
			JOptionPane.showMessageDialog(null, "Sorry! You already own this square!");
			return;
		}
		
	
		// if the square is a property
		if(square instanceof Property){
			Property property = (Property) square;
			// check if balance available
			if(!checkBalance(property.getCost()) )
			{
				JOptionPane.showMessageDialog(null, "Sorry, you don't have enough balance to buy this square!");
				return;
			}
			// check if owner is already present
			else if(property.getOwner()!=null){
				JOptionPane.showMessageDialog(null, "Sorry this square is already owned by another player!");
				return;
			}
			// buy property
			property.setOwner(this);
			//put this property color to colormap
			String color = property.getColor();
			if(colorMap.containsKey(color)){
				colorMap.put(color, colorMap.get(color)+1);
			}
			else{
				colorMap.put(color, 1);
			}
			// if color set complete then add property color to color set
			if(property.hasColorset())colorSets.add(color);
			 balance-= property.getCost();
		}
		// if the square is a railway
		else if(square instanceof RailwayStation){
			RailwayStation railway  = (RailwayStation) square;
			// check if balance available
			if(!checkBalance(railway.getCost()))
			{
				JOptionPane.showMessageDialog(null, "Sorry, you don't have enough balance to buy this square!");
				return;
			}
			// check if owner is already present
			else if(railway.getOwner()!=null){
				JOptionPane.showMessageDialog(null, "Sorry this square is already owned by another player!");
				return;
			}
			// buy railway
			railway.setOwner(this);
			balance-= railway.getCost();
			numberOfRailway++; 
		}
		// if the square is a utility
		else if(square instanceof Utility){
			Utility utility  = (Utility) square;
			// check if balance available
			if(!checkBalance(utility.getCost()))
			{
				JOptionPane.showMessageDialog(null, "Sorry, you don't have enough balance to buy this square!");
				return;
			}
			// check if owner is already present
			else if(utility.getOwner()!=null){
				JOptionPane.showMessageDialog(null, "Sorry this square is already owned by another player!");
				return;
			}
			// buy utility
			utility.setOwner(this);
			 balance-= utility.getCost();
			 numberOfUtility++; 
		}
		// add to owned square
		ownedSquares.add(square);
		
		
	}
	
	
	//getters and setters
	public int getRollForDoubleCount() {
		return rollForDoubleCount;
	}
	public void setRollForDoubleCount(int rollForDoubleCount) {
		this.rollForDoubleCount = rollForDoubleCount;
	}
	public boolean checkBalance(int cost) {
		if(balance<cost)return false;
		else return true;
	}
	public ArrayList<String> getColorSets() {
		return colorSets;
	}
	public void setColorSets(ArrayList<String> colorSets) {
		this.colorSets = colorSets;
	}
	public HashMap<String, Integer> getColorMap() {
		return colorMap;
	}
	public void setColorMap(HashMap<String, Integer> colorMap) {
		this.colorMap = colorMap;
	}
	public int getNumberOfUtility() {
		return numberOfUtility;
	}


	public void setNumberOfUtility(int numberOfUtility) {
		this.numberOfUtility = numberOfUtility;
	}


	public int getNumberOfRailway() {
		return numberOfRailway;
	}


	public void setNumberOfRailway(int numberOfRailway) {
		this.numberOfRailway = numberOfRailway;
	}


	public boolean isInsideJail() {
		return insideJail;
	}


	public void setInsideJail(boolean insideJail) {
		this.insideJail = insideJail;
	}


	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPosition() {
		return position;
	}
	public void setPosition(int position) {
		this.position = position;
	}
	public double getBalance() {
		return balance;
	}
	
	public ArrayList<Square> getOwnedSquares() {
		return ownedSquares;
	}
	public void setOwnedSquares(ArrayList<Square> ownedSquares) {
		this.ownedSquares = ownedSquares;
	}
	public Game getGame() {
		return game;
	}
	public void setGame(Game game) {
		this.game = game;
	}
	public int getNumberOfJailFreeCard() {
		return numberOfJailFreeCard;
	}
	public void setNumberOfJailFreeCard(int numberOfJailFreeCard) {
		this.numberOfJailFreeCard = numberOfJailFreeCard;
	}
	
	// set balance of player with referrnce of player by whom this balance change happened
	public void setBalance(double balance, Player affector) {
		// if balance gets below zero
		if(balance<0){
			// if affector is present
			if(affector!=null){
				
				// change owner of all properties to affector
				for(Square square: ownedSquares){
					if(square instanceof Property){
						Property property = (Property)square;
						property.setOwner(affector);
					}
					else if(square instanceof RailwayStation){
						RailwayStation railway = (RailwayStation)square;
						railway.setOwner(affector);
					}
					else if(square instanceof Utility){
						Utility utility = (Utility)square;
						utility.setOwner(affector);
					}
				}
				// remove this player from game
				game.getPlayers().remove(this);
				JOptionPane.showMessageDialog(null, "Player "+name+" got bankrupt and removed from game! His properties are handed to "+affector.getName());
				
			}
			else{
				// affector is not present so player got bankrupt himself so 
				// setting owner of all properties to null
				game.getPlayers().remove(this);
				for(Square square: ownedSquares){
					if(square instanceof Property){
						Property property = (Property)square;
						property.setOwner(affector);
					}
					else if(square instanceof RailwayStation){
						RailwayStation railway = (RailwayStation)square;
						railway.setOwner(affector);
					}
					else if(square instanceof Utility){
						Utility utility = (Utility)square;
						utility.setOwner(affector);
					}
				}
				// show message
				JOptionPane.showMessageDialog(null, "Player "+name+" got bankrupt and removed from game!");
			}
		}
		else{
			// normally change balance
			this.balance = balance;
		}
	
		
		
	}
	
	// mortgage a property
	public void mortgageProperty(Square square) {
		// if invalid square to mortgage
		if(square instanceof SpecialSquare){
			JOptionPane.showMessageDialog(null, "Sorry! You can't mortgage this square!");
			return;
		}
		else{
			// if property
			if(square instanceof Property){
				Property property = (Property) square;
				// if property is not owned by player then show message
				if(property.getOwner()==null || !property.getOwner().equals(this)){
					JOptionPane.showMessageDialog(null, "Sorry! You do not own this property!");
					return;
				}
				// else mortgage property and get half money from bank 
				else{
					setBalance(balance+ property.getCost()/2,null);
					property.setMortgaged(true);
				}
			}
			// if railway
			else if(square instanceof RailwayStation){
				RailwayStation railway = (RailwayStation) square;
				// if railway is not owned by player then show message
				if(railway.getOwner()==null || !railway.getOwner().equals(this)){
					JOptionPane.showMessageDialog(null, "Sorry! You do not own this property!");
					return;
				}
				// else mortgage railway and get half money from bank 
				else{
					setBalance(balance+ railway.getCost()/2,null);
					railway.setMortgaged(true);
				}
			}
			// if utility
			else if(square instanceof Utility){
				Utility utility = (Utility) square;
				// if utility is not owned by player then show message
				if(utility.getOwner()==null || !utility.getOwner().equals(this)){
					JOptionPane.showMessageDialog(null, "Sorry! You do not own this property!");
					return;
				}
				// else mortgage utility and get half money from bank 
				else{
					setBalance(balance+ utility.getCost()/2,null);
					utility.setMortgaged(true);
				}
			}
		}
		
	}
	
	public void unmortgageProperty(Square square) {
		// if invalid square to unmortgage
		if(square instanceof SpecialSquare){
			JOptionPane.showMessageDialog(null, "Sorry! You can't unmortgage this square!");
			return;
		}
		else{
			// if property
			if(square instanceof Property){
				Property property = (Property) square;
				// if property is owned by someone else
				if(property.getOwner()==null || !property.getOwner().equals(this)){
					JOptionPane.showMessageDialog(null, "Sorry! You do not own this property!");
					return;
				}
				else{
					//check if balance is there to unmortgage
					if(checkBalance((int) (balance- property.getCost()/2))){
					setBalance(balance- property.getCost()/2,null);
					property.setMortgaged(false);
					}
					// balance is short so show warning
					else{
						
						JOptionPane.showMessageDialog(null, "Sorry! You do not have enough money to unmortgage this property!");
						return;
					}
				}
			}
			// if railway
			else if(square instanceof RailwayStation){
				RailwayStation railway = (RailwayStation) square;
				// if property is owned by someone else
				if(railway.getOwner()==null || !railway.getOwner().equals(this)){
					JOptionPane.showMessageDialog(null, "Sorry! You do not own this property!");
					return;
				}
				else{
					//check if balance is there to unmortgage
					if(checkBalance((int) (balance- railway.getCost()/2))){
						setBalance(balance- railway.getCost()/2,null);
						railway.setMortgaged(false);
						}
					// balance is short so show warning
						else{
							JOptionPane.showMessageDialog(null, "Sorry! You do not have enough money to unmortgage this railway!");
							return;
						}
				}
			}
			// if utility
			else if(square instanceof Utility){
				Utility utility = (Utility) square;
				// if property is owned by someone else
				if(utility.getOwner()==null || !utility.getOwner().equals(this)){
					JOptionPane.showMessageDialog(null, "Sorry! You do not own this property!");
					return;
				}
				else{
					//check if balance is there to unmortgage
					if(checkBalance((int) (balance- utility.getCost()/2))){
					setBalance(balance- utility.getCost()/2,null);
					utility.setMortgaged(false);
					}
					// balance is short so show warning
					else{
						JOptionPane.showMessageDialog(null, "Sorry! You do not have enough money to unmortgage this utility!");
						return;
					}
				}
			}
		}
			
	}
	// sell house of a square
	public void sellHouse(Square square) {
		// check if property
		if(square instanceof Property){
			Property property = (Property)square;
			// if property is owned by someone else
			if(property.getOwner()==null || !property.getOwner().equals(this)){
				JOptionPane.showMessageDialog(null, "Sorry! You do not own this property");
				
			}
			// if no house is there on that property
			else if(property.getNumberOfHouse()==0){
				JOptionPane.showMessageDialog(null, "Sorry! This property does not have any house on it");
				
			}
			// if there is a hotel on that square
			else if(property.getNumberOfHotel()>0){
				//sell hotel and get half money back
				setBalance(balance+property.getHotelBuildingCost()/2, null);
				property.setNumberOfHotel(property.getNumberOfHotel()-1);
				return;
			}
			//if there is house
			else if(property.getNumberOfHouse()<4){
				// check if any other property of same color has more houses or not
				// then show warning if it has
				for(Square squareOwned: ownedSquares){
					if(squareOwned instanceof Property){
						Property ownedProperty = (Property)squareOwned;
						if(ownedProperty.getColor().equals(property.getColor()) && ownedProperty.getNumberOfHouse()>property.getNumberOfHouse()){
							JOptionPane.showMessageDialog(null, "Sorry! You must sell house evenly!");
							return;
						}
					}
					
				}
				setBalance(balance+property.getHouseBuildingCost()/2, null);
				property.setNumberOfHouse(property.getNumberOfHouse()-1);
				
		}
			// selling houses from only property square is possible
		else{
			JOptionPane.showMessageDialog(null, "Sorry! This square is not a property");
		}
		
	}
	}



}
