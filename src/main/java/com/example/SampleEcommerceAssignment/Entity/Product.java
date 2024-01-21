package com.example.SampleEcommerceAssignment.Entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Product")
public class Product {

    @Id
    private Integer productId;
    private String name;
    private Integer inventory;
    private Integer price;
    private String pickupAddress;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id")
    List<Serviceabilty> serviceability = new ArrayList<>();

}
