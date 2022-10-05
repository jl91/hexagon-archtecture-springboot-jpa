package com.example.hexagonalarchtecturespringbootjpa.infrastructure.database.entities;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.UUID;


@Data
@Accessors(chain = true)
@Entity
@Table(name = "products")
public class ProductDatabaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "uuid")
    private UUID uuid;

    @Column(name = "SKU")
    private String SKU;

    @Column(name = "name")
    private String name;

}
