package com.ltp.multithreading.entity.state;

import com.ltp.multithreading.entity.Car;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TransportingState implements CarState {

    private final Logger LOGGER = LogManager.getLogger();

    public void next(Car car) {
        LOGGER.info(String.format("State of %s changed to TransportedState, current state is the last one", car.toString()));
        car.setCarState(new TransportedState());
    }

    @Override
    public String toString(){
        return new String("{TransportingState}");
    }

}
