package app;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class CustomerPane extends JPanel {
	
	private JLabel customerAddress, customerName, customerTelNo;
	private JLabel customerNameLabel, customerAddressLabel, customerTelLabel;
	
	public CustomerPane() {
		super(new GridBagLayout());
		this.setBorder(BorderFactory.createTitledBorder("Customer info"));
		
		customerNameLabel = new JLabel("Name: ");
		customerAddressLabel = new JLabel("Address: ");
		customerTelLabel = new JLabel("Tel.nr: ");
		
		customerName = new JLabel();
		customerTelNo = new JLabel();
		customerAddress = new JLabel();

		// Customer info pane
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.weightx = 0;
		c.anchor = c.WEST;
		c.insets = new Insets(0, 10, 5, 5);
		add(customerNameLabel, c);

		c.gridx = 1;
		c.gridy = 0;
		c.weightx = 1;
		c.anchor = c.WEST;
		c.insets = new Insets(0, 10, 5, 5);
		add(customerName, c);

		c.gridx = 0;
		c.gridy = 1;
		c.weightx = 0;
		c.anchor = c.WEST;
		c.insets = new Insets(0, 10, 5, 5);
		add(customerAddressLabel, c);

		c.gridx = 1;
		c.gridy = 1;
		c.weightx = 1;
		c.anchor = c.WEST;
		c.insets = new Insets(0, 10, 5, 5);
		add(customerAddress, c);

		c.gridx = 0;
		c.gridy = 2;
		c.weightx = 0;
		c.anchor = c.WEST;
		c.insets = new Insets(0, 10, 5, 5);
		add(customerTelLabel, c);

		c.gridx = 1;
		c.gridy = 2;
		c.weightx = 1;
		c.anchor = c.WEST;
		c.insets = new Insets(0, 10, 5, 5);
		add(customerTelNo, c);
	}
	
	public void setCustomerName(String name) {
		customerName.setText(name);
	}
	
	public void setCustomerAddress(String address) {
		customerAddress.setText(address);
	}
	
	public void setCustomerTelNo(String telNo) {
		customerTelNo.setText(telNo);
	}

}
