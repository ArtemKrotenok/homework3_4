package com.gmail.artemkrotenok.repository;

import java.sql.Connection;
import java.sql.SQLException;

public interface CarRepository {

    void addRandomRecords(Connection connection, int countRecords) throws SQLException;

    void printToConsoleRecords(Connection connection, int engineCapacity) throws SQLException;

    void deleteWithMinEngineCapacity(Connection connection) throws SQLException;

    void printToConsoleCountRecords(Connection connection, int engineCapacity) throws SQLException;

    void renameRecords(Connection connection, int engineCapacity, String newTitle) throws SQLException;

}