package gui;



import javax.swing.*;

import monopoly.Square;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class HelpGUI extends JFrame {

	
	private static final long serialVersionUID = 1L;

	// constructor using square object and image file name
	public HelpGUI(String content) {
		// initialize design 
	//	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 675,550);
		// initialize image label
		JTextArea display = new JTextArea(content);
	    display.setEditable(false); // set textArea non-editable
		JScrollPane   scroll = new JScrollPane(display);
		  scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

		    //Add Textarea in to middle panel
		  getContentPane().add(scroll);
		
		// initialize ok button
//		JButton okButton  = new JButton("Ok");
//		okButton.setIcon(new ImageIcon("images/buttons/okbutton.png"));
//		okButton.setBounds(160,355,90,30);
//		getContentPane().add(okButton);
		setVisible(true);
		// action for ok button
		// if pressed then close this frame
//		okButton.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent arg0) {
//				setVisible(false);
//			}
//		});
//		
	}
}
