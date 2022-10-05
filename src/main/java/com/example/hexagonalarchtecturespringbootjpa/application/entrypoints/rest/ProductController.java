package com.example.hexagonalarchtecturespringbootjpa.application.entrypoints.rest;

import com.example.hexagonalarchtecturespringbootjpa.application.entrypoints.rest.dto.ProductDTO;
import com.example.hexagonalarchtecturespringbootjpa.domain.product.entities.ProductDomainEntity;
import com.example.hexagonalarchtecturespringbootjpa.domain.product.repositories.ProductRepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("products")
public class ProductController {

    @Autowired
    private ProductRepositoryService productRepositoryService;

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<ProductDTO> listAll(
            @RequestParam(name = "page") final Optional<BigInteger> page,
            @RequestParam(name = "size") final Optional<BigInteger> size
    ) {
        return productRepositoryService.findAll(
                        page.orElse(BigInteger.ZERO),
                        size.orElse(BigInteger.TEN)
                )
                .stream()
                .map(ProductDTO::toProductResponseDTO)
                .collect(Collectors.toList())
                ;
    }

    @GetMapping("/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public ProductDTO byId(
            @PathVariable("id") final UUID id
    ) {
        final var result = productRepositoryService.findOne(id);
        return ProductDTO.toProductResponseDTO(result);
    }

    @PostMapping()
    @ResponseStatus(code = HttpStatus.CREATED)
    public ProductDTO newProduct(
            @RequestBody ProductDTO data
    ) {
        final var result = productRepositoryService.save(data.toProductDomainEntity());
        return ProductDTO.toProductResponseDTO(result);
    }

    @PutMapping("/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public ProductDTO updateProduct(
            @PathVariable("id") final UUID id,
            @RequestBody ProductDTO data
    ) {
        data.setUuid(id);
        final var result = productRepositoryService.update(data.toProductDomainEntity());
        return ProductDTO.toProductResponseDTO(result);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void updateProduct(
            @PathVariable("id") final UUID id
    ) {
        productRepositoryService.delete(new ProductDomainEntity().setUuid(id));
    }
}
