package com.accountingsystem_web_api.accountingsystemwebapi.Response;

import com.accountingsystem_web_api.accountingsystemwebapi.Model.Category;
import com.accountingsystem_web_api.accountingsystemwebapi.Model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {
    private int userID;
    private String name;
    private String surname;
    private String email;
    private String phone;
    private String address;
    private String companyCode;
    private String username;
    private String password;
    private List<SubCategoryResponse> categoriesResponsible = new ArrayList<>();
    private String userType;

    public UserResponse(User user)
    {
        this.userID = user.getUserID();
        this.name = user.getName();
        this.surname = user.getSurname();
        this.email = user.getEmail();
        this.phone = user.getPhone();
        this.address = user.getAddress();
        this.companyCode = user.getCompanyCode();
        this.username = user.getUsername();
        this.password = user.getPassword();

        for (Category category : user.getCategoriesResponsible())
        {
            categoriesResponsible.add( new SubCategoryResponse(category));
        }

        this.userType = user.getUserType();
    }
}
