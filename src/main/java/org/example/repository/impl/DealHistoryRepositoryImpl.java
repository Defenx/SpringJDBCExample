package org.example.repository.impl;

import lombok.RequiredArgsConstructor;
import org.example.entity.Deal;
import org.example.repository.DealHistoryRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.Objects;

import static org.example.constants.SqlQuery.*;

@Repository
@RequiredArgsConstructor
public class DealHistoryRepositoryImpl implements DealHistoryRepository {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public Deal save(@NonNull Deal deal) {
        if (Objects.isNull(deal.getId())) {
            jdbcTemplate.update(insertDealQuery, deal.getDate(), deal.getStatus().toString(), deal.getBuyerId(), deal.getSellerId(), deal.getCarId());
            var id = jdbcTemplate.queryForObject(findMaxDealHistoryIdQuery, Long.class);
            deal.setId(id);
        } else {
            jdbcTemplate.update(updateDealQuery, deal.getDate(), deal.getStatus().toString(), deal.getBuyerId(), deal.getSellerId(), deal.getCarId(), deal.getId());
        }

        return deal;
    }
}
