package com.example.SampleEcommerceAssignment.Repsitiory;

import com.example.SampleEcommerceAssignment.Entity.Buyer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BuyerRepo extends JpaRepository<Buyer,Integer> {
}
