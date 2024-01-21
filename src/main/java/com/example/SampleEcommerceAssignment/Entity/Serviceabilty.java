package com.example.SampleEcommerceAssignment.Entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Serviceability")
public class Serviceabilty {

    @Id
    private Integer serviceId;
    private Integer sourcePincode;
    private Integer destPincode;

    private String paymentType;
}
