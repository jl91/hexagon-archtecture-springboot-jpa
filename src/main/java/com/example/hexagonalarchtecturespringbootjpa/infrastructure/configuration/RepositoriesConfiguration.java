package com.example.hexagonalarchtecturespringbootjpa.infrastructure.configuration;

import com.example.hexagonalarchtecturespringbootjpa.domain.product.repositories.ProductRepositoryService;
import com.example.hexagonalarchtecturespringbootjpa.infrastructure.database.repositories.ProductCrudRepository;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = {"com.example.hexagonalarchtecturespringbootjpa.infrastructure.database"})
@EntityScan(basePackages = {"com.example.hexagonalarchtecturespringbootjpa.infrastructure.database"})
public class RepositoriesConfiguration {

    @Bean
    public ProductRepositoryService productRepositoryService(
            final ProductCrudRepository repository
    ){
        return new ProductRepositoryService(repository);
    }
}
