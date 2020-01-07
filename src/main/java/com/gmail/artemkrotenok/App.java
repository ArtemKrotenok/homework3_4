package com.gmail.artemkrotenok;

import com.gmail.artemkrotenok.service.CarService;
import com.gmail.artemkrotenok.service.FirstTaskService;
import com.gmail.artemkrotenok.service.impl.CarServiceImpl;
import com.gmail.artemkrotenok.service.impl.FirstTaskServiceImpl;
import com.gmail.artemkrotenok.util.RandomUtil;

import static com.gmail.artemkrotenok.repository.constant.CarConstant.MAX_ENGINE_CAPACITY;
import static com.gmail.artemkrotenok.repository.constant.CarConstant.MIN_ENGINE_CAPACITY;

public class App {

    private static final String COMMANDS_FILE_INPUT_NAME = "src\\main\\resources\\commands.sql";
    private static final int COUNT_ADD_RANDOM_RECORDS = 10;
    private static final String NEW_TITLE_FOR_CAR_NAME = "Good capacity";

    public static void main(String[] args) {
        FirstTaskService firstTaskService = new FirstTaskServiceImpl();
        firstTaskService.run(COMMANDS_FILE_INPUT_NAME);
        CarService carService = new CarServiceImpl();
        carService.addRandomRecords(COUNT_ADD_RANDOM_RECORDS);
        int selectedEngineCapacity = RandomUtil.getRandomNumber(MIN_ENGINE_CAPACITY, MAX_ENGINE_CAPACITY);
        carService.printToConsoleRecords(selectedEngineCapacity);
        carService.deleteWithMinEngineCapacity();
        selectedEngineCapacity = RandomUtil.getRandomNumber(MIN_ENGINE_CAPACITY, MAX_ENGINE_CAPACITY);
        carService.printToConsoleCountRecords(selectedEngineCapacity);
        selectedEngineCapacity = RandomUtil.getRandomNumber(MIN_ENGINE_CAPACITY, MAX_ENGINE_CAPACITY);
        carService.renameRecordsAndPrintToConsole(selectedEngineCapacity, NEW_TITLE_FOR_CAR_NAME);
    }

}