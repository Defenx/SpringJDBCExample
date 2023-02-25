package org.example.repository;

import org.example.entity.Deal;

public interface DealHistoryRepository {
    Deal save(Deal deal);
}
