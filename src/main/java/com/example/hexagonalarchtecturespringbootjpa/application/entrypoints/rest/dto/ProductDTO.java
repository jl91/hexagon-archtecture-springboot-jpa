package com.example.hexagonalarchtecturespringbootjpa.application.entrypoints.rest.dto;

import com.example.hexagonalarchtecturespringbootjpa.domain.product.entities.ProductDomainEntity;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.UUID;


@Data
@Accessors(chain = true)
public class ProductDTO {

    private UUID uuid;

    private String SKU;

    private String name;

    public static ProductDTO toProductResponseDTO(
            final ProductDomainEntity productDomainEntity
    ) {
        return new ProductDTO()
                .setName(productDomainEntity.getName())
                .setSKU(productDomainEntity.getSKU())
                .setUuid(productDomainEntity.getUuid());
    }

    public static ProductDomainEntity toProductDomainEntity(
            final ProductDTO productDTO
    ) {
        return new ProductDomainEntity()
                .setUuid(productDTO.getUuid())
                .setSKU(productDTO.getSKU())
                .setName(productDTO.getName())
                ;

    }

    public ProductDomainEntity toProductDomainEntity() {
        return ProductDTO.toProductDomainEntity(this);

    }
}
