package com.airport_management.service_layer.transaction;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.AdditionalAnswers.answer;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.airport_management.exception.FlightNotFoundException;
import com.airport_management.model.Flight;
import com.airport_management.model.Plane;
import com.airport_management.repository.mongo.FlightRepositoryMongo;
import com.airport_management.repository.mongo.RepositoryMongo;
import com.airport_management.transaction.TransactionCode;
import com.airport_management.transaction.TransactionManager;


public class SearchServiceLayerTest {
	
	@Mock
	TransactionManager transactionManager;
	
	@Mock
	RepositoryMongo repositoryMongo;
	
	@Mock
	FlightRepositoryMongo flightRepositoryMongo;
	
	@InjectMocks
	AirportServiceLayer airportServiceLayer;
	
	private static final String ID_FIXTURE_1 = "id1-test";
	private static final String MODEL_FIXTURE = "model-test";
	private static final String NUM_FIXTURE = "num-test";
	private static final String ORIGIN_FIXTURE = "origin-test";
	private static final String DESTINATION_FIXTURE = "destination-test";
	private static final Plane PLANE_FIXTURE_1 = new Plane(ID_FIXTURE_1, MODEL_FIXTURE);
	private static final Flight FLIGHT_FIXTURE = new Flight(NUM_FIXTURE, null, null, ORIGIN_FIXTURE, DESTINATION_FIXTURE, PLANE_FIXTURE_1);
	
	
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		
		when(transactionManager.doInTransaction(any()))
			.thenAnswer(answer((TransactionCode<?> code) -> code.apply(repositoryMongo)));
		when(repositoryMongo.createFlightRepository()).thenReturn(flightRepositoryMongo);
	}
	

	
	@Test
	public void testFindAllFlightsByOriginWhenExist() {
		List<Flight> flights = asList(FLIGHT_FIXTURE);
		
		when(flightRepositoryMongo.findAllFlights())
			.thenReturn(flights);
		
		List<Flight> flightsFounded = airportServiceLayer.findAllFlightsByOriginSL(ORIGIN_FIXTURE);
		assertThat(flightsFounded).containsExactly(FLIGHT_FIXTURE);
	}
	
	
	
	@Test
	public void testFindAllFlightsByOriginWhenNoExist() {
		List<Flight> flights = asList(FLIGHT_FIXTURE);
		
		when(flightRepositoryMongo.findAllFlights())
			.thenReturn(flights);
		
		FlightNotFoundException ex = assertThrows(FlightNotFoundException.class, () -> {
			airportServiceLayer.findAllFlightsByOriginSL("new origin");
		});
		assertEquals("There aren't flights with this origin", ex.getMessage());
	}
	
	
	
	@Test
	public void testFindAllFlightsByDestinationWhenExist() {
		List<Flight> flights = asList(FLIGHT_FIXTURE);
		
		when(flightRepositoryMongo.findAllFlights())
			.thenReturn(flights);
		
		List<Flight> flightsFounded = airportServiceLayer.findAllFlightsByDestinationSL(DESTINATION_FIXTURE);
		assertThat(flightsFounded).containsExactly(FLIGHT_FIXTURE);
	}
	
	
	
	@Test
	public void testFindAllFlightsByDestinationWhenNoExist() {
		List<Flight> flights = asList(FLIGHT_FIXTURE);
		
		when(flightRepositoryMongo.findAllFlights())
			.thenReturn(flights);
		
		FlightNotFoundException ex = assertThrows(FlightNotFoundException.class, () -> {
			airportServiceLayer.findAllFlightsByDestinationSL("new destination");
		});	
		assertEquals("There aren't flights with this destination", ex.getMessage());
	}
	
}

