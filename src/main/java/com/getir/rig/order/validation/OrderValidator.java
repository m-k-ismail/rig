package com.getir.rig.order.validation;

import com.getir.rig.domain.model.book.Book;
import com.getir.rig.domain.model.customer.Customer;
import com.getir.rig.exception.ErrorException;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class OrderValidator {

    public void validate(Customer customer, List<Book> books) {
        validateCustomer(customer);
        validateBook(books);
    }

    private void validateCustomer(Customer customer) {
        if (customer == null) {
            throw new ErrorException("Customer does not exist.");
        }
    }

    private void validateBook(List<Book> books) {
        if (books.isEmpty()) {
            throw new ErrorException("Books list is empty.");
        }
    }

}
