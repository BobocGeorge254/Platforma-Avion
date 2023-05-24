package model;
import model.enums.AirlineType ;

import java.util.UUID;

public class Airline {


    static int index = 0 ;
    private int id ;
    private String name ;
    private AirlineType type ;

    public Airline() {}

    public Airline(AirlineBuilder builder) {
        index = index + 1 ;
        this.id = builder.id; ;
        this.name = builder.name;
        this.type = builder.type;

    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public AirlineType getType() {
        return type;
    }

    public static class AirlineBuilder {
        private int id ;
        private String name ;
        private AirlineType type ;
        public AirlineBuilder() {
            this.id = index ;
        }
        public AirlineBuilder setName(String name) {
            this.name = name;
            return this ;
        }
        public AirlineBuilder setType(AirlineType type) {
            this.type = type;
            return this ;
        }
        public Airline build() {
            return new Airline(this);
        }

    }
    @Override
    public String toString() {
        return "Airline{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", type=" + type +
                '}';
    }
}
