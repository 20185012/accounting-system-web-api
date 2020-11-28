package com.accountingsystem_web_api.accountingsystemwebapi.Request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentRequest {
    private float sum;
    private LocalDate date;
    private int categoryID;
}
