package com.accountingsystem_web_api.accountingsystemwebapi.Service;

import com.accountingsystem_web_api.accountingsystemwebapi.Model.Category;
import com.accountingsystem_web_api.accountingsystemwebapi.Model.Payment;
import com.accountingsystem_web_api.accountingsystemwebapi.Model.User;
import com.accountingsystem_web_api.accountingsystemwebapi.Repository.CategoryRepository;
import com.accountingsystem_web_api.accountingsystemwebapi.Repository.PaymentRepository;
import com.accountingsystem_web_api.accountingsystemwebapi.Request.CategoryRequest;
import com.accountingsystem_web_api.accountingsystemwebapi.Request.PaymentRequest;
import com.accountingsystem_web_api.accountingsystemwebapi.Response.PaymentResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;


@Service
public class PaymentService {


    private PaymentRepository repository;
    private CategoryRepository categoryRepository;


    @Autowired
    public PaymentService(PaymentRepository repository, CategoryRepository categoryRepository) {
        this.repository = repository;
        this.categoryRepository = categoryRepository;
    }




    public PaymentResponse getPaymentById(int id)
    {
        Payment payment = repository.findById(id).orElse(null);
        return new PaymentResponse(payment);
    }

    public Payment getById(int id)
    {
        return repository.findById(id).orElse(null);
    }

    public List<PaymentResponse> getPayments()
    {
        List<PaymentResponse> responses = new ArrayList<>();
        for (Payment payment : repository.findAll())
        {
            responses.add( new PaymentResponse(payment));
        }
        return responses;
    }

    public PaymentResponse createPayment(PaymentRequest paymentRequest)
    {
        Category category = categoryRepository.findById(paymentRequest.getCategoryID()).orElse(null);

        categoryExists(category);

        Payment payment = new Payment(
          paymentRequest.getSum(),
          paymentRequest.getDate(),
          category
        );

        repository.save(payment);

        return new PaymentResponse(payment);
    }

    public PaymentResponse updatePayment(PaymentRequest paymentRequest, int paymentID)
    {
        Payment existingPayment = repository.findById(paymentID).orElse(null);

        Category newCategory = categoryRepository.findById(paymentRequest.getCategoryID()).orElse(null);

        paymentExists(existingPayment);
        categoryExists(newCategory);



        existingPayment.setPaymentSum(paymentRequest.getSum());
        existingPayment.setPaymentDate(paymentRequest.getDate());
        existingPayment.setCategory(newCategory);

        repository.save(existingPayment);

        return new PaymentResponse(existingPayment);
    }

    public void deletePayment(int paymentID)
    {
        paymentExists(getById(paymentID));

        repository.deleteById(paymentID);
    }




    private void categoryExists(Category category) {
        if (category == null) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Category not found.");
    }

    private void paymentExists(Payment payment) {
        if (payment == null) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Payment not found");
    }
}
