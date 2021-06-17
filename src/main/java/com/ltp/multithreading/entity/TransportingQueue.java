package com.ltp.multithreading.entity;

import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;

public class TransportingQueue {

    private static final int MAX_CAPACITY = 5;
    private static final int MAX_WEIGHT = 25;

    private ReentrantLock mainLock, transportingLock;
    private Queue<Car> cars;
    private CountDownLatch countDownLatch = new CountDownLatch(6);

    private TransportingQueue(){
        mainLock = new ReentrantLock(true);
        transportingLock = new ReentrantLock(true);
        cars = new ArrayDeque<>();
    }

    public void addCar(Car car){
        try{
            mainLock.lock();
            cars.offer(car);
        } finally {
            mainLock.unlock();
        }
    }

    public void transport(Car car){
        try {
            transportingLock.lock();

            car.getCarState().next(car);
            count();
        } finally {
            transportingLock.unlock();
        }
    }

    public void invoke(){
        try{
            countDownLatch.await();
            while(cars.size() > 0){
                Car currentCar = cars.element();

                int leftCapacity = MAX_CAPACITY;
                int leftWeight = MAX_WEIGHT;

                while(currentCar != null && currentCar.getWeight() <= leftWeight && leftCapacity > 0){
                    leftCapacity--;
                    leftWeight -= currentCar.getWeight();

                    currentCar.getCarState().next(currentCar);

                    cars.remove();

                    if(cars.size() > 0){
                        currentCar = cars.element();
                    }else{
                        currentCar = null;
                    }
                }

                countDownLatch = new CountDownLatch(MAX_CAPACITY - leftCapacity);

                countDownLatch.await();
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void count(){
        if (countDownLatch == null) {
            return;
        }

        countDownLatch.countDown();
    }

    public static TransportingQueue getInstance(){
        return TransportingQueueHolder.instance;
    }

    private static class TransportingQueueHolder {
        public static final TransportingQueue instance = new TransportingQueue();
    }

}
