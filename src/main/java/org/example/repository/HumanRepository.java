package org.example.repository;

import org.example.entity.Human;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface HumanRepository {

    List<Human> findAll();

    Optional<Human> findById(Long id);

    List<Human> findByName(String name);

    Long count();

    Human save(Human human);

    Human updateBalance(Human human);
}
