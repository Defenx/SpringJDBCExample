package org.example.repository.impl;

import lombok.RequiredArgsConstructor;
import org.example.repository.CarRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import static org.example.constants.SqlQuery.updateCarIdQuery;

@Repository
@RequiredArgsConstructor
public class CarRepositoryImpl implements CarRepository {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public void updateOwner(Long newOwnerId, Long carId) {
        jdbcTemplate.update(updateCarIdQuery, ps -> {
            ps.setLong(1, newOwnerId);
            ps.setLong(2, carId);
        });
    }
}
