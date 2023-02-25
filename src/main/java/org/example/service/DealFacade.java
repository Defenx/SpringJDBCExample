package org.example.service;

import org.example.entity.Car;
import org.example.entity.Human;

public interface DealFacade {
    void deal(Human seller, Human buyer, Car car);
}
