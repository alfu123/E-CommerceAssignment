package com.example.SampleEcommerceAssignment.Controller;

import com.example.SampleEcommerceAssignment.Entity.Buyer;
import com.example.SampleEcommerceAssignment.Service.BuyerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/buyer")
public class BuyerController {
    @Autowired
    private BuyerService buyerService;

    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public String addBuyer(@RequestBody Buyer buyer) {

        return buyerService.addBuyer(buyer);
    }
}
