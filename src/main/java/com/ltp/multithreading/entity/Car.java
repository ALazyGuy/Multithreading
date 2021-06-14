package com.ltp.multithreading.entity;

import com.ltp.multithreading.entity.state.CarState;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Setter;

@Data
@AllArgsConstructor
public class Car {

    @Setter(AccessLevel.NONE)
    private int id;
    private int weight;
    private String number;
    private CarState carState;

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
