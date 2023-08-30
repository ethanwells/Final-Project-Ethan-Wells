package com.company.gamestore.service;

import com.company.gamestore.model.Invoice;
import com.company.gamestore.repository.*;
import com.company.gamestore.viewmodel.InvoiceViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.Optional;

@Component
public class ServiceLayer
{
    private GameRepository gameRepository;
    private ConsoleRepository consoleRepository;
    private TshirtRepository tshirtRepository;
    private InvoiceRepository invoiceRepository;
    private FeeRepository FeeRepository;
    private TaxRepository taxRepository;

    @Autowired
    public ServiceLayer(GameRepository gameRepository, ConsoleRepository consoleRepository, TshirtRepository tshirtRepository, InvoiceRepository invoiceRepository, FeeRepository FeeRepository, TaxRepository taxRepository){
        this.gameRepository = gameRepository;
        this.consoleRepository = consoleRepository;
        this.tshirtRepository = tshirtRepository;
        this.invoiceRepository = invoiceRepository;
        this.FeeRepository = FeeRepository;
        this.taxRepository = taxRepository;
    }

    @Transactional
    public InvoiceViewModel saveInvoice(InvoiceViewModel viewModel){

        Invoice I = new Invoice();
        I.setName(viewModel.getName());
        I.setStreet(viewModel.getStreet());
        I.setCity(viewModel.getCity());
        I.setState(viewModel.getState());
        I.setZipcode(viewModel.getZipcode());
        I.setItemType(viewModel.getItemType());
        I.setItemId(viewModel.getItemId());
        I.setQuantity(viewModel.getQuantity());
        I = invoiceRepository.save(I);
        viewModel.setId(I.getInvoiceId());

        return viewModel;
    }

    public InvoiceViewModel findInvoice(int id){
        Optional<Invoice> invoice = invoiceRepository.findById(id);

        return invoice.isPresent() ? buildInvoiceViewModel(invoice.get()) : null;
    }

    private InvoiceViewModel buildInvoiceViewModel(Invoice invoice){
        InvoiceViewModel ivm = new InvoiceViewModel();
        ivm.setId(invoice.getInvoiceId());
        ivm.setName(invoice.getName());
        ivm.setStreet(invoice.getStreet());
        ivm.setCity(invoice.getCity());
        ivm.setState(invoice.getState());
        ivm.setZipcode(invoice.getZipcode());
        ivm.setItemType(invoice.getItemType());
        ivm.setItemId(invoice.getItemId());
        ivm.setQuantity(invoice.getQuantity());

        return ivm;
    }






    // Console API


    // Game API


    // Tshirt API

}
