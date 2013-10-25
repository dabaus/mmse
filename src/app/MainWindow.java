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
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
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
	private JList vList, claimList;
	private String[] columnNames = {"ID","First name", "Last name"};
	private Customer selectedCustomer;
	private Vehicle selectedVehicle;
	private JFormattedTextField idSearchField;
	private JTextField regSearchField;
	private JMenuBar menuBar;
	private JMenu fileMenu;
	private JMenuItem logOutMenuItem;
	private JMenuItem exitMenuItem;
	
	private ClaimsPane claimsPane;
	private CustomerPane customerPane;
	private InsurancePane insurancePane;
	private JPanel rootPane, leftPane, rightPane, bottomPane, topPane;
	private JLabel  idSearchLabel, regSearchLabel;
	private JPanel vListPane, claimsListPane;
	private JScrollPane scrollPane;
	
	public MainWindow() {
		
		rootPane = new JPanel(new BorderLayout());
		leftPane =  new JPanel(new BorderLayout());
		rightPane = new JPanel(new BorderLayout());
		bottomPane =  new JPanel(new BorderLayout());
		topPane = new JPanel(new GridBagLayout());
		
		CustSelectionListener sl = new CustSelectionListener();
		vListPane = new JPanel(new BorderLayout());
		claimsListPane = new JPanel(new BorderLayout());
		VehicleSelectionListener vl = new VehicleSelectionListener();	
		
		idSearchLabel = new JLabel("ID:");
		regSearchLabel = new JLabel("Reg no:");

		
		frame = new JFrame("Car Insurance Company");
		table = new JTable();
		vList = new JList();
		claimList = new JList();
		idSearchField = new JFormattedTextField(NumberFormat.getInstance());
		regSearchField = new JTextField();
		
		claimsPane =  new ClaimsPane(this);
		customerPane = new CustomerPane();
		insurancePane = new InsurancePane();
		
		scrollPane = new JScrollPane(table,
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, 
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		
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
		
		// Set dimensions
		idSearchLabel.setPreferredSize(	 new Dimension(20, 20));
		bottomPane.setPreferredSize(	 new Dimension(0,  200));
		vListPane.setPreferredSize(		 new Dimension(100,0));
		insurancePane.setPreferredSize(	 new Dimension(180,0));
		claimsListPane.setPreferredSize( new Dimension(100,0));
		scrollPane.setPreferredSize(	 new Dimension(380,  200));
		idSearchField.setPreferredSize(	 new Dimension(80, 25));
		regSearchField.setPreferredSize( new Dimension(80, 25));
		claimsPane.setPreferredSize(     new Dimension(385,0));
		
		setupTopPane();
		setupBottomPane();
		setupRightPane();
		setupLeftPane();
		
		vListPane.add(vList, BorderLayout.CENTER);
		claimsListPane.add(claimList, BorderLayout.CENTER);
		
		// add panels
		rootPane.add(leftPane, BorderLayout.WEST);
		rootPane.add(rightPane, BorderLayout.CENTER);
		
		// add menu
		menuBar = new JMenuBar();
		fileMenu = new JMenu("File");
		logOutMenuItem = new JMenuItem("Log out");
		exitMenuItem = new JMenuItem("Exit");
		menuBar.add(fileMenu);
		fileMenu.add(logOutMenuItem);
		fileMenu.addSeparator();
		fileMenu.add(exitMenuItem);
		frame.setJMenuBar(menuBar);
		
		addActionListeners();
		vList.addListSelectionListener(vl);
		
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

	private void setupTopPane() {
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
	}
	
	private void setupRightPane() {
		rightPane.add(claimsPane, BorderLayout.CENTER);
		rightPane.add(customerPane, BorderLayout.NORTH);
	}
	
	private void setupLeftPane() {
		leftPane.add(topPane, BorderLayout.NORTH);
		leftPane.add(scrollPane,  BorderLayout.CENTER);
		leftPane.add(bottomPane, BorderLayout.SOUTH);
	}
	
	private void setupBottomPane() {
		bottomPane.add(vListPane, BorderLayout.WEST);
		bottomPane.add(insurancePane, BorderLayout.CENTER);
		bottomPane.add(claimsListPane, BorderLayout.EAST);
	}
	
	private void addActionListeners() {
		
		logOutMenuItem.addActionListener(
				new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							Session.getInstance().logOut();
							MainWindow.this.frame.setVisible(false);;
							MainWindow.this.frame.dispose();
							new LoginWindow();
						}
				}
		);
		
		exitMenuItem.addActionListener(
				new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						Session.getInstance().logOut();
						System.exit(0);
					}
				}
		);
		
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
			insurancePane.setInsuranceStatus("");
			insurancePane.setInsuranceDate("");
			insurancePane.setCostOfCar("");
			claimsPane.setNewClaimButtonEnabled(false);
		} else {
			vl = selectedCustomer.getVehicles();
			v = vl.get(index);
			selectedVehicle = v;
			insurancePane.setCostOfCar(Integer.toString(v.getPrice()));
			in = v.getInsurance();
			if(in != null) {
				if (in.isActive()) {
					insurancePane.setInsuranceStatus("Active");
					claimsPane.setNewClaimButtonEnabled(true);
				} else {
					insurancePane.setInsuranceStatus("Inactive");
					claimsPane.setNewClaimButtonEnabled(false);
				}
				insurancePane.setInsuranceDate(new SimpleDateFormat("yyyy-MM-dd").format(in.getValidTo()));
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
					
					customerPane.setCustomerName(selectedCustomer.getFirstName() + " " + selectedCustomer.getLastName());
					customerPane.setCustomerAddress(selectedCustomer.getAddress() + " " 
											+ selectedCustomer.getPostNr() + " " 
											+ selectedCustomer.getCity());
					customerPane.setCustomerTelNo(selectedCustomer.getTelNr());
					
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

	public JList getvList() {
		return vList;
	}
	
	public ClaimsPane getClaimsPane() {
		return claimsPane;
	}
	
	public JList getClaimList() {
		return claimList;
	}
	
	public Vehicle getSelectedVehicle() {
		return selectedVehicle;
	}
}