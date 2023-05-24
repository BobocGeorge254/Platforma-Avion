package model;
import model.enums.* ;

public class Ticket {
    private static int index = 1 ;
    private int id ;
    private Passenger passenger ;
    private Flight flight ;
    private Seat seat ;
    private double price ;

    public Ticket() {}

    public Ticket(TicketBuilder builder) {
        this.id = builder.id; ;
        this.passenger = builder.passenger;
        this.flight = builder.flight;
        this.seat = builder.seat;
        this.price = this.getPrice() ;
        index = index + 1 ;
    }

    public double getPrice() {
        double price = 0;
        switch (this.flight.getAirline().getType()) {
            case LOWCOST -> price = 100;
            case COMMERCIAL -> price = 300;
            case CHARTER -> price = 1000;
        }
        switch (this.seat.getSeatClass()) {
            case ECONOMY -> price = 0.85 * price;
            case BUSINESS -> price = 1.25 * price;
            case FIRSTCLASS -> price = 3 * price;
        }
        switch (this.seat.getSeatType()) {
            case AISLE -> price = 0.85 * price;
            case MIDDLE -> price = 1 * price;
            case WINDOW -> price = 1.25 * price;
        }
        return price;
    }
    public Passenger getPassenger() {
        return passenger;
    }

    public Flight getFlight() {
        return flight;
    }

    public Seat getSeat() {
        return seat;
    }



    public static class TicketBuilder {
        private int id ;
        private Passenger passenger ;
        private Flight flight ;
        private Seat seat ;
        private double price ;

        public TicketBuilder() {
            this.id = index ;
        }
        public TicketBuilder setPrice(double price) {
            this.price = price ;
            return this ;
        }
        public TicketBuilder setPassenger(Passenger passenger) {
            this.passenger = passenger;
            return this ;
        }
        public TicketBuilder setFlight(Flight flight) {
            this.flight = flight;
            return this ;
        }
        public TicketBuilder setSeat(Seat seat) {
            this.seat = seat;
            return this ;
        }
        public Ticket build() {
            return new Ticket(this);
        }
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", passenger=" + passenger +
                ", flight=" + flight +
                ", seat=" + seat +
                ", price=" + price +
                '}';
    }
}
