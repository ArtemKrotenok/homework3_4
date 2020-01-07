package com.gmail.artemkrotenok.service;

public interface CarService {

    void addRandomRecords(int countRecords);

    void printToConsoleRecords(int engineCapacity);

    void deleteWithMinEngineCapacity();

    void printToConsoleCountRecords(int engineCapacity);

    void renameRecordsAndPrintToConsole(int engineCapacity, String newTitle);

}