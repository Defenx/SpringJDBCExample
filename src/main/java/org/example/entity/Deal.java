package org.example.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.example.enums.Status;

import java.time.LocalDate;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class Deal {
    Long id;
    LocalDate date;
    Status status;
    Long sellerId;
    Long buyerId;
    Long carId;
}
