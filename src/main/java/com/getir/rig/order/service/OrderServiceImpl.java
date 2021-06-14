package com.getir.rig.order.service;

import com.getir.rig.domain.model.book.Book;
import com.getir.rig.domain.model.customer.Customer;
import com.getir.rig.domain.model.order.Order;
import com.getir.rig.domain.model.order.OrderSearchResult;
import com.getir.rig.domain.repository.OrderRepository;
import com.getir.rig.domain.service.IBookService;
import com.getir.rig.domain.service.ICustomerService;
import com.getir.rig.domain.service.IOrderService;
import com.getir.rig.exception.ErrorException;
import com.getir.rig.order.validation.OrderValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements IOrderService {

    private static final int OUT_OF_STOCK_COUNT = 0;

    private OrderRepository orderRepository;
    private OrderValidator orderValidator;
    private ICustomerService customerService;
    private IBookService bookService;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, OrderValidator orderValidator, ICustomerService customerService, IBookService bookService) {
        this.orderRepository = orderRepository;
        this.orderValidator = orderValidator;
        this.customerService = customerService;
        this.bookService = bookService;
    }

    @Override
    public Order create(Order order) {
        Customer customer = customerService.getCustomer(order.getCustomerId());
        List<Book> books = bookService.getBooks(order.getBookIds());

        orderValidator.validate(customer, books);

        Order savedOrder = orderRepository.save(order);

        boolean isOutOfStock = books.stream().map(Book::getStock).anyMatch(s -> s <= OUT_OF_STOCK_COUNT);

        if (isOutOfStock) {
            throw new ErrorException("This order is not valid. One of the books is out of stock");
        }

        for (Book book : books) {
            book.setStock(book.getStock() - 1);
        }

        bookService.update(books);

        return savedOrder;
    }

    @Override
    public OrderSearchResult getOrderById(Long id) {
        Optional<Order> order = orderRepository.findById(id);

        OrderSearchResult orderSearchResult = new OrderSearchResult();

        order.ifPresent(orderSearchResult::addOrder);

        return orderSearchResult;
    }

    @Override
    public OrderSearchResult getOrdersByStartEndDates(Date startDate, Date endDate) {
        List<Order> orders = orderRepository.findAllByCreatedAtBetween(startDate, endDate);

        OrderSearchResult orderSearchResult = new OrderSearchResult();

        orderSearchResult.addAllOrders(orders);

        return orderSearchResult;
    }

}
