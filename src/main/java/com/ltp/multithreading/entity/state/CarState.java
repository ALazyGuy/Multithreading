package com.ltp.multithreading.entity.state;

import com.ltp.multithreading.entity.Car;

public interface CarState {
    void next(Car car);
}
