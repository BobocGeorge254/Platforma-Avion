package model;

import java.util.Date;
import model.Seat;

import java.util.Date;

public class Flight {
    private static int index = 1000;
    private int id;
    private Airline airline;
    private Airport departure;
    private Airport destination;
    private Seat[] seats;
    private String date;
    private Pilot pilot;

    private Flight(FlightBuilder builder) {
        this.id = builder.id;
        this.airline = builder.airline;
        this.departure = builder.departure;
        this.destination = builder.destination;
        this.date = builder.date;
        this.pilot = builder.pilot;
        this.seats = new Seat[141];
        for (int i = 0; i <= 140; ++i) {
            this.seats[i] = new Seat(i);
        }
        index = index + 1;
    }

    public int getId() {
        return id;
    }

    public Airline getAirline() {
        return airline;
    }

    public Airport getDeparture() {
        return departure;
    }

    public Airport getDestination() {
        return destination;
    }

    public Seat[] getSeats() {
        return seats;
    }

    public String getDate() {
        return date;
    }

    public Pilot getPilot() {
        return pilot;
    }

    public void setSeats(Seat[] seats) {
        this.seats = seats ;
    }

    public static class FlightBuilder {
        private int id;
        private Airline airline;
        private Airport departure;
        private Airport destination;
        private Seat[] seats;
        private String date;
        private Pilot pilot;

        public FlightBuilder() {
            this.id = index;
        }

        public FlightBuilder setAirline(Airline airline) {
            this.airline = airline;
            return this;
        }

        public FlightBuilder setDeparture(Airport departure) {
            this.departure = departure;
            return this;
        }

        public FlightBuilder setDestination(Airport destination) {
            this.destination = destination;
            return this;
        }

        public FlightBuilder setDate(String date) {
            this.date = date;
            return this;
        }

        public FlightBuilder setPilot(Pilot pilot) {
            this.pilot = pilot;
            return this;
        }

        public Flight build() {
            return new Flight(this);
        }
    }

    @Override
    public String toString() {
        return "Flight{" +
                "id=" + id +
                ", airline=" + airline +
                ", departure=" + departure +
                ", destination=" + destination +
                ", date=" + date +
                ", pilot=" + pilot +
                '}';
    }







}

