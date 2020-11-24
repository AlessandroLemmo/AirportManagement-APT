package com.airport_management.service_layer;

import java.util.List;
import com.airport_management.model.Flight;


public interface SearchServiceLayer {
	
	public List<Flight> findAllFlightsByOriginSL(String origin);
	public List<Flight> findAllFlightsByDestinationSL(String destination);

}
