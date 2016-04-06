package com.goit.projects;

import java.util.Random;

public class Bootstrap {
    public static void main(String[] args) {
        int[] array = new int[32];
        Random random = new Random();
        for (int i = 0; i < array.length; i++) {
            array[i] = random.nextInt(1000);
        }

        long res = new SquareSumImpl().getSquareSum(array, 4);
        System.out.println(res);
    }
}
