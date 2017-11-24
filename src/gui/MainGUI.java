package gui;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import monopoly.*;
import java.awt.event.*;

public class MainGUI {
	// reference of game object
	private Game game;
	// main frame
	private JFrame frame;
	// turn indicates which player is going to play now
	private int turn = 0;
	DrawPanel drawPanel;
	// attributes for all image objects
	BufferedImage shoeImage = null;
	BufferedImage boardImage = null;
	BufferedImage oneDiceImage = null;
	BufferedImage twoDiceImage = null;
	BufferedImage threeDiceImage = null;
	BufferedImage fourDiceImage = null;
	BufferedImage fiveDiceImage = null;
	BufferedImage sixDiceImage = null;
	BufferedImage firstDiceImage = null;
	BufferedImage secondDiceImage = null;
	BufferedImage hotelImage = null;
	BufferedImage houseImage = null;
	// list of images of each square
	BufferedImage[] squareImages = new BufferedImage[40];
	// list of image icons of players
	ArrayList<BufferedImage> playerImages = new ArrayList<BufferedImage>();
	ArrayList<Player> players = new ArrayList<Player>();
	// current player
	Player currentPlayer = null;
	// number of players playing
	int numberOfPlayers = 0;
	// pixel value to draw images
	private int pixelX = 0;
	private int pixelY = 0;

	// initialize and play game with players
	public MainGUI(Game game) throws IOException {

		this.game = game;

		initialize();
		// run game
		go();
	}

	private void go() throws IOException {
		// set initial frame design
		frame = new JFrame("Game Window");
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// initialize draw panel object
		drawPanel = new DrawPanel();
		// initialize roll button
		JButton rollButton = new JButton("Roll");
		rollButton.setBounds(825, 495, 90, 30);
		rollButton.setIcon(new ImageIcon("images/buttons/Roll.png"));

		frame.getContentPane().add(rollButton);
		// initialize buy button
		JButton buyButton = new JButton("Buy");
		buyButton.setBounds(921, 495, 90, 30);
		buyButton.setIcon(new ImageIcon("images/buttons/buy.png"));
		frame.getContentPane().add(buyButton);
		// initialize mortgage button
		JButton mortgageButton = new JButton("Mortgage");
		mortgageButton.setBounds(1016, 495, 90, 30);
		mortgageButton.setIcon(new ImageIcon("images/buttons/mortgage.png"));
		frame.getContentPane().add(mortgageButton);
		// initialize sell house button
		JButton sellHouseButton = new JButton("Sell House");
		sellHouseButton.setBounds(825, 555, 90, 30);
		sellHouseButton.setIcon(new ImageIcon("images/buttons/SellHouse.png"));
		frame.getContentPane().add(sellHouseButton);
		// initialize get out of jail button
		JButton getOutOfJailButton = new JButton("Get Out Of Jail");
		getOutOfJailButton.setBounds(921, 555, 92, 30);
		getOutOfJailButton.setIcon(new ImageIcon("images/buttons/getOutOfJail.png"));
		frame.getContentPane().add(getOutOfJailButton);
		// initialize view property button
		JButton viewPropertyButton = new JButton();
		viewPropertyButton.setBounds(1016, 555, 92, 30);
		viewPropertyButton.setIcon(new ImageIcon("images/buttons/viewProperty.png"));
		frame.getContentPane().add(viewPropertyButton);
		// initialize build house button
		JButton buildHouseButton = new JButton("Build House");
		buildHouseButton.setIcon(new ImageIcon("images/buttons/BuildHouse.png"));
		buildHouseButton.setBounds(825, 615, 90, 30);
		frame.getContentPane().add(buildHouseButton);
		// initialize unmortgage button
		JButton unmortgageButton = new JButton("Unmortgage");
		unmortgageButton.setIcon(new ImageIcon("images/buttons/unmortgage.png"));
		unmortgageButton.setBounds(921, 615, 90, 30);
		frame.getContentPane().add(unmortgageButton);
		// initialize end turn button
		JButton endTurnButton = new JButton("End Turn");
		endTurnButton.setIcon(new ImageIcon("images/buttons/EndTurn.png"));
		endTurnButton.setBounds(1016, 615, 90, 30);
		frame.getContentPane().add(endTurnButton);
		// initialize text console on gui
		JLabel textConsole = new JLabel("");
		textConsole.setFont(new Font("TimesRoman", Font.ITALIC, 28));
		textConsole.setText("<html> Welcome! To Monopoly!" + "</html>");
		textConsole.setBounds(880, 100, 200, 400);
		frame.getContentPane().add(textConsole);
		// initialize background image on the right
		JLabel backgroundLabel = new JLabel();
		backgroundLabel.setBounds(804, 6, 324, 655);
		backgroundLabel.setIcon(new ImageIcon("images/background/MainGameGUI.png"));
		frame.getContentPane().add(backgroundLabel);
		// initialize other frame properties
		frame.getContentPane().add(BorderLayout.CENTER, drawPanel);

		frame.setVisible(true);

		frame.setSize(1150, 706);
		frame.setLocation(100, 10);
		currentPlayer = players.get(turn);
		if (currentPlayer.isInsideJail()) {
			rollButton.setEnabled(false);
			endTurnButton.setEnabled(false);
		}
		// action for roll button
		rollButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// get current player
				currentPlayer = players.get(turn);
				Random random = new Random();
				// get dice values
				int firstDice = 1 + random.nextInt(6);
				int secondDice = 1 + random.nextInt(6);
				firstDiceImage = setCorrectImage(firstDice, firstDiceImage);
				secondDiceImage = setCorrectImage(secondDice, secondDiceImage);
				// set text that which player is rolling
				textConsole.setText("<html>Player " + players.get(turn).getName() + " "
						+ " is rolling! Current balance " + currentPlayer.getBalance() + "$</html>");

				// total value of two dices
				int displacement = firstDice + secondDice;
				// move one by one step forward
				while (displacement > 0) {
					displacement--;

					rollButton.setEnabled(false);
					movePlayer(currentPlayer);

					frame.repaint();

				}
				// execute move for the square player reached
				Square square = game.getSquares()[currentPlayer.getPosition()];
				square.executeMove(currentPlayer);

			}

		});
		// action for building house
		buildHouseButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				// get current player
				currentPlayer = players.get(turn);

				Square square = game.getSquares()[currentPlayer.getPosition()];
				// if present square is not a property then show waring
				if (!(square instanceof Property)) {
					JOptionPane.showMessageDialog(null, "Sorry! The player is not on a property square");

				} else {

					Property property = (Property) square;
					// if property not owned by player then show warning
					if (!property.getOwner().equals(currentPlayer)) {
						JOptionPane.showMessageDialog(null, "Sorry! The player is not the owner of this property");
						return;
					}
					// build house on that square
					property.buildHouse();

				}
				frame.repaint();
			}
		});
		// action for mortgage button
		mortgageButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// get current square and player and mortgage it
				currentPlayer = players.get(turn);
				Square square = game.getSquares()[currentPlayer.getPosition()];
				currentPlayer.mortgageProperty(square);
			}
		});
		// action for sell house button
		sellHouseButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// get current square and player and sell house from it
				currentPlayer = players.get(turn);
				Square square = game.getSquares()[currentPlayer.getPosition()];
				currentPlayer.sellHouse(square);
			}
		});
		// action for unmortgage button
		unmortgageButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// get current square and player and unmortgage it
				currentPlayer = players.get(turn);
				Square square = game.getSquares()[currentPlayer.getPosition()];
				currentPlayer.unmortgageProperty(square);
			}
		});
		// action for get out of jail button
		getOutOfJailButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				currentPlayer = players.get(turn);
				// if inside jail
				if (currentPlayer.isInsideJail()) {
					// show get out of jail frame
					game.setJailFrame(new GetOutOfJailFrame(currentPlayer));
				}
				// show warning that already out of jail
				else {
					JOptionPane.showMessageDialog(null, "You are already out of jail!");

				}
			}
		});
		// action for end turn button
		endTurnButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// clear dice images
				firstDiceImage = null;
				secondDiceImage = null;
				// set message that which player ended turn
				textConsole.setText("<html>Player " + players.get(turn).getName() + " ended turn! Current balance "
						+ currentPlayer.getBalance() + "$</html>");
				turn = (turn + 1) % players.size();
				// activate roll button
				rollButton.setEnabled(true);
				frame.repaint();

			}
		});
		// action for view property button
		viewPropertyButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				Square square = game.getSquares()[currentPlayer.getPosition()];
				// if square is a property
				if (square instanceof Property) {
					Property property = (Property) square;
					// show details on property frame
					game.setPropertyFrame(new PropertyFrame(square, "images/squares/" + property.getImageFileName()));

				}
				// not a property square so show warning
				else {
					JOptionPane.showMessageDialog(null, "The player is not on a property square");
				}
				// redraw frame
				frame.repaint();

			}
		});
		// action for buy button
		buyButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// if roll button is enabled then show warning of rolling first
				if (rollButton.isEnabled()) {
					JOptionPane.showMessageDialog(null, "You have to roll first");
				}
				// if player inside jail then show warning
				else if (currentPlayer.isInsideJail()) {
					JOptionPane.showMessageDialog(null, "You are inside Jail! Get out of it first");
				} else {
					Square square = game.getSquares()[currentPlayer.getPosition()];
					// if square is not of property type then show warning
					if (square instanceof SpecialSquare) {
						JOptionPane.showMessageDialog(null, "Sorry! You can not buy this square!");

					}
					// everything ok, so buy square
					else {
						currentPlayer.buySquare(square);
						// show this activity on console
						if (currentPlayer.getOwnedSquares().contains(square)) {
							textConsole.setText(
									"<html>Player " + currentPlayer.getName() + " " + " bought " + square.getName()
											+ "! Current balance " + currentPlayer.getBalance() + "$</html>");

						}
						frame.repaint();
					}
				}
			}
		});

	}

	// initialize image objects
	private void initialize() throws IOException {
		playerImages.clear();
		this.numberOfPlayers = game.getPlayers().size();
		this.players = game.getPlayers();
		for (Player player : players) {
			String name = player.getName();
			// initialize
			if (name.equals("Shoe")) {
				playerImages.add(ImageIO.read(new File("images/icons/shoe.png")));
			} else if (name.equals("Dog")) {
				playerImages.add(ImageIO.read(new File("images/icons/dog.png")));
			} else if (name.equals("Thimble")) {
				playerImages.add(ImageIO.read(new File("images/icons/iron.png")));
			} else if (name.equals("Car")) {
				playerImages.add(ImageIO.read(new File("images/icons/car.png")));
			} else if (name.equals("Hat")) {
				playerImages.add(ImageIO.read(new File("images/icons/hat.png")));
			} else if (name.equals("Battleship")) {
				playerImages.add(ImageIO.read(new File("images/icons/battleship.png")));
			}

		}
		// initialize all the images of dice and house
		shoeImage = ImageIO.read(new File("images/shoeSmall.jpg"));
		boardImage = ImageIO.read(new File("images/MONOPOLYGUI.jpg"));
		oneDiceImage = ImageIO.read(new File("images/oneDice.png"));
		twoDiceImage = ImageIO.read(new File("images/twoDice.png"));
		threeDiceImage = ImageIO.read(new File("images/threeDice.png"));
		fourDiceImage = ImageIO.read(new File("images/fourDice.png"));
		fiveDiceImage = ImageIO.read(new File("images/fiveDice.png"));
		sixDiceImage = ImageIO.read(new File("images/sixDice.png"));
		hotelImage = ImageIO.read(new File("images/houseHotels/hotel.png"));
		houseImage = ImageIO.read(new File("images/houseHotels/house.png"));
	}

	class DrawPanel extends JPanel {

		private static final long serialVersionUID = 1L;

		// painting component inside main board
		public void paintComponent(Graphics g) {
			try {
				// initialize in each step to check if a player is removed or
				// not
				initialize();
			} catch (IOException e) {

				e.printStackTrace();
			}
			// draw borders
			g.setColor(Color.BLUE);
			g.fillRect(0, 0, this.getWidth(), this.getHeight());
			g.setColor(Color.RED);
			g.fillRect(3, 3, this.getWidth() - 6, this.getHeight() - 6);
			g.setColor(Color.cyan);
			g.fillRect(6, 6, this.getWidth() - 12, this.getHeight() - 12);
			g.setColor(Color.BLACK);
			g.drawImage(boardImage, 6, 6, this);
			// draw dice images
			g.drawImage(firstDiceImage, 355, 116, this);
			g.drawImage(secondDiceImage, 434, 116, this);
			//for each player
			for (int i = 0; i < players.size(); i++) {
				Player player = players.get(i);
				Square square = game.getSquares()[player.getPosition()];
				// pixelX and pixelY is pixel value calculated by player index
				pixelX = square.getPixels()[2 * i];
				pixelY = square.getPixels()[2 * i + 1];
				// if player inside jail then change pixel values
				if (player.isInsideJail()) {
					pixelX = square.getPixels()[8 + 2 * i];
					pixelY = square.getPixels()[8 + 2 * i + 1];
				}
				// draw player image icon to correct position
				g.drawImage(playerImages.get(i), pixelX, pixelY, this);
				// for each square of owned squares of player
				for (Square squareOwned : player.getOwnedSquares()) {
					// if owned by player then draw icon of player on top of square
					g.drawImage(playerImages.get(i), squareOwned.getPixels()[8], squareOwned.getPixels()[9], this);
					// if the square is of type property
					if (squareOwned instanceof Property) {
						Property property = (Property) squareOwned;
						// if there is a hotel then draw hotel image in right on top label
						if (property.getNumberOfHotel() > 0) {
							g.drawImage(hotelImage, squareOwned.getPixels()[18], squareOwned.getPixels()[19], this);
						} else if (property.getNumberOfHouse() > 0) {
							if (property.getNumberOfHouse() > 3) {
								// if number of house==4 then draw fourth house
								g.drawImage(houseImage, squareOwned.getPixels()[16], squareOwned.getPixels()[17], this);
							}
							// if number of house>=3 then draw third house
							if (property.getNumberOfHouse() > 2) {
								g.drawImage(houseImage, squareOwned.getPixels()[14], squareOwned.getPixels()[15], this);
							}
							// if number of house>=2 then draw second house
							if (property.getNumberOfHouse() > 1) {
								g.drawImage(houseImage, squareOwned.getPixels()[12], squareOwned.getPixels()[13], this);
							}
							// if number of house>=1 then draw first house
							if (property.getNumberOfHouse() > 0) {
								g.drawImage(houseImage, squareOwned.getPixels()[10], squareOwned.getPixels()[11], this);
							}
						}
					}
				}

			}

		}
	}

	// select correct dice image by integer value between 1-6
	private BufferedImage setCorrectImage(int dice, BufferedImage image) {
		switch (dice) {
		case 1:
			return oneDiceImage;

		case 2:
			return twoDiceImage;

		case 3:
			return threeDiceImage;

		case 4:
			return fourDiceImage;

		case 5:
			return fiveDiceImage;

		default:
			return sixDiceImage;

		}

	}

	// move player one square forward
	private void movePlayer(Player player) {

		try {
			// wait 100 milliseconds for each move
			Thread.sleep(100);
			player.setPosition((player.getPosition() + 1) % 40);
			// move in a circular way
			if (player.getPosition() == 39) {
				player.setBalance(player.getBalance() + 200, null);
			}

		} catch (Exception e) {
		}

	}
}
