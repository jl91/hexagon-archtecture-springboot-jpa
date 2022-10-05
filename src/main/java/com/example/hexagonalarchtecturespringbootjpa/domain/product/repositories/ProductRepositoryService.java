package com.example.hexagonalarchtecturespringbootjpa.domain.product.repositories;

import com.example.hexagonalarchtecturespringbootjpa.domain.product.entities.ProductDomainEntity;
import com.example.hexagonalarchtecturespringbootjpa.domain.repositories.PersistenceRepositoryPort;
import com.example.hexagonalarchtecturespringbootjpa.infrastructure.database.entities.ProductDatabaseEntity;
import com.example.hexagonalarchtecturespringbootjpa.infrastructure.database.repositories.ProductCrudRepository;
import org.springframework.data.domain.PageRequest;

import java.math.BigInteger;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class ProductRepositoryService implements PersistenceRepositoryPort<ProductDomainEntity> {

    private ProductCrudRepository repository;

    public ProductRepositoryService(
            final ProductCrudRepository repository
    ) {
        this.repository = repository;
    }

    @Override
    public ProductDomainEntity save(
            final ProductDomainEntity data
    ) {

        final var databaseEntity = new ProductDatabaseEntity()
                .setSKU(data.getSKU())
                .setName(data.getName());

        final var result = repository.save(databaseEntity);

        return new ProductDomainEntity()
                .setUuid(result.getUuid())
                .setName(result.getName())
                .setSKU(result.getSKU());
    }

    @Override
    public ProductDomainEntity update(
            final ProductDomainEntity data
    ) {

        final var databaseEntity =  findProduct(data.getUuid());

        databaseEntity
                .setSKU(data.getSKU())
                .setName(data.getName());

        final var result = repository.save(databaseEntity);

        return new ProductDomainEntity()
                .setUuid(data.getUuid())
                .setName(data.getName())
                .setSKU(result.getSKU())
                ;
    }

    @Override
    public boolean delete(
            final ProductDomainEntity data
    ) {
        final var result = findProduct(data.getUuid());

        repository.delete(result);
        return true;
    }

    @Override
    public List<ProductDomainEntity> findAll(
            final BigInteger page,
            final BigInteger size

    ) {
        final var pageRequest = PageRequest.of(page.intValue(), size.intValue());
        final var result = repository.findAll(pageRequest);
        return result
                .stream()
                .map(
                        current -> new ProductDomainEntity().setUuid(current.getUuid())
                                .setSKU(current.getSKU())
                                .setName(current.getName())
                )
                .collect(Collectors.toList());
    }

    @Override
    public ProductDomainEntity findOne(
            final UUID id
    ) {
        final var result = findProduct(id);
        return new ProductDomainEntity()
                .setUuid(result.getUuid())
                .setSKU(result.getSKU())
                .setName(result.getName())
                ;
    }

    private ProductDatabaseEntity findProduct(final UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException(
                        String.format("Resource not found for Product Id: %s", id)
                ));

    }
}
