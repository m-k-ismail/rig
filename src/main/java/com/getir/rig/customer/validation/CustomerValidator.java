package com.getir.rig.customer.validation;

import com.getir.rig.domain.model.customer.Customer;
import com.getir.rig.exception.ErrorException;
import org.springframework.stereotype.Component;

@Component
public class CustomerValidator {


    public void validate(Customer existingCustomer) {
        validateDuplicateCustomer(existingCustomer);
    }

    private void validateDuplicateCustomer(Customer existingCustomer) {
        if (existingCustomer != null) {
            throw new ErrorException("This customer already exists. Customer id: " + existingCustomer.getId());
        }
    }

}
