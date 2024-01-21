package com.example.SampleEcommerceAssignment.Service;

import com.example.SampleEcommerceAssignment.Entity.Buyer;
import com.example.SampleEcommerceAssignment.Repsitiory.BuyerRepo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BuyerService {

    ObjectMapper objMapper = new ObjectMapper();
    @Autowired
    private BuyerRepo buyerRepo;

    public String addBuyer(Buyer buyer){
        Buyer buyer1 = buyerRepo.findById(buyer.getBuyerId()).orElse(null);

        if (buyer1 == null) {
            try {
                return objMapper.writeValueAsString(buyerRepo.save(buyer));
            } catch (JsonProcessingException e) {
                e.printStackTrace();
                return "Some Error Occurred";
            }
        }


        return "Buyer Already Exists";
    }

}
