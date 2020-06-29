package org.sprintpay.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.sprintpay.product.entities.Product;


public interface ProductRepository extends JpaRepository<Product, Long> {

}
