package com.pikimell.binanceparserapiv1.service;

import com.pikimell.binanceparserapiv1.model.Transaction;
import com.pikimell.binanceparserapiv1.repositories.TransactionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {
    @Autowired
    private TransactionRepo transactionRepo;

    public List<Transaction> getTransactions() {
        return transactionRepo.findAll();
    }

    public Transaction saveTransaction(Transaction transaction) {
    return transactionRepo.save(transaction);
    }
}
