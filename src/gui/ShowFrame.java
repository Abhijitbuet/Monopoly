package gui;



import javax.swing.*;

import monopoly.Square;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ShowFrame extends JFrame {

	
	private static final long serialVersionUID = 1L;
	

	public ShowFrame(Square square, String imageFileName) {
		// initialize frame design and image label
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 425, 370);
		JLabel imageLabel = new JLabel();
		imageLabel.setBounds(20, 10, 350, 300);
		imageLabel.setIcon(new ImageIcon(imageFileName));
		getContentPane().setLayout(null);
		getContentPane().add(imageLabel);
		
		// initialize ok button
		JButton okButton  = new JButton("Ok");
		okButton.setIcon(new ImageIcon("images/buttons/okbutton.png"));
		okButton.setBounds(180,290,90,30);
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
