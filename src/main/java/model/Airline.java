package model;
import model.enums.AirlineType ;

import java.util.UUID;

public class Airline {

    private UUID id;
    private String name;
    private AirlineType type;

    public Airline() {}

    public Airline(AirlineBuilder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.type = builder.type;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public AirlineType getType() {
        return type;
    }

    public static class AirlineBuilder {
        private UUID id;
        private String name;
        private AirlineType type;

        public AirlineBuilder setId(UUID id) {
            this.id = id;
            return this;
        }

        public AirlineBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public AirlineBuilder setType(AirlineType type) {
            this.type = type;
            return this;
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
