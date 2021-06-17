package com.ltp.multithreading.entity;

import com.ltp.multithreading.entity.state.CarState;
import com.ltp.multithreading.entity.state.WaitingState;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

@Data
public class Car implements Callable<Car> {

    @Setter(AccessLevel.NONE)
    private int id;
    private int weight;
    private String number;
    private CarState carState;

    public Car(int id, int weight, String number){
        this.id = id;
        this.weight = weight;
        this.number = number;
        this.carState = new WaitingState();
    }

    @Override
    public Car call() throws Exception {
        TransportingQueue.getInstance().addCar(this);
        TransportingQueue.getInstance().count();
        while (true){
            if(carState instanceof WaitingState){
                continue;
            }

            TimeUnit.SECONDS.sleep(1);
            TransportingQueue.getInstance().transport(this);

            break;
        }

        return this;
    }

    @Override
    public int hashCode(){
        int result = 17 * id * weight * number.hashCode() + carState.hashCode();
        return result;
    }

    @Override
    public boolean equals(Object o){
        if(o == this) return true;
        if(o == null || o.getClass() != getClass()) return false;
        Car current = (Car)o;
        return current.getCarState().equals(carState) && current.getId() == id && current.getNumber() == number && current.getWeight() == weight;
    }

    @Override
    public String toString(){
        return String.format("[ID: %d, Weight: %d, Number: %s, State: %s]", id, weight, number, carState.toString());
    }

}
