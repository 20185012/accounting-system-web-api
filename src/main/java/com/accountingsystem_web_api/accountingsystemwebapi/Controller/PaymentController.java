package com.accountingsystem_web_api.accountingsystemwebapi.Controller;


import com.accountingsystem_web_api.accountingsystemwebapi.Request.PaymentRequest;
import com.accountingsystem_web_api.accountingsystemwebapi.Response.PaymentResponse;
import com.accountingsystem_web_api.accountingsystemwebapi.Service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PaymentController {

    private PaymentService service;

    @Autowired
    public PaymentController(PaymentService service) {
        this.service = service;
    }

    @GetMapping("/payments")
    public List<PaymentResponse> getPayments()
    {
        return service.getPayments();
    }

    @GetMapping("/payment/{id}")
    public PaymentResponse getPaymentById(@PathVariable int id)
    {
        return service.getPaymentById(id);
    }

    @PostMapping("/payment")
    public ResponseEntity<HttpStatus> addPayment(@RequestBody PaymentRequest paymentRequest)
    {
        service.createPayment(paymentRequest);
        return new ResponseEntity<>(HttpStatus.valueOf(204));
    }

    @PutMapping("/payment/{paymentID}")
    public PaymentResponse updatePayment(@RequestBody PaymentRequest paymentRequest, @PathVariable int paymentID)
    {
        return service.updatePayment(paymentRequest, paymentID);
    }

    @DeleteMapping("/payment/{paymentID}")
    public void deletePayment(@PathVariable int paymentID)
    {
        service.deletePayment(paymentID);
    }


}
