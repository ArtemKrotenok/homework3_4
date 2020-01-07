package com.gmail.artemkrotenok.service.impl;

import java.lang.invoke.MethodHandles;
import java.sql.Connection;
import java.sql.SQLException;

import com.gmail.artemkrotenok.repository.ConnectionRepository;
import com.gmail.artemkrotenok.repository.CarRepository;
import com.gmail.artemkrotenok.repository.impl.ConnectionRepositoryImpl;
import com.gmail.artemkrotenok.repository.impl.CarRepositoryImpl;
import com.gmail.artemkrotenok.service.CarService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CarServiceImpl implements CarService {

    private static final Logger logger = LogManager.getLogger(MethodHandles.lookup().lookupClass());
    private ConnectionRepository connectionRepository = new ConnectionRepositoryImpl();
    private CarRepository carRepository = new CarRepositoryImpl();

    @Override
    public void addRandomRecords(int countRecords) {
        try (Connection connection = connectionRepository.getConnection()) {
            connection.setAutoCommit(false);
            try {
                carRepository.addRandomRecords(connection, countRecords);
                connection.commit();
            } catch (SQLException e) {
                connection.rollback();
                logger.error(e.getMessage(), e);
            }
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
        }
    }

    @Override
    public void printToConsoleRecords(int engineCapacity) {
        try (Connection connection = connectionRepository.getConnection()) {
            connection.setAutoCommit(false);
            try {
                carRepository.printToConsoleRecords(connection, engineCapacity);
                connection.commit();
            } catch (SQLException e) {
                connection.rollback();
                logger.error(e.getMessage(), e);
            }
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
        }
    }

    @Override
    public void deleteWithMinEngineCapacity() {
        try (Connection connection = connectionRepository.getConnection()) {
            connection.setAutoCommit(false);
            try {
                carRepository.deleteWithMinEngineCapacity(connection);
                connection.commit();
            } catch (SQLException e) {
                connection.rollback();
                logger.error(e.getMessage(), e);
            }
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
        }
    }

    @Override
    public void printToConsoleCountRecords(int engineCapacity) {
        try (Connection connection = connectionRepository.getConnection()) {
            connection.setAutoCommit(false);
            try {
                carRepository.printToConsoleCountRecords(connection, engineCapacity);
                connection.commit();
            } catch (SQLException e) {
                connection.rollback();
                logger.error(e.getMessage(), e);
            }
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
        }
    }

    @Override
    public void renameRecordsAndPrintToConsole(int engineCapacity, String newTitle) {
        try (Connection connection = connectionRepository.getConnection()) {
            connection.setAutoCommit(false);
            try {
                carRepository.renameRecords(connection, engineCapacity, newTitle);
                carRepository.printToConsoleRecords(connection, engineCapacity);
                connection.commit();
            } catch (SQLException e) {
                connection.rollback();
                logger.error(e.getMessage(), e);
            }
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
        }
    }

}