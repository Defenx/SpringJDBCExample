package org.example.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.entity.Car;
import org.example.entity.Deal;
import org.example.entity.Human;
import org.example.service.DealFacade;
import org.example.service.DealHistoryService;
import org.example.service.DealService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

import static org.example.enums.Status.*;

@Service
@RequiredArgsConstructor
public class DealFacadeImpl implements DealFacade {

    private final DealHistoryService dealHistoryService;
    private final DealService dealService;

    @Override
    @Transactional
    public void deal(Human seller, Human buyer, Car car) {
        var deal = dealHistoryService.save(Deal.builder()
                .date(LocalDate.now())
                .sellerId(seller.getId())
                .buyerId(buyer.getId())
                .carId(car.getId())
                .status(PROCESSING)
                .build());

        try {
            dealService.deal(seller.getId(), buyer.getId(), car.getId());
            deal.setStatus(SUCCESS);
            dealHistoryService.save(deal);
        } catch (Exception e) {
            deal.setStatus(FAIL);
            dealHistoryService.save(deal);
        }
    }

}
