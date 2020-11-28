package com.accountingsystem_web_api.accountingsystemwebapi.Response;

import com.accountingsystem_web_api.accountingsystemwebapi.Model.Payment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentResponse {
    private int paymentId;
    private float paymentSum;
    private LocalDate paymentDate;
    private int categoryID;

    public PaymentResponse(Payment payment)
    {
        this.paymentId = payment.getPaymentId();
        this.paymentSum = payment.getPaymentSum();
        this.paymentDate = payment.getPaymentDate();
        this.categoryID = payment.getCategory().getCategoryID();
    }
}
