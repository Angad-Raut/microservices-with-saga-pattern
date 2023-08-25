package com.projectx.saga.productservice.command.api.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "product_details")
public class ProductDetails {
    @Id
    private String productId;
    private String productName;
    private String productDescription;
    private Double productPrice;
    private Integer productQuantity;
}
