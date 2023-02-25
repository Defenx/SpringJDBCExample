package org.example.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class Human {
    Long id;
    String name;
    Integer age;
    BigDecimal balance;
    List<Car> cars;
}
