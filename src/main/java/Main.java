import java.sql.Connection;
import java.util.*;

import model.*;
import model.enums.*;
import repository.AirlineRepository;
import repository.PilotRepository;
import repository.impl.AirlineRepositoryImpl;
import repository.impl.AirportRepositoryImpl;
import repository.impl.PassengerRepositoryImpl;
import repository.impl.PilotRepositoryImpl;
import service.impl.*;
import exceptions.*;
import config.DatabaseConfiguration ;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.postgresql.Driver;

public class Main {
    public static void main(String[] args) {


        TicketSystem ts = TicketSystem.getInstance() ;
        TicketSystemServiceImpl tsService = new TicketSystemServiceImpl(ts) ;

        Passenger passenger = new Passenger.PassengerBuilder("George", "Boboc")
                .setEmail("ghitzaboboc@gmail.com")
                .setPhoneNumber("0770759972").
                build();
        Passenger passenger1 = new Passenger.PassengerBuilder("George", "Boboc")
                .setEmail("ghitzaboboc@gmail.com")
                .setPhoneNumber("0770759973").
                build();

        Airline airline = new Airline.AirlineBuilder()
                .setName("Tarom")
                .setType(AirlineType.COMMERCIAL)
                .build();

        Airline airline1 = new Airline.AirlineBuilder()
                .setName("Wizzair")
                .setType(AirlineType.COMMERCIAL)
                .build() ;

        Airport departure = new Airport.AirportBuilder()
                .setCity("Bucharest")
                .setAddress("Str. Henri Coanda, no. 25")
                .build() ;

        Airport departure1 = new Airport.AirportBuilder()
                .setCity("London")
                .setAddress("Street Heathrow, no. 15")
                .build() ;

        Airport arrival = new Airport.AirportBuilder()
                .setCity("Paris")
                .setAddress("Rue Bovais, no.1")
                .build() ;
        Airport arrival1 = new Airport.AirportBuilder()
                .setCity("Madrid")
                .setAddress("Avenue Leo Messi, no.292")
                .build() ;

        Pilot pilot = new Pilot.PilotBuilder("Labus", "Claudiu")
                .setHireDate(new Date(2003, Calendar.APRIL, 25))
                .build();
        Pilot pilot1 = new Pilot.PilotBuilder("Matei", "Andrei")
                .setHireDate(new Date(2005, Calendar.MAY, 1))
                .build();

        PilotRepositoryImpl ob = new PilotRepositoryImpl() ;
        //ob.addNewPilot(pilot);
        //ob.updatePilotById(pilot.getId(), pilot1);
        //System.out.println(ob.getPilotById(0));
        //ob.deletePilotById(0);
        Flight flight = new Flight.FlightBuilder()
                .setAirline(airline)
                .setDeparture(departure)
                .setDestination(arrival)
                .setDate(new Date(2023, Calendar.APRIL, 21))
                .setPilot(pilot)
                .build() ;
        Flight flight1 = new Flight.FlightBuilder()
            .setAirline(airline)
            .setDeparture(departure1)
            .setDestination(arrival1)
            .setDate(new Date(2023, Calendar.APRIL, 22))
            .setPilot(pilot1)
            .build() ;

        Seat seat = new Seat(1) ;
        Ticket ticket = new Ticket.TicketBuilder()
                .setPassenger(passenger)
                .setFlight(flight)
                .setSeat(seat)
                .build();
        tsService.bookTicket(ticket);
        tsService.addFlight(flight);
        tsService.addFlight(flight1);

        System.out.println("How many tickets would you like to book?");
        Scanner input = new Scanner(System.in) ;
        Integer counter = Integer.valueOf(input.nextLine()) ;

        while (counter > 0) {
            System.out.println("Book your flight");

            System.out.println("Where do you want to fly from?");
            input = new Scanner(System.in);
            String inputDeparture = input.nextLine(); ///input the departure


            List<String> destinations = tsService.destinations(inputDeparture); ///get the destinations

            boolean hasDestinations = false;
            while (!hasDestinations) {
                try {
                    if (destinations.isEmpty()) {
                        throw new NoDestinationException("No destinations available from " + inputDeparture);
                    }

                    hasDestinations = true;
                }
                catch (NoDestinationException e) {
                    System.out.println(e.getMessage());
                    System.out.println();
                    System.out.println("Where do you want to fly from?");
                    input = new Scanner(System.in);
                    inputDeparture = input.nextLine();
                    destinations = tsService.destinations(inputDeparture);
                }
            } ///try-catch block to handle if there are no destinations


            ///when you find a destination, print all of them
            System.out.println("Here are your possible destinations. Where do you want to fly to?");
            Collections.sort(destinations) ;
            for (int i = 0; i < destinations.size(); ++i) {
                System.out.println(destinations.get(i));
            }


            input = new Scanner(System.in);
            String inputDestination = input.nextLine(); ///input the destination


            List<Flight> flights = tsService.flights(inputDeparture, inputDestination);
            boolean hasFlights = false ;
            while (!hasFlights) {
                try {
                    while (flights.isEmpty()) {
                        throw new NoFlightsException("No flights available from " + inputDeparture + " to " + inputDestination);
                    }
                    hasFlights = true ;
                }
                catch (NoFlightsException e) {
                    System.out.println(e.getMessage());
                    System.out.println();
                    System.out.println("Here are your possible destinations. Where do you want to fly to?");
                    for (int i = 0; i < destinations.size(); ++i) {
                        System.out.println(destinations.get(i));
                    }
                    input = new Scanner(System.in);
                    inputDestination = input.nextLine();
                    flights = tsService.flights(inputDeparture, inputDestination);
                }
            } ///try-catch block to handle if there are no flights between departure and destination


            System.out.println("Here are your possible flights. Please select an id!");
            for (int i = 0; i < flights.size(); ++i) {
                FlightServiceImpl flightService = new FlightServiceImpl(flights.get(i)) ;
                System.out.println("Flight: " + flights.get(i).getId());
                System.out.println("Company: " + flights.get(i).getAirline().getName() + " - " + flights.get(i).getAirline().getType());
                System.out.println("Date: " + flights.get(i).getDate().toString());
                System.out.println("Free seats in Economy: " + flightService.getFreeSeatsEconomy());
                System.out.println("Free seats in Business: " + flightService.getFreeSeatsBusiness());
                System.out.println("Free seats in First Class: " + flightService.getFreeSeatsFirstClass());
                System.out.println("Pilot :" + flights.get(i).getPilot());
                System.out.println();
            } ///print everything about the possible found flights


            Scanner idFlight = new Scanner(System.in);
            Flight inputFlight = new Flight.FlightBuilder().build(); ///input its id


            Boolean found = false;
            while (!found) {
                try {
                    for (int i = 0; i < flights.size(); ++i) {
                        if (flights.get(i).getId() == Integer.valueOf(idFlight.nextLine())) {
                            inputFlight = flights.get(i);
                            found = true;
                            break;
                        }
                    }
                    if (!found)
                        throw new InvalidFlightException("Invalid Flight! Try again.");
                }
                catch (InvalidFlightException e) {
                    System.out.println(e.getMessage());
                }
            } ///try-catch block to handle if the inputed flight is not correct



            boolean isValidSeatClass = false ;
            String inputSeatClass = new String();
            while (!isValidSeatClass) {
                try {
                    System.out.println("How would you like to fly? (ECONOMY/BUSINESS/FIRSTCLASS)");
                    input = new Scanner(System.in);
                    inputSeatClass = input.nextLine().toString();
                    if (inputSeatClass.equals("ECONOMY") || inputSeatClass.equals("BUSINESS") || inputSeatClass.equals("FIRSTCLASS")) {
                        isValidSeatClass = true;
                    }
                    else {
                        throw new NoSeatClassException("Invalid seat class. Try again!");
                    }
                }
                catch(NoSeatClassException e) {
                    System.out.println(e.getMessage());
                }
            } ///try-catch block to handle if the inputed seat class is incorrect


            FlightServiceImpl flightService = new FlightServiceImpl(flight) ;
            Integer aisleSeats = flightService.getAisleSeats(inputSeatClass) ;
            Integer middleSeats = flightService.getMiddleSeats(inputSeatClass) ;
            Integer windowSeats = flightService.getWindowSeats(inputSeatClass) ;
            System.out.println("Aisle seats: " + aisleSeats);
            System.out.println("Middle seats: " + middleSeats);
            System.out.println("Window seats: " + windowSeats);

            boolean isValidSeatType = false;
            input = new Scanner(System.in);
            String inputSeatType = new String() ;
            while (!isValidSeatType) {
                try {
                    System.out.println("Pick your preferred seat! (AISLE/MIDDLE/WINDOW)");
                    inputSeatType = input.nextLine();
                    if (inputSeatType.equals("AISLE") || inputSeatType.equals("MIDDLE") || inputSeatType.equals("WINDOW")) {
                        if (inputSeatType.equals("AISLE") && aisleSeats == 0)
                            throw new NoSeatsAvailableException("No seats available! Try again.") ;
                        if (inputSeatType.equals("MIDDLE") && middleSeats == 0)
                            throw new NoSeatsAvailableException("No seats available! Try again.") ;
                        if (inputSeatType.equals("WINDOW") && windowSeats == 0)
                            throw new NoSeatsAvailableException("No seats available! Try again.") ;
                        isValidSeatType = true;
                    }
                    else {
                        throw new NoSeatTypeException("Invalid. Try again!");
                    }
                }
                catch (NoSeatTypeException e) {
                    System.out.println(e.getMessage());
                }
                catch (NoSeatsAvailableException e) {
                    System.out.println(e.getMessage());
                }
            } ///try-catch block to handle if the inputed seat type is incorrect

            System.out.println("First name: ");
            input = new Scanner(System.in);
            String firstNameInput = input.nextLine().toString();

            System.out.println("Last name: ");
            input = new Scanner(System.in);
            String lastNameInput = input.nextLine().toString();

            System.out.println("Email: ");
            input = new Scanner(System.in);
            String email = input.nextLine().toString();

            System.out.println("Phone number: ");
            input = new Scanner(System.in);
            String phoneNumber = input.nextLine().toString();

            Passenger inputPassenger = new Passenger.PassengerBuilder(firstNameInput, lastNameInput)
                    .setEmail(email)
                    .setPhoneNumber(phoneNumber)
                    .build();

            Seat[] seats = inputFlight.getSeats();
            Set<Seat> setSeats = new HashSet<>() ;
            for ( int i = 0 ; i < seats.length; ++i)
                setSeats.add(seats[i]) ;


            FlightServiceImpl inputFlightService = new FlightServiceImpl(inputFlight) ;
            for (Seat seatIt : seats) {
                if (seatIt.getSeatType().toString().equals(inputSeatType) && seatIt.getSeatClass().toString().equals(inputSeatClass)) {
                    if (seatIt.isFree()) {
                        inputFlightService.reserveSeat(seatIt.getNumber());
                        Seat inputSeat = seatIt;
                        Ticket inputTicket = new Ticket.TicketBuilder()
                                .setPassenger(inputPassenger)
                                .setFlight(inputFlight)
                                .setSeat(inputSeat)
                                .build();
                        System.out.println("The price will be: " + inputTicket.getPrice() + ". Continue? (YES/NO)");
                        input = new Scanner(System.in);
                        String decision = input.nextLine().toString();
                        if (decision.equals("YES"))
                            tsService.bookTicket(inputTicket);
                        break;
                    }
                }

            }
            counter = counter - 1 ;
        }
        tsService.printAllTickets();
    }
}
