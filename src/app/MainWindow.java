package app;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;

public class MainWindow {
	private JFrame frame;
	private Hashtable<Integer,Customer>  cust;
	private JTable table;
	private JList vList; 
	private String[] columnNames = {"ID","First name", "Last name"};
	private JLabel insuranceStatus;
	private Customer selectedCustomer;
	private Vehicle selectedVehicle;
	private Claim selectedClaim;
	private JList claimList;
	private JFormattedTextField idSearchField;
	private JTextField regSearchField;
	private JLabel claimDate;
	private JTextArea descrArea;
	private JButton claimAcceptButton;
	private JButton newClaimButton;
	private JButton claimRejectButton;
	private JLabel claimType;
	private JLabel insuranceDate;
	private JLabel costOfCar;
	private JLabel customerName;
	private JLabel customerTelNr;
	private JLabel customerAddress;
	
	public MainWindow() {
				
		JLabel claimDateLabel = new JLabel("Filed: ");
		JLabel claimTypeLabel = new JLabel("Type:");
		JLabel insStatusLabel = new JLabel("Status:");
		JLabel insDateLabel = new JLabel("Valid to:");
		JLabel costOfCarLabel = new JLabel("Cost:");
		JPanel rootPane = new JPanel(new BorderLayout());
		JPanel leftPane =  new JPanel(new BorderLayout());
		JPanel rightPane = new JPanel(new BorderLayout());
		JPanel customerPane = new JPanel(new GridBagLayout());
		JPanel claimsPane =  new JPanel(new BorderLayout());
		JPanel claimsTopPane =  new JPanel(new GridBagLayout());
		JPanel claimsCenterPane = new JPanel(new GridBagLayout());
		JPanel claimsBottomPane = new JPanel(new GridBagLayout());
		JPanel bottomPane =  new JPanel(new BorderLayout());
		CustSelectionListener sl = new CustSelectionListener();
		JPanel vListPane = new JPanel(new BorderLayout());
		JPanel insurancePane = new JPanel(new GridBagLayout());
		JPanel claimsListPane = new JPanel(new BorderLayout());
		VehicleSelectionListener vl = new VehicleSelectionListener();
		JPanel topPane = new JPanel(new GridBagLayout());	
		JLabel idSearchLabel = new JLabel("ID:");
		JLabel regSearchLabel = new JLabel("Reg no:");
		ClaimSelectionListener cl = new ClaimSelectionListener();
		JLabel descrLabel = new JLabel("Description:");
		JLabel customerNameLabel = new JLabel("Name: ");
		JLabel customerAddressLabel = new JLabel("Address: ");
		JLabel customerTelLabel = new JLabel("Tel.nr: ");

		
		frame = new JFrame("Car Insurance Company");
		claimDate = new JLabel();
		table = new JTable();
		vList = new JList();
		claimList = new JList();
		insuranceStatus = new JLabel();
		idSearchField = new JFormattedTextField(NumberFormat.getInstance());
		regSearchField = new JTextField();
		descrArea = new JTextArea("");
		JScrollPane descrScroll = new JScrollPane(descrArea);
		claimAcceptButton = new JButton("Accept");
		claimRejectButton = new JButton("Reject");
		newClaimButton = new JButton("New claim");
		claimType = new JLabel();
		insuranceDate = new JLabel();
		costOfCar = new JLabel();
		customerName = new JLabel();
		customerTelNr = new JLabel();
		customerAddress = new JLabel();
		
		JScrollPane scrollPane = new JScrollPane(table,
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, 
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		
		claimAcceptButton.setEnabled(false);
		claimRejectButton.setEnabled(false);
		newClaimButton.setEnabled(false);
		descrArea.setEditable(false);

		// Actions
		idSearchField.addActionListener( 
			new ActionListener() {			
				public void actionPerformed(ActionEvent e) {
					int input = Integer.parseInt(idSearchField.getText());
					int id;
					for(int i=0; i<table.getRowCount(); i++) {
						id = (Integer)table.getValueAt(i, 0);
						if(id == input) {
							table.setRowSelectionInterval(i, i);	
							break;
						}
					}							
				}
			}
		);

		regSearchField.addActionListener( 
				new ActionListener() {			
					public void actionPerformed(ActionEvent e) {
						String input = regSearchField.getText();
						
						Vehicle v = DB.getInstance().loadVehicle(input);
						if(v != null){
							int id = v.getOwner().getId();
							for(int i=0; i<table.getRowCount(); i++) {
								int tmp = (Integer)table.getValueAt(i, 0);
								if(id == tmp) {
									table.setRowSelectionInterval(i, i);	
									break;
								}
							}								
						}
					}
				}
			);
 
		claimAcceptButton.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						selectedClaim.accept();
						claimList.repaint();
						claimAcceptButton.setEnabled(false);
					}
				}
		);
		
		claimRejectButton.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						selectedClaim.reject();
						claimList.repaint();
						claimRejectButton.setEnabled(false);
					}
				}
		);
		
		final MainWindow window = this; // HACK
		newClaimButton.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						new CreateClaimWindow(selectedVehicle, window);
					}
				}
		);
		
		vList.addListSelectionListener(vl);
		claimList.addListSelectionListener(cl);
		
		// Load customers
		DB db = DB.getInstance();
		cust = db.loadCustomers();

		// Setup table
		table.setAutoCreateRowSorter(true);
		table.setModel(new CustTableModel());
		table.setRowSelectionAllowed(true);
		table.getSelectionModel().addListSelectionListener(sl);
		table.getColumnModel().getSelectionModel().addListSelectionListener(sl);

		// Set border
		vListPane.setBorder(BorderFactory.createTitledBorder("Vehicles"));
		insurancePane.setBorder(BorderFactory.createTitledBorder("Insurance"));
		claimsListPane.setBorder(BorderFactory.createTitledBorder("Claims"));
		claimsPane.setBorder(BorderFactory.createTitledBorder("Claim Info"));
		topPane.setBorder(BorderFactory.createTitledBorder("Search"));
		customerPane.setBorder(BorderFactory.createTitledBorder("Customer info"));
		
		// Set dimensions
		idSearchLabel.setPreferredSize(	 new Dimension(20, 20));
		bottomPane.setPreferredSize(	 new Dimension(0,  200));
		vListPane.setPreferredSize(		 new Dimension(100,0));
		insurancePane.setPreferredSize(	 new Dimension(180,0));
		claimsListPane.setPreferredSize( new Dimension(100,0));
		scrollPane.setPreferredSize(	 new Dimension(380,  200));
		idSearchField.setPreferredSize(	 new Dimension(80, 25));
		regSearchField.setPreferredSize( new Dimension(80, 25));
		claimsPane.setPreferredSize(     new Dimension(300,0));
		
		// Set up topPane
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.weightx = 0;
		c.insets =  new Insets(0, 10, 10, 0);
		topPane.add(idSearchLabel, c);
		c.gridx = 1;
		c.insets =  new Insets(0, 0, 10, 0);
		topPane.add(idSearchField, c);
		c.gridx = 2;
		c.insets =  new Insets(0, 10, 10, 0);
		topPane.add(regSearchLabel, c);
		c.gridx = 3;
		c.insets =  new Insets(0, 0, 10, 0);
		topPane.add(regSearchField, c);
		c.gridx = 4;
		c.insets =  new Insets(0,0,0,0);
		c.weightx = 1;
		c.fill = c.HORIZONTAL;
		topPane.add(new JPanel(), c); // PADDING

		
		// insurance pane
		c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.weightx = 0;
		c.anchor = c.WEST;
		c.insets = new Insets(0, 10, 0, 5);
		insurancePane.add(insStatusLabel, c);
		c.gridx = 1;
		c.gridy = 0;
		c.insets = new Insets(0, 5, 0, 10);
		insurancePane.add(insuranceStatus, c);
		c.gridx = 0;
		c.gridy = 1;
		c.insets = new Insets(5, 10, 0, 5);
		insurancePane.add(insDateLabel, c);
		c.gridx = 1;
		c.gridy = 1;
		c.insets = new Insets(5, 5, 0, 10);
		insurancePane.add(insuranceDate, c);
		c.gridx = 0;
		c.gridy = 2;
		c.insets = new Insets(5, 10, 0, 5);
		insurancePane.add(costOfCarLabel, c);
		c.gridx = 1;
		c.gridy = 2;
		c.insets = new Insets(5, 5, 0, 10);
		insurancePane.add(costOfCar, c);
		c.gridx = 0;
		c.gridy = 3;
		c.gridwidth = 2;
		c.fill = c.BOTH;
		c.weightx = 1;
		c.weighty = 1;
		c.insets = new Insets(0,0,0,0);
		insurancePane.add(new JPanel(), c); // PADDING

		// Customer info pane
		c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.weightx = 0;
		c.anchor = c.WEST;
		c.insets = new Insets(0, 10, 5, 5);
		customerPane.add(customerNameLabel, c);
		
		c.gridx = 1;
		c.gridy = 0;
		c.weightx = 1;
		c.anchor = c.WEST;
		c.insets = new Insets(0, 10, 5, 5);
		customerPane.add(customerName, c);
		
		c.gridx = 0;
		c.gridy = 1;
		c.weightx = 0;
		c.anchor = c.WEST;
		c.insets = new Insets(0, 10, 5, 5);
		customerPane.add(customerAddressLabel, c);
		
		c.gridx = 1;
		c.gridy = 1;
		c.weightx = 1;
		c.anchor = c.WEST;
		c.insets = new Insets(0, 10, 5, 5);
		customerPane.add(customerAddress, c);
		
		c.gridx = 0;
		c.gridy = 2;
		c.weightx = 0;
		c.anchor = c.WEST;
		c.insets = new Insets(0, 10, 5, 5);
		customerPane.add(customerTelLabel, c);
		
		c.gridx = 1;
		c.gridy = 2;
		c.weightx = 1;
		c.anchor = c.WEST;
		c.insets = new Insets(0, 10, 5, 5);
		customerPane.add(customerTelNr, c);
		
		
		// claims top pane
		c = new GridBagConstraints();
		c.gridx = 0;
		c.weightx = 0;
		c.anchor = c.WEST;
		c.insets = new Insets(0,10,5,0);
		claimsTopPane.add(claimDateLabel, c);	
		c.gridx = 1;
		claimsTopPane.add(claimDate, c);
		c.gridx = 2;
		c.fill = c.HORIZONTAL;
		c.weightx = 1;
		claimsTopPane.add(new JPanel(), c); // PADDING
		c.gridx = 0;
		c.gridy = 1;
		c.weightx = 0;
		c.fill = c.NONE;
		c.anchor = c.WEST;
		c.insets = new Insets(0,10,5,0);
		claimsTopPane.add(claimTypeLabel, c);
		c.gridx = 1;
		claimsTopPane.add(claimType, c);
			
		// claims center pane
		c.gridx = 0;
		c.gridy = 0;
		c.weightx = 0;
		c.fill = c.NONE;
		c.anchor = c.WEST;
		c.insets = new Insets(0, 10, 0, 0);
		claimsCenterPane.add(descrLabel, c);
		c.gridx = 1;
		c.weightx = 1;
		c.fill = c.HORIZONTAL;
		claimsCenterPane.add(new JPanel() , c);
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 2;
		c.weighty = 1;
		c.fill = c.BOTH;
		c.insets = new Insets(0, 10, 10, 10);
		claimsCenterPane.add(descrScroll, c);
		//Reset
		c.gridwidth = 1;
		c.weighty = 0;
		
		// claims bottom pane
		c.gridx = 0;
		c.weightx = 0;
		c.fill = c.NONE;
		c.anchor = c.WEST;
		c.insets = new Insets(5,5,5,5);
		claimsBottomPane.add(claimAcceptButton, c);
		
		c.gridx = 1;
		c.weightx = 0;
		c.insets = new Insets(5,5,5,5);
		claimsBottomPane.add(claimRejectButton, c);
		
		c.gridx = 2;
		c.weightx = 0;
		c.insets = new Insets(5,5,5,5);
		claimsBottomPane.add(newClaimButton, c);
		
		c.gridx = 3;
		c.fill = c.HORIZONTAL;
		c.weightx = 1;
		claimsBottomPane.add(new JPanel(), c);
		
		// Organize panels
		claimsPane.add(claimsTopPane, BorderLayout.NORTH);
		claimsPane.add(claimsCenterPane, BorderLayout.CENTER);
		claimsPane.add(claimsBottomPane, BorderLayout.SOUTH);
		vListPane.add(vList, BorderLayout.CENTER);
		claimsListPane.add(claimList, BorderLayout.CENTER);
		bottomPane.add(vListPane, BorderLayout.WEST);
		bottomPane.add(insurancePane, BorderLayout.CENTER);
		bottomPane.add(claimsListPane, BorderLayout.EAST);
		
		leftPane.add(topPane, BorderLayout.NORTH);
		leftPane.add(scrollPane,  BorderLayout.CENTER);
		leftPane.add(bottomPane, BorderLayout.SOUTH);

		rightPane.add(claimsPane, BorderLayout.CENTER);
		rightPane.add(customerPane, BorderLayout.NORTH);
		
		// add panels
		rootPane.add(leftPane, BorderLayout.WEST);
		rootPane.add(rightPane, BorderLayout.CENTER);

		// Prepare for display
		frame.addWindowListener(new WindowListener() {
			public void windowClosing(WindowEvent e) {
				Session s = Session.getInstance();
				s.logOut();
			}
			public void windowOpened(WindowEvent e) {}
			public void windowClosed(WindowEvent e) {}
			public void windowIconified(WindowEvent e) {}
			public void windowDeiconified(WindowEvent e) {}
			public void windowActivated(WindowEvent e) {}
			public void windowDeactivated(WindowEvent e) {}
		});
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(rootPane);
		frame.pack();
		Point pt = GraphicsEnvironment.getLocalGraphicsEnvironment().getCenterPoint();
		pt.x -= (frame.getWidth()/2);
		pt.y -= (frame.getHeight()/2);
		frame.setLocation(pt);
		frame.setVisible(true);
	}

	private class CustTableModel extends AbstractTableModel {

		public int getColumnCount() {
			return columnNames.length;
		}

		public int getRowCount() {
			return cust.size();
		}

		public String getColumnName(int col) {
			return columnNames[col];
		}

		public Object getValueAt(int row, int col) {
			Customer c = cust.get(row);
			switch(col) {
			case 0: return c.getId();
			case 1: return c.getFirstName();
			case 2: return c.getLastName();
			default: return null;
			}
		}

		public Class getColumnClass(int c) {
			return getValueAt(0, c).getClass();
		}

		public boolean isCellEditable(int row, int col) {
			return (col == 3);
		}
	}
	

	private class ClaimSelectionListener implements ListSelectionListener {
		@Override
		public void valueChanged(ListSelectionEvent e) {
			int index;
			List<Claim> cl = selectedVehicle.getInsurance().getClaims();
			Claim c;
			Date d;
			if(!e.getValueIsAdjusting()) {
				index = claimList.getSelectedIndex();
				if(index == -1) {
					claimDate.setText("");
					claimType.setText("");
					claimAcceptButton.setEnabled(false);
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
						claimAcceptButton.setEnabled(false);
						claimRejectButton.setEnabled(false);
					}					
				}
			}
		}
	}

	private class VehicleSelectionListener implements ListSelectionListener {
		@Override
		public void valueChanged(ListSelectionEvent e) {
			if (!e.getValueIsAdjusting()) {       		
				vehicleSelection();
			}
		}
	}

	protected void vehicleSelection() {
		int index;
		List<Vehicle> vl;
		Vehicle v;
		Insurance in;
		index = vList.getSelectedIndex();
		if(index == -1) {
			insuranceStatus.setText("");
			insuranceDate.setText("");
			costOfCar.setText("");
			newClaimButton.setEnabled(false);
		} else {
			vl = selectedCustomer.getVehicles();
			v = vl.get(index);
			selectedVehicle = v;
			costOfCar.setText(Integer.toString(v.getPrice()));
			in = v.getInsurance();
			if(in != null) {
				if (in.isActive()) {
					insuranceStatus.setText("Active");
					newClaimButton.setEnabled(true);
				} else {
					insuranceStatus.setText("Inactive");
					newClaimButton.setEnabled(false);
				}
				insuranceDate.setText(new SimpleDateFormat("yyyy-MM-dd").format(in.getValidTo()));
			}				
			DefaultListModel listModel = new DefaultListModel();
			in = v.getInsurance();
			if(in != null){
				List<Claim> claims = in.getClaims();
				for(Claim c: claims){
					listModel.addElement(c);
				}
			}
			claimList.setModel(listModel);			
//			claimList.setSelectedIndex(0);
//			claimList.grabFocus();
		}
	}
	
	
	private class CustSelectionListener implements ListSelectionListener {

		@Override
		public void valueChanged(ListSelectionEvent e) {

			List<Vehicle> vl;
			int index;
			// If cell selection is enabled, both row and column change events are fired
			if (!e.getValueIsAdjusting()) {
				if (e.getSource() == table.getSelectionModel()
						&& table.getRowSelectionAllowed())  {
					System.out.println("Row selected");
					DefaultListModel listModel = new DefaultListModel();
					index = (Integer)table.getValueAt(table.getSelectedRow(), 0);
					selectedCustomer = cust.get(index);
					vl = selectedCustomer.getVehicles();
					for(Vehicle v: vl) {
						listModel.addElement(v);
					}
					vList.setModel(listModel);
					claimList.setModel(new DefaultListModel());
					
					customerName.setText(selectedCustomer.getFirstName() + " " + selectedCustomer.getLastName());
					customerAddress.setText(selectedCustomer.getAddress() + " " 
											+ selectedCustomer.getPostNr() + " " 
											+ selectedCustomer.getOrt());
					customerTelNr.setText(selectedCustomer.getTelNr());
					
//					vList.setSelectedIndex(0);
//					vList.grabFocus();
					
				} else if (e.getSource() == table.getColumnModel().getSelectionModel()
						&&  table.getColumnSelectionAllowed()){
					System.out.println("Column selected");
				}
			}
		}
		public void repaintClaimList() {
			claimList.repaint();
		}
	}
	
	public JTextField getIdSearchField() {
		return idSearchField;
	}
	
	public JTable getTable() {
		return table;
	}
	
	
}