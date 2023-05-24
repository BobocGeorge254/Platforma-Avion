package service;

import java.util.ArrayList;
import java.util.List;
import model.Flight ;
import model.Ticket ;

public interface TicketSystemService {
    void addFlight(Flight flight) ;

    void bookTicket(Ticket ticket) ;

    public List<String> destinations(String departure) ;
    List<Flight> flights(String departure, String destination) ;
    void printAllTickets() ;
}
