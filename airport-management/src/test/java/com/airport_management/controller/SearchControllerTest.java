package com.airport_management.controller;

import static org.mockito.Mockito.doThrow;
import static java.util.Arrays.asList;
import static org.mockito.Mockito.*;

import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.airport_management.exception.FlightNotFoundException;
import com.airport_management.model.Flight;
import com.airport_management.model.Plane;
import com.airport_management.service_layer.transaction.AirportServiceLayer;
import com.airport_management.view.SearchView;


public class SearchControllerTest {

	@Mock 
	private SearchView searchView;
	
	@Mock
	private AirportServiceLayer serviceLayer;
	
	@InjectMocks
	private SearchController searchController;
	
	private static final String NUM_FIXTURE = "num-test";
	private static final String ORIGIN_FIXTURE = "origin-test";
	private static final String DESTINATION_FIXTURE = "destination-test";
	private static final String ID_FIXTURE = "id-test";
	private static final String MODEL_FIXTURE = "model-test";
	private static final Date DEPARTURE_DATE_FIXTURE = new Date();
	private static final Date ARRIVAL_DATE_FIXTURE = new Date();
	private static final Plane PLANE_FIXTURE = new Plane(ID_FIXTURE, MODEL_FIXTURE);
	private static final Flight FLIGHT_FIXTURE = new Flight(NUM_FIXTURE, DEPARTURE_DATE_FIXTURE, ARRIVAL_DATE_FIXTURE, ORIGIN_FIXTURE, DESTINATION_FIXTURE, PLANE_FIXTURE);
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	
	
	@Test
	public void testFindAllFlightsByOriginWhenNoExist() {		
		
		doThrow(new FlightNotFoundException("No existing flight with the insert origin"))
			.when(serviceLayer).findAllFlightsByOriginSL(ORIGIN_FIXTURE);
		
		searchController.findAllFlightsByOrigin(ORIGIN_FIXTURE);
		verify(searchView).showSearchFlightError("No existing flight with the insert origin");
		verify(searchView).clearListSearchByOrigin();
		verifyNoMoreInteractions(ignoreStubs(serviceLayer));
		verifyNoMoreInteractions(searchView);
	}
	
	
	
	@Test
	public void testFindAllFlightsByOriginWhenExist() {
		List<Flight> flights = asList(FLIGHT_FIXTURE);
		
		when(serviceLayer.findAllFlightsByOriginSL(ORIGIN_FIXTURE))
			.thenReturn(flights);
		
		searchController.findAllFlightsByOrigin(ORIGIN_FIXTURE);
		verify(searchView).showAllFoundedFlightsByOrigin(flights);
		verifyNoMoreInteractions(ignoreStubs(serviceLayer));
		verifyNoMoreInteractions(searchView);
	}
	
}


