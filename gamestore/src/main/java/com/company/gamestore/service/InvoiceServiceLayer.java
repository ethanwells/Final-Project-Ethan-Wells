package com.company.gamestore.service;

import com.company.gamestore.model.Fee;
import com.company.gamestore.model.Game;
import com.company.gamestore.model.Invoice;
import com.company.gamestore.model.Tax;
import com.company.gamestore.repository.FeeRepository;
import com.company.gamestore.repository.GameRepository;
import com.company.gamestore.repository.InvoiceRepository;
import com.company.gamestore.repository.TaxRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Optional;

@Component
public class InvoiceServiceLayer {
    private GameRepository gameRepository;
    private TaxRepository taxRepository;
    private FeeRepository feeRepository;

    private InvoiceRepository invoiceRepository;

    @Autowired
    public InvoiceServiceLayer(GameRepository gameRepository, TaxRepository taxRepository, FeeRepository feeRepository, InvoiceRepository invoiceRepository){
        this.gameRepository = gameRepository;
        this.taxRepository = taxRepository;
        this.feeRepository = feeRepository;
        this.invoiceRepository = invoiceRepository;
    }

    public Invoice createInvoice(Invoice partialData){
        //Get the item type
        String itemType = partialData.getItemType();
        Object data;

        //Get data corresponding to their itemType & itemId
        switch (itemType) {
            case "Games":
                data = gameRepository.findById(partialData.getItemId());
                break;
            default:
                throw new IllegalArgumentException("Item type is illegal");
        }

        if (data instanceof Game) {
            Game game = (Game) data;
            partialData.setUnitPrice(game.getPrice());
        }

        //Subtotal Calculation
        BigDecimal quantity = BigDecimal.valueOf(partialData.getQuantity());
        BigDecimal price = partialData.getUnitPrice();
        BigDecimal subtotal = quantity.multiply(price);
        partialData.setSubtotal(subtotal);

        //Find the tax
        Optional<Tax> query = taxRepository.findTaxByState(partialData.getState());

        if(query.isEmpty()){
            throw new IllegalArgumentException("State does not exist");
        }

        Tax stateTax = query.get();
        partialData.setTax(stateTax.getRate());

        //Find processing fee
        Optional<Fee> query2 = feeRepository.findFeeByProductType(partialData.getItemType());

        Fee processingFee = query2.get();
        partialData.setProcessingFee(processingFee.getFee());

        //Calculate with tax and processing fee
        BigDecimal subTotalWithTax = partialData.getSubtotal().multiply(partialData.getTax()).add(partialData.getSubtotal());
        BigDecimal total = subTotalWithTax.add(partialData.getProcessingFee());

        partialData.setTotal(total);

        Invoice completeData = invoiceRepository.save(partialData);

        return completeData;
    }

}
