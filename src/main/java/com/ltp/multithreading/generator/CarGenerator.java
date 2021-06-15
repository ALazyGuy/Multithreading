package com.ltp.multithreading.generator;

import com.ltp.multithreading.entity.Car;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CarGenerator {

    private static int ID = 0;

    public static Car generate(int weight, String number){
        return new Car(ID++, weight, number);
    }

}
