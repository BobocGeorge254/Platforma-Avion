package model;

import model.enums.AirlineType;

public class Airport {

    private static int index = 0 ;
    private int id ;
    private String city ;
    private String address ;

    public Airport() {}

    public Airport(AirportBuilder builder) {
        this.id = builder.id; ;
        this.city = builder.city;
        this.address = builder.address;
        index = index + 1 ;
    }

    public int getId() {
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
                "city='" + city + '\'' +
                ", address='" + address + '\'' +
                '}';
    }

    public static class AirportBuilder {

        private int id ;
        private String city ;
        private String address ;

        public AirportBuilder() {
            this.id = index ;
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
