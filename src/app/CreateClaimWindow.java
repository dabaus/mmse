package app;

import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class CreateClaimWindow {

	private JFrame frame;
	private JPanel panel;
	private Date cal = Calendar.getInstance().getTime();
	private JTextArea description = new JTextArea();
	private JTextArea infoArea = new JTextArea();
	private JCheckBox cBox = new JCheckBox("Complex", false);
	private JCheckBox fBox = new JCheckBox("Send form", true);
	private JButton submitButton;
	
	public CreateClaimWindow(final Vehicle vehicle, final MainWindow window){
	
		frame = new JFrame("New claim");
		panel = new JPanel(new GridBagLayout());
		
		frame.add(panel);
		submitButton = new JButton("Submit");
		JLabel descriptionLabel = new JLabel("Description:");
		JScrollPane scrollPane = new JScrollPane(description);
		JLabel dateLabel = new JLabel("Date: ");
		JLabel date = new JLabel(new SimpleDateFormat("yyyy-MM-dd").format(cal));
		JLabel nameLabel = new JLabel("Name: ");
		JLabel name = new JLabel(vehicle.getOwner().toString());
		JLabel regNoLabel = new JLabel("RegNo: ");
		JLabel regNo = new JLabel(vehicle.getRegNo());
		JLabel costLabel = new JLabel("Cost estimate: ");
		JTextField costField = new JTextField(10);
		JLabel infoLabel = new JLabel("Additional form information:");
		JScrollPane infoPane = new JScrollPane(infoArea);
		
		description.setLineWrap(true);
		description.setWrapStyleWord(true);
		
		infoArea.setLineWrap(true);
		infoArea.setWrapStyleWord(true);
		
		submitButton.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Claim c = new Claim(cal, description.getText(), cBox.isSelected());
						c.setStatus(Claim.Status.PENDING);
						vehicle.getInsurance().addClaim(c);
						window.vehicleSelection();
						frame.dispose();
					}
				}
		);
		
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.anchor = c.WEST;
		c.weightx = 0;
		c.insets = new Insets(10,10,10,10);		
		panel.add(nameLabel, c);
		
		c.gridx = 1;
		c.gridy = 0;
		c.weightx = 0;
		c.insets = new Insets(10,10,10,10);		
		panel.add(name, c);
		
		c.gridx = 2;
		c.gridy = 0;
		c.anchor = c.EAST;
		c.weightx = 1;
		c.insets = new Insets(10,10,10,5);		
		panel.add(dateLabel, c);
		
		c.gridx = 3;
		c.gridy = 0;
		c.anchor = c.WEST;
		c.weightx = 1;
		c.insets = new Insets(10,5,10,0);		
		panel.add(date, c);
		
		c.gridx = 0;
		c.gridy = 1;
		c.anchor = c.WEST;
		c.weightx = 0;
		c.insets = new Insets(0,10,5,10);		
		panel.add(regNoLabel, c);
		
		c.gridx = 1;
		c.gridy = 1;
		c.weightx = 1;
		c.insets = new Insets(0,10,5,10);		
		panel.add(regNo, c);
		
		c.gridx = 0;
		c.gridy = 2;
		c.weightx = 1;
		c.insets = new Insets(5,10,5,10);	
		panel.add(descriptionLabel, c);
		
		c.gridx = 3;
		c.gridy = 2;
		c.weightx = 0;
		c.anchor = c.WEST;
		c.insets = new Insets(0, 0, 0, 0);
		panel.add(cBox, c);
		
		c.gridx = 0;
		c.gridy = 4;
		c.weightx = 1;
		c.weighty = 1;
		c.fill = c.BOTH;
		c.gridwidth = 4;
		c.insets = new Insets(5,10,5,10);	
		panel.add(scrollPane, c);
		
		c.gridx = 0;
		c.gridy = 5;
		c.weightx = 0;
		c.weighty = 0;
		c.fill = c.NONE;	
		c.insets = new Insets(5,10,5,5);	
		panel.add(costLabel, c);
		
		c.gridx = 1;
		c.gridy = 5;
		c.weightx = 1;
		c.insets = new Insets(5,5,5,10);	
		panel.add(costField, c);
		
		
		c.gridx = 0;
		c.gridy = 6;
		c.insets = new Insets(5, 10, 5, 10);
		panel.add(infoLabel, c);
		
		c.gridx = 0;
		c.gridy = 7;
		c.weightx = 1;
		c.weighty = 1;
		c.fill = c.BOTH;
		c.gridwidth = 4;
		c.insets = new Insets(5,10,5,10);	
		panel.add(infoPane, c);
				
		c.gridx = 0;
		c.gridy = 8;
		c.weightx = 0;
		c.weighty = 0;
		c.fill = c.NONE;
		c.insets = new Insets(5,10,10,10);
		panel.add(submitButton, c);
		
		c.gridx = 3;
		c.gridy = 6;
		c.weightx = 0;
		c.anchor = c.WEST;
		c.insets = new Insets(0, 0, 0, 0);
		panel.add(fBox, c);
		
		frame.setPreferredSize(new Dimension(450,450));
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.pack();
		Point pt = GraphicsEnvironment.getLocalGraphicsEnvironment().getCenterPoint();
		pt.x -= (frame.getWidth()/2);
		pt.y -= (frame.getHeight()/2);
		frame.setLocation(pt);
		frame.setVisible(true);
	}
	
	public JTextArea getDescription() {
		return description;
	}
	
	public JButton getSubmitButton() {
		return submitButton;
	}
}
