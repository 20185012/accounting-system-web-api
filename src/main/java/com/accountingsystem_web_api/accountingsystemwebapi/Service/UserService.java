package com.accountingsystem_web_api.accountingsystemwebapi.Service;

import com.accountingsystem_web_api.accountingsystemwebapi.Model.Category;
import com.accountingsystem_web_api.accountingsystemwebapi.Model.User;
import com.accountingsystem_web_api.accountingsystemwebapi.Repository.CategoryRepository;
import com.accountingsystem_web_api.accountingsystemwebapi.Repository.UserRepository;
import com.accountingsystem_web_api.accountingsystemwebapi.Request.CategoryRequest;
import com.accountingsystem_web_api.accountingsystemwebapi.Request.UserRequest;
import com.accountingsystem_web_api.accountingsystemwebapi.Response.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {


    private UserRepository repository;

    private CategoryRepository categoryRepository;

    @Autowired
    public UserService(UserRepository repository, CategoryRepository categoryRepository) {
        this.repository = repository;
        this.categoryRepository = categoryRepository;
    }





    public UserResponse createUser(UserRequest userRequest)
    {

        givenUsernameIsUnique(userRequest);

        givenUserTypeIsCorrect(userRequest);

        User user = new User(userRequest.getName(),
                             userRequest.getSurname(),
                             userRequest.getEmail(),
                             userRequest.getPhone(),
                             userRequest.getAddress(),
                             userRequest.getCompanyCode(),
                             userRequest.getUsername(),
                             userRequest.getPassword(),
                             new ArrayList<Category>(),
                             userRequest.getUserType());
        repository.save(user);

        return new UserResponse(user);
    }



    public List<UserResponse> getUsers()
    {
        List<UserResponse> responseList = new ArrayList<>();

        for (User user : repository.findAll())
        {
            responseList.add(new UserResponse(user));
        }
        return responseList;
    }

    public List<User> getUsersList()
    {
        return repository.findAll();
    }

    public UserResponse getUserById(int id)
    {

        User user = repository.findById(id).orElse(null);

        userExists(user);


        return new UserResponse(user);
    }

    public User getById(int id)
    {

        User user = repository.findById(id).orElse(null);

        return user;
    }

    public UserResponse updateUser(UserRequest userRequest, int userID)
    {
        User existingUser = getById(userID);

        userExists(existingUser);
        givenUsernameIsUnique(userRequest);
        givenUserTypeIsCorrect(userRequest);


        existingUser.setName(userRequest.getName());
        existingUser.setSurname(userRequest.getSurname());
        existingUser.setEmail(userRequest.getEmail());
        existingUser.setPhone(userRequest.getPhone());
        existingUser.setCompanyCode(userRequest.getCompanyCode());
        existingUser.setAddress(userRequest.getAddress());
        existingUser.setUsername(userRequest.getUsername());
        existingUser.setPassword(userRequest.getPassword());
        existingUser.setUserType(userRequest.getUserType());

        repository.save(existingUser);

        return new UserResponse(existingUser);
    }


    public UserResponse addCategoryResponsible(int categoryID, int userID)
    {
        Category category = categoryRepository.findById(categoryID).orElse(null);
        User user = repository.findById(userID).orElse(null);

        categoryExists(category);
        userExists(user);

        user.getCategoriesResponsible().add(category);
        category.getResponsibleUsers().add(user);

        repository.save(user);
        categoryRepository.save(category);

        return new UserResponse(user);
    }



    public void deleteUser(int userID)
    {

        userExists(getById(userID));

        repository.deleteById(userID);
    }








    private void categoryExists(Category category) {
        if (category == null) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Category not found.");
    }

    private void givenUserTypeIsCorrect(UserRequest userRequest) {
        if (!userRequest.getUserType().toUpperCase().equals("Individual".toUpperCase()) || !userRequest.getUserType().toUpperCase().equals("Legal".toUpperCase()))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User type is Individual or Legal");
    }

    private void userExists(User user) {
        if (user == null) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User not found");
    }

    private void givenUsernameIsUnique(UserRequest userRequest) {
        for (User user : getUsersList()) {
            if (user.getUsername().equals(userRequest.getUsername()))
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cannot update, user with given username already exists");
        }
    }
}
