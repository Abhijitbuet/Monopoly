package monopoly;

public class RailwayStation extends Square {
	// attributes like mortgaged or not, owner, cost
	private boolean mortgaged=false;
	private Player owner;
	private int cost;
	// constructor
	public RailwayStation(String name, int position) {
		super(name, position);
		cost =200;
	}
	// implementing move function
	@Override
	public void executeMove(Player player) {
		// if square owned by another player then pay rent
		if(owner!=null && !mortgaged && !player.equals(owner)){
			int multiplicationFactor =  (int) Math.pow(2, owner.getNumberOfRailway()-1);
				player.setBalance(player.getBalance()-25*multiplicationFactor,owner);
				owner.setBalance(owner.getBalance()+25*multiplicationFactor,null);
			
		}
		
	}
// setters and getters
	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
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

}
