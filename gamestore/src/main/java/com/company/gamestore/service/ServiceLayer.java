package com.company.gamestore.service;

import com.company.gamestore.model.ProcessingFee;
import com.company.gamestore.repository.ProcessingFeeRepository;
import com.company.gamestore.repository.TaxRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ServiceLayer
{
    //private GameRepository gameRepository;
    //private ConsoleRepository consoleRepository;
    //private TshirtRepository tshirtRepository;
    //private InvoiceRepository invoiceRepository;
    private ProcessingFeeRepository processingFeeRepository;
    private TaxRepository taxRepository;

    @Autowired
    public ServiceLayer(ProcessingFeeRepository processingFeeRepository, TaxRepository taxRepository){
        this.processingFeeRepository = processingFeeRepository;
        this.taxRepository = taxRepository;
    }


    // Console API


    // Game API


    // Tshirt API


    //Invoice API
}
