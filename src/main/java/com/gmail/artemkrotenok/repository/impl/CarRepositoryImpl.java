package com.gmail.artemkrotenok.repository.impl;

import java.lang.invoke.MethodHandles;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.gmail.artemkrotenok.repository.CarRepository;
import com.gmail.artemkrotenok.repository.model.Car;
import com.gmail.artemkrotenok.repository.model.CarModelEnum;
import com.gmail.artemkrotenok.util.RandomUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.gmail.artemkrotenok.repository.constant.CarConstant.COLUMN_CAR_MODEL;
import static com.gmail.artemkrotenok.repository.constant.CarConstant.COLUMN_ENGINE_CAPACITY;
import static com.gmail.artemkrotenok.repository.constant.CarConstant.COLUMN_NAME;
import static com.gmail.artemkrotenok.repository.constant.CarConstant.MAX_ENGINE_CAPACITY;
import static com.gmail.artemkrotenok.repository.constant.CarConstant.MIN_ENGINE_CAPACITY;

public class CarRepositoryImpl implements CarRepository {

    private static final Logger logger = LogManager.getLogger(MethodHandles.lookup().lookupClass());

    @Override
    public void addRandomRecords(Connection connection, int countRecords) throws SQLException {
        try (
                PreparedStatement statement = connection.prepareStatement(
                        "INSERT INTO car(name, car_model, engine_capacity) VALUES (?,?,?)"
                )
        ) {
            for (int i = 0; i < countRecords; i++) {
                Car car = getNewRandomCar();
                statement.setString(1, car.getName());
                statement.setString(2, car.getCarModel().toString());
                statement.setInt(3, car.getEngineCapacity());
                int affectedRows = statement.executeUpdate();
                if (affectedRows == 0) {
                    throw new SQLException("Creating record failed, no rows affected.");
                }
            }
            logger.info(countRecords + " - records, was add.");
        }
    }

    @Override
    public void printToConsoleRecords(Connection connection, int engineCapacity) throws SQLException {
        try (
                PreparedStatement statement = connection.prepareStatement(
                        "SELECT * FROM car WHERE engine_capacity=?"
                )
        ) {
            statement.setInt(1, engineCapacity);
            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    logger.info(getCar(rs));
                }
            }
        }
    }

    private Car getCar(ResultSet rs) throws SQLException {
        String name = rs.getString(COLUMN_NAME);
        CarModelEnum carModel = CarModelEnum.valueOf(rs.getString(COLUMN_CAR_MODEL));
        Integer engineCapacity = rs.getInt(COLUMN_ENGINE_CAPACITY);
        return Car.newBuilder()
                .name(name)
                .carModel(carModel)
                .engineCapacity(engineCapacity)
                .build();
    }

    @Override
    public void deleteWithMinEngineCapacity(Connection connection) throws SQLException {
        try (
                Statement statement = connection.createStatement();
        ) {
            int countDeleteRecords = statement.executeUpdate(
                    "DELETE FROM car WHERE engine_capacity = (SELECT * FROM (SELECT MIN(engine_capacity) FROM car) AS t1);");
            logger.info("Deleted " + countDeleteRecords + " records with min engine capacity");
        }
    }

    @Override
    public void printToConsoleCountRecords(Connection connection, int engineCapacity) throws SQLException {
        try (
                PreparedStatement statement = connection.prepareStatement(
                        "SELECT COUNT(*) FROM car WHERE engine_capacity=?"
                )
        ) {
            statement.setInt(1, engineCapacity);
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    logger.info(rs.getInt("COUNT(*)") + " - records with engine capacity = " + engineCapacity);
                }
            }
        }
    }

    @Override
    public void renameRecords(Connection connection, int engineCapacity, String newTitle) throws SQLException {
        try (
                PreparedStatement statement = connection.prepareStatement(
                        "UPDATE car SET name =? WHERE engine_capacity =?;"
                )
        ) {
            statement.setString(1, newTitle);
            statement.setInt(2, engineCapacity);
            int affectedRows = statement.executeUpdate();
            logger.info(affectedRows + " - records have been updated.");
        }
    }

    private Car getNewRandomCar() {
        {
            String name = "Name" + RandomUtil.getRandomNumber(1, 100);
            CarModelEnum carModel = getRandomCarModel();
            Integer engineCapacity = RandomUtil.getRandomNumber(MIN_ENGINE_CAPACITY, MAX_ENGINE_CAPACITY);
            return Car.newBuilder()
                    .name(name)
                    .carModel(carModel)
                    .engineCapacity(engineCapacity)
                    .build();
        }
    }

    private CarModelEnum getRandomCarModel() {
        int minCarModelEnum = 1;
        int maxCarModelEnum = 3;
        int randomCarModelEnum = RandomUtil.getRandomNumber(minCarModelEnum, maxCarModelEnum);
        switch (randomCarModelEnum) {
            case 1: {
                return CarModelEnum.UNIVERSAL;
            }
            case 2: {
                return CarModelEnum.SEDAN;
            }
            case 3: {
                return CarModelEnum.HATCHBACK;
            }
            default: {
                logger.error("Error range CarModelEnum");
            }
        }
        return null;
    }

}