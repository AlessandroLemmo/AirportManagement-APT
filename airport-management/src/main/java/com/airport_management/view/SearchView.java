package com.airport_management.view;

import java.util.List;
import com.airport_management.model.Flight;


public interface SearchView {

	public void showSearchFlightError(String message);
	
	public void clearListSearchByOrigin();
	
	public void showAllFoundedFlightsByOrigin(List<Flight> flights);

}
