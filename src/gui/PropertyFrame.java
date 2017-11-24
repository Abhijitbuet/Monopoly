package gui;



import javax.swing.*;

import monopoly.Square;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PropertyFrame extends JFrame {

	
	private static final long serialVersionUID = 1L;

// constructor using square object and image file name
	public PropertyFrame(Square square, String imageFileName) {
		// initialize design 
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 375, 450);
		// initialize image label
		JLabel imageLabel = new JLabel();
		imageLabel.setBounds(10, 10, 286, 337);
		imageLabel.setIcon(new ImageIcon(imageFileName));		
		getContentPane().setLayout(null);
		getContentPane().add(imageLabel);
		
		// initialize ok button
		JButton okButton  = new JButton("Ok");
		okButton.setIcon(new ImageIcon("images/buttons/okbutton.png"));
		okButton.setBounds(160,355,90,30);
		getContentPane().add(okButton);
		setVisible(true);
		// action for ok button
		// if pressed then close this frame
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
			}
		});
		
	}
}
