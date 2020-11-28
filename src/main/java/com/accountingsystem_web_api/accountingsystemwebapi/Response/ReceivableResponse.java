package com.accountingsystem_web_api.accountingsystemwebapi.Response;

import com.accountingsystem_web_api.accountingsystemwebapi.Model.Receivable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReceivableResponse {
    private int receivableId;
    private float receivableSum;
    private LocalDate receivableDate;
    private int categoryID;

    public ReceivableResponse(Receivable receivable) {
        this.receivableId = receivable.getReceivableId();
        this.receivableSum = receivable.getReceivableSum();
        this.receivableDate = receivable.getReceivableDate();
        this.categoryID = receivable.getCategory().getCategoryID();
    }
}
