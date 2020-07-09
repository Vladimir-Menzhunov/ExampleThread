package com.vladimir;

import java.util.concurrent.*;

public class Sample {

    private static volatile int mCount = 0;

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        int floors = 10;
        int countThread = floors * 4 / 10;
        ExecutorService executorService = Executors.newFixedThreadPool(countThread);

        for (int x = 0; x < floors; x++) {
            mCount += executorService.submit(new MyCallable()).get();
        }
        executorService.shutdown();
        System.out.println("Этажей - " + floors);
        System.out.println("Людей - " + mCount);
        System.out.println("Потоков - " + countThread);
    }

    private static class MyCallable implements Callable<Integer> {
        @Override
        public Integer call() throws Exception {
            int count = 0;
            System.out.println("Thread - " + Thread.currentThread().getName());
            for (int i = 0; i < 100; i++) {
                count++;
            }
            return count;
        }
    }
}