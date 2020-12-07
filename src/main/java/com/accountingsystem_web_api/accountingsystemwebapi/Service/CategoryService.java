package com.accountingsystem_web_api.accountingsystemwebapi.Service;


import com.accountingsystem_web_api.accountingsystemwebapi.Model.Category;
import com.accountingsystem_web_api.accountingsystemwebapi.Model.Payment;
import com.accountingsystem_web_api.accountingsystemwebapi.Model.Receivable;
import com.accountingsystem_web_api.accountingsystemwebapi.Model.User;
import com.accountingsystem_web_api.accountingsystemwebapi.Repository.CategoryRepository;
import com.accountingsystem_web_api.accountingsystemwebapi.Repository.UserRepository;
import com.accountingsystem_web_api.accountingsystemwebapi.Request.CategoryRequest;
import com.accountingsystem_web_api.accountingsystemwebapi.Request.PaymentRequest;
import com.accountingsystem_web_api.accountingsystemwebapi.Request.ReceivableRequest;
import com.accountingsystem_web_api.accountingsystemwebapi.Request.UserRequest;
import com.accountingsystem_web_api.accountingsystemwebapi.Response.CategoryResponse;
import com.accountingsystem_web_api.accountingsystemwebapi.Response.PaymentResponse;
import com.accountingsystem_web_api.accountingsystemwebapi.Response.ReceivableResponse;
import com.accountingsystem_web_api.accountingsystemwebapi.Response.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryService {

    private CategoryRepository repository;

    private UserRepository userRepository;

    @Autowired
    public CategoryService(CategoryRepository repository, UserRepository userRepository) {
        this.repository = repository;
        this.userRepository = userRepository;
    }







    public CategoryResponse createCategory(CategoryRequest categoryRequest, int parentCategoryID)
    {

        categoryExists(getById(parentCategoryID));
        givenCategoryNameIsUnique(categoryRequest);


        Category category = new Category(
                categoryRequest.getCategoryName(),
                new ArrayList<User>(),
                new ArrayList<Category>(),
                getById(parentCategoryID),
                0,
                new ArrayList<Receivable>(),
                new ArrayList<Payment>()
        );

        repository.save(category);

        return new CategoryResponse(category);
    }

    public Category getById(int id)
    {
        return repository.findById(id).orElse(null);
    }

    public CategoryResponse getCategoryById(int id)
    {
        Category category = getById(id);
        return new CategoryResponse(category);
    }

    public List<CategoryResponse> getCategories()
    {
        List<CategoryResponse> responses = new ArrayList<>();

        for (Category category : repository.findAll())
        {
            responses.add(new CategoryResponse(category));
        }

        return responses;
    }

    public List<Category> getCategoriesList()
    {
        return repository.findAll();
    }

    public CategoryResponse addPayment(PaymentRequest paymentRequest, int  categoryID)
    {
        Category category = getById(categoryID);

        categoryExists(category);



        Payment payment = new Payment(
                paymentRequest.getSum(),
                paymentRequest.getDate(),
                category
        );

        category.getExpense().add(payment);

        repository.save(category);

        return new CategoryResponse(category);
    }

    public CategoryResponse addReceivable(ReceivableRequest receivableRequest, int  categoryID)
    {
        Category category = getById(categoryID);

        categoryExists(category);


        Receivable receivable = new Receivable(
                receivableRequest.getSum(),
                receivableRequest.getDate(),
                category
        );

        category.getIncome().add(receivable);


        repository.save(category);

        System.out.println(category.toString());

        return new CategoryResponse(category);
    }

    public List<CategoryResponse> getCategoriesOfParent(int parentCategoryID)
    {
        categoryExists(getById(parentCategoryID));

        List<CategoryResponse> categories = new ArrayList<>();
        for (Category category : repository.findAll())
        {
            if (category.getParentCategory() != null)
            {
                if(category.getParentCategory().getCategoryID() == parentCategoryID) categories.add( new CategoryResponse(category));
            }
            else
            {
                if (parentCategoryID == 0) categories.add( new CategoryResponse(category));
            }

        }

        return categories;
    }

    public List<PaymentResponse> getPaymentsOfCategory(int categoryID)
    {
        List<PaymentResponse> payments = new ArrayList<>();
        Category category = repository.findById(categoryID).orElse(null);

        categoryExists(category);

        if (category != null)
        {
            for (Payment payment : category.getExpense())
            {
                payments.add( new PaymentResponse(payment));
            }
        }

        return payments;
    }

    public List<ReceivableResponse> getReceivablesOfCategory(int categoryID)
    {
        List<ReceivableResponse> receivables = new ArrayList<>();
        Category category = repository.findById(categoryID).orElse(null);
        if (category != null)
        {
            for (Receivable receivable : category.getIncome())
            {
                receivables.add( new ReceivableResponse(receivable));
            }
        }

        return receivables;
    }

    public List<UserResponse> getResponsibleUsersOfCategory(int categoryID)
    {
        List<UserResponse> responsibleUsers = new ArrayList<>();
        Category category = repository.findById(categoryID).orElse(null);

        categoryExists(category);

        if (category != null)
        {
            for (User user : category.getResponsibleUsers())
            {
                responsibleUsers.add( new UserResponse(user));
            }
        }

        return responsibleUsers;
    }
    public CategoryResponse addResponsibleUser(int categoryID, int userID)
    {
        Category category = repository.findById(categoryID).orElse(null);
        User user = userRepository.findById(userID).orElse(null);

        categoryExists(category);
        userExists(user);

        category.getResponsibleUsers().add(user);
        user.getCategoriesResponsible().add(category);

        userRepository.save(user);
        repository.save(category);

        return new CategoryResponse(category);
    }

    public CategoryResponse removeResponsibleUser(int categoryID, int userID)
    {
        Category category = repository.findById(categoryID).orElse(null);
        User user = userRepository.findById(userID).orElse(null);

        categoryExists(category);
        userExists(user);

        category.getResponsibleUsers().remove(user);
        user.getCategoriesResponsible().remove(category);

        userRepository.save(user);
        repository.save(category);

        return new CategoryResponse(category);
    }


    private void categoryExists(Category category) {
        if (category == null) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Category not found.");
    }

    private void userExists(User user) {
        if (user == null) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User not found");
    }

    private void givenCategoryNameIsUnique(CategoryRequest categoryRequest)
    {
        for (Category category : getCategoriesList())
        {
            if (category.getCategoryName().toUpperCase().equals(categoryRequest.getCategoryName().toUpperCase())) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Category name is not unique.");
        }
    }

    public List<CategoryResponse> getRootCategories() {
        List<CategoryResponse> categories = new ArrayList<>();

        for (Category category : getCategoriesList())
        {
            if (category.getParentCategory() == null) categories.add( new CategoryResponse(category));
        }
        return categories;
    }
}
