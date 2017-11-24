package gui;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.*;

import monopoly.*;

import java.util.*;
import java.awt.event.*;
import java.io.IOException;

// starting screen
public class StartingGUI {
	// main frame
	private JFrame frame;
	// number of players
	int numberOfPlayers = 2;
	ArrayList<Player> players = new ArrayList<Player>();
	String[] icons;

	// main method for whole game
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					// start initial gui
					StartingGUI window = new StartingGUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public StartingGUI() {
		// initialize gui
		initialize();
	}

	private void initialize() {
		// initialize design
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.CYAN);
		frame.getContentPane().setLayout(null);

		// initialize start game button
		JButton startGameButton = new JButton("Start Game");
		startGameButton.setBounds(331, 335, 90, 23);
		startGameButton.setIcon(new ImageIcon("images/buttons/StartGame.png"));
		frame.getContentPane().add(startGameButton);
		
		// initialize start game button
			JButton helpButton = new JButton("Start Game");
			helpButton.setBounds(231, 335, 90, 23);
			helpButton.setIcon(new ImageIcon("images/buttons/help.png"));
			frame.getContentPane().add(helpButton);

		// a combo box containing options of player number 2/3/4
		String[] playOptions = { "2 players", "3 players", "4 players" };
		JComboBox<String> numberOfPlayersBox = new JComboBox<String>(playOptions);
		numberOfPlayersBox.setBounds(269, 42, 106, 25);

		// check box for dog
		frame.getContentPane().add(numberOfPlayersBox);
		JCheckBox tigerIconChecker = new JCheckBox();
		tigerIconChecker.setBounds(220, 194, 20, 20);
		frame.getContentPane().add(tigerIconChecker);
		// icon for dog
		JLabel tigerLabel = new JLabel();
		tigerLabel.setIcon(new ImageIcon("images/bigIcons/dog.png"));
		tigerLabel.setBounds(240, 182, 153, 75);
		frame.getContentPane().add(tigerLabel);

		// check box for shoe
		JCheckBox shoeIconChecker = new JCheckBox();
		shoeIconChecker.setBounds(320, 194, 20, 20);
		frame.getContentPane().add(shoeIconChecker);
		// icon for shoe
		JLabel shoeLabel = new JLabel();
		shoeLabel.setIcon(new ImageIcon("images/bigIcons/shoe.png"));
		shoeLabel.setBounds(325, 180, 153, 75);
		frame.getContentPane().add(shoeLabel);

		// check box for thimble
		JCheckBox ironIconChecker = new JCheckBox();
		ironIconChecker.setBounds(420, 194, 20, 20);
		frame.getContentPane().add(ironIconChecker);
		// icon for thimble
		JLabel ironLabel = new JLabel();
		ironLabel.setIcon(new ImageIcon("images/bigIcons/thimble.png"));
		ironLabel.setBounds(440, 180, 153, 75);
		frame.getContentPane().add(ironLabel);

		// check box for hat
		JCheckBox hatIconChecker = new JCheckBox();
		hatIconChecker.setBounds(420, 254, 20, 20);
		frame.getContentPane().add(hatIconChecker);
		// icon for hat
		JLabel hatLabel = new JLabel();
		hatLabel.setIcon(new ImageIcon("images/bigIcons/hat.png"));
		hatLabel.setBounds(436, 245, 153, 75);
		frame.getContentPane().add(hatLabel);

		// check box for car
		JCheckBox carIconChecker = new JCheckBox();
		carIconChecker.setBounds(320, 254, 20, 20);
		frame.getContentPane().add(carIconChecker);
		// icon for car
		JLabel carLabel = new JLabel();
		carLabel.setIcon(new ImageIcon("images/bigIcons/car.png"));
		carLabel.setBounds(330, 250, 153, 75);
		frame.getContentPane().add(carLabel);

		// check box for battle ship
		JCheckBox shipIconChecker = new JCheckBox();
		shipIconChecker.setBounds(220, 264, 20, 20);
		frame.getContentPane().add(shipIconChecker);
		// icon for battle ship
		JLabel shipLabel = new JLabel();
		shipLabel.setIcon(new ImageIcon("images/bigIcons/battleship.png"));
		shipLabel.setBounds(230, 240, 153, 95);
		frame.getContentPane().add(shipLabel);

		// set background image
		JLabel backgroundLabel = new JLabel();
		backgroundLabel.setBounds(0, 0, 650, 450);
		backgroundLabel.setIcon(new ImageIcon("images/background/StartingGUI.png"));

		// set some design for frame
		frame.getContentPane().add(backgroundLabel);

		frame.setBounds(100, 100, 650, 450);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// action for start game button
		startGameButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				// create a player list and number of player to be selected
				String numberOfPlayerText = (String) numberOfPlayersBox.getSelectedItem();
				numberOfPlayers = Integer.parseInt(numberOfPlayerText.substring(0, 1));
				int numOfSelectedIcons = 0;
				// if dog icon is selected then add a player named dog
				if (tigerIconChecker.isSelected()) {
					numOfSelectedIcons++;
					Player player = new Player("Dog");
					players.add(player);

				}
				// if shoe icon is selected then add a player named shoe
				if (shoeIconChecker.isSelected()) {
					numOfSelectedIcons++;
					Player player = new Player("Shoe");
					players.add(player);

				}
				// if thimble icon is selected then add a player named thimble
				if (ironIconChecker.isSelected()) {
					numOfSelectedIcons++;
					Player player = new Player("Thimble");
					players.add(player);

				}
				// if hat icon is selected then add a player named hat
				if (hatIconChecker.isSelected()) {
					numOfSelectedIcons++;
					Player player = new Player("Hat");
					players.add(player);

				}
				// if car icon is selected then add a player named car
				if (carIconChecker.isSelected()) {
					numOfSelectedIcons++;
					Player player = new Player("Car");
					players.add(player);

				}
				// if ship icon is selected then add a player named battle ship
				if (shipIconChecker.isSelected()) {
					numOfSelectedIcons++;
					Player player = new Player("Battleship");
					players.add(player);

				}
				// if number of selected icons and number of players do not
				// match then show warning
				if (numOfSelectedIcons != numberOfPlayers) {
					JOptionPane.showMessageDialog(null, "Please select any " + numberOfPlayers + " icons");
					players.clear();
				} else {
					// initialize game object with players list
					Game game = new Game(players);
					frame.setVisible(false);
					try {
						// start main gui with game object
						new MainGUI(game);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

			}
		});
		
		helpButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				FileLoader loader = new FileLoader("help.txt");
				String content = loader.getFileText();
				System.out.println(content);
				new HelpGUI(content);
				
			}
		});
	}
}
