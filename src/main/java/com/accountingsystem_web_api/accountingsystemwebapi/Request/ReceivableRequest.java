package com.accountingsystem_web_api.accountingsystemwebapi.Request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.tomcat.jni.Local;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReceivableRequest {
    private float sum;
    private LocalDate date;
    private int categoryID;
}
