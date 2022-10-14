package com.njoyer.loans.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.njoyer.loans.config.LoansServiceConfig;
import com.njoyer.loans.model.Customer;
import com.njoyer.loans.model.Loans;
import com.njoyer.loans.model.Properties;
import com.njoyer.loans.repository.LoansRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class LoansController {

    private final LoansRepository loansRepository;
    private final LoansServiceConfig loansConfig;

    @Autowired
    public LoansController(LoansRepository loansRepository, LoansServiceConfig loansConfig) {
        this.loansRepository = loansRepository;
        this.loansConfig = loansConfig;
    }

    @PostMapping("/myLoans")
    public List<Loans> getLoansDetails(@RequestBody Customer customer) {
        return loansRepository.findByCustomerIdOrderByStartDtDesc(customer.getCustomerId());
    }

    @GetMapping("/loans/properties")
    public String getPropertyDetails() throws JsonProcessingException {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        Properties properties = new Properties(loansConfig.getMsg(), loansConfig.getBuildVersion(),
                loansConfig.getMailDetails(), loansConfig.getActiveBranches());
        return ow.writeValueAsString(properties);
    }

}
