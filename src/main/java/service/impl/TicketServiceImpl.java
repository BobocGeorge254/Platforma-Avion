package service.impl;

import application.csv.CsvWriter;
import model.Ticket;
import service.TicketService;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class TicketServiceImpl implements TicketService {
    private Ticket ticket ;

    public TicketServiceImpl(Ticket ticket) {
        this.ticket = ticket ;
    }
    public double getPrice() {
        double price = 0;
        switch (ticket.getFlight().getAirline().getType()) {
            case LOWCOST -> price = 100;
            case COMMERCIAL -> price = 300;
            case CHARTER -> price = 1000;
        }
        switch (ticket.getSeat().getSeatClass()) {
            case ECONOMY -> price = 0.85 * price;
            case BUSINESS -> price = 1.25 * price;
            case FIRSTCLASS -> price = 3 * price;
        }
        switch (ticket.getSeat().getSeatType()) {
            case AISLE -> price = 0.85 * price;
            case MIDDLE -> price = 1 * price;
            case WINDOW -> price = 1.25 * price;
        }
        return price;
    }



}
