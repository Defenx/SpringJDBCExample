package org.example.repository;

public interface CarRepository {

    void updateOwner(Long newOwnerId, Long carId);
}
