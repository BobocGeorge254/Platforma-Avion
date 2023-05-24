package model;

public class Passenger extends Human {

    private static int index = 0 ;
    private int id ;
    private String email;
    private String phoneNumber;

    private Passenger(PassengerBuilder builder) {
        super(builder.firstName, builder.lastName);
        this.id = builder.id ;
        this.email = builder.email;
        this.phoneNumber = builder.phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public int getId() {
        return id;
    }

    public static class PassengerBuilder {

        private int id ;
        private String firstName;
        private String lastName;
        private String email;
        private String phoneNumber;

        public PassengerBuilder(String firstName, String lastName) {
            this.id = index ;
            this.firstName = firstName;
            this.lastName = lastName;
        }

        public PassengerBuilder setEmail(String email) {
            this.email = email;
            return this;
        }

        public PassengerBuilder setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        public Passenger build() {
            return new Passenger(this);
        }
    }

    @Override
    public String toString() {
        return "Passenger{" +
                "firstName='" + getFirstName() + '\'' +
                ", lastName='" + getLastName() + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}