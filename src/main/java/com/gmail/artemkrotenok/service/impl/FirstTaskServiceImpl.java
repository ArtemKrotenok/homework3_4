package com.gmail.artemkrotenok.service.impl;

import com.gmail.artemkrotenok.repository.FirstTaskRepository;
import com.gmail.artemkrotenok.repository.impl.FirstTaskRepositoryImpl;
import com.gmail.artemkrotenok.service.FirstTaskService;

public class FirstTaskServiceImpl implements FirstTaskService {

    @Override
    public void run(String commandsFileInputName) {
        FirstTaskRepository firstTaskRepository = new FirstTaskRepositoryImpl();
        firstTaskRepository.run(commandsFileInputName);
    }

}