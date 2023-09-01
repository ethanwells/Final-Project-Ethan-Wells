package com.company.gamestore.service;

import com.company.gamestore.model.*;
import com.company.gamestore.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Optional;

@Component
public class InvoiceServiceLayer {
    @Autowired
    private GameRepository gameRepository;
    @Autowired
    private TaxRepository taxRepository;
    @Autowired
    private FeeRepository feeRepository;
    @Autowired
    private TshirtRepository tshirtRepository;
    @Autowired
    private ConsoleRepository consoleRepository;
    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    public InvoiceServiceLayer(GameRepository gameRepository, TaxRepository taxRepository, FeeRepository feeRepository, InvoiceRepository invoiceRepository, TshirtRepository tshirtRepository, ConsoleRepository consoleRepository){

        this.gameRepository = gameRepository;
        this.taxRepository = taxRepository;
        this.feeRepository = feeRepository;
        this.invoiceRepository = invoiceRepository;
        this.tshirtRepository = tshirtRepository;
        this.consoleRepository = consoleRepository;
    }

    public Invoice createInvoice(Invoice partialData){
        //Get the item type
        String itemType = partialData.getItemType();

        //The resource we need to collect & data we need to fill in
        Object resource;
        Invoice completeData = null;

        //Get resource corresponding to their itemType & itemId
        switch (itemType) {
            case "Consoles":
                resource = consoleRepository.findById(partialData.getItemId());
                Optional<Console> consoleData = (Optional<Console>) resource;

                if (!consoleData.isPresent()) {
                    throw new NotFoundException("No console found by this ID");
                }

                Console console = consoleData.get();

                // Complete the invoice for Console
                completeData = createConsoleInvoice(console, partialData);
                break;

            case "T-shirts":
                resource = tshirtRepository.findById(partialData.getItemId());
                Optional<Tshirt> tshirtData = (Optional<Tshirt>) resource;

                if (!tshirtData.isPresent()) {
                    throw new NotFoundException("No T-shirt found by this ID");
                }

                Tshirt tshirt = tshirtData.get();

                // Complete the invoice for T-shirts
                completeData = createTshirtInvoice(tshirt, partialData);
                break;

            case "Games":
                resource = gameRepository.findById(partialData.getItemId());
                Optional<Game> gameData = (Optional<Game>) resource;

                if (!gameData.isPresent()) {
                    throw new NotFoundException("No game found by this ID");
                }

                Game game = gameData.get();

                // Complete the invoice for Games
                completeData = createGameInvoice(game, partialData);
                break;

            default:
                throw new NotFoundException("Item type was not found");
        }

        return invoiceRepository.save(completeData);
    }

    public Invoice createTshirtInvoice(Tshirt tshirt, Invoice partialData) {
        if (!supplyMeetsDemand(tshirt.getQuantity(), partialData.getQuantity(), Tshirt.class.getName())) {
            throw new NotEnoughQuantityException("The quantity of " + Tshirt.class + " is insufficient");
        }

        // Update the amount of T-shirts
        tshirt.setQuantity(tshirt.getQuantity() - partialData.getQuantity());
        tshirtRepository.save(tshirt);

        // Calculate subtotal
        BigDecimal quantity = BigDecimal.valueOf(partialData.getQuantity());
        BigDecimal price = partialData.getUnitPrice();
        BigDecimal subtotal = quantity.multiply(price);
        partialData.setSubtotal(subtotal);

        Optional<Tax> query = taxRepository.findTaxByState(partialData.getState());
        if (query.isEmpty()) {
            throw new IllegalArgumentException("Tax does not exist");
        }

        Tax stateTax = query.get();
        partialData.setTax(stateTax.getRate().multiply(partialData.getSubtotal()));

        Optional<Fee> query2 = feeRepository.findFeeByProductType(partialData.getItemType());
        if (query2.isEmpty()) {
            throw new IllegalArgumentException("Fee does not exist");
        }

        Fee processingFee = query2.get();


        if( partialData.getQuantity() > 10){
            BigDecimal additionalFee = new BigDecimal("15.49");
            partialData.setProcessingFee(processingFee.getFee().add(additionalFee));
        }else{
            partialData.setProcessingFee(processingFee.getFee());
        }

        // Calculate with tax and processing fee
        BigDecimal subtotalTaxApplied = partialData.getSubtotal().add(partialData.getTax());
        BigDecimal total = subtotalTaxApplied.add(partialData.getProcessingFee());

        partialData.setTotal(total);

        Invoice completeData = invoiceRepository.save(partialData);
        return completeData;
    }

    public Invoice createConsoleInvoice(Console console, Invoice partialData){

        if(!supplyMeetsDemand(console.getQuantity(), partialData.getQuantity(), Console.class.getName())){
            throw new NotEnoughQuantityException("The quantity of " + Console.class +" is insufficient");
        }

        //Update the amount of consoles
        console.setQuantity(console.getQuantity() - partialData.getQuantity());
        consoleRepository.save(console);

        partialData.setUnitPrice(console.getPrice());

        //Calculate subtotal
        BigDecimal quantity = BigDecimal.valueOf(partialData.getQuantity());
        BigDecimal price = partialData.getUnitPrice();
        BigDecimal subtotal = quantity.multiply(price);
        partialData.setSubtotal(subtotal);
        Optional<Tax> query = taxRepository.findTaxByState(partialData.getState());

        if(query.isEmpty()){
            throw new IllegalArgumentException("Tax does not exist");
        }

        Tax stateTax = query.get();
        partialData.setTax(stateTax.getRate().multiply(partialData.getSubtotal()));

        //Find processing fee
        Optional<Fee> query2 = feeRepository.findFeeByProductType(partialData.getItemType());

        if(query2.isEmpty()){
            throw new IllegalArgumentException("Fee does not exist");
        }

        Fee processingFee = query2.get();
        //Calculate with tax and processing fee


        BigDecimal subtotalTaxApplied = partialData.getSubtotal().add(partialData.getTax());
        BigDecimal totalFees = new BigDecimal("0.00");

        if(partialData.getQuantity() > 15){
             BigDecimal additionalFees = new BigDecimal("15.49");
             totalFees = totalFees.add(additionalFees).add(processingFee.getFee());
        }else{
            totalFees = totalFees.add(processingFee.getFee());
        }

        partialData.setProcessingFee(totalFees);

        BigDecimal finalTotal = subtotalTaxApplied.add(totalFees);

        partialData.setTotal(finalTotal);

        Invoice completeData = invoiceRepository.save(partialData);

        return completeData;

    }

    public Invoice createGameInvoice(Game game, Invoice partialData) {
        if (!supplyMeetsDemand(game.getQuantity(), partialData.getQuantity(), Game.class.getName())) {
            throw new NotEnoughQuantityException("The quantity of " + Game.class + " is insufficient");
        }

        // Update the amount of games
        game.setQuantity(game.getQuantity() - partialData.getQuantity());
        gameRepository.save(game);

        // Calculate subtotal
        BigDecimal quantity = BigDecimal.valueOf(partialData.getQuantity());
        BigDecimal price = partialData.getUnitPrice();
        BigDecimal subtotal = quantity.multiply(price);
        partialData.setSubtotal(subtotal);

        Optional<Tax> query = taxRepository.findTaxByState(partialData.getState());
        if (query.isEmpty()) {
            throw new IllegalArgumentException("Tax does not exist");
        }

        Tax stateTax = query.get();
        partialData.setTax(stateTax.getRate());

        Optional<Fee> query2 = feeRepository.findFeeByProductType(partialData.getItemType());
        if (query2.isEmpty()) {
            throw new IllegalArgumentException("Fee does not exist");
        }

        Fee processingFee = query2.get();
        partialData.setProcessingFee(processingFee.getFee());

        // Calculate with tax and processing fee
        BigDecimal subtotalTaxApplied = partialData.getSubtotal().multiply(partialData.getTax()).add(partialData.getSubtotal());
        BigDecimal total = subtotalTaxApplied.add(partialData.getProcessingFee());

        if (partialData.getQuantity() > 10) {
            BigDecimal additionalFees = new BigDecimal("10.99");
            total = total.add(additionalFees);
        }

        partialData.setTotal(total);

        Invoice completeData = invoiceRepository.save(partialData);
        return completeData;
    }



    public Boolean supplyMeetsDemand(int supply, int demand, String resource){
        if(demand == 0){
            throw new IllegalArgumentException("Demand for " + resource + " cannot be zero");
        }
        return supply > demand;
    }
}
