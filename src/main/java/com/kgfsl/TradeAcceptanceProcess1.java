package com.kgfsl;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TradeAcceptanceProcess1 {
	private final String filename = "E:\\Shanthini\\flight.txt";
	private List<Flight> allFlights;

	public void readGiveUpCSVFile() throws FileNotFoundException {
		String[] columns = new String[] { "id", "name", "departure", "arrival", "fare" };
		allFlights = (ParseCsvToBean.convert(filename, ',', Flight.class, columns));
		allFlights.forEach(System.out::println);
		System.out.println("allFlights.size() .... >" + allFlights.size());
		// uniqueTradeNo.size()); type 1
		Set<Flight> uniqueFlights = allFlights.stream()
				.collect(Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(Flight::getId))));
		// directly removing the elements from list if already existed in set
		// type 2
		// Set<String> uniqueFlights = new HashSet<>();
		// allFlights.removeIf(p -> !uniqueFlights.add(p.getId()));
		System.out.println("uniqueFlights.size() .... >" + uniqueFlights.size());

		// Departure wise Flights count
		Map<String, Long> departureCounting = uniqueFlights.stream()
				.collect(Collectors.groupingBy(Flight::getDeparture, Collectors.counting()));
		System.out.println(departureCounting);

		//new
		/*
		System.out.println("Using compareto");
		System.out.println(	allFlights.stream()
		.collect(Collectors.groupingBy(Flight::getId, Collectors.minBy(new Comparator<Flight>() {
					public int compare(Flight s1, Flight s2) {
						return (s1.getDeparture()).compareTo((s2.getArrival()));
					}
				}))));
		
		System.out.println("$$$$$$$$$$compareto$$$$$$");
		Collections.sort(allFlights, new calcroundtrip());	
		*/
		//calculating roundtrip set flag
		// System.out.println("********BY SET FLAG*********");
		// Map<String, Optional<Flight>> aa = allFlights.stream()
		// 		.collect(Collectors.groupingBy(Flight::getId, Collectors.maxBy(new Comparator<Flight>() {
		// 			public int compare(Flight s1, Flight s2) {
		// 				System.out.println("s1=" + s1.getId() + " s2=" + s2.getId());
		// 				//System.out.println("s1="+s1.getDeparture() + " s2=" +s2.getArrival());
		// 				s1.setFlag("R");
		// 				s2.setFlag("R");
		// 				return s1.getDeparture().compareTo(s2.getArrival());
		// 			}
		// 		})));
		// System.out.println("\n" + aa);
		//FIND MIN VALUE
		
		//List<Flight> list = ((Collection<Flight>) aa).stream().collect(Collectors.toList());
		//System.out.println(list);

		//aa.values().stream().collect(Collectors.groupingBy(Flight::getId),Collectors.minBy(Comparator.comparing(Flight::getFare)));
		// Heathrow to Londan flights 
		System.out.println("Using Predicate");
		Predicate<Flight> depatrurePredicate = x -> (x.getArrival().equals(x.getDeparture()));
		Predicate<Flight> arrivalPredicate = x -> (x.getDeparture().equals(x.getArrival()));
		System.out.println(uniqueFlights.stream().filter(depatrurePredicate.and(arrivalPredicate)).map(x -> x.getId())
				.collect(Collectors.toList()));
		// Airlines name wise grouping
		Map<String, Set<String>> result = uniqueFlights.stream()
				.collect(Collectors.groupingBy(Flight::getName, Collectors.mapping(Flight::getId, Collectors.toSet())));
		System.out.println(result);

		// Map<String, Long> rountripPredicate1 = allFlights.stream().collect(Collectors.groupingBy(Flight::getId))
		// 		.values().stream().filter(flightWithSameId -> flightWithSameId.size() > 1).collect(Collectors.toList());
		// System.out.println(rountripPredicate1);

		// RoundTrip flights
		System.out.println("++++++++++++++");
		Predicate<Flight> rountripPredicate = x -> (x.getDeparture().equals(x.getArrival())
				&& (x.getArrival().equals(x.getDeparture())));
		//	|| (x.getDeparture().equals(x.getArrival())) && (x.getArrival().equals(x.getDeparture())));

		System.out.println(allFlights.stream().filter(rountripPredicate)
				.collect(Collectors.groupingBy(Flight::getId, Collectors.toList())));

		// Minimum fare RoundTrip flights
		System.out.println("?????????????");
		System.out.println(
				allFlights.stream().filter(rountripPredicate).min(Comparator.comparing(Flight::getFare)).get());

		Map<String, Optional<Flight>> personByMaxAge = allFlights.stream().filter(rountripPredicate)
				.collect(Collectors.groupingBy(Flight::getId, Collectors.minBy(Comparator.comparing(Flight::getFare))));

		System.out.println("personByMaxAge ...>" + personByMaxAge.toString());

		// Group persons by their ID
		Map<String, List<Flight>> peopleById = allFlights.stream().collect(Collectors.groupingBy(Flight::getId));

		// Print out groups of people that share one ID
		peopleById.values().stream().filter(peopleWithSameId -> peopleWithSameId.size() > 1)
				.forEach(peopleWithSameId -> System.out.println("People with identical IDs: " + peopleWithSameId));

		List<List<Flight>> result4 = peopleById.values().stream()
				.filter(peopleWithSameId -> peopleWithSameId.size() > 1).collect(Collectors.toList());
		System.out.println("result4 *************");
		System.out.println(result4);

		Map<Object, Integer> roundtripFareTotal = allFlights.stream().filter(rountripPredicate)
				.collect(Collectors.groupingBy(p -> p.getId(), Collectors.summingInt(p -> p.getFare())));

		System.out.println("************* roundtripFareTotal *************");
		System.out.println(roundtripFareTotal);
		System.out.println("*********************");
		long max = roundtripFareTotal.values().stream().max(Comparator.naturalOrder()).get();
		System.out.println("The maximum fare in the roundtrip is:" + max);

		Stream<Flight> aa23 = allFlights.stream().sorted(new Comparator<Flight>() {
			public int compare(Flight s1, Flight s2) {
				if(s1.getId().equals("EA-926")){
					System.out.println("\n s1="+s1.toString()+"\n s2="+s2.toString());
				System.out.println("s1="+s1.getId() + " s2=" +s2.getId());}
				
				if(s1.getDeparture().equals(s2.getArrival()) && s1.getId().equals(s2.getId())){
					
					s1.setFlag("R");
					s2.setFlag("R");
					return 1;
				} else {
					// s1.setFlag("R");
					// s2.setFlag("R");
					return -1;
				}
				// return s1.getDeparture().compareTo(s2.getArrival());
			}
		});
		aa23.forEach(System.out::println);
		//System.out.println(aa23.collect(Collectors.toList()));
		System.out.println("lllllllllll");
		List<Flight> ts=allFlights.stream().filter(p->p.getFlag() =="R").collect(Collectors.groupingBy(Flight::getId, Collectors.toList())));
	
		 
			

}
}
