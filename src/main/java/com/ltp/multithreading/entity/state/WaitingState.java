package com.ltp.multithreading.entity.state;

import com.ltp.multithreading.entity.Car;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class WaitingState implements CarState{

    private final Logger LOGGER = LogManager.getLogger();

    public void next(Car car) {
        LOGGER.info(String.format("State of %s changed to TransportingState", car.toString()));
        car.setCarState(new TransportingState());
    }

    @Override
    public String toString(){
        return new String("{WaitingState}");
    }
}
