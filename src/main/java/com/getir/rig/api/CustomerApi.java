package com.getir.rig.api;

import com.getir.rig.domain.model.customer.CustomerREQ;
import com.getir.rig.domain.model.customer.CustomerRES;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/v1/rig")
public interface CustomerApi {

    @PostMapping("/customers")
    ResponseEntity<CustomerRES> postCustomer(@RequestBody CustomerREQ body);

    @GetMapping("/customers/{customerId}/orders")
    ResponseEntity<CustomerRES> getCustomerWithOrders(@PathVariable Long customerId, @RequestParam int pageOffset, @RequestParam int pageLimit);

}