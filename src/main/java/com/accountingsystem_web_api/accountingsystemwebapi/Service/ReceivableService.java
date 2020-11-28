package com.accountingsystem_web_api.accountingsystemwebapi.Service;

import com.accountingsystem_web_api.accountingsystemwebapi.Model.Category;
import com.accountingsystem_web_api.accountingsystemwebapi.Model.Payment;
import com.accountingsystem_web_api.accountingsystemwebapi.Model.Receivable;
import com.accountingsystem_web_api.accountingsystemwebapi.Repository.CategoryRepository;
import com.accountingsystem_web_api.accountingsystemwebapi.Repository.ReceivableRepository;
import com.accountingsystem_web_api.accountingsystemwebapi.Request.PaymentRequest;
import com.accountingsystem_web_api.accountingsystemwebapi.Request.ReceivableRequest;
import com.accountingsystem_web_api.accountingsystemwebapi.Response.ReceivableResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReceivableService {
    private ReceivableRepository repository;
    private CategoryRepository categoryRepository;

    @Autowired
    public ReceivableService(ReceivableRepository repository, CategoryRepository categoryRepository) {
        this.repository = repository;
        this.categoryRepository = categoryRepository;
    }





    public List<ReceivableResponse> getReceivables()
    {
        List<ReceivableResponse> responses = new ArrayList<>();
        for (Receivable receivable : repository.findAll())
        {
            responses.add( new ReceivableResponse(receivable));
        }

        return responses;
    }

    public ReceivableResponse getReceivableById(int id)
    {
        Receivable receivable = repository.findById(id).orElse(null);

        receivableExists(receivable);

        return new ReceivableResponse(receivable);
    }

    public Receivable getById(int id)
    {
        return repository.findById(id).orElse(null);
    }

    public ReceivableResponse createReceivable(ReceivableRequest receivableRequest)
    {
        Category category = categoryRepository.findById(receivableRequest.getCategoryID()).orElse(null);

        categoryExists(category);

        Receivable receivable = new Receivable(
                receivableRequest.getSum(),
                receivableRequest.getDate(),
                category
        );

        repository.save(receivable);

        return new ReceivableResponse(receivable);
    }

    public ReceivableResponse updateReceivable(ReceivableRequest receivableRequest, int receivableID)
    {
        Receivable existingReceivable = repository.findById(receivableID).orElse(null);

        Category newCategory = categoryRepository.findById(receivableRequest.getCategoryID()).orElse(null);

        receivableExists(existingReceivable);
        categoryExists(newCategory);


        existingReceivable.setReceivableSum(receivableRequest.getSum());
        existingReceivable.setReceivableDate(receivableRequest.getDate());
        existingReceivable.setCategory(newCategory);

        repository.save(existingReceivable);

        return new ReceivableResponse(existingReceivable);
    }

    public void deleteReceivable(int receivableID)
    {
        receivableExists(getById(receivableID));

        repository.deleteById(receivableID);
    }


    private void categoryExists(Category category) {
        if (category == null) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Category not found.");
    }

    private void receivableExists(Receivable receivable) {
        if (receivable == null) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Receivable not found");
    }
}
