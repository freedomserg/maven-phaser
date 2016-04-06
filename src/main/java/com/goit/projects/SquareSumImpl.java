package com.goit.projects;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.IntStream;

public class SquareSumImpl implements SquareSum {
    private List<Callable<Long>> tasks = new ArrayList<>();
    private final Phaser phaser = new Phaser();
    private ExecutorService executor;
    private List<Future<Long>> results;
    private long ultimateSum;

    @Override
    public long getSquareSum(int[] values, int numberOfThreads) {
        IntStream.range(0, numberOfThreads).forEach(i -> {
            tasks.add(() -> {
                phaser.register();

                long intermediateSum = 0;
                int startPos = (values.length * i) / numberOfThreads;
                int endPos = (values.length * (i + 1)) / numberOfThreads;
                for (int j = startPos; j < endPos; j++) {
                    intermediateSum += Math.pow(values[j], 2);
                }

                phaser.arriveAndAwaitAdvance();
                phaser.arriveAndDeregister();

                return intermediateSum;
            });
        });

        executor = Executors.newFixedThreadPool(numberOfThreads);
        try {
            results = executor.invokeAll(tasks);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for (Future future : results) {
            try {
                ultimateSum += (long)future.get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
        executor.shutdown();

        return ultimateSum;
    }
}
