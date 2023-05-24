import java.sql.Connection;
import java.util.*;

import mappers.AirlineMapper;
import model.*;
import model.enums.*;
import repository.AirlineRepository;
import repository.AirportRepository;
import repository.FlightRepository;
import repository.PilotRepository;
import repository.impl.*;
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

        Scanner menu = new Scanner(System.in) ;
        if (Integer.valueOf(menu.nextLine()) == 1) {
            System.out.println("-------------WELCOME TO ADMIN MENU--------------------");
            while (true) {
                System.out.println("1 - ADD AN AIRLINE");
                System.out.println("2 - ADD AN AIRPORT");
                System.out.println("3 - ADD A PILOT");
                System.out.println("4 - ADD A FLIGHT");
                System.out.println("5 - EXIT");
                System.out.println("6 - DELETE AN AIRLINE");
                System.out.println("7 - DELETE AN AIRPORT");
                System.out.println("8 - DELETE A PILOT");
                System.out.println("9 - DELETE  A FLIGHT");
                Scanner input = new Scanner(System.in) ;
                Integer decision = Integer.valueOf(input.nextLine()) ;
                if ( decision == 1 )  {
                    System.out.println("Airline name?");
                    input = new Scanner(System.in) ;
                    String nume = input.nextLine() ;

                    System.out.println("Airline type?");
                    System.out.println("1 - LOWCOST");
                    System.out.println("2 - COMMERCIAL");
                    System.out.println("3 - CHARTER");
                    input = new Scanner(System.in) ;
                    Integer tip = Integer.valueOf(input.nextLine()) ;

                    AirlineType airlineType = AirlineType.CHARTER;
                    switch (tip) {
                        case 1 :
                            airlineType = AirlineType.LOWCOST ;
                            break ;
                        case 2 :
                            airlineType = AirlineType.COMMERCIAL ;
                            break ;
                        case 3 :
                            airlineType = AirlineType.CHARTER ;
                            break ;
                    }

                    Airline airline = new Airline.AirlineBuilder()
                            .setName(nume)
                            .setType(airlineType)
                            .build();


                    AirlineRepositoryImpl airlineRepository = new AirlineRepositoryImpl() ;
                    airlineRepository.addNewAirline(airline);
                }

                if ( decision == 2 )  {
                    System.out.println("Which city is the airport located at?");
                    input = new Scanner(System.in) ;
                    String city = input.nextLine() ;

                    System.out.println("What address?");
                    input = new Scanner(System.in) ;
                    String address = input.nextLine() ;

                    Airport airport = new Airport.AirportBuilder()
                            .setCity(city)
                            .setAddress(address)
                            .build() ;


                    AirportRepositoryImpl airportRepository = new AirportRepositoryImpl() ;
                    airportRepository.addNewAirport(airport);
                }

                if ( decision == 3 ) {
                    System.out.println("First name of the pilot: ");
                    input = new Scanner(System.in) ;
                    String firstName = input.nextLine() ;

                    System.out.println("Last name of the pilot: ");
                    input = new Scanner(System.in) ;
                    String lastName = input.nextLine() ;

                    System.out.println("Date since becoming a pilot (DD/MM/YYYY): ");
                    input = new Scanner(System.in) ;
                    String hire_date = input.nextLine() ;

                    Pilot pilot = new Pilot.PilotBuilder(firstName, lastName)
                            .setHireDate(hire_date)
                            .build() ;

                    PilotRepositoryImpl pilotRepository = new PilotRepositoryImpl() ;
                    pilotRepository.addNewPilot(pilot);
                }
                if ( decision == 4 ) {
                    AirlineRepositoryImpl airlineRepository = new AirlineRepositoryImpl() ;
                    AirportRepositoryImpl airportRepository = new AirportRepositoryImpl() ;
                    FlightRepositoryImpl flightRepository = new FlightRepositoryImpl() ;
                    PilotRepositoryImpl pilotRepository = new PilotRepositoryImpl() ;

                    List <Airline> airlineList = airlineRepository.getAllAirlines() ;
                    for (Airline airline : airlineList) {
                        System.out.println(airline);
                    }

                    System.out.println("ID of the flight airline:" );
                    input = new Scanner(System.in) ;
                    Integer airlineID = Integer.valueOf(input.nextLine()) ;

                    List <Airport> airportList = airportRepository.getAllAirports() ;
                    for (Airport airport : airportList) {
                        System.out.println(airport);
                    }

                    System.out.println("ID of the departure airport:" );
                    input = new Scanner(System.in) ;
                    Integer departureID = Integer.valueOf(input.nextLine()) ;

                    System.out.println("ID of the arrival airport:" );
                    input = new Scanner(System.in) ;
                    Integer arrivalID = Integer.valueOf(input.nextLine()) ;

                    System.out.println("Date of the flight (DD/MM/YYYY):" );
                    input = new Scanner(System.in) ;
                    String flightDate = input.nextLine() ;

                    List <Pilot> pilotList = pilotRepository.getAllPilots() ;
                    for (Pilot pilot : pilotList) {
                        System.out.println(pilot);
                    }

                    System.out.println("ID of the pilot: ");
                    input = new Scanner(System.in) ;
                    Integer pilotID = Integer.valueOf(input.nextLine()) ;

                    Airline airline = (Airline) airlineRepository.getAirlineById(airlineID) ;
                    airline.setId(airlineID);

                    Airport departure = (Airport) airportRepository.getAirportById(departureID) ;
                    departure.setId(departureID);

                    Airport arrival = (Airport) airportRepository.getAirportById(arrivalID) ;
                    arrival.setId(arrivalID);

                    Pilot pilot = (Pilot) pilotRepository.getPilotById(pilotID) ;
                    pilot.setId(pilotID) ;

                    Flight flight = new Flight.FlightBuilder()
                            .setAirline(airline)
                            .setDeparture(departure)
                            .setDestination(arrival)
                            .setDate(flightDate)
                            .setPilot(pilot)
                            .build() ;


                    flightRepository.addNewFlight(flight);

                }

                if (decision == 5) {
                    break ;
                }
                if (decision == 6) {
                    AirlineRepositoryImpl airlineRepository = new AirlineRepositoryImpl() ;
                    List <Airline> airlineList = airlineRepository.getAllAirlines() ;
                    for (Airline airline : airlineList) {
                        System.out.println(airline);
                    }

                    System.out.println("ID of the flight airline:" );
                    input = new Scanner(System.in) ;
                    Integer airlineID = Integer.valueOf(input.nextLine()) ;

                    airlineRepository.deleteAirlineById(airlineID);

                }
                if (decision == 7) {
                    AirportRepositoryImpl airportRepository = new AirportRepositoryImpl() ;
                    List <Airport> airportList = airportRepository.getAllAirports() ;
                    for (Airport airport : airportList) {
                        System.out.println(airport);
                    }

                    System.out.println("ID of the airport:" );
                    input = new Scanner(System.in) ;
                    Integer airportID = Integer.valueOf(input.nextLine()) ;

                    airportRepository.deleteAirportById(airportID);
                }
                if (decision == 8) {
                    PilotRepositoryImpl pilotRepository = new PilotRepositoryImpl() ;
                    List <Pilot> PilotList = pilotRepository.getAllPilots() ;
                    for (Pilot pilot : PilotList) {
                        System.out.println(pilot);
                    }

                    System.out.println("ID of the Pilot:" );
                    input = new Scanner(System.in) ;
                    Integer PilotID = Integer.valueOf(input.nextLine()) ;

                    pilotRepository.deletePilotById(PilotID);
                }

                if (decision == 9) {
                    FlightRepositoryImpl flightRepository = new FlightRepositoryImpl() ;

                    System.out.println("ID of the Flight:" );
                    input = new Scanner(System.in) ;
                    Integer FlightID = Integer.valueOf(input.nextLine()) ;

                    flightRepository.deleteFlightById(FlightID);
                }

            }
        }
        /*
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

        AirportRepositoryImpl ari = new AirportRepositoryImpl() ;
        //ari.addNewAirport(departure);

        Airport arrival = new Airport.AirportBuilder()
                .setCity("Paris")
                .setAddress("Rue Bovais, no.1")
                .build() ;
        //ari.addNewAirport(arrival);
        Airport arrival1 = new Airport.AirportBuilder()
                .setCity("Madrid")
                .setAddress("Avenue Leo Messi, no.292")
                .build() ;

        Pilot pilot = new Pilot.PilotBuilder("Labus", "Claudiu")
                .setHireDate("25/04/2003")
                .build();
        Pilot pilot1 = new Pilot.PilotBuilder("Matei", "Andrei")
                .setHireDate("01/05/2005")
                .build();

        PilotRepositoryImpl pri = new PilotRepositoryImpl() ;
        //pri.addNewPilot(pilot);
        //pri.addNewPilot(pilot1);
        //System.out.println(pri.getPilotById(0)) ;
        //pri.deletePilotById(0);
        Flight flight = new Flight.FlightBuilder()
                .setAirline(airline)
                .setDeparture(departure)
                .setDestination(arrival)
                .setDate("21/05/2023")
                .setPilot(pilot)
                .build() ;
        Flight flight1 = new Flight.FlightBuilder()
            .setAirline(airline)
            .setDeparture(departure1)
            .setDestination(arrival1)
            .setDate("15/12/2023")
            .setPilot(pilot1)
            .build() ;
        FlightRepositoryImpl fri = new FlightRepositoryImpl() ;
        //fri.addNewFlight(flight);
        //fri.deleteFlightById(flight.getId());
        //System.out.println(fri.getFlightById(flight.getId()));
        Seat seat = new Seat(1) ;
        Ticket ticket = new Ticket.TicketBuilder()
                .setPassenger(passenger)
                .setFlight(flight)
                .setSeat(seat)
                .build();
        tsService.bookTicket(ticket);
        tsService.addFlight(flight);
        tsService.addFlight(flight1);
        */
        else {

            System.out.println("How many tickets would you like to book?");
            Scanner input = new Scanner(System.in);
            Integer counter = Integer.valueOf(input.nextLine());

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
                    } catch (NoDestinationException e) {
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
                Collections.sort(destinations);
                for (int i = 0; i < destinations.size(); ++i) {
                    System.out.println(destinations.get(i));
                }


                input = new Scanner(System.in);
                String inputDestination = input.nextLine(); ///input the destination


                List<Flight> flights = tsService.flights(inputDeparture, inputDestination);
                boolean hasFlights = false;
                while (!hasFlights) {
                    try {
                        while (flights.isEmpty()) {
                            throw new NoFlightsException("No flights available from " + inputDeparture + " to " + inputDestination);
                        }
                        hasFlights = true;
                    } catch (NoFlightsException e) {
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
                    FlightServiceImpl flightService = new FlightServiceImpl(flights.get(i));
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
                    } catch (InvalidFlightException e) {
                        System.out.println(e.getMessage());
                    }
                } ///try-catch block to handle if the inputed flight is not correct


                boolean isValidSeatClass = false;
                String inputSeatClass = new String();
                while (!isValidSeatClass) {
                    try {
                        System.out.println("How would you like to fly? (ECONOMY/BUSINESS/FIRSTCLASS)");
                        input = new Scanner(System.in);
                        inputSeatClass = input.nextLine().toString();
                        if (inputSeatClass.equals("ECONOMY") || inputSeatClass.equals("BUSINESS") || inputSeatClass.equals("FIRSTCLASS")) {
                            isValidSeatClass = true;
                        } else {
                            throw new NoSeatClassException("Invalid seat class. Try again!");
                        }
                    } catch (NoSeatClassException e) {
                        System.out.println(e.getMessage());
                    }
                } ///try-catch block to handle if the inputed seat class is incorrect


                FlightServiceImpl flightService = new FlightServiceImpl(inputFlight);
                Integer aisleSeats = flightService.getAisleSeats(inputSeatClass);
                Integer middleSeats = flightService.getMiddleSeats(inputSeatClass);
                Integer windowSeats = flightService.getWindowSeats(inputSeatClass);
                System.out.println("Aisle seats: " + aisleSeats);
                System.out.println("Middle seats: " + middleSeats);
                System.out.println("Window seats: " + windowSeats);

                boolean isValidSeatType = false;
                input = new Scanner(System.in);
                String inputSeatType = new String();
                while (!isValidSeatType) {
                    try {
                        System.out.println("Pick your preferred seat! (AISLE/MIDDLE/WINDOW)");
                        inputSeatType = input.nextLine();
                        if (inputSeatType.equals("AISLE") || inputSeatType.equals("MIDDLE") || inputSeatType.equals("WINDOW")) {
                            if (inputSeatType.equals("AISLE") && aisleSeats == 0)
                                throw new NoSeatsAvailableException("No seats available! Try again.");
                            if (inputSeatType.equals("MIDDLE") && middleSeats == 0)
                                throw new NoSeatsAvailableException("No seats available! Try again.");
                            if (inputSeatType.equals("WINDOW") && windowSeats == 0)
                                throw new NoSeatsAvailableException("No seats available! Try again.");
                            isValidSeatType = true;
                        } else {
                            throw new NoSeatTypeException("Invalid. Try again!");
                        }
                    } catch (NoSeatTypeException e) {
                        System.out.println(e.getMessage());
                    } catch (NoSeatsAvailableException e) {
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
                Set<Seat> setSeats = new HashSet<>();
                for (int i = 0; i < seats.length; ++i)
                    setSeats.add(seats[i]);


                FlightServiceImpl inputFlightService = new FlightServiceImpl(inputFlight);
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
                counter = counter - 1;
            }
            tsService.printAllTickets();
        }
    }
}
