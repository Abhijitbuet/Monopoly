package monopoly;

import java.util.*;

import gui.*;


public class Game {
	// attributes like player list, chance card list, community card list, square list, 
	private ArrayList<Player> players = new ArrayList<Player>();
	private ArrayList<ChanceCard>chanceCards= new ArrayList<ChanceCard>();
	private ArrayList<CommunityChestCard>communityCards = new ArrayList<CommunityChestCard>();
	private Square[] squares = new Square[40];
	// card showing gui
	private ShowFrame frame = null;
	// property showing gui
	private PropertyFrame propertyFrame = null;
	// get out of jail gui
	private GetOutOfJailFrame jailFrame = null;
	//constructor using list of players
	public Game(ArrayList<Player> players) {

		this.players = players;
		initializePlayersToGame();
		initializeSquares();
		initializeCards();
	}
// initialize game objects to players
	private void initializePlayersToGame() {
		for(Player player: players){
			player.setGame(this);
		}
		
	}
// initialize all cards
	private void initializeCards() {
		initializeChanceCards();
		initializeCommunityCards();
		
	}
// initialize all community chest cards
	private void initializeCommunityCards() {
		
		communityCards.add(new CommunityChestCard("Community Chest", -1, 50, true, "communityCollect50each.jpg", "Opera tonight! collect 50$ from each player!"));
		communityCards.add(new CommunityChestCard("Community Chest", 39, 0, false, "communitygo200.jpg", "Your show is a go! Collect 200$"));
		communityCards.add(new CommunityChestCard("Community Chest", -1,-100 , false, "communityminus100.jpg", "Steal babies for market research! Pay 100$"));
		communityCards.add(new CommunityChestCard("Community Chest", -1,-25 , false, "communityminus25.jpg", "Marriage penalty tax! Pay 25$"));
		communityCards.add(new CommunityChestCard("Community Chest", -1,-50 , false, "communityminus50.jpg", "HMO operates on wrong leg! Pay 50$"));
		communityCards.add(new CommunityChestCard("Community Chest", -1,0 , false, "communityNothing.jpg", "CEO gets 100 million X-Mas bonus! You get nothing"));
		communityCards.add(new CommunityChestCard("Community Chest", -1,100 , false, "communityOldPlus100.jpg", "You're 30! Too old to work on TV! Collect 100$"));
		communityCards.add(new CommunityChestCard("Community Chest", -1,100 , false, "communityplus100.jpg", "Your show has 50 million viewers! Collect 100$"));
		communityCards.add(new CommunityChestCard("Community Chest", -1,15 , false, "communityplus15.jpg", "You are nominated for writing an EMMY, but you didn't win! Collect 15$"));
		communityCards.add(new CommunityChestCard("Community Chest", -1,200 , false, "communityplus200.jpg", "Network mistakenly pays a residual! Collect 200$"));
		communityCards.add(new CommunityChestCard("Community Chest", -1,45 , false, "communityplus45.jpg", "Cash out 41k! Collect 45$"));
		communityCards.add(new CommunityChestCard("Community Chest", -1,20 , false, "communityplus20.jpg", "IRS believes your deductions! Collect 20$"));
		CommunityChestCard goToJailCard = new CommunityChestCard("Community Chest", -1, 0, false, "communityGoToJail.jpg", "Go to Jail! Do not pass Go! Do not collect 200$");
		goToJailCard.setGoToJail(true);
		communityCards.add(goToJailCard);
		
		CommunityChestCard getPerHouseHotelCard = new CommunityChestCard("Community Chest", -1, 0, false, "communityhousehotel.jpg", "Network pays you to make their season disappear! Collect 40$ per house and 115$ per hotel");
		getPerHouseHotelCard.setPerHouseHotel(true);
		communityCards.add(getPerHouseHotelCard);
		
		CommunityChestCard jailFreeCard = new CommunityChestCard("Community Chest", -1, 0, false, "communityJailFree.jpg", "Jail free card! This card may be kep until used or sold!");
		jailFreeCard.setJailFreeCard(true);;
		communityCards.add(jailFreeCard);
	}
// initialize all chance cards
	private void initializeChanceCards() {
		chanceCards.add(new ChanceCard("Chance Card" , -1, 0, true, false, false, "chanceGoToJail.jpg", "Go directly to jail! Do not pass go! Do not collect 200$!"));
		chanceCards.add(new ChanceCard("Chance Card" , -1, 0, false, false, true, "chanceGoBack3Space.jpg", "You received a death threat! Go back three spaces!"));
		chanceCards.add(new ChanceCard("Chance Card" , 39, 0, false, false, false, "chanceGoPlus200.jpg", "Advance to go! Collect 200$!"));
		chanceCards.add(new ChanceCard("Chance Card" , -1, -15, false, false, false, "chanceMinus15.jpg", "Pay the bureau of internal revenues luxury tax of 15$!"));
		chanceCards.add(new ChanceCard("Chance Card" , -1, 50,false , true, false, "chanceminus50perplayer.jpg", "You have been selected as chairman of board of trustees! Pay each player 50$"));
		chanceCards.add(new ChanceCard("Chance Card" , -1, 150, false, false, false, "chanceplus150.jpg", "Your building loan matures! Collect 150$ from bank"));
		chanceCards.add(new ChanceCard("Chance Card" , -1, 50, false, false, false, "chancepluss50.jpg", "Error in dividend computation! Bank pays you bank 50$"));

		ChanceCard nearestRailwayCard = new ChanceCard("Chance Card", -1, 0, false, false, false, "chanceNearestRailway.jpg", "Go to nearest railway and pay the owner twice its rent! If it is unowned you can buy it from bank!");
		nearestRailwayCard.setNearestRailway(true);
		chanceCards.add(nearestRailwayCard);
		
		ChanceCard jailFreeCard = new ChanceCard("Chance Card", -1, 0, false,false,false, "chanceGetOutJail.jpg", "Jail free card! This card may be kep until used or sold!");
		jailFreeCard.setJailFreeCard(true);;
		chanceCards.add(jailFreeCard);
		
		ChanceCard getPerHouseHotelCard = new ChanceCard("Chance Card", -1, 0,false,false, false, "chanceMinusPerHouse25.jpg", "Make general repairs on all your property! Pay 25$ per house and 100$ per hotel");
		getPerHouseHotelCard.setPerHouseHotel(true);
		chanceCards.add(getPerHouseHotelCard);
	}
// initialize all 40 squares
	private void initializeSquares() {
		
		 squares[0] = new Property("Mediterranean Avenue", 0, "Brown", 60, "Mediterranean Avenue.png");
		 squares[1] = new SpecialSquare("Community Chest", 1);
		 squares[2] = new Property("Baltic Avenue",2, "Brown", 60,"BalticAvenue.png");
		 squares[3] = new SpecialSquare("Income Tax", 3);
		 squares[4] = new RailwayStation("Reading Railroad", 4);
		 squares[5] = new Property("Oriental Avenue",5, "Light Blue", 100, "OrientalAvenue.png");
		 squares[6] = new SpecialSquare("Chance", 6);
		 squares[7] = new Property("Vermont Avenue",7, "Light Blue", 100,"VermontAvenue.png");
		 squares[8] = new Property("Connecticut Avenue",8, "Light Blue", 120,"ConnecticutAvenue.png");
		 squares[9] = new SpecialSquare("Jail", 9);
		 squares[10] = new Property("St Charles Avenue",10,"Pink", 140,"StCharlesPlace.png");
		 squares[11] = new Utility("Electric Company", 11);
		 squares[12] = new Property("States Avenue",12,"Pink", 140,"StatesAvenue.png");
		 squares[13] = new Property("Virginia Avenue",13,"Pink", 160,"VirginiaAvenue.png");
		 squares[14] = new RailwayStation("Pensylvania Railroad", 14);
		 squares[15] = new Property("St James Place",15,"Orange", 180,"StJamesPlace.png");
		 squares[16] = new SpecialSquare("Community Chest", 16);
		 squares[17] = new Property("Tennessee Avenue",17,"Orange", 180,"TennesseeAvenue.png");
		 squares[18] = new Property("New York Avenue",18,"Orange", 200,"NewYorkAvenue.png");
		 squares[19] = new SpecialSquare("Free Parking", 19);
		 squares[20] = new Property("Kentucky Avenue",20, "Red",220,"KentuckyAvenue.png");
		 squares[21] = new SpecialSquare("Chance", 21);
		 squares[22] = new Property("Indiana Avenue",22, "Red",220,"IndianaAvenue.png");
		 squares[23] = new Property("Illinois Avenue",23, "Red",240,"IlinoisAvenue.png");
		 squares[24] = new RailwayStation("B. & O. Railroad", 24);
		 squares[25] = new Property("Atlantic Avenue",25,"Yellow",260,"AtlanticAvenue.png");
		 squares[26] = new Property("Ventnor Avenue",26,"Yellow",260,"VentorAvenue.png");
		 squares[27] = new Utility("Water Works", 27);
		 squares[28] = new Property("Marvin Gardens",28,"Yellow",280,"MarvinGardens.png");
		 squares[29] = new SpecialSquare("Go To Jail", 29);
		 squares[30] = new Property("Pacific Avenue",30, "Green", 300, "PacificAvenue.png");
		 squares[31] = new Property("North Carolina Avenue",31, "Green", 300,"NorthCarolina.png");
		 squares[32] = new SpecialSquare("Community Chest", 32);
		 squares[33] = new Property("Pensylvania Avenue",33, "Green", 320,"Pensylvania.png");
		 squares[34] = new RailwayStation("Short Line", 34);
		 squares[35] = new SpecialSquare("Chance", 35);
		 squares[36] = new Property("Park Place",36, "Blue",350,"ParkPlace.png");
		 squares[37] = new SpecialSquare("Luxury Tax",37);
		 squares[38] = new Property("Boardwalk",38, "Blue",400,"Boardwalk.png");
		 squares[39] = new SpecialSquare("Go",39);
		 
	}
	// getters and setters

	public GetOutOfJailFrame getJailFrame() {
		return jailFrame;
	}

	public void setJailFrame(GetOutOfJailFrame jailFrame) {
		this.jailFrame = jailFrame;
	}

	public PropertyFrame getPropertyFrame() {
		return propertyFrame;
	}

	public void setPropertyFrame(PropertyFrame propertyFrame) {
		this.propertyFrame = propertyFrame;
	}

	public ShowFrame getFrame() {
		return frame;
	}

	public void setFrame(ShowFrame frame) {
		this.frame = frame;
	}

	public ArrayList<ChanceCard> getChanceCards() {
		return chanceCards;
	}

	public void setChanceCards(ArrayList<ChanceCard> chanceCards) {
		this.chanceCards = chanceCards;
	}

	public ArrayList<CommunityChestCard> getCommunityCards() {
		return communityCards;
	}

	public void setCommunityCards(ArrayList<CommunityChestCard> communityCards) {
		this.communityCards = communityCards;
	}

	public ArrayList<Player> getPlayers() {
		return players;
	}

	public void setPlayers(ArrayList<Player> players) {
		this.players = players;
	}

	public Square[] getSquares() {
		return squares;
	}

	public void setSquares(Square[] squares) {
		this.squares = squares;
	}

}
