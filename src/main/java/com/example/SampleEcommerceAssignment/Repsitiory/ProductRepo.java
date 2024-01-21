package com.example.SampleEcommerceAssignment.Repsitiory;

import com.example.SampleEcommerceAssignment.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepo extends JpaRepository<Product,Integer> {
}
