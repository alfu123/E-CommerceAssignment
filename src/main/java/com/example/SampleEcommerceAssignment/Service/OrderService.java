package com.example.SampleEcommerceAssignment.Service;

import com.example.SampleEcommerceAssignment.Constants.Constant;
import com.example.SampleEcommerceAssignment.Entity.Order;
import com.example.SampleEcommerceAssignment.Repsitiory.OrderRepo;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    @Autowired
    private OrderRepo orderRepo;
    @Autowired
    private ProductService productService;

    public ResponseEntity<String> addOrder(Order order){

        ResponseEntity<String> orderStatus=productService.getAvalaibility(order.getProductId(),order.getQuantity(),order.getDestPincode(),order.getPaymentMode());

        if(orderStatus.getStatusCode()== HttpStatus.OK){

            String responseOrder = orderStatus.getBody();
            JsonNode dataNode=parseJson(responseOrder);
            boolean val=dataNode.get(Constant.RESPONSE_DATA_STRING).get(Constant.AVAILABILITY_FIELD).booleanValue();
            if (val) {
                orderRepo.save(order);
            }
        }

        return orderStatus;
    }

    public JsonNode parseJson(String jsonString) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readTree(jsonString);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
