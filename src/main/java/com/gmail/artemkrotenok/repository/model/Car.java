package com.gmail.artemkrotenok.repository.model;

public class Car {

    private String name;
    private CarModelEnum carModel;
    private Integer engineCapacity;

    public String getName() {
        return name;
    }

    public CarModelEnum getCarModel() {
        return carModel;
    }

    public Integer getEngineCapacity() {
        return engineCapacity;
    }

    private Car(Builder builder) {
        name = builder.name;
        carModel = builder.carModel;
        engineCapacity = builder.engineCapacity;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static final class Builder {

        private String name;
        private CarModelEnum carModel;
        private Integer engineCapacity;

        private Builder() {}

        public Builder name(String val) {
            name = val;
            return this;
        }

        public Builder carModel(CarModelEnum val) {
            carModel = val;
            return this;
        }

        public Builder engineCapacity(Integer val) {
            engineCapacity = val;
            return this;
        }

        public Car build() {
            return new Car(this);
        }

    }

    @Override
    public String toString() {
        return "Car: name=" + name +
                ", model=" + carModel +
                ", engine capacity=" + engineCapacity;
    }

}