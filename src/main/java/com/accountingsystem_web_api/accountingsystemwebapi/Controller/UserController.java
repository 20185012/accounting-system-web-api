package com.accountingsystem_web_api.accountingsystemwebapi.Controller;


import com.accountingsystem_web_api.accountingsystemwebapi.Request.CategoryRequest;
import com.accountingsystem_web_api.accountingsystemwebapi.Request.UserRequest;
import com.accountingsystem_web_api.accountingsystemwebapi.Response.UserResponse;
import com.accountingsystem_web_api.accountingsystemwebapi.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    private UserService service;

    @Autowired
    public UserController(UserService userService)
    {
        this.service = userService;
    }

    @PostMapping("/user")
    public UserResponse createUser(@RequestBody UserRequest userRequest)
    {
        return service.createUser(userRequest);
    }

    @GetMapping("/users")
    public List<UserResponse> findAllUsers()
    {
        return service.getUsers();
    }

    @GetMapping("/user/{userID}")
    public UserResponse findUserById(@PathVariable int userID)
    {
        return service.getUserById(userID);
    }

    @PutMapping("/user/{userID}")
    public ResponseEntity<HttpStatus> updateUser(@RequestBody UserRequest userRequest, @PathVariable int userID)
    {
        service.updateUser(userRequest, userID);
        return new ResponseEntity<>(HttpStatus.valueOf(204));
    }

    @PutMapping("/user/addCat/user={userID}/category={categoryID}")
    public ResponseEntity<HttpStatus> addCategoryResponsible(@PathVariable int userID, @PathVariable int categoryID)
    {
        service.addCategoryResponsible(categoryID, userID);
        return new ResponseEntity<>(HttpStatus.valueOf(204));
    }

    @DeleteMapping("/user/{userID}")
    public void deleteUser(@PathVariable int userID)
    {
        service.deleteUser(userID);
    }
}
