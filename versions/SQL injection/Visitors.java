package com.tpp.tpplab3.models;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

@Entity
@Table(name = "visitor")
public class Visitors {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer visitorId;

    @NotBlank(message = "Необхідно вказати ім'я")
    @Pattern(regexp = "^[\\p{L}]+$", message = "Ім'я може містити лише літери")
    private String name;

    @NotBlank(message = "Необхідно вказати прізвище")
    @Pattern(regexp = "^[\\p{L}]+$", message = "Прізвище може містити лише літери")
    private String surname;

    @NotBlank(message = "Телефон обов'язковий")
    @Pattern(regexp = "^\\+?[0-9]+$", message = "Номер телефону має бути дійсним")
    private String phone;

    @NotBlank(message = "Необхідно вказати адресу електронної пошти")
    @Email(message = "Електронна адреса має бути дійсною")
    private String email;

    @OneToMany(mappedBy = "visitor", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<Orders> orders = new ArrayList<>();

    // Гетери та сетери
    public Integer getVisitorId() {
        return visitorId;
    }
    public void setVisitorId(Integer visitorId) {
        this.visitorId = visitorId;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getSurname() {
        return surname;
    }
    public void setSurname(String surname) {
        this.surname = surname;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public List<Orders> getOrders() {
        return orders;
    }
    public void setOrders(List<Orders> orders) {
        this.orders.clear();
        if (orders != null) {
            this.orders.addAll(orders);
        }
    }
}
