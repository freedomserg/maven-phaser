package com.goit.projects;

import org.junit.Assert;
import org.junit.Test;

import java.util.Random;
import java.util.stream.IntStream;

public class SquareSumImplTest {

    @Test
    public void testGetSquareSum() throws Exception {
        int[] digits = new int[32];
        Random random = new Random();
        IntStream.range(0, digits.length).forEach(i -> {
            digits[i] = random.nextInt(100);
        });
        long actual = new SquareSumImpl().getSquareSum(digits, 7);

        long expected = 0;
        for (int digit : digits) {
            expected += Math.pow(digit, 2);
        }
        Assert.assertEquals(expected, actual);
    }
}