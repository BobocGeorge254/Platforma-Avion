package model;

import java.util.ArrayList;
import java.util.List;
public class TicketSystem {

    private static TicketSystem instance ;
    private List<Flight> flights ;
    private List<Ticket> ticketList ;

    private TicketSystem() {
        flights = new ArrayList<>() ;
        ticketList = new ArrayList<>();
    }
    public static TicketSystem getInstance() {
        if (instance == null) {
            synchronized (TicketSystem.class) {
                if (instance == null) {
                    instance = new TicketSystem();
                }
            }
        }
        return instance;
    }
    public List<Flight> getFlights() {
        return flights;
    }

    public void setFlights(List<Flight> flights) {
        this.flights = flights;
    }

    public List<Ticket> getTicketList() {
        return ticketList;
    }

    public void setTicketList(List<Ticket> ticketList) {
        this.ticketList = ticketList;
    }
}
