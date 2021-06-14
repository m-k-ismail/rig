package com.getir.rig.order.service;

import com.getir.rig.domain.model.book.Book;
import com.getir.rig.domain.model.customer.Customer;
import com.getir.rig.domain.model.order.Order;
import com.getir.rig.domain.model.order.OrderSearchResult;
import com.getir.rig.domain.model.order.OrderStatus;
import com.getir.rig.domain.repository.OrderRepository;
import com.getir.rig.domain.service.IBookService;
import com.getir.rig.domain.service.ICustomerService;
import com.getir.rig.domain.service.IOrderService;
import com.getir.rig.order.validation.OrderValidator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class OrderServiceImplTest {

    private static final Long ORDER_ID = 1L;
    private static final Long BOOK_ID = 1L;
    private static final Long CUSTOMER_ID = 1L;

    @Mock
    private OrderRepository orderRepository;
    @Mock
    private OrderValidator orderValidator;
    @Mock
    private IOrderService orderService;
    @Mock
    private IBookService bookService;
    @Mock
    private ICustomerService customerService;

    @Before
    public void setUp() {
        orderService = new OrderServiceImpl(orderRepository, orderValidator, customerService, bookService);
    }

    @Test
    public void should_create_order_successfully() {
        // Given
        Order order = createOrder();
        List<Book> books = createBooks(5);
        Customer customerWitId = createCustomerWithId();
        when(customerService.getCustomer(order.getCustomerId())).thenReturn(customerWitId);
        when(bookService.getBooks(order.getBookIds())).thenReturn(books);
        when(bookService.update(books)).thenReturn(createBooks(4));
        when(orderRepository.save(order)).thenReturn(createOrderWithId());

        // When
        Order orderOutput = orderService.create(order);

        // Then
        Assert.assertEquals(ORDER_ID, orderOutput.getId());
    }

    @Test
    public void should_find_order_successfully() {
        // Given
        Order order = createOrderWithId();
        when(orderRepository.findById(ORDER_ID)).thenReturn(Optional.of(order));

        // When
        OrderSearchResult orderSearchResult = orderService.getOrderById(ORDER_ID);

        // Then
        Assert.assertFalse(orderSearchResult.getOrderList().isEmpty());
    }

    @Test
    public void should_find_orders_successfully() {
        // Given
        List<Order> orders = createOrders();
        when(orderRepository.findAllByCreatedAtBetween(any(Date.class), any(Date.class))).thenReturn(orders);

        // When
        OrderSearchResult orderSearchResult = orderService.getOrdersByStartEndDates(new Date(), new Date());

        // Then
        Assert.assertFalse(orderSearchResult.getOrderList().isEmpty());
    }

    private Order createOrder() {
        Order order = new Order();

        order.setTotalPrice(1000);
        order.setCreatedAt(new Date());
        order.setOrderStatus(OrderStatus.PROCESSING);
        order.setCustomerId(1L);
        order.setBookIds(Arrays.asList(1L, 2L));

        return order;
    }

    private Order createOrderWithId() {
        Order order = createOrder();
        order.setId(ORDER_ID);
        return order;
    }

    private List<Order> createOrders() {
        Order order = createOrderWithId();

        List<Order> orders = new ArrayList<>();
        orders.add(order);

        return orders;
    }

    private List<Book> createBooks(int stock) {
        Book book = createBook(stock);

        List<Book> books = new ArrayList<>();
        books.add(book);
        return books;
    }

    private Book createBook(int stock) {
        Book book = new Book();

        book.setId(BOOK_ID);
        book.setName("Test Book");
        book.setStock(stock);
        return book;
    }

    private Customer createCustomerWithId() {
        Customer customer = new Customer();

        customer.setId(CUSTOMER_ID);
        customer.setFirstName("First Name");
        customer.setLastName("Last Name");
        customer.setAddress("Istanbul");
        customer.setEmail("test@gmail.com");

        return customer;
    }

}
