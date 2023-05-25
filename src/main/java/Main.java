import java.util.*;

import model.*;
import model.enums.*;
import repository.PassengerRepository;
import repository.impl.*;
import service.impl.*;
import exceptions.*;

import java.util.stream.Collectors;

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

        else {

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

                System.out.println("Where do you want to fly from?");
                for (Airport departure : departuresBD) {
                    System.out.println(departure.getCity());
                }

                boolean isValidDeparture = false ;
                Integer departureID = null ;
                while (!isValidDeparture) {
                    try {
                        input = new Scanner(System.in);
                        String inputDeparture = input.nextLine();
                        final String finalInputDeparture = inputDeparture;
                        departureID = departuresBD.stream()
                                .filter(departure -> departure.getCity().equals(finalInputDeparture))
                                .map(departure -> departure.getId())
                                .findFirst()
                                .orElse(null);
                        if (departureID == null) {
                            throw new InvalidDestinationException("No destinations available from " + inputDeparture);
                        } else {
                            isValidDeparture = true;
                        }
                    }
                    catch (InvalidDestinationException e) {
                        System.out.println(e.getMessage());
                    }
                }

               final Integer finalDepartureId = departureID ;
               List<Airport> destinations = flightsBD.stream()
                       .filter(flight -> flight.getDeparture().getId() == finalDepartureId)
                       .map(flight -> flight.getDestination())
                       .collect(Collectors.toList());


               System.out.println("Where do you want to fly to?");
               for (Airport destination : destinations) {
                   System.out.println(destination.getCity());
               }

               boolean isValidDestination = false ;
               Integer destinationID = null ;
               while (!isValidDestination) {
                   try {
                       input = new Scanner(System.in);
                       String inputDestination = input.nextLine();
                       final String finalInputDestination = inputDestination;
                       destinationID = destinations.stream()
                               .filter(destination -> destination.getCity().equals(finalInputDestination))
                               .map(destination -> destination.getId())
                               .findFirst()
                               .orElse(null);
                       if (destinationID == null) {
                           throw new NoFlightsException("No flights available from the selected departure to the destination") ;
                       } else {
                           isValidDestination = true;
                       }
                   }
                   catch (NoFlightsException e) {
                       System.out.println(e.getMessage());
                   }
               }


               final Integer finalDepartureId_ = departureID ;
               final Integer finalDestinationId_ = destinationID ;
               List<Flight> flights = flightsBD.stream()
                       .filter(flight -> flight.getDeparture().getId() == finalDepartureId_)
                       .filter(flight -> flight.getDestination().getId() == finalDestinationId_)
                       .collect(Collectors.toList());

               System.out.println(flights);



               boolean validFlight = false ;
               Flight flight = null ;
               while (!validFlight) {
                   try {
                       System.out.println("Pick a flight!");
                       input = new Scanner(System.in) ;
                       Integer flightID = Integer.valueOf(input.nextLine()) ;

                       flight = flights.stream()
                               .filter(flight1 -> flight1.getId() == flightID)
                               .findFirst()
                               .orElse(null);
                       if (flight == null)
                           throw new InvalidFlightException("This flight dosen't exist! Try again!");

                       validFlight = true ;
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
                        .setEmail(email)
                        .setPhoneNumber(phoneNumber)
                        .build();

                Seat[] seats = flight.getSeats();
                Set<Seat> setSeats = new HashSet<>();
                for (int i = 0; i < seats.length; ++i)
                    setSeats.add(seats[i]);

                for (Seat seatIt : seats) {
                    if (seatIt.getSeatType().toString().equals(inputSeatType) && seatIt.getSeatClass().toString().equals(inputSeatClass)) {
                        if (seatIt.isFree()) {
                            flightService.reserveSeat(seatIt.getNumber());
                            Seat inputSeat = seatIt;
                            Ticket inputTicket = new Ticket.TicketBuilder()
                                    .setPassenger(inputPassenger)
                                    .setFlight(flight)
                                    .setSeat(inputSeat)
                                    .build();
                            System.out.println("The price will be: " + inputTicket.getPrice() + ". Continue? (YES/NO)");
                            input = new Scanner(System.in);
                            String decision = input.nextLine().toString();
                            if (decision.equals("YES")) {
                                PassengerRepositoryImpl passengerRepository = new PassengerRepositoryImpl() ;
                                passengerRepository.addNewPassenger(inputPassenger);
                                //passengerRepository.deletePassengerById(0);

                                TicketRepositoryImpl ticketRepository = new TicketRepositoryImpl() ;
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
