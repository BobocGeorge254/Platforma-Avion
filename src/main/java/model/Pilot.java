package model;

import java.util.Date;
import java.util.UUID;

public class Pilot extends Human {

    private UUID id ;
    private String hireDate;

    private Pilot(PilotBuilder builder) {
        super(builder.firstName, builder.lastName);
        this.id = builder.id ;
        this.hireDate = builder.hireDate;
    }



    public String getHireDate() {
        return hireDate;
    }

    public UUID getId() {
        return id;
    }

    public static class PilotBuilder {

        private UUID id ;
        private String firstName;
        private String lastName;
        private String hireDate;

        public PilotBuilder(String firstName, String lastName) {

            this.firstName = firstName;
            this.lastName = lastName;
        }

        public PilotBuilder setId(UUID id) {
            this.id = id ;
            return this ;
        }

        public PilotBuilder setHireDate(String  hireDate) {
            this.hireDate = hireDate;
            return this;
        }

        public Pilot build() {
            return new Pilot(this);
        }
    }

    @Override
    public String toString() {
        return "Pilot{" +
                "id='" + getId() + '\'' +
                ", firstName='" + getFirstName() + '\'' +
                ", lastName='" + getLastName() + '\'' +
                ", hireDate=" + hireDate +
                '}';
    }
}