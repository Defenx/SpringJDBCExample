package org.example.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.FieldDefaults;
import org.simpleflatmapper.map.annotation.Column;

import java.math.BigDecimal;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class Car {
    Long id;
    String model;
    BigDecimal cost;

    @Column("human_id")
    @ToString.Exclude
    Long humanId;
}
