package model;

import java.util.Date;

public class Pilot extends Human {

    private static int index = 0 ;
    private int id ;
    private Date hireDate;

    private Pilot(PilotBuilder builder) {
        super(builder.firstName, builder.lastName);
        this.id = builder.id ;
        this.hireDate = builder.hireDate;
    }

    public Date getHireDate() {
        return hireDate;
    }

    public int getId() {
        return id;
    }

    public static class PilotBuilder {

        private int id ;
        private String firstName;
        private String lastName;
        private Date hireDate;

        public PilotBuilder(String firstName, String lastName) {
            this.id = index ;
            this.firstName = firstName;
            this.lastName = lastName;
        }

        public PilotBuilder setHireDate(Date hireDate) {
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
                "firstName='" + getFirstName() + '\'' +
                ", lastName='" + getLastName() + '\'' +
                ", hireDate=" + hireDate +
                '}';
    }
}