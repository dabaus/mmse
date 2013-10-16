package app;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class InsurancePane extends JPanel {
	
	private JLabel insuranceDate, insuranceStatus, costOfCar;
	private JLabel insDateLabel, insStatusLabel, costOfCarLabel;
	
	public InsurancePane() {
		super(new GridBagLayout());
		
		createComponents();
		addComponents();
	}
	
	private void createComponents() {
		insStatusLabel = new JLabel("Status:");
		insDateLabel = new JLabel("Valid to:");
		costOfCarLabel = new JLabel("Cost:"); 
		costOfCar = new JLabel();
		insuranceDate = new JLabel();
		insuranceStatus = new JLabel();
	}
	
	private void addComponents() {
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.weightx = 0;
		c.anchor = c.WEST;
		c.insets = new Insets(0, 10, 0, 5);
		add(insStatusLabel, c);
		c.gridx = 1;
		c.gridy = 0;
		c.insets = new Insets(0, 5, 0, 10);
		add(insuranceStatus, c);
		c.gridx = 0;
		c.gridy = 1;
		c.insets = new Insets(5, 10, 0, 5);
		add(insDateLabel, c);
		c.gridx = 1;
		c.gridy = 1;
		c.insets = new Insets(5, 5, 0, 10);
		add(insuranceDate, c);
		c.gridx = 0;
		c.gridy = 2;
		c.insets = new Insets(5, 10, 0, 5);
		add(costOfCarLabel, c);
		c.gridx = 1;
		c.gridy = 2;
		c.insets = new Insets(5, 5, 0, 10);
		add(costOfCar, c);
		c.gridx = 0;
		c.gridy = 3;
		c.gridwidth = 2;
		c.fill = c.BOTH;
		c.weightx = 1;
		c.weighty = 1;
		c.insets = new Insets(0,0,0,0);
		add(new JPanel(), c); // PADDING
	}
	
	public void setInsuranceDate(String string) {
		insuranceDate.setText(string);
	}
	
	public void setInsuranceStatus(String status) {
		insuranceStatus.setText(status);
	}
	
	public void setCostOfCar(String cost) {
		costOfCar.setText(cost);
	}

}
