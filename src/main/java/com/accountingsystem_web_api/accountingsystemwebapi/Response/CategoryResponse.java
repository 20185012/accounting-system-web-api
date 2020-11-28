package com.accountingsystem_web_api.accountingsystemwebapi.Response;

import com.accountingsystem_web_api.accountingsystemwebapi.Model.Category;
import com.accountingsystem_web_api.accountingsystemwebapi.Model.Payment;
import com.accountingsystem_web_api.accountingsystemwebapi.Model.Receivable;
import com.accountingsystem_web_api.accountingsystemwebapi.Model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryResponse {
    private int categoryID;
    private String categoryName;
    private List<UserResponse> responsibleUsers = new ArrayList<>();
    private List<SubCategoryResponse> subCategories = new ArrayList<>();
    private int parentCategoryID;
    private List<ReceivableResponse> receivables = new ArrayList<>();
    private List<PaymentResponse> payments = new ArrayList<>();

    public CategoryResponse (Category category)
    {
        this.categoryID = category.getCategoryID();
        this.categoryName = category.getCategoryName();

        if (category.getParentCategory() != null) this.parentCategoryID = category.getParentCategory().getCategoryID();
        else this.parentCategoryID = 0;

        if (!category.getResponsibleUsers().isEmpty())
        for (User user : category.getResponsibleUsers())
        {
            this.responsibleUsers.add( new UserResponse(user));
        }

        if (!category.getSubCategories().isEmpty())
        for (Category category1 : category.getSubCategories())
        {
            this.subCategories.add( new SubCategoryResponse(category1));
        }
        if (!category.getIncome().isEmpty())
        for (Receivable receivable : category.getIncome())
        {
            this.receivables.add( new ReceivableResponse(receivable));
        }

        if (!category.getExpense().isEmpty())
        for(Payment payment : category.getExpense())
        {
            this.payments.add( new PaymentResponse(payment));
        }
    }

    @Override
    public String toString() {
        return "CategoryResponse{" +
                "\ncategoryID=" + categoryID +
                "\n, categoryName='" + categoryName + '\'' +
                "\n, responsibleUsers=" + responsibleUsers +
                "\n, subCategories=" + subCategories +
                "\n, parentCategoryID=" + parentCategoryID +
                "\n, receivables=" + receivables +
                "\n, payments=" + payments +
                '}';
    }
}
