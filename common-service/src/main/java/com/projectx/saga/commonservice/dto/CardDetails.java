package com.projectx.saga.commonservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CardDetails {
    private String name;
    private String cardNumber;
    private Integer validUtilMonth;
    private Integer validUtilYear;
    private Integer cvv;
}
