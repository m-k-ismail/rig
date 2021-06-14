package com.getir.rig.domain.model.customer;

import com.getir.rig.domain.model.order.Order;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "CUSTOMERS")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "email")
    private String email;
    private String firstName;
    private String lastName;
    private int phoneNumber;
    private String address;
    @OneToMany(targetEntity = Order.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private final List<Order> orders = new ArrayList<>();


    public Customer() {

    }

    public Customer(String firstName) {

    }

    public Customer(String email, String firstName, String lastName, int phoneNumber, String address) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void addOrders(List<Order> orders) {
        this.orders.addAll(orders);
    }
}
