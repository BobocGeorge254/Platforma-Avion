package service.impl;

import service.TicketService;
import service.TicketSystemService;
import service.impl.TicketServiceImpl ;
import model.*;

import java.util.ArrayList;
import java.util.List;

public class TicketSystemServiceImpl implements TicketSystemService {

    private TicketSystem ticketSystem ;

    public TicketSystemServiceImpl(TicketSystem ticketSystem) {
        this.ticketSystem = ticketSystem;
    }
    public void addFlight(Flight flight) {
        List<Flight> flights = ticketSystem.getFlights() ;
        flights.add(flight) ;
        ticketSystem.setFlights(flights);
    }

    public void bookTicket(Ticket ticket) {
        Flight flight = ticket.getFlight() ;
        FlightServiceImpl flightService = new FlightServiceImpl(flight) ;
        boolean isReserved = flightService.reserveSeat(ticket.getSeat().getNumber()) ;
        if (isReserved == false) {
            List<Ticket> tickets = ticketSystem.getTicketList() ;
            tickets.add(ticket) ;
            ticketSystem.setTicketList(tickets);
        }
    }

    public List<String> destinations(String departure) {
        List<String> dest = new ArrayList<>() ;
        for (int i = 0 ; i < ticketSystem.getFlights().size() ; ++i)
            if (ticketSystem.getFlights().get(i).getDeparture().getCity().equals(departure)) {
                dest.add(ticketSystem.getFlights().get(i).getDestination().getCity()) ;
            }
        return dest ;
    }

    public List<Flight> flights(String departure, String destination) {
        List<Flight> fls = new ArrayList<>() ;
        for (int i = 0 ; i < ticketSystem.getFlights().size() ; ++i) {
            if (ticketSystem.getFlights().get(i).getDeparture().getCity().equals(departure) &&
                    ticketSystem.getFlights().get(i).getDestination().getCity().equals(destination) ) {
                fls.add(ticketSystem.getFlights().get(i)) ;
            }
        }
        return fls ;
    }
    public void printAllTickets() {
        for (int i = 0 ; i < ticketSystem.getTicketList().size() ; ++i) {
            System.out.println(ticketSystem.getTicketList().get(i).getPassenger().toString());
            System.out.println(ticketSystem.getTicketList().get(i).getFlight().toString());
            System.out.println(ticketSystem.getTicketList().get(i).getSeat().toString());
            System.out.println();
        }
    }
}
