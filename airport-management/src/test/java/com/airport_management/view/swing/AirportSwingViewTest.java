package com.airport_management.view.swing;

import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.*;
import static java.util.Arrays.asList;

import org.assertj.swing.annotation.GUITest;
import org.assertj.swing.core.matcher.JButtonMatcher;
import org.assertj.swing.core.matcher.JLabelMatcher;
import org.assertj.swing.edt.GuiActionRunner;
import org.assertj.swing.fixture.FrameFixture;
import org.assertj.swing.junit.testcase.AssertJSwingJUnitTestCase;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.junit.Test;

import com.airport_management.controller.FlightController;
import com.airport_management.controller.PlaneController;
import com.airport_management.controller.SearchController;
import com.airport_management.model.Plane;


public class AirportSwingViewTest extends AssertJSwingJUnitTestCase {
	
	private FrameFixture window;
	private AirportSwingView airportSwingView;

	@Mock
	private PlaneController planeController;
	
	@Mock
	private FlightController flightController;

	@Mock
	private SearchController searchController;
	
	@Override
	protected void onSetUp() {
		MockitoAnnotations.initMocks(this); 
		
		GuiActionRunner.execute(() -> {
			airportSwingView = new AirportSwingView();
			airportSwingView.setAirportController(planeController, flightController, searchController);
			return airportSwingView;
		});
		window = new FrameFixture(robot(), airportSwingView);
		window.show(); // shows the frame to test
	}
	
	
	
	@Test @GUITest
	public void testControlsInitialStates() {
		window.button(JButtonMatcher.withText("Plane Panel")).requireEnabled();
		window.button(JButtonMatcher.withText("Flight Panel")).requireEnabled();
		window.button(JButtonMatcher.withText("Flight Search")).requireEnabled();
	}
	
	
	
	@Test @GUITest
	public void testPanelsButtonsSwitchToCorrispondentPanel() {
		window.button(JButtonMatcher.withText("Plane Panel")).click();
		testControlsInitialStatesPlanePanel();
		window.button(JButtonMatcher.withText("Flight Panel")).click();
		testControlsInitialStatesFlightPanel();
		window.button(JButtonMatcher.withText("Flight Search")).click();
		testControlsInitialStatesSearchFlightPanel();
	}
	
	
	
	@Test @GUITest
	public void testControlsInitialStatesPlanePanel() {	
		window.panel("panel1").label(JLabelMatcher.withText("model"));
		window.panel("panel1").textBox("modelTextBox").requireEnabled();		
		window.panel("panel1").list("planeList");
		window.panel("panel1").button(JButtonMatcher.withText("Add")).requireDisabled();
		window.panel("panel1").button(JButtonMatcher.withText("Delete Selected")).requireDisabled();
		window.panel("panel1").label("errorMessageLabel").requireText(" ");
	}
	
	
	
	@Test @GUITest
	public void testControlsInitialStatesFlightPanel() {	
		window.button(JButtonMatcher.withText("Flight Panel")).click();
		window.panel("panel2").label(JLabelMatcher.withText("plane"));
		window.panel("panel2").comboBox("planeComboBox").requireEnabled();
		window.panel("panel2").label(JLabelMatcher.withText("origin"));
		window.panel("panel2").textBox("originTextBox").requireEnabled();	
		window.panel("panel2").label(JLabelMatcher.withText("destination"));
		window.panel("panel2").textBox("destinationTextBox").requireEnabled();	
		window.panel("panel2").label(JLabelMatcher.withText("departure date"));
		window.panel("panel2").spinner("spinnerDepartureDate").requireEnabled();
		window.panel("panel2").label(JLabelMatcher.withText("arrival date"));
		window.panel("panel2").spinner("spinnerArrivalDate").requireEnabled();
		window.panel("panel2").list("flightsList");
		window.panel("panel2").button(JButtonMatcher.withText("Add")).requireDisabled();
		window.panel("panel2").button(JButtonMatcher.withText("Delete Selected")).requireDisabled();
		window.panel("panel2").label("errorMessageLabel").requireText(" ");
	}
	
	
	
	@Test @GUITest
	public void testControlsInitialStatesSearchFlightPanel() {
		window.button(JButtonMatcher.withText("Flight Search")).click();
		window.panel("panel3").label(JLabelMatcher.withText("Flight origin"));
		window.panel("panel3").textBox("searchOriginTextBox").requireEnabled();	
		window.panel("panel3").button(JButtonMatcher.withText("Search by origin")).requireEnabled();
		window.panel("panel3").list("searchOriginList");
		
		window.panel("panel3").label(JLabelMatcher.withText("Flight destination"));
		window.panel("panel3").textBox("searchDestinationTextBox").requireEnabled();	
		window.panel("panel3").button(JButtonMatcher.withText("Search by destination")).requireEnabled();
		window.panel("panel3").list("searchDestinationList");
	}
	
	
	
	@Test @GUITest
	public void testButtonFlightPanelUpdateComboBoxOfPlanesId() {
		
		when(planeController.returnAllPlanes())
				.thenReturn(asList(new Plane("id", "model")));
		
		window.button(JButtonMatcher.withText("Flight Panel")).click();
		assertThat(airportSwingView.getComboBox().getItemAt(0)).isEqualTo("id");
	}

}


