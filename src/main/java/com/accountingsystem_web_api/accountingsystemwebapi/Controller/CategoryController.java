package com.accountingsystem_web_api.accountingsystemwebapi.Controller;

import com.accountingsystem_web_api.accountingsystemwebapi.Request.CategoryRequest;
import com.accountingsystem_web_api.accountingsystemwebapi.Request.PaymentRequest;
import com.accountingsystem_web_api.accountingsystemwebapi.Request.ReceivableRequest;
import com.accountingsystem_web_api.accountingsystemwebapi.Response.CategoryResponse;
import com.accountingsystem_web_api.accountingsystemwebapi.Response.PaymentResponse;
import com.accountingsystem_web_api.accountingsystemwebapi.Response.ReceivableResponse;
import com.accountingsystem_web_api.accountingsystemwebapi.Service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CategoryController {

    private CategoryService service;

    @Autowired
    public CategoryController(CategoryService service) {
        this.service = service;
    }

    @GetMapping("/categories")
    public List<CategoryResponse> getAllCategories()
    {
        return service.getCategories();
    }

    @GetMapping("/category/{id}")
    public CategoryResponse getCategoryById(@PathVariable int id)
    {
       return service.getCategoryById(id);
    }

    @GetMapping("/categories/{parentCategoryID}/")
    public List<CategoryResponse> getCategoriesOfParent(@PathVariable int parentCategoryID)
    {
        return service.getCategoriesOfParent(parentCategoryID);
    }

    @GetMapping("/categories/{categoryID}/payments")
    public List<PaymentResponse> getPaymentsOfCategory(@PathVariable int categoryID)
    {
        return service.getPaymentsOfCategory(categoryID);
    }

    @GetMapping("/categories/{categoryID}/receivables")
    public List<ReceivableResponse> getReceivablesOfCategory(@PathVariable int categoryID)
    {
        return service.getReceivablesOfCategory(categoryID);
    }

    @PostMapping("/categories/{parentCategoryID}")
    public ResponseEntity<HttpStatus> createCategory(@RequestBody CategoryRequest categoryRequest,@PathVariable int parentCategoryID)
    {
        service.createCategory(categoryRequest, parentCategoryID);

        return new ResponseEntity<>(HttpStatus.valueOf(204));
    }

    @PutMapping("/categories/{categoryID}/payment")
    public ResponseEntity<HttpStatus> addPaymentToCategory(@RequestBody PaymentRequest paymentRequest, @PathVariable int categoryID)
    {
        service.addPayment(paymentRequest, categoryID);

        return new ResponseEntity<>(HttpStatus.valueOf(204));
    }

    @PutMapping("/categories/{categoryID}/receivable")
    public ResponseEntity<HttpStatus> addReceivableToCategory(@RequestBody ReceivableRequest receivableRequest, @PathVariable int categoryID)
    {
        service.addReceivable(receivableRequest, categoryID);

        return new ResponseEntity<>(HttpStatus.valueOf(204));
    }

    @PutMapping("/categories/addUser/category={categoryID}/user={userID}")
    public ResponseEntity<HttpStatus> addResponsibleUserToCategory(@PathVariable int categoryID, @PathVariable int userID)
    {
        service.addResponsibleUser(categoryID, userID);

        return new ResponseEntity<>(HttpStatus.valueOf(204));
    }

    @PutMapping("/categories/delUser/category={categoryID}/user={userID}")
    public ResponseEntity<HttpStatus> removeResponsibleUserFromCategory(@PathVariable int categoryID, @PathVariable int userID)
    {
        service.removeResponsibleUser(categoryID, userID);

        return new ResponseEntity<>(HttpStatus.valueOf(204));
    }
}
