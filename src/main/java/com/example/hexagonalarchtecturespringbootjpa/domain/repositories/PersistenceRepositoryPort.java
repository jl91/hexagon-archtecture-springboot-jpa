package com.example.hexagonalarchtecturespringbootjpa.domain.repositories;


import java.math.BigInteger;
import java.util.List;
import java.util.UUID;

public interface PersistenceRepositoryPort<T> {

    T save(T data);

    T update(T data);

    boolean delete(T data);

    List<T> findAll(BigInteger page, BigInteger size);

    T findOne(UUID id);
}
