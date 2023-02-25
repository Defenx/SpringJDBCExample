package org.example.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.entity.Deal;
import org.example.repository.DealHistoryRepository;
import org.example.service.DealHistoryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DealHistoryServiceImpl implements DealHistoryService {

    private final DealHistoryRepository dealHistoryRepository;

    @Override
    @Transactional
    public Deal save(Deal deal) {
        return dealHistoryRepository.save(deal);
    }
}
