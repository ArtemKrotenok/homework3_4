package com.gmail.artemkrotenok.util;

import java.util.Random;

public class RandomUtil {

    private static final Random RANDOM_NUMBER = new Random();

    public static int getRandomNumber(int minElement, int maxElement) {
        return RANDOM_NUMBER.nextInt(maxElement - minElement + 1) + minElement;
    }

}