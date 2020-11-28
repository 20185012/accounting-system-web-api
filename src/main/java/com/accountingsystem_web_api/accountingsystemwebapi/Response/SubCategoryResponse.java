package com.accountingsystem_web_api.accountingsystemwebapi.Response;

import com.accountingsystem_web_api.accountingsystemwebapi.Model.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubCategoryResponse {
    private int categoryID;
    private String name;

    public SubCategoryResponse (Category category){
        this.categoryID = category.getCategoryID();
        this.name = category.getCategoryName();
    }
}
