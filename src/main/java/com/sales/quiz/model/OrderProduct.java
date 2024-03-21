package com.sales.quiz.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sales.quiz.converter.ProductDTO;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
public class OrderProduct {

    @EmbeddedId
    @JsonIgnore
    private OrderProductPK pk;

    @Column(nullable = false)
    @Min(value = 0)
    private Integer quantity;




    @Transient
    public Double getTotalPrice() {
        return getProduct().getPrice() * getQuantity();
    }
    @Transient
    public Product getProduct() {
        return this.pk.getProduct();
    }
}
