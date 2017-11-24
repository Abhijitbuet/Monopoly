package monopoly;


import java.util.*;

import javax.swing.JOptionPane;

public class Property extends Square{
	// attributes like owner, cost, color, number of houses, number of hotels,
	// house rents( rent for 1 house, 2 houses, 3 houses, 4 houses, hotel)
	// rent if squares of same color owned by owner that is called color set
	// house building cost, hotel building cost, and image file name
	private Player owner;
	private int cost;
	private String color;
	private int numberOfHouse;
	private int numberOfHotel;
	private int [] houseRents= new int[5];
	private int rent;
	private int rentWithColorSet;
	private int houseBuildingCost;
	private int hotelBuildingCost;
	private String imageFileName;
	private boolean mortgaged=false;
	
	// constructor using name, position, color, cost, image file name
	public Property(String name, int position,String color, int cost,String imageFileName) {
		super(name, position);
		initialize();
	    setColor(color);
	    setCost(cost);
	    setImageFileName(imageFileName);
	}
	// initialze by position, setting all house rents, normal rate, color set rent, 
	//house building cost and hotel building cost
	private void initialize() {
		switch(getPosition()){
			case 0: 
				int[] rents= {10,30,90,160,250};
				houseRents = rents;
				setValues(2,4,50,50);
				break;
			case 2:
				int[] rents2= {20,60,180,320,450};
				houseRents = rents2;
				setValues(4,8,50,50);
				break;
			case 5:
				
			case 7:
				int[] rents7= {30,90,270,400,550};
				houseRents = rents7;
				setValues(6,12,50,50);
					break;
			case 8:
				int[] rents8={40,100,300,450,600}; 
				houseRents = rents8;
				setValues(8,16,50,50);
					break;
				
			case 10:
				
			case 12:
				int[] rents10={50,150,450,625,750}; 
				houseRents = rents10;
				setValues(10,20,100,100);
				break;
			case 13:
				int[] rents13={60,180,500,700,900}; 
				houseRents = rents13;
				setValues(12,24,100,100);
				break;
			case 15:
				
			case 17:
				int[] rents17={70,200,550,750,950}; 
				houseRents = rents17;
				setValues(14,28,100,100);
				break;
			case 18:
				int[] rents18={80,220,600,800,1000}; 
				houseRents = rents18;
				setValues(16,32,100,100);
				break;
			case 20:
			
			case 22:
				int[] rents22={90,250,700,875,1050}; 
				houseRents = rents22;
				setValues(18,36,150,150);
				break;
			case 23:
				int[] rents23={100,300,750,925,1100}; 
				houseRents = rents23;
				setValues(20,40,150,150);
				break;
			case 25:
			
			case 26:
				int[] rents26={110,330,800,975,1150}; 
				houseRents = rents26;
				setValues(22,44,150,150);
				break;
			case 28:
				int[] rents28={120,360,850,1025,1200}; 
				houseRents = rents28;
				setValues(24,48,150,150);
				break;
			case 30:
				
			case 31:
				int[] rents31={130,390,900,1100,1275}; 
				houseRents = rents31;
				setValues(26,52,200,200);
				break;
			case 33:
				int[] rent33={150,450,1000,1200,1400}; 
				houseRents = rent33;
				setValues(28,56,200,200);
				break;
			case 36:
				int[] rent36={175,500,1100,1300,1500}; 
				houseRents = rent36;
				setValues(35,70,200,200);
				break;
			case 38:
				int[] rent38={200,600,1400,1700,2000}; 
				houseRents = rent38;
				setValues(50,100,200,200);
				break;
			default:
				break;
			
		}
	}
	
	// getters and setters
	public String getImageFileName() {
		return imageFileName;
	}
	public void setImageFileName(String imageFileName) {
		this.imageFileName = imageFileName;
	}
	// set normal rent, color set rent, 
	//house building cost and hotel building cost
	private void setValues(int rent, int rentWithColorSet, int houseBuildingCost, int hotelBuildingCost) {
		this.rent = rent;
		this.rentWithColorSet = rentWithColorSet;
		this.houseBuildingCost = houseBuildingCost;
		this.hotelBuildingCost= hotelBuildingCost;
		
	}
	// getters and setters
	public int[] getHouseRents() {
		return houseRents;
	}
	public void setHouseRents(int[] houseRents) {
		this.houseRents = houseRents;
	}
	public int getRent() {
		return rent;
	}
	public void setRent(int rent) {
		this.rent = rent;
	}
	public int getRentWithColorSet() {
		return rentWithColorSet;
	}
	public void setRentWithColorSet(int rentWithColorSet) {
		this.rentWithColorSet = rentWithColorSet;
	}
	public int getHouseBuildingCost() {
		return houseBuildingCost;
	}
	public void setHouseBuildingCost(int houseBuildingCost) {
		this.houseBuildingCost = houseBuildingCost;
	}
	public int getHotelBuildingCost() {
		return hotelBuildingCost;
	}
	public void setHotelBuildingCost(int hotelBuildingCost) {
		this.hotelBuildingCost = hotelBuildingCost;
	}
	public boolean isMortgaged() {
		return mortgaged;
	}
	public void setMortgaged(boolean mortgaged) {
		this.mortgaged = mortgaged;
	}
	
	public Player getOwner() {
		return owner;
	}
	public void setOwner(Player owner) {
		this.owner = owner;
	}
	public int getCost() {
		return cost;
	}
	public void setCost(int cost) {
		this.cost = cost;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public int getNumberOfHouse() {
		return numberOfHouse;
	}
	public void setNumberOfHouse(int numberOfHouse) {
		this.numberOfHouse = numberOfHouse;
	}
	public int getNumberOfHotel() {
		return numberOfHotel;
	}
	public void setNumberOfHotel(int numberOfHotel) {
		this.numberOfHotel = numberOfHotel;
	}
	// check if properties of same color owned by owner or not
	public boolean hasColorset(){
		if(owner==null)return false;
		if(color.equals("Brown") || color.equals("Blue") && owner.getColorMap().get(color)==2){
			return true;
		}
		else if(owner.getColorMap().get(color)==3)return true;
		return false;
	}
	// implementing move function
	@Override
	public void executeMove(Player player) {
		// check if square is owned by another player
		if(owner!=null && !isMortgaged() && !player.equals(owner)){
			int squreRent = 0;
			// check if hotel is there
			if(numberOfHotel>0){
				squreRent = houseRents[4];
			}
			// check if house is there
			else if(numberOfHouse>0){
				squreRent = houseRents[numberOfHouse-1];
			}
			// check if color set present
			else if(hasColorset()){
				squreRent= rentWithColorSet;
			}
			// check if normally owned
			else{
				squreRent = rent;
			}
			// pay rent to owner
			player.setBalance(player.getBalance()-squreRent,owner);
			owner.setBalance(owner.getBalance()+squreRent,null);
		}
		
	}
	// build house on property
	public void buildHouse() {
		// check if owner is not present
		if(owner==null){
			JOptionPane.showMessageDialog(null, "Sorry! This square is not owned yet!");
			return;
		}
		// check if color set is there
		if(!hasColorset()){
			JOptionPane.showMessageDialog(null, "Sorry! Owner has not obtained color set of this square!");
			return;
		}
		// check is already hotel is there
		else if(numberOfHotel>0){
			JOptionPane.showMessageDialog(null, "Sorry! Maximum number of structures built on this square!");
			return;
		}
		// if house is possible to build new
		else if(numberOfHouse<4){
		
			ArrayList<Square> ownedSquareList = owner.getOwnedSquares();
			// for each square of owned square list check if color matches property color
			// and then if there is less house than this property then prevent building new house
			for(Square square: ownedSquareList){
				if(square instanceof Property){
					Property property = (Property)square;
					if(property.getColor().equals(color) && property.getNumberOfHouse()<numberOfHouse){
						JOptionPane.showMessageDialog(null, "Sorry! You must build house evenly!");
						return;
					}
				}
				
			}
			// check if player has balance of building new house
			if(owner.checkBalance(houseBuildingCost)){
			this.numberOfHouse++;
			owner.setBalance(owner.getBalance()-houseBuildingCost,null);
			}
			// balance not enough, show warning
			else{
				JOptionPane.showMessageDialog(null, "Sorry! You do not have enough balance to build house on this square!");
				return;
			}
		}
		// if already four house is there
		else{
			// for each square of owned square list check if color matches property color
			// and then if there is less house than this property then prevent building hotel
			ArrayList<Square> ownedSquareList = owner.getOwnedSquares();
			for(Square square: ownedSquareList){
				if(square instanceof Property){
					Property property = (Property)square;
					if(property.getColor().equals(color) && property.getNumberOfHouse()<numberOfHouse){
						JOptionPane.showMessageDialog(null, "Sorry! You must build house evenly!");
						return;
					}
				}
				
			}
			// check if player has balance of building hotel
			if(owner.checkBalance(hotelBuildingCost)){
				numberOfHotel++;
				owner.setBalance(owner.getBalance()-hotelBuildingCost,null);
			}
			// balance not enough, show warning
			else{
				JOptionPane.showMessageDialog(null, "Sorry! You do not have enough balance to build hotel on this square!");
				return;
			}
		}
		
	}

}
