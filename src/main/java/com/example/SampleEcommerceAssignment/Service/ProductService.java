package com.example.SampleEcommerceAssignment.Service;

import com.example.SampleEcommerceAssignment.Constants.Constant;
import com.example.SampleEcommerceAssignment.Entity.Product;
import com.example.SampleEcommerceAssignment.Entity.Serviceabilty;
import com.example.SampleEcommerceAssignment.Repsitiory.ProductRepo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    ObjectMapper objMapper = new ObjectMapper();
    @Autowired
    private ProductRepo productRepo;


    public String addProduct(Product product){
        Product product1=productRepo.findById(product.getProductId()).orElse(null);



        if (product1 == null) {
            try {
                return objMapper.writeValueAsString(productRepo.save(product));
            } catch ( JsonProcessingException e) {
                e.printStackTrace();
                return "Some Error Occurred";
            }
        }
        // Return Product ID Already Exists
        return "Product Already Exists";
    }

    public ResponseEntity<String> getAvalaibility(int pid,int quantity,int pincode, String paymentMode){

        JsonNode dataNode = objMapper.createObjectNode();
        JsonNode errorNode = objMapper.createObjectNode();

        Product product=productRepo.findById(pid).orElse(null);
        if(product!=null ){
            if(product.getInventory()>=quantity){
                Serviceabilty serviceabilty = product.getServiceability()
                        .stream()
                        .filter(x -> x.getDestPincode() == pincode && x.getPaymentType().equalsIgnoreCase(paymentMode))
                        .findAny()
                        .orElse(null);
                if(serviceabilty!=null) {
                    ((ObjectNode) dataNode).put(Constant.PID_FIELD, pid);
                    ((ObjectNode) dataNode).put(Constant.PINCODE_FIELD, pincode);
                    ((ObjectNode) dataNode).put(Constant.AVAILABILITY_FIELD, true);
                    ((ObjectNode) dataNode).put(Constant.RESPONSE_STATUS, "Order Succesful");
                    return new ResponseEntity<String>(
                            constructResponse(Constant.STATUS_SUCCESS, HttpStatus.OK.value(), dataNode, null),
                            HttpStatus.OK);
                }
                ((ObjectNode) dataNode).put(Constant.PID_FIELD, pid);
                ((ObjectNode) dataNode).put(Constant.PINCODE_FIELD, pincode);
                ((ObjectNode) dataNode).put(Constant.AVAILABILITY_FIELD, false);
                ((ObjectNode) dataNode).put(Constant.RESPONSE_STATUS, "Order failed because pincode is unserviceable");
                return new ResponseEntity<String>(
                        constructResponse(Constant.STATUS_SUCCESS, HttpStatus.OK.value(), dataNode, null),
                        HttpStatus.OK);

            }
            ((ObjectNode) dataNode).put(Constant.PID_FIELD, pid);
            ((ObjectNode) dataNode).put(Constant.PINCODE_FIELD, pincode);
            ((ObjectNode) dataNode).put(Constant.AVAILABILITY_FIELD, false);
            ((ObjectNode) dataNode).put(Constant.RESPONSE_STATUS,"Order failed because product stock is insufficient");
            return new ResponseEntity<String>(
                    constructResponse(Constant.STATUS_SUCCESS, HttpStatus.OK.value(), dataNode, null),
                    HttpStatus.OK);

        }
        ((ObjectNode) errorNode).put(Constant.RESPONSE_ERROR_FIELD, Constant.PID_FIELD);
        ((ObjectNode) errorNode).put(Constant.RESPONSE_ERROR_MESSAGE, Constant.NO_PRODUCT_FOUND);

        return new ResponseEntity<String>(
                constructResponse(Constant.STATUS_SUCCESS, HttpStatus.NOT_FOUND.value(), null, errorNode),
                HttpStatus.OK);



    }

    public String constructResponse(String status, int statusCode, JsonNode dataNode, JsonNode errorNode) {
        String response = "";

        ObjectNode objNode = objMapper.createObjectNode();
        objNode.put(Constant.RESPONSE_STATUS, status);
        objNode.put(Constant.RESPONSE_STATUS_CODE, statusCode);
        objNode.set(Constant.RESPONSE_DATA_STRING, dataNode);
        objNode.set(Constant.RESPONSE_ERRORS, errorNode);

        try {
            response = objMapper.writeValueAsString(objNode);
        } catch (JsonProcessingException e) {
            response = null;
        }

        return response;
    }




}
