package com.pikimell.binanceparserapiv1.repositories;

import com.pikimell.binanceparserapiv1.model.Transaction;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface TransactionRepo extends CrudRepository<Transaction, Long> {
    List<Transaction> findAll();
    Optional<Transaction> findById(long id);
}
