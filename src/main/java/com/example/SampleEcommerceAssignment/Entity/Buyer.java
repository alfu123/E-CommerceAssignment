package com.example.SampleEcommerceAssignment.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Buyer")
public class Buyer {
    @Id
    private Integer buyerId;
    private String buyerName;
    private String buyerAddress;
}
