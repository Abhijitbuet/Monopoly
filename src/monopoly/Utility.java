package monopoly;

public class Utility extends Square {
	// cost, owner , type=: water/electric , mortgaged or not
	private int cost;
	private Player owner;
	private String type;
	private boolean mortgaged = false;
	// constructor using name and position
	public Utility(String name, int position) {
		super(name, position);
		cost =150;
	}
// setters and getters
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	@Override
	public void executeMove(Player player) {

	}

	public void executeMove(Player player, int diceValue) {
		if (owner != null && !isMortgaged() && !player.equals(owner)) {
			int multiplicationFactor = 0;
			if (owner.getNumberOfUtility() == 1)
				multiplicationFactor = 4;
			else
				multiplicationFactor = 10;
			owner.setBalance(owner.getBalance() + multiplicationFactor * diceValue,null);
			player.setBalance(player.getBalance() - multiplicationFactor * diceValue,owner);

		}

	}

}
