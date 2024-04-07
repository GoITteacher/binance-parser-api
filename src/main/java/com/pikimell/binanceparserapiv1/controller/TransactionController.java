package com.pikimell.binanceparserapiv1.controller;


import com.pikimell.binanceparserapiv1.model.Transaction;
import com.pikimell.binanceparserapiv1.service.TransactionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;


@RestController
@RequestMapping("/transaction")
@ResponseBody
@CrossOrigin(origins = "http://localhost:5173/")
public class TransactionController {
    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping("")
    public ResponseEntity<List<Transaction>> getTransaction() throws IOException, ParseException {
        try{
            List<Transaction> transactions = transactionService.getTransactions();
            return ResponseEntity.ok(transactions);
        }catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping("")
    public ResponseEntity<Transaction> createTransaction(@RequestBody Transaction transaction) throws IOException, ParseException {
        try{
            System.out.println(transaction);
            Transaction savedTransaction = transactionService.saveTransaction(transaction);
            return ResponseEntity.ok(savedTransaction);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return ResponseEntity.internalServerError().build();
        }

    }
}
