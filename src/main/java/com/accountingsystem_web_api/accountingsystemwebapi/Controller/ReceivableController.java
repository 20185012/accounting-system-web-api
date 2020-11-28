package com.accountingsystem_web_api.accountingsystemwebapi.Controller;

import com.accountingsystem_web_api.accountingsystemwebapi.Request.ReceivableRequest;
import com.accountingsystem_web_api.accountingsystemwebapi.Response.ReceivableResponse;
import com.accountingsystem_web_api.accountingsystemwebapi.Service.ReceivableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.function.EntityResponse;

import javax.swing.text.html.parser.Entity;
import java.util.List;

@RestController
public class ReceivableController {

    private ReceivableService service;

    @Autowired
    public ReceivableController(ReceivableService service) {
        this.service = service;
    }

    @GetMapping("/receivables")
    public List<ReceivableResponse> getReceivables()
    {
        return service.getReceivables();
    }

    @GetMapping("/receivables/{id}")
    public ReceivableResponse getReceivableById(@PathVariable int id)
    {
        return service.getReceivableById(id);
    }


    @PostMapping("/receivable")
    public ResponseEntity<HttpStatus> createReceivable(@RequestBody ReceivableRequest receivableRequest)
    {
        service.createReceivable(receivableRequest);
        return new ResponseEntity<>(HttpStatus.valueOf(204));
    }

    @PutMapping("/receivable/{receivableID}")
    public ResponseEntity<HttpStatus> updateReceivable(@RequestBody ReceivableRequest receivableRequest, @PathVariable int receivableID)
    {
        service.updateReceivable(receivableRequest, receivableID);
        return new ResponseEntity<>(HttpStatus.valueOf(204));
    }

    @DeleteMapping("/receivable/{receivableID}")
    public void deleteReceivable(@PathVariable int receivableID)
    {
        service.deleteReceivable(receivableID);
    }
}
