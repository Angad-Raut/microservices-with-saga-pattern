package com.projectx.saga.productservice.command.api.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateProductCommand {
    @TargetAggregateIdentifier
    private String productId;
    private String productName;
    private String productDescription;
    private Double productPrice;
    private Integer productQuantity;
}
