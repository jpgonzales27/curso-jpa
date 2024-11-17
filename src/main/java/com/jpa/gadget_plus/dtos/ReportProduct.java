package com.jpa.gadget_plus.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReportProduct {
    private String brand_name;
    private Double averagePrice;
    private BigDecimal totalPrice;
}
