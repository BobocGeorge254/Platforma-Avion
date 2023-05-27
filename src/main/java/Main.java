import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

import application.csv.CsvWriter;
import com.opencsv.CSVWriter;
import model.*;
import model.enums.*;
import repository.AirportRepository;
import repository.PassengerRepository;
import repository.TicketRepository;
import repository.impl.*;
import service.impl.*;
import exceptions.*;

import java.util.stream.Collectors;

import com.opencsv.CSVWriter;

import java.io.FileWriter;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws Exception {


        TicketSystem ts = TicketSystem.getInstance() ;
        TicketSystemServiceImpl tsService = new TicketSystemServiceImpl(ts) ;
        final CsvWriter CSV_WRITER = CsvWriter.getInstance() ;
        String filePathString = "data.csv" ;
        Path filePath = Paths.get(filePathString) ;
        while (true) {
            Scanner menu = new Scanner(System.in);
            System.out.println("1 - For admin menu");
            System.out.println("2 - For passenger menu");
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
                    Scanner input = new Scanner(System.in);
                    Integer decision = Integer.valueOf(input.nextLine());
                    if (decision == 1) {
                        System.out.println("Airline name?");
                        input = new Scanner(System.in);
                        String nume = input.nextLine();

                        System.out.println("Airline type?");
                        System.out.println("1 - LOWCOST");
                        System.out.println("2 - COMMERCIAL");
                        System.out.println("3 - CHARTER");
                        input = new Scanner(System.in);
                        Integer tip = Integer.valueOf(input.nextLine());

                        AirlineType airlineType = AirlineType.CHARTER;
                        switch (tip) {
                            case 1:
                                airlineType = AirlineType.LOWCOST;
                                break;
                            case 2:
                                airlineType = AirlineType.COMMERCIAL;
                                break;
                            case 3:
                                airlineType = AirlineType.CHARTER;
                                break;
                        }

                        Airline airline = new Airline.AirlineBuilder()
                                .setId(UUID.randomUUID())
                                .setName(nume)
                                .setType(airlineType)
                                .build();


                        AirlineRepositoryImpl airlineRepository = new AirlineRepositoryImpl();
                        airlineRepository.addNewAirline(airline);
                    }

                    if (decision == 2) {
                        System.out.println("Which city is the airport located at?");
                        input = new Scanner(System.in);
                        String city = input.nextLine();

                        System.out.println("What address?");
                        input = new Scanner(System.in);
                        String address = input.nextLine();

                        Airport airport = new Airport.AirportBuilder()
                                .setId(UUID.randomUUID())
                                .setCity(city)
                                .setAddress(address)
                                .build();


                        AirportRepositoryImpl airportRepository = new AirportRepositoryImpl();
                        airportRepository.addNewAirport(airport);
                    }

                    if (decision == 3) {
                        System.out.println("First name of the pilot: ");
                        input = new Scanner(System.in);
                        String firstName = input.nextLine();

                        System.out.println("Last name of the pilot: ");
                        input = new Scanner(System.in);
                        String lastName = input.nextLine();

                        System.out.println("Date since becoming a pilot (DD/MM/YYYY): ");
                        input = new Scanner(System.in);
                        String hire_date = input.nextLine();

                        Pilot pilot = new Pilot.PilotBuilder(firstName, lastName)
                                .setId(UUID.randomUUID())
                                .setHireDate(hire_date)
                                .build();

                        PilotRepositoryImpl pilotRepository = new PilotRepositoryImpl();
                        pilotRepository.addNewPilot(pilot);
                    }
                    if (decision == 4) {
                        AirlineRepositoryImpl airlineRepository = new AirlineRepositoryImpl();
                        AirportRepositoryImpl airportRepository = new AirportRepositoryImpl();
                        FlightRepositoryImpl flightRepository = new FlightRepositoryImpl();
                        PilotRepositoryImpl pilotRepository = new PilotRepositoryImpl();

                        List<Airline> airlineList = airlineRepository.getAllAirlines();
                        for (Airline airline : airlineList) {
                            System.out.println(airline);
                        }

                        System.out.println("ID of the flight airline:");
                        input = new Scanner(System.in);
                        String airlineID = input.nextLine();

                        List<Airport> airportList = airportRepository.getAllAirports();
                        for (Airport airport : airportList) {
                            System.out.println(airport);
                        }

                        System.out.println("ID of the departure airport:");
                        input = new Scanner(System.in);
                        String departureID = input.nextLine();

                        System.out.println("ID of the arrival airport:");
                        input = new Scanner(System.in);
                        String arrivalID = input.nextLine();

                        System.out.println("Date of the flight (DD/MM/YYYY):");
                        input = new Scanner(System.in);
                        String flightDate = input.nextLine();

                        List<Pilot> pilotList = pilotRepository.getAllPilots();
                        for (Pilot pilot : pilotList) {
                            System.out.println(pilot);
                        }

                        System.out.println("ID of the pilot: ");
                        input = new Scanner(System.in);
                        String pilotID = input.nextLine();

                        Airline airline = (Airline) airlineRepository.getAirlineById(UUID.fromString(airlineID));
                        Airport departure = (Airport) airportRepository.getAirportById(UUID.fromString(departureID));
                        Airport arrival = (Airport) airportRepository.getAirportById(UUID.fromString(arrivalID));
                        Pilot pilot = (Pilot) pilotRepository.getPilotById(UUID.fromString(pilotID));


                        Flight flight = new Flight.FlightBuilder()
                                .setId(UUID.randomUUID())
                                .setAirline(airline)
                                .setDeparture(departure)
                                .setDestination(arrival)
                                .setDate(flightDate)
                                .setPilot(pilot)
                                .build();


                        flightRepository.addNewFlight(flight);

                    }

                    if (decision == 5) {
                        break;
                    }
                    if (decision == 6) {
                        AirlineRepositoryImpl airlineRepository = new AirlineRepositoryImpl();
                        List<Airline> airlineList = airlineRepository.getAllAirlines();
                        for (Airline airline : airlineList) {
                            System.out.println(airline);
                        }

                        System.out.println("ID of the flight airline:");
                        input = new Scanner(System.in);
                        String airlineID = input.nextLine();

                        airlineRepository.deleteAirlineById(UUID.fromString(airlineID));

                    }
                    if (decision == 7) {
                        AirportRepositoryImpl airportRepository = new AirportRepositoryImpl();
                        List<Airport> airportList = airportRepository.getAllAirports();
                        for (Airport airport : airportList) {
                            System.out.println(airport);
                        }

                        System.out.println("ID of the airport:");
                        input = new Scanner(System.in);
                        String airportID = input.nextLine();

                        airportRepository.deleteAirportById(UUID.fromString(airportID));
                    }
                    if (decision == 8) {
                        PilotRepositoryImpl pilotRepository = new PilotRepositoryImpl();
                        List<Pilot> PilotList = pilotRepository.getAllPilots();
                        for (Pilot pilot : PilotList) {
                            System.out.println(pilot);
                        }

                        System.out.println("ID of the Pilot:");
                        input = new Scanner(System.in);
                        String PilotID = input.nextLine();

                        pilotRepository.deletePilotById(UUID.fromString(PilotID));
                    }

                    if (decision == 9) {
                        FlightRepositoryImpl flightRepository = new FlightRepositoryImpl();
                        List<Flight> FlightList = flightRepository.getAllFlights();
                        for (Flight flight : FlightList) {
                            System.out.println(flight);
                        }

                        System.out.println("ID of the Flight:");
                        input = new Scanner(System.in);
                        String FlightID = input.nextLine();

                        TicketRepositoryImpl ticketRepository = new TicketRepositoryImpl() ;
                        List<Ticket> tickets = ticketRepository.getTicketsByFlightId(UUID.fromString(FlightID)) ;

                        for (Ticket ticket : tickets) {
                            ticketRepository.deleteTicketById(ticket.getId()) ;
                        }

                        flightRepository.deleteFlightById(UUID.fromString(FlightID));
                    }

                }
            } else {

                //PassengerRepositoryImpl a = new PassengerRepositoryImpl() ;
                //a.deletePassengerById(0);
                AirportRepositoryImpl airportRepository = new AirportRepositoryImpl();
                FlightRepositoryImpl flightRepository = new FlightRepositoryImpl();

                List<Flight> flightsBD = flightRepository.getAllFlights();
                List<Airport> departuresBD = flightsBD.stream().map(flight -> flight.getDeparture()).collect(Collectors.toList());

                System.out.println("How many tickets would you like to book?");
                Scanner input = new Scanner(System.in);
                Integer counter = Integer.valueOf(input.nextLine());

                while (counter > 0) {
                    System.out.println("Book your flight");
                    counter = counter - 1;

                    Set<String> printedCities = new HashSet<>();

                    System.out.println("Where do you want to fly from?");
                    for (Airport departure : departuresBD) {
                        String city = departure.getCity();
                        if (!printedCities.contains(city)) {
                            System.out.println(city);
                            printedCities.add(city);
                        }
                    }

                    boolean isValidDeparture = false;
                    String departureID = null;
                    while (!isValidDeparture) {
                        try {
                            input = new Scanner(System.in);
                            String inputDeparture = input.nextLine();
                            final String finalInputDeparture = inputDeparture;
                            departureID = departuresBD.stream()
                                    .filter(departure -> departure.getCity().equals(finalInputDeparture))
                                    .map(departure -> departure.getId().toString())
                                    .findFirst()
                                    .orElse(null);
                            if (departureID == null) {
                                throw new InvalidDestinationException("No destinations available from " + inputDeparture);
                            } else {
                                isValidDeparture = true;
                            }
                        } catch (InvalidDestinationException e) {
                            System.out.println(e.getMessage());
                        }
                    }

                    final UUID finalDepartureId = UUID.fromString(departureID);
                    List<Airport> destinations = flightsBD.stream()
                            .filter(flight -> flight.getDeparture().getId().equals(finalDepartureId))
                            .map(flight -> flight.getDestination())
                            .collect(Collectors.toList());

                    printedCities = new HashSet<>();

                    System.out.println("Where do you want to fly to?");
                    for (Airport destination : destinations) {
                        String city = destination.getCity();
                        if (!printedCities.contains(city)) {
                            System.out.println(city);
                            printedCities.add(city);
                        }
                    }

                    boolean isValidDestination = false;
                    String destinationID = null;
                    while (!isValidDestination) {
                        try {
                            input = new Scanner(System.in);
                            String inputDestination = input.nextLine();
                            final String finalInputDestination = inputDestination;
                            destinationID = destinations.stream()
                                    .filter(destination -> destination.getCity().equals(finalInputDestination))
                                    .map(destination -> destination.getId().toString())
                                    .findFirst()
                                    .orElse(null);
                            if (destinationID == null) {
                                throw new NoFlightsException("No flights available from the selected departure to the destination");
                            } else {
                                isValidDestination = true;
                            }
                        } catch (NoFlightsException e) {
                            System.out.println(e.getMessage());
                        }
                    }


                    final UUID finalDepartureId_ = UUID.fromString(departureID);
                    final UUID finalDestinationId_ = UUID.fromString(destinationID);
                    List<Flight> flights = flightsBD.stream()
                            .filter(flight -> flight.getDeparture().getId().equals(finalDepartureId_))
                            .filter(flight -> flight.getDestination().getId().equals(finalDestinationId_))
                            .collect(Collectors.toList());

                    System.out.println(flights);


                    boolean validFlight = false;
                    Flight flight = null;
                    UUID FlightId = null;
                    while (!validFlight) {
                        try {
                            System.out.println("Pick a flight!");
                            input = new Scanner(System.in);
                            String flightID = input.nextLine();
                            UUID flightUUID = UUID.fromString(flightID);
                            FlightId = flightUUID ;

                            flight = flights.stream()
                                    .filter(flight1 -> flight1.getId().equals(flightUUID))
                                    .findFirst()
                                    .orElse(null);
                            if (flight == null)
                                throw new InvalidFlightException("This flight dosen't exist! Try again!");

                            validFlight = true;
                        } catch (InvalidFlightException e) {
                            System.out.println(e.getMessage());
                        }
                    }

                    FlightServiceImpl flightService = new FlightServiceImpl(flight);

                    System.out.println("Free seats in Economy: " + flightService.getFreeSeatsEconomy());
                    System.out.println("Free seats in Business: " + flightService.getFreeSeatsBusiness());
                    System.out.println("Free seats in First Class: " + flightService.getFreeSeatsFirstClass());

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
                    }

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
                            .setId(UUID.randomUUID())
                            .setEmail(email)
                            .setPhoneNumber(phoneNumber)
                            .build();

                    Seat[] seats = flight.getSeats();
                    TicketRepositoryImpl ticketRepository = new TicketRepositoryImpl();
                    List <Ticket> tickets = ticketRepository.getTicketsByFlightId(FlightId) ;
                    List <Integer> seat_numbers = new ArrayList<>() ;
                    for (Ticket ticket : tickets) {
                        int seatNumber = ticket.getSeat().getNumber() ;
                        seat_numbers.add(seatNumber) ;
                    }

                    for (Seat seatIt : seats) {
                        if (seatIt.getSeatType().toString().equals(inputSeatType) && seatIt.getSeatClass().toString().equals(inputSeatClass)) {
                            if (!seat_numbers.contains(seatIt.getNumber())) {
                                flightService.reserveSeat(seatIt.getNumber());
                                Seat inputSeat = seatIt;
                                Ticket inputTicket = new Ticket.TicketBuilder()
                                        .setId(UUID.randomUUID())
                                        .setPassenger(inputPassenger)
                                        .setFlight(flight)
                                        .setSeat(inputSeat)
                                        .build();
                                System.out.println("The price will be: " + inputTicket.getPrice() + ". Continue? (YES/NO)");
                                input = new Scanner(System.in);
                                String decision = input.nextLine().toString();
                                if (decision.equals("YES")) {
                                    PassengerRepositoryImpl passengerRepository = new PassengerRepositoryImpl();
                                    passengerRepository.addNewPassenger(inputPassenger);

                                    TicketServiceImpl ticketService = new TicketServiceImpl(inputTicket);
                                    String[] content = {
                                            "Airline: " + inputTicket.getFlight().getAirline().getName() + '\n',
                                            "From: " + inputTicket.getFlight().getDeparture().getCity() + '\n',
                                            "To: " + inputTicket.getFlight().getDestination().getCity() + '\n',
                                            "On: " + inputTicket.getFlight().getDate() + '\n',
                                            "First name: " + inputTicket.getPassenger().getFirstName() + '\n',
                                            "Last name: " + inputTicket.getPassenger().getLastName() + '\n'
                                    };
                                    try {
                                        CSV_WRITER.appendToCSV(content, filePath);
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }

                                    ticketRepository.addNewTicket(inputTicket);
                                    tsService.bookTicket(inputTicket);

                                }
                                break;
                            }
                        }
                    }
                    tsService.printAllTickets();

                }
            }
        }
    }
}
