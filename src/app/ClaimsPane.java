package app;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class ClaimsPane extends JPanel {
	
	private JPanel topPane; 
	private JPanel centerPane;
	private JPanel bottomPane;
	private Claim selectedClaim;
	
	private JButton claimAcceptButton, newClaimButton, claimRejectButton, printClaimButton;
	private JLabel claimDateLabel, claimDate, claimTypeLabel, claimType, descrLabel;
	
	private CreateClaimWindow claimWindow;
	private MainWindow parent;
	private JTextArea descrArea;
	
	public ClaimsPane(MainWindow w) {
		super(new BorderLayout());
		this.parent = w;
		
		topPane =  new JPanel(new GridBagLayout());
		centerPane = new JPanel(new GridBagLayout());
		bottomPane =  new JPanel(new GridBagLayout());
		
		claimDateLabel = new JLabel("Filed: ");
		claimTypeLabel = new JLabel("Type:");
		descrLabel = new JLabel("Description:");
		claimDate = new JLabel();
		claimType = new JLabel();
		descrLabel = new JLabel();
		
		claimAcceptButton = new JButton("Accept");
		claimRejectButton = new JButton("Reject");
		printClaimButton = new JButton("Print");
		newClaimButton = new JButton("New claim");
		descrArea = new JTextArea("");
		JScrollPane descrScroll = new JScrollPane(descrArea);
		
		printClaimButton.setEnabled(false);
		claimAcceptButton.setEnabled(false);
		claimRejectButton.setEnabled(false);
		newClaimButton.setEnabled(false);
		
		descrArea.setEditable(false);
		
		claimAcceptButton.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						selectedClaim.accept();
						parent.getClaimList().repaint();
						claimAcceptButton.setEnabled(false);
						printClaimButton.setEnabled(true);
					}
				}
		);
		
		claimRejectButton.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						selectedClaim.reject();
						parent.getClaimList().repaint();
						claimRejectButton.setEnabled(false);
						printClaimButton.setEnabled(true);
					}
				}
		);
		
		newClaimButton.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Vehicle selectedVehicle = parent.getSelectedVehicle();
						claimWindow = new CreateClaimWindow(selectedVehicle, parent);
					}
				}
		);
		
		printClaimButton.addActionListener( 
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						JOptionPane.showMessageDialog(null, "Claim printed!");
					}
				}
		);
		
		// claims top pane
		GridBagConstraints	c = new GridBagConstraints();
		c.gridx = 0;
		c.weightx = 0;
		c.anchor = c.WEST;
		c.insets = new Insets(0,10,5,0);
		topPane.add(claimDateLabel, c);	
		c.gridx = 1;
		topPane.add(claimDate, c);
		c.gridx = 2;
		c.fill = c.HORIZONTAL;
		c.weightx = 1;
		topPane.add(new JPanel(), c); // PADDING
		c.gridx = 0;
		c.gridy = 1;
		c.weightx = 0;
		c.fill = c.NONE;
		c.anchor = c.WEST;
		c.insets = new Insets(0,10,5,0);
		topPane.add(claimTypeLabel, c);
		c.gridx = 1;
		topPane.add(claimType, c);
					
		// claims center pane
		c.gridx = 0;
		c.gridy = 0;
		c.weightx = 0;
		c.fill = c.NONE;
		c.anchor = c.WEST;
		c.insets = new Insets(0, 10, 0, 0);
		centerPane.add(descrLabel, c);
		c.gridx = 1;
		c.weightx = 1;
		c.fill = c.HORIZONTAL;
		centerPane.add(new JPanel() , c);
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 2;
		c.weighty = 1;
		c.fill = c.BOTH;
		c.insets = new Insets(0, 10, 10, 10);
		centerPane.add(descrScroll, c);
		//Reset
		c.gridwidth = 1;
		c.weighty = 0;
				
		// claims bottom pane
		c.gridx = 0;
		c.weightx = 0;
		c.fill = c.NONE;
		c.anchor = c.WEST;
		c.insets = new Insets(4,2,5,2);
		bottomPane.add(claimAcceptButton, c);
				
		c.gridx = 1;
		c.weightx = 0;
		c.insets = new Insets(4,2,5,2);
		bottomPane.add(claimRejectButton, c);
				
		c.gridx = 2;
		c.weightx = 0;
		c.insets = new Insets(4,2,5,2);
		bottomPane.add(newClaimButton, c);
				
		c.gridx = 3;
		c.weightx = 0;
		c.insets = new Insets(4,2,5,2);
		bottomPane.add(printClaimButton, c);
				
		c.gridx = 4;
		c.fill = c.HORIZONTAL;
		c.weightx = 1;
		bottomPane.add(new JPanel(), c);
				
		// Organize panels
		this.add(topPane, BorderLayout.NORTH);
		this.add(centerPane, BorderLayout.CENTER);
		this.add(bottomPane, BorderLayout.SOUTH);
		
		ClaimSelectionListener cl = new ClaimSelectionListener();
		parent.getClaimList().addListSelectionListener(cl);
	}
	
	private class ClaimSelectionListener implements ListSelectionListener {
		@Override
		public void valueChanged(ListSelectionEvent e) {
			int index;
			Vehicle selectedVehicle = parent.getSelectedVehicle();
			List<Claim> cl = selectedVehicle.getInsurance().getClaims();
			Claim c;
			Date d;
			if(!e.getValueIsAdjusting()) {
				index = parent.getClaimList().getSelectedIndex();
				if(index == -1) {
					claimDate.setText("");
					claimType.setText("");
					claimAcceptButton.setEnabled(false);
					printClaimButton.setEnabled(false);
				} else {
					c = cl.get(index);
					selectedClaim = c;
					d = c.getFileDate();
					Calendar cal = Calendar.getInstance();
					cal.setTime(d);
					Date date = cal.getTime();
					
					claimDate.setText(new SimpleDateFormat("yyyy-MM-dd").format(date));
					claimType.setText((c.isComplex()) ? "Complex": "Simple");
					descrArea.setText(c.getDescription());
					
					if(c.getStatus() == Claim.Status.PENDING){
						printClaimButton.setEnabled(false);
						if(Session.getInstance().getUser().getRank() == User.Rank.LOW){
							if(!c.isComplex()) {
								claimAcceptButton.setEnabled(true);
								claimRejectButton.setEnabled(true);
							} else {
								claimAcceptButton.setEnabled(false);
								claimRejectButton.setEnabled(false);
							}
						} else {
							claimAcceptButton.setEnabled(true);
							claimRejectButton.setEnabled(true);
						}
					} else {
						printClaimButton.setEnabled(true);
						claimAcceptButton.setEnabled(false);
						claimRejectButton.setEnabled(false);
					}					
				}
			}
		}
	}
	
	public void setNewClaimButtonEnabled(boolean enabled) {
		newClaimButton.setEnabled(enabled);
	}
	
	public CreateClaimWindow getClaimWindow() {
		return claimWindow;
	}
	
	public JButton getNewClaimButton() {
		return newClaimButton;
	}

}
