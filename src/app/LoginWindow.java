package app	;

import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class LoginWindow {

	private class LoginListener implements ActionListener {
		JFrame parent;
		LoginListener(JFrame parent) {
			this.parent = parent;
		}
		public void actionPerformed(ActionEvent e) {
			if (Session.logIn(userField.getText(), pswField.getText()) == null) {
				JOptionPane.showMessageDialog(parent,
					    "Login failed, invalid credentials.",
					    "Login Error",
					    JOptionPane.ERROR_MESSAGE);
				userField.setText("");
				pswField.setText("");
			} else {
				parent.setVisible(false);
				parent.dispose();
				new MainWindow();
			} 
		}
	}
	
	private JFrame frame;
	public JTextField userField;
	public JTextField pswField;
	public JButton loginButton;
	
	public LoginWindow() {
		JPanel pane;
		JLabel userLabel;
		JLabel pswLabel;
		JButton cancelButton;
		GridBagConstraints c;
		Dimension d;
		Point p;
		
		frame = new JFrame("Login");
		userField = new JTextField();
		pswField = new JTextField();
		userLabel = new JLabel("Username:");
		pswLabel  = new JLabel("Password:");
		pane = new JPanel(new GridBagLayout());
		loginButton = new JButton("Login");
		cancelButton = new JButton("Cancel");
		
		//Listeners
		loginButton.addActionListener(new LoginListener(frame));
		
		cancelButton.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						System.exit(0);
					}
				}	
		);
		
		userField.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						pswField.requestFocusInWindow();
					}
				}
		);
		
		pswField.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						loginButton.doClick();
					}
				}
		);

		// Set dimensions
		d = new Dimension(100, 26);
		userField.setPreferredSize(d);
		pswField.setPreferredSize(d);
//		d = new Dimension(60, 26);
//		loginButton.setPreferredSize(d);
//		cancelButton.setPreferredSize(d);
		
		// Add to panel
		c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.insets =  new Insets(10,10,5,0);
		pane.add(userLabel, c);
		
		c.gridx = 0;
		c.gridy = 1;
		c.insets =  new Insets(5,10,5,0);
		pane.add(pswLabel, c);
		
		c.gridx = 1;
		c.gridy = 0;
		c.insets =  new Insets(10,5,5,10);
		pane.add(userField, c);		
		 
		c.gridx = 1;
		c.gridy = 1;
		c.insets =  new Insets(5,5,5,10);
		pane.add(pswField, c);
		
		c.gridx = 0;
		c.gridy = 2;
		c.insets =  new Insets(5,10,10,0);
		pane.add(cancelButton, c);
		
		c.gridx = 1;
		c.gridy = 2;
		c.insets =  new Insets(5,10,10,10);
		pane.add(loginButton, c);
		
		// Some convenient default values.
		userField.setText("user");
		pswField.setText("password");
		// Prepare for display
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(pane);
		frame.pack();
		p = GraphicsEnvironment.getLocalGraphicsEnvironment().getCenterPoint();
		p.x -= (frame.getWidth()/2);
		p.y -= (frame.getHeight()/2);
		frame.setLocation(p);
		frame.setVisible(true);
		
	}
	
	public JTextField getPswField() {
		return pswField;
	}
	
	public JTextField getNameField() {
		return userField;
	}
	
	public JButton getLoginButton() {
		return loginButton;
	}
}
