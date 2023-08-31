package com.company.gamestore.controller;

import com.company.gamestore.model.Invoice;
import com.company.gamestore.repository.InvoiceRepository;
import com.company.gamestore.service.InvoiceServiceLayer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
public class InvoiceController {
    @Autowired
    private InvoiceRepository invoiceRepo;
    private InvoiceServiceLayer invoiceServiceLayer;

    @PostMapping("/invoice")
    @ResponseStatus(HttpStatus.CREATED)
    public Invoice addInvoice(@Valid @RequestBody Invoice invoice) {
       return invoiceServiceLayer.createInvoice(invoice);
    }

    @GetMapping("/invoice/{invoiceId}")
    public Optional<Invoice> getInvoiceById(@PathVariable("invoiceId") int invoiceId) {
        return invoiceRepo.findById(invoiceId);
    }

    @GetMapping("/invoices")
    public List<Invoice> getAllInvoices() {
        return invoiceRepo.findAll();
    }


    @GetMapping("/invoice/by-name")
    public List<Invoice> getInvoiceByName(@RequestParam("name") String name) {
        return invoiceRepo.findInvoiceByName(name).get();
    }

    @PutMapping("/invoice/{invoiceId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateInvoice(
            @PathVariable("invoiceId") int invoiceId,
            @Valid @RequestBody Invoice invoice
    ) {
        if (!invoiceRepo.existsById(invoiceId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Invoice not found");
        }
        invoice.setInvoiceId(invoiceId);
        invoiceRepo.save(invoice);
    }

    @DeleteMapping("/invoice/{invoiceId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteInvoice(@PathVariable("invoiceId") int invoiceId) {
        if (!invoiceRepo.existsById(invoiceId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Invoice not found");
        }
        invoiceRepo.deleteById(invoiceId);
    }
}
