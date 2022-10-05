package com.example.hexagonalarchtecturespringbootjpa.domain.product.entities;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.UUID;

@Data
@Accessors(chain = true)
public class ProductDomainEntity {

    private UUID uuid;

    private String SKU;

    private String name;
}
