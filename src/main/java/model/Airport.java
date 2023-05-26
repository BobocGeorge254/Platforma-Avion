package model;

import model.enums.AirlineType;

import java.util.UUID;

public class Airport {

    private UUID id ;
    private String city ;
    private String address ;

    public Airport() {}

    public Airport(AirportBuilder builder) {
        this.id = builder.id ;
        this.city = builder.city;
        this.address = builder.address;
    }


    public UUID getId() {
        return id;
    }

    public String getCity() {
        return city;
    }

    public String getAddress() {
        return address;
    }

    @Override
    public String toString() {
        return "Airport{" +
                "id='" + id + '\'' +
                ", city='" + city + '\'' +
                ", address='" + address + '\'' +
                '}';
    }

    public static class AirportBuilder {
        private UUID id ;
        private String city ;
        private String address ;

        public AirportBuilder setId(UUID id) {
            this.id = id ;
            return this ;
        }

        public AirportBuilder setCity(String city) {
            this.city = city;
            return this ;
        }
        public AirportBuilder setAddress(String address) {
            this.address = address;
            return this ;
        }
        public Airport build() {
            return new Airport(this);
        }
    }
}
