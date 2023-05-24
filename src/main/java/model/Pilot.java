package model;

import java.util.Date;

public class Pilot extends Human {

    private static int index = 0 ;
    private int id ;
    private String hireDate;

    private Pilot(PilotBuilder builder) {
        super(builder.firstName, builder.lastName);
        this.id = builder.id ;
        this.hireDate = builder.hireDate;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHireDate() {
        return hireDate;
    }

    public int getId() {
        return id;
    }

    public static class PilotBuilder {

        private int id ;
        private String firstName;
        private String lastName;
        private String hireDate;

        public PilotBuilder(String firstName, String lastName) {
            this.id = index ;
            index ++ ;
            this.firstName = firstName;
            this.lastName = lastName;
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