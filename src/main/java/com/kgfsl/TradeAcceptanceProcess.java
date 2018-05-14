package com.kgfsl;

import java.io.FileNotFoundException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.function.*;
import com.kgfsl.Flight;

public class TradeAcceptanceProcess {
	List<Flight> flights1 = new ArrayList<Flight>();  
	// String Coimbatore;
	//private static final String Coimbatore = null;
	private final String filename = "E:\\Shanthini\\flight.txt";
	public  List<Flight> allFlights;
	public List<Flight> allFlights2;

	public void readGiveUpCSVFile() throws FileNotFoundException {

		String[] columns = new String[] { "id", "name", "departure", "arrival", "fare" };
		allFlights = (ParseCsvToBean.convert(filename, ',', Flight.class, columns));
		allFlights.forEach(System.out::println);
		System.out.println("allFlights.size() .... >" + allFlights.size());
		// Unique Flights
		Set<Flight> uniqueFlights = allFlights.stream()
				.collect(Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(Flight::toString))));
		System.out.println("uniqueFlights.size() .... >" + uniqueFlights.size());
		System.out.printf("%n");
		System.out.printf("%n");
		// Departure wise Flights count
		Map<String, Long> departureCounting = uniqueFlights.stream()
				.collect(Collectors.groupingBy(Flight::getDeparture, Collectors.counting()));
		System.out.println(departureCounting);
		System.out.printf("%n");
		System.out.printf("%n");
		// Coimbatore to London flights
		Predicate<Flight> depatrurePredicate = x -> "Coimbatore".equals(x.getDeparture());
		Predicate<Flight> arrivalPredicate = x -> "London".equals(x.getArrival());
		System.out.println(uniqueFlights.stream().filter(depatrurePredicate.and(arrivalPredicate)).map(x -> x.getId())
				.collect(Collectors.toList()));
		System.out.printf("%n");
		System.out.printf("%n");
		// Airlines name wise grouping
		Map<String, Set<String>> result = uniqueFlights.stream()
				.collect(Collectors.groupingBy(Flight::getName, Collectors.mapping(Flight::getId, Collectors.toSet())));
		System.out.println(result);
		System.out.printf("%n");
		System.out.printf("%n");

		//printing all details
		Map<String, Set<String>> result1 = uniqueFlights.stream().collect(
				Collectors.groupingBy(Flight::getName, Collectors.mapping(Flight::toString, Collectors.toSet())));
		System.out.println(result1);
		System.out.printf("%n");
		System.out.printf("%n");

		//using multiple groupingby
		final Map<String, Map<String, List<Flight>>> flightByIdAndName = uniqueFlights.stream()
				.collect(Collectors.groupingBy(Flight::getName, Collectors.groupingBy(Flight::getId)));
		System.out.println(flightByIdAndName);
		System.out.printf("%n");
		System.out.printf("%n");

		//finding round trip flights
		allFlights.stream().collect(Collectors.groupingBy(Flight::getId)).values().stream()
				.filter(flightWithSameId -> flightWithSameId.size() > 1)
				.collect(Collectors.toList()); 
				System.out.println(flights1);  

				// .forEach(flightWithSameId -> System.out.println("Flights with identical IDs: " + flightWithSameId));
		System.out.println("*********************");
		//Map<String, Set<String>> result = emp.stream()
        //.collect(Collectors.groupingBy(Flight::getName, Collectors.mapping(Flight::toString, Collectors.toSet())));
		List<List<Flight>> abc= allFlights.stream()
				.collect(Collectors.groupingBy(Flight::getArrival)).values().stream()
				.filter(flightWithSamearrive -> flightWithSamearrive.size() > 1)
				.collect(Collectors.toList());
				//.forEa(flightWithSamearrive -> System.out.println("Flights with identical arrivals: " + flightWithSamearrive));
		System.out.println("*********************");
		allFlights.stream().collect(Collectors.groupingBy(Flight::getDeparture)).values().stream()
				.filter(flightWithSamedepart -> flightWithSamedepart.size() > 1)
				.forEach(flightWithSamedepart -> System.out
						.println("Flights with identical depetures: " + flightWithSamedepart));
		System.out.println("*********************");

          Integer totalPrice = allFlights.stream()
		// .collect(Collectors.groupingBy(Flight::getArrival)).values().stream()
		// 		.filter(flightWithSamearrive -> flightWithSamearrive.size() > 1)
		.map(flightfare ->flightfare.getFare())
		.reduce(0,(sum, price)->sum+price); // accumulating price 
		
		// Integer totalPrice1 = abc.stream()
		// // .collect(Collectors.groupingBy(Flight::getArrival)).values().stream()
		// // 		.filter(flightWithSamearrive -> flightWithSamearrive.size() > 1)
		// .map(flightfare1 ->flightfare1.)
		// .reduce(0,(sum, price)->sum+price); // accumulating price  

		// System.out.println("the total fare value is " + totalPrice1);

		// Predicate<Flight> depatrurePredicate1 = x -> "Heathrow".equals(x.getDeparture());
		// Predicate<Flight> arrivalPredicate2 = x -> "Heathrow".equals(x.getArrival());
		// Predicate<Flight> arrivalPredicate1 = x -> "London".equals(x.getArrival());
		// Predicate<Flight> depatrurePredicate2 = x -> "London".equals(x.getDeparture());
		// System.out.println(uniqueFlights.stream().filter(depatrurePredicate1.and(arrivalPredicate1)).map(x -> x.getName())
		// 		.collect(Collectors.toList()));
		// System.out.println(uniqueFlights.stream().filter(depatrurePredicate2.and(arrivalPredicate2)).map(x -> x.getId())
		// 		.collect(Collectors.toList()));

	}

}
