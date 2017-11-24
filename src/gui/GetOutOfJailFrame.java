package gui;

import javax.swing.*;

import monopoly.*;

import java.awt.event.ActionListener;
import java.util.Random;
import java.awt.event.ActionEvent;

public class GetOutOfJailFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	
// constructor using player who wants to get out of jail 
	public GetOutOfJailFrame(Player player) {
		// set initial frame setting
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 375, 450);
		JLabel imageLabel = new JLabel();
		imageLabel.setBounds(10, 10, 286, 337);
		imageLabel.setIcon(new ImageIcon("images/frame/jail.jpg"));

		getContentPane().setLayout(null);
		getContentPane().add(imageLabel);
// initialize use card button
		JButton jailFreeCardButton = new JButton("Use Card!");
		jailFreeCardButton.setBounds(10, 355, 90, 30);
		jailFreeCardButton.setIcon(new ImageIcon("images/buttons/usecard.png"));
		getContentPane().add(jailFreeCardButton);
// initialize pay 50 button
		JButton payFiftyButton = new JButton("Pay 50$");
		payFiftyButton.setBounds(110, 355, 90, 30);
		payFiftyButton.setIcon(new ImageIcon("images/buttons/PAY50.png"));
		getContentPane().add(payFiftyButton);
// initialize roll for double button
		JButton rollButton = new JButton("Roll For Double");
		rollButton.setBounds(210, 355, 90, 30);
		rollButton.setIcon(new ImageIcon("images/buttons/rollForDouble.png"));
		getContentPane().add(rollButton);

		setVisible(true);
// if balance less than 50 then disable pay 50 button
		if (!player.checkBalance(50)) {
			payFiftyButton.setEnabled(false);
		}
		// if roll for double count greater than 2 then disable roll for double button
		if (player.getRollForDoubleCount() > 2) {
			rollButton.setEnabled(false);
		}
		// if player has no jail free card then disable use card button
		if (player.getNumberOfJailFreeCard() == 0) {
			jailFreeCardButton.setEnabled(false);
		}
		// action for roll for double  button 
		jailFreeCardButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// use one of jail free cards of player and set him free of jail
				player.setInsideJail(false);
				player.setRollForDoubleCount(0);
				player.setNumberOfJailFreeCard(player.getNumberOfJailFreeCard()-1);
				setVisible(false);
			}
		});
		// action for "pay 50 $"  button 
		payFiftyButton.addActionListener(new ActionListener() {
			// pay 50 $ and get out of jail
			public void actionPerformed(ActionEvent arg0) {
				player.setInsideJail(false);
				player.setRollForDoubleCount(0);
				player.setBalance(player.getBalance()-50,null);
				setVisible(false);
			}
		});
		
		// action for roll for double button
		rollButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Random random = new Random();
// if 6+6 is found then set player free of jail
				int firstDice = 1 + random.nextInt(6);
				int secondDice = 1 + random.nextInt(6);
				if (firstDice + secondDice == 12) {
					player.setPosition(21);
					player.setInsideJail(false);
					// clear roll for double count
					player.setRollForDoubleCount(0);
				} 
				// increment count for roll for double
				else {
					player.setRollForDoubleCount(player.getRollForDoubleCount() + 1);
					
				}
				setVisible(false);

			}
		});

	}
}
