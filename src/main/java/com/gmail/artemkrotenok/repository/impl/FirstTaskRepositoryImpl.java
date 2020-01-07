package com.gmail.artemkrotenok.repository.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.invoke.MethodHandles;

import com.gmail.artemkrotenok.repository.FirstTaskRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FirstTaskRepositoryImpl implements FirstTaskRepository {

    private static final Logger logger = LogManager.getLogger(MethodHandles.lookup().lookupClass());

    @Override
    public void run(String commandsFileInputName) {
        File file = new File(commandsFileInputName);
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            String line = bufferedReader.readLine();
            while (line != null) {
                logger.info(line);
                line = bufferedReader.readLine();
            }
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
            throw new IllegalStateException("Cannot read from file: " + commandsFileInputName);
        }
    }

}