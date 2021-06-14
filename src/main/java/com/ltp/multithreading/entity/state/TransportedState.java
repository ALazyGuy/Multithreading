package com.ltp.multithreading.entity.state;

import com.ltp.multithreading.entity.Car;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TransportedState implements CarState{

    private final Logger LOGGER = LogManager.getLogger();

    public void next(Car car) {
        LOGGER.error(String.format("The last state passed by %s", car.toString()));
        throw new UnsupportedOperationException();
    }

    @Override
    public String toString(){
        return new String("{TransportedState}");
    }

}
