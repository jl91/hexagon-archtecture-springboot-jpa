package com.example.hexagonalarchtecturespringbootjpa.infrastructure.database.repositories;

import com.example.hexagonalarchtecturespringbootjpa.infrastructure.database.entities.ProductDatabaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProductCrudRepository extends JpaRepository<ProductDatabaseEntity, UUID> {
}
