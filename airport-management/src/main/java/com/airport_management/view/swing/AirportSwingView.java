package com.airport_management.view.swing;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.border.EmptyBorder;

import com.airport_management.view.FlightView;
import com.airport_management.view.PlaneView;
import com.airport_management.controller.FlightController;
import com.airport_management.controller.PlaneController;
import com.airport_management.model.Flight;
import com.airport_management.model.Plane;

import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SpinnerDateModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;


public class AirportSwingView extends JFrame implements PlaneView, FlightView{

	private static final long serialVersionUID = 1L;

	private transient PlaneController planeController;
	private transient FlightController flightController;
	
	private JLayeredPane layeredPane;
	private JPanel contentPane;
	private JPanel panel1;
	private JPanel panel2;

	private DefaultListModel<Plane> listPlanesModel;
	private DefaultListModel<Flight> listFlightsModel;
	
	private JList<Plane> listPlanes;
	private JList<Flight> listFlights;

	private JScrollPane scrollPane;
	private JScrollPane scrollPane1;

	private JTextField txtModel;
	private JTextField txtOrigin;
	private JTextField txtDestination;
	
	private JLabel lblErrorMessage;
	private JLabel lblModel;
	private JLabel lblOrigin;
	private JLabel lblErrorMessageFlight;
	
	private JButton btnAdd;
	private JButton btnPlanePanel;
	private JButton btnFlightPanel;
	private JButton btnAddFlight;
	private JButton btnDeleteSelected2;
	
	private JSpinner spinnerDepartureDate;
	private JSpinner spinnerArrivalDate;
	private JSpinner spinnerSearchByDepartureDateStart;
	private JSpinner spinnerSearchByDepartureDateEnd;
	private JSpinner spinnerSearchByArrivalDateStart;
	private JSpinner spinnerSearchByArrivalDateEnd;
	
	private JComboBox<String> comboBox;
	private JComboBox<String> comboBoxSearch;
	
	private transient List<Plane> planes = new ArrayList<>();
	private JLabel lblPlane;
	private JLabel lblDestination;
	private JLabel lblDepartureDate;
	private JButton btnDeleteSelected;
	


	public JComboBox<String> getComboBox() {
		return comboBox;
	}
	
	public JComboBox<String> getComboBoxSearch() {
		return comboBoxSearch;
	}
	
	public JSpinner getSpinnerDepartureDate() {
		return spinnerDepartureDate;
	}
	
	public JSpinner getSpinnerArrivalDate() {
		return spinnerArrivalDate;
	}
	
	public JSpinner getSpinnerSearchByDepartureDateStart() {
		return spinnerSearchByDepartureDateStart;
	}
	
	public JSpinner getSpinnerSearchByDepartureDateEnd() {
		return spinnerSearchByDepartureDateEnd;
	}
	
	public JSpinner getSpinnerSearchByArrivalDateStart() {
		return spinnerSearchByArrivalDateStart;
	}
	
	public JSpinner getSpinnerSearchByArrivalDateEnd() {
		return spinnerSearchByArrivalDateEnd;
	}
	
	
	DefaultListModel<Plane> getListPlaneModel() {
		return listPlanesModel;
	}
	
	DefaultListModel<Flight> getListFlightsModel() {
		return listFlightsModel;
	}

	
	public void setAirportController(PlaneController planeController, FlightController flightController) {
		this.planeController = planeController;
		this.flightController = flightController;
	}

	
	public void switchPanels(JPanel panel) {
		layeredPane.removeAll();
		layeredPane.add(panel);
		layeredPane.repaint();
		layeredPane.revalidate();
	}
	
	
	
	/**
	 * Create the frame.
	 * @throws ParseException 
	 */

	public AirportSwingView() throws ParseException {

		setTitle("Airport Controller");
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 982, 817);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{54, 124, 124, 127, 122, 404, 49, 0};
		gbl_contentPane.rowHeights = new int[]{25, 35, 708, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);

		
		//setup of spinner date
		Calendar cal = Calendar.getInstance();
		
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		Date oneSecondBeforeMidnight = cal.getTime();
		
		cal.add(Calendar.DAY_OF_MONTH, 1);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		Date tomorrow = cal.getTime();

		cal.add(Calendar.YEAR, 1); 
		Date nextYear = cal.getTime();
		
		
		
		
		
		/*
		 * ########### switch panel #############
		 * 
		 */
		
		//button plane panel
		btnPlanePanel = new JButton("Plane Panel");
		btnPlanePanel.addActionListener(arg0 -> switchPanels(panel1));
		GridBagConstraints gbc_btnPlanePanel = new GridBagConstraints();
		gbc_btnPlanePanel.anchor = GridBagConstraints.NORTH;
		gbc_btnPlanePanel.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnPlanePanel.insets = new Insets(0, 0, 5, 5);
		gbc_btnPlanePanel.gridx = 1;
		gbc_btnPlanePanel.gridy = 0;
		contentPane.add(btnPlanePanel, gbc_btnPlanePanel);
		
		
		//button flight panel
		btnFlightPanel = new JButton("Flight Panel");
		btnFlightPanel.addActionListener(arg0 -> switchPanels(panel2));
		GridBagConstraints gbc_btnFlightPanel = new GridBagConstraints();
		gbc_btnFlightPanel.anchor = GridBagConstraints.NORTHWEST;
		gbc_btnFlightPanel.insets = new Insets(0, 0, 5, 5);
		gbc_btnFlightPanel.gridx = 2;
		gbc_btnFlightPanel.gridy = 0;
		contentPane.add(btnFlightPanel, gbc_btnFlightPanel);
		
		
		//button flight panel add action combo box options
		btnFlightPanel.addActionListener(arg0 -> {	
			planes = planeController.returnAllPlanes();	
			comboBox.removeAllItems();
			for(int i = 0; i < planes.size(); i++)
				comboBox.addItem(planes.get(i).getId());
			comboBox.setSelectedIndex(-1);

		});		
		
		
		//layered pane
		layeredPane = new JLayeredPane();
		GridBagConstraints gbc_layeredPane = new GridBagConstraints();
		gbc_layeredPane.insets = new Insets(0, 0, 0, 5);
		gbc_layeredPane.fill = GridBagConstraints.BOTH;
		gbc_layeredPane.gridwidth = 5;
		gbc_layeredPane.gridx = 1;
		gbc_layeredPane.gridy = 2;
		contentPane.add(layeredPane, gbc_layeredPane);
		layeredPane.setLayout(new CardLayout(0, 0));
		

		
		
		
		/*
		 * ########### plane panel ############ 
		 * 
		 */
				
		//panel 1
		panel1 = new JPanel();
		panel1.setName("panel1");
		layeredPane.add(panel1, "name_518306635232");


		//for activate Add plane button
		KeyAdapter btnAddEnabler = new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				btnAdd.setEnabled(
					!txtModel.getText().trim().isEmpty() 
				);
			}
		};
		GridBagLayout gbl_panel1 = new GridBagLayout();
		gbl_panel1.columnWidths = new int[]{101, 78, 34, 104, 56, 555, 0};
		gbl_panel1.rowHeights = new int[]{19, 19, 19, 15, 25, 25, 433, 25, 15, 0};
		gbl_panel1.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel1.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel1.setLayout(gbl_panel1);
		
		
		//model label
		lblModel = new JLabel("model");
		GridBagConstraints gbc_lblModel = new GridBagConstraints();
		gbc_lblModel.anchor = GridBagConstraints.EAST;
		gbc_lblModel.insets = new Insets(0, 0, 5, 5);
		gbc_lblModel.gridx = 0;
		gbc_lblModel.gridy = 0;
		panel1.add(lblModel, gbc_lblModel);
		
		
		//model text
		txtModel = new JTextField();
		txtModel.setName("modelTextBox");
		txtModel.addKeyListener(btnAddEnabler);
		GridBagConstraints gbc_txtModel = new GridBagConstraints();
		gbc_txtModel.anchor = GridBagConstraints.NORTH;
		gbc_txtModel.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtModel.insets = new Insets(0, 0, 5, 0);
		gbc_txtModel.gridwidth = 5;
		gbc_txtModel.gridx = 1;
		gbc_txtModel.gridy = 0;
		panel1.add(txtModel, gbc_txtModel);
		txtModel.setColumns(10);
				
				
		//button delete plane
		btnDeleteSelected = new JButton("Delete Selected");
		btnDeleteSelected.setEnabled(false);
		btnDeleteSelected.addActionListener(
				e -> planeController.deletePlane(listPlanes.getSelectedValue()));
		GridBagConstraints gbc_btnDeleteSelected = new GridBagConstraints();
		gbc_btnDeleteSelected.anchor = GridBagConstraints.NORTHWEST;
		gbc_btnDeleteSelected.insets = new Insets(0, 0, 5, 0);
		gbc_btnDeleteSelected.gridx = 5;
		gbc_btnDeleteSelected.gridy = 7;
		panel1.add(btnDeleteSelected, gbc_btnDeleteSelected);	
		
		
		//button add plane
		btnAdd = new JButton("Add");
		btnAdd.setEnabled(false);
		GridBagConstraints gbc_btnAdd = new GridBagConstraints();
		gbc_btnAdd.anchor = GridBagConstraints.NORTHWEST;
		gbc_btnAdd.insets = new Insets(0, 0, 5, 0);
		gbc_btnAdd.gridx = 5;
		gbc_btnAdd.gridy = 1;
		panel1.add(btnAdd, gbc_btnAdd);
		btnAdd.addActionListener(
				e -> planeController.newPlane(
						new Plane(txtModel.getText()))); 
				
		
		//list flights
		listPlanesModel = new DefaultListModel<>();
		//scroll list flights
		scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridheight = 5;
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPane.gridwidth = 6;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 2;
		panel1.add(scrollPane, gbc_scrollPane);
		listPlanes = new JList<>(listPlanesModel);
		listPlanes.addListSelectionListener(
				e -> btnDeleteSelected.setEnabled(listPlanes.getSelectedIndex() != -1));
		listPlanes.setName("planeList");
		scrollPane.setViewportView(listPlanes);
		listPlanes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		
		//error message
		lblErrorMessage = new JLabel(" ");
		lblErrorMessage.setForeground(Color.RED);
		lblErrorMessage.setName("errorMessageLabel");
		GridBagConstraints gbc_lblErrorMessage = new GridBagConstraints();
		gbc_lblErrorMessage.anchor = GridBagConstraints.NORTH;
		gbc_lblErrorMessage.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblErrorMessage.gridwidth = 6;
		gbc_lblErrorMessage.gridx = 0;
		gbc_lblErrorMessage.gridy = 8;
		panel1.add(lblErrorMessage, gbc_lblErrorMessage);
				
				
		
		
		
		
		/*
		 * ########### flight panel ###########
		 * 
		 */

		//panel 2
		panel2 = new JPanel();
		panel2.setName("panel2");
		layeredPane.add(panel2, "name_520876488608");


		//for activate Add flight button
		KeyAdapter btnAddFlightEnabler = new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				btnAddFlight.setEnabled(
					!txtOrigin.getText().trim().isEmpty() &&
					!txtDestination.getText().trim().isEmpty() &&
					comboBox.getSelectedItem() != null 	
				);
			}
		};
		GridBagLayout gbl_panel2 = new GridBagLayout();
		gbl_panel2.columnWidths = new int[]{101, 78, 34, 104, 56, 526, 0};
		gbl_panel2.rowHeights = new int[]{19, 19, 19, 15, 25, 25, 433, 25, 15, 0};
		gbl_panel2.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel2.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel2.setLayout(gbl_panel2);
		
		
		//plane label
		lblPlane = new JLabel("plane");
		GridBagConstraints gbc_lblPlane = new GridBagConstraints();
		gbc_lblPlane.insets = new Insets(0, 0, 5, 5);
		gbc_lblPlane.anchor = GridBagConstraints.EAST;
		gbc_lblPlane.gridx = 0;
		gbc_lblPlane.gridy = 0;
		panel2.add(lblPlane, gbc_lblPlane);


		//plane id combo box
		comboBox = new JComboBox<>();
		comboBox.setName("planeComboBox");
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.fill = GridBagConstraints.BOTH;
		gbc_comboBox.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox.gridwidth = 3;
		gbc_comboBox.gridx = 1;
		gbc_comboBox.gridy = 0;
		panel2.add(comboBox, gbc_comboBox);
		comboBox.addKeyListener(btnAddFlightEnabler);
		comboBox.setBackground(Color.WHITE);

		
		//origin label
		lblOrigin = new JLabel("origin");
		GridBagConstraints gbc_lblOrigin = new GridBagConstraints();
		gbc_lblOrigin.anchor = GridBagConstraints.EAST;
		gbc_lblOrigin.insets = new Insets(0, 0, 5, 5);
		gbc_lblOrigin.gridx = 0;
		gbc_lblOrigin.gridy = 1;
		panel2.add(lblOrigin, gbc_lblOrigin);


		//origin text
		txtOrigin = new JTextField();
		txtOrigin.setName("originTextBox");
		txtOrigin.addKeyListener(btnAddFlightEnabler);
		GridBagConstraints gbc_txtOrigin = new GridBagConstraints();
		gbc_txtOrigin.anchor = GridBagConstraints.NORTH;
		gbc_txtOrigin.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtOrigin.insets = new Insets(0, 0, 5, 0);
		gbc_txtOrigin.gridwidth = 5;
		gbc_txtOrigin.gridx = 1;
		gbc_txtOrigin.gridy = 1;
		panel2.add(txtOrigin, gbc_txtOrigin);
		txtOrigin.setColumns(10);


		//destination label		
		lblDestination = new JLabel("destination");
		GridBagConstraints gbc_lblDestination = new GridBagConstraints();
		gbc_lblDestination.insets = new Insets(0, 0, 5, 5);
		gbc_lblDestination.anchor = GridBagConstraints.EAST;
		gbc_lblDestination.gridx = 0;
		gbc_lblDestination.gridy = 2;
		panel2.add(lblDestination, gbc_lblDestination);
		
		
		//destination text
		txtDestination = new JTextField();
		txtDestination.setName("destinationTextBox");
		txtDestination.addKeyListener(btnAddFlightEnabler);
		GridBagConstraints gbc_txtDestination = new GridBagConstraints();
		gbc_txtDestination.anchor = GridBagConstraints.NORTH;
		gbc_txtDestination.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtDestination.insets = new Insets(0, 0, 5, 0);
		gbc_txtDestination.gridwidth = 5;
		gbc_txtDestination.gridx = 1;
		gbc_txtDestination.gridy = 2;
		panel2.add(txtDestination, gbc_txtDestination);
		txtDestination.setColumns(10);
		
		
		//departure date label
		lblDepartureDate = new JLabel("departure date");
		GridBagConstraints gbc_lblDepartureDate = new GridBagConstraints();
		gbc_lblDepartureDate.insets = new Insets(0, 0, 5, 5);
		gbc_lblDepartureDate.gridx = 0;
		gbc_lblDepartureDate.gridy = 3;
		panel2.add(lblDepartureDate, gbc_lblDepartureDate);
		
		
		//arrival date label
		JLabel lblArrivalDate = new JLabel("arrival date");
		GridBagConstraints gbc_lblArrivalDate = new GridBagConstraints();
		gbc_lblArrivalDate.anchor = GridBagConstraints.NORTH;
		gbc_lblArrivalDate.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblArrivalDate.insets = new Insets(0, 0, 5, 5);
		gbc_lblArrivalDate.gridx = 3;
		gbc_lblArrivalDate.gridy = 3;
		panel2.add(lblArrivalDate, gbc_lblArrivalDate);
		
		
		//departure date spinner
		spinnerDepartureDate = new JSpinner();
		spinnerDepartureDate.setName("spinnerDepartureDate");
		spinnerDepartureDate.setModel(new SpinnerDateModel(tomorrow, oneSecondBeforeMidnight, nextYear, Calendar.DAY_OF_MONTH));
		GridBagConstraints gbc_spinnerDepartureDate = new GridBagConstraints();
		gbc_spinnerDepartureDate.fill = GridBagConstraints.BOTH;
		gbc_spinnerDepartureDate.insets = new Insets(0, 0, 5, 5);
		gbc_spinnerDepartureDate.gridwidth = 2;
		gbc_spinnerDepartureDate.gridx = 0;
		gbc_spinnerDepartureDate.gridy = 4;
		panel2.add(spinnerDepartureDate, gbc_spinnerDepartureDate);
		
		
		//arrival date spinner
		spinnerArrivalDate = new JSpinner();
		spinnerArrivalDate.setName("spinnerArrivalDate");
		spinnerArrivalDate.setModel( new SpinnerDateModel(tomorrow, oneSecondBeforeMidnight, nextYear, Calendar.DAY_OF_MONTH));
		GridBagConstraints gbc_spinnerArrivalDate = new GridBagConstraints();
		gbc_spinnerArrivalDate.anchor = GridBagConstraints.WEST;
		gbc_spinnerArrivalDate.fill = GridBagConstraints.VERTICAL;
		gbc_spinnerArrivalDate.insets = new Insets(0, 0, 5, 0);
		gbc_spinnerArrivalDate.gridwidth = 3;
		gbc_spinnerArrivalDate.gridx = 3;
		gbc_spinnerArrivalDate.gridy = 4;
		panel2.add(spinnerArrivalDate, gbc_spinnerArrivalDate);
		
				
		//button delete flight
		btnDeleteSelected2 = new JButton("Delete Selected");
		btnDeleteSelected2.setEnabled(false);
		btnDeleteSelected2.addActionListener(
				e -> flightController.deleteFlight(listFlights.getSelectedValue()));
				
						
		//button add flight
		btnAddFlight = new JButton("Add");
		btnAddFlight.setEnabled(false);
		GridBagConstraints gbc_btnAddFlight = new GridBagConstraints();
		gbc_btnAddFlight.anchor = GridBagConstraints.NORTHWEST;
		gbc_btnAddFlight.insets = new Insets(0, 0, 5, 0);
		gbc_btnAddFlight.gridx = 5;
		gbc_btnAddFlight.gridy = 5;
		panel2.add(btnAddFlight, gbc_btnAddFlight);
		btnAddFlight.addActionListener(
				e -> flightController.newFlight(
						new Flight(
								(Date)spinnerDepartureDate.getValue(),
								(Date)spinnerArrivalDate.getValue(),
								txtOrigin.getText(), 
								txtDestination.getText(), 
								planeController.idPlane((String)comboBox.getSelectedItem())))); 
				
		
		//list flights
		listFlightsModel = new DefaultListModel<>();
		//scroll list flights
		scrollPane1 = new JScrollPane();
		GridBagConstraints gbc_scrollPane_1 = new GridBagConstraints();
		gbc_scrollPane_1.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_1.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPane_1.gridwidth = 6;
		gbc_scrollPane_1.gridx = 0;
		gbc_scrollPane_1.gridy = 6;
		panel2.add(scrollPane1, gbc_scrollPane_1);
		listFlights = new JList<>(listFlightsModel);
		listFlights.addListSelectionListener(
				e -> btnDeleteSelected2.setEnabled(listFlights.getSelectedIndex() != -1));
		listFlights.setName("flightsList");
		scrollPane1.setViewportView(listFlights);
		listFlights.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		GridBagConstraints gbc_btnDeleteSelected2 = new GridBagConstraints();
		gbc_btnDeleteSelected2.anchor = GridBagConstraints.NORTHWEST;
		gbc_btnDeleteSelected2.insets = new Insets(0, 0, 5, 0);
		gbc_btnDeleteSelected2.gridx = 5;
		gbc_btnDeleteSelected2.gridy = 7;
		panel2.add(btnDeleteSelected2, gbc_btnDeleteSelected2);		


		//error message
		lblErrorMessageFlight = new JLabel(" ");
		lblErrorMessageFlight.setForeground(Color.RED);
		lblErrorMessageFlight.setName("errorMessageLabel");
		GridBagConstraints gbc_lblErrorMessageFlight = new GridBagConstraints();
		gbc_lblErrorMessageFlight.anchor = GridBagConstraints.NORTH;
		gbc_lblErrorMessageFlight.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblErrorMessageFlight.gridwidth = 6;
		gbc_lblErrorMessageFlight.gridx = 0;
		gbc_lblErrorMessageFlight.gridy = 8;
		panel2.add(lblErrorMessageFlight, gbc_lblErrorMessageFlight);

	}
	
	
	
	
	//######### plane methods ##########
	
	@Override
	public void showAllPlanes(List<Plane> planes) {
		planes.stream()
			.forEach(listPlanesModel::addElement);	
	}

	@Override
	public void showPlaneError(String message) {
		lblErrorMessage.setText(message);
	}

	@Override
	public void planeAdded(Plane plane) {
		listPlanesModel.addElement(plane);
		lblErrorMessage.setText(" ");
		
	}

	@Override
	public void planeRemoved(Plane plane) {
		listPlanesModel.removeElement(plane);
		lblErrorMessage.setText(" ");
	}


	
	
	//######### flight methods ##########
	
	@Override
	public void showAllFlights(List<Flight> flights) {
		flights.stream()
			.forEach(listFlightsModel::addElement);		 
	}
	
	
	@Override
	public void showFlightError(String message) { 
		lblErrorMessageFlight.setText(message);	
	}


	@Override
	public void flightAdded(Flight flight) {
		listFlightsModel.addElement(flight);
		lblErrorMessageFlight.setText(" ");
	}
 

	@Override
	public void flightRemoved(Flight flight) {
		listFlightsModel.removeElement(flight);
		lblErrorMessageFlight.setText(" ");
	}
	
}

