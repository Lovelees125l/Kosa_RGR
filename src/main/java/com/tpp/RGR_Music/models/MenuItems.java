package com.tpp.RGR_Music.models;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

@Entity
@Table(name = "menu_items")
public class MenuItems {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer menuItemId;

    @NotBlank(message = "Необхідно вказати ім'я")
    @Pattern(regexp = "^[\\p{L}0-9 ]+$", message = "Ім’я може містити лише літери, цифри та пробіли")
    private String name;

    @NotNull(message = "Необхідно вказати ціну")
    @DecimalMin(value = "0.0", inclusive = false, message = "Ціна має бути більше 0")
    private BigDecimal price;

    @NotBlank(message = "Необхідно вказати концерт")
    @Pattern(regexp = "^[\\p{L}0-9 ]+$", message = "Концерт може містити лише літери, цифри та пробіли")
    private String category;

    @OneToMany(mappedBy = "menuItem", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItems> orderItems = new ArrayList<>();

    // Гетери та сетери
    public Integer getMenuItemId() {
        return menuItemId;
    }
    public void setMenuItemId(Integer menuItemId) {
        this.menuItemId = menuItemId;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public BigDecimal getPrice() {
        return price;
    }
    public void setPrice(BigDecimal price) {
        this.price = price;
    }
    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }
    public List<OrderItems> getOrderItems() {
        return orderItems;
    }
    public void setOrderItems(List<OrderItems> orderItems) {
        this.orderItems = orderItems;
    }
}
