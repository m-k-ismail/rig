package com.getir.rig.domain.repository;

import com.getir.rig.domain.model.customer.Customer;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface CustomerRepository extends JpaRepository<Customer, Long> {
    @Query(
            value = "SELECT * FROM CUSTOMERS c JOIN ORDERS o ON c.id = o.customer_Id  where c.id = ?  ORDER BY id",
            countQuery = "SELECT count(*) FROM ORDERS",
            nativeQuery = true)
    List<Customer> getCustomerWithOrdersPagination(Long customerId, Pageable pageable);
}
