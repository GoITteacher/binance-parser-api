package com.pikimell.binanceparserapiv1.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "Transaction")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "symbol")
    private String symbol;

    @Column(name = "amount")
    private Double amount;

    @Column(name = "price")
    private Double price;

    @Column(name = "type_transaction")
    private String typeTransaction;

    @Column(name = "create_transaction")
    private String timestamp;

    @Override
    public String toString() {
        String result = "";
        result += "Transaction [id=" + id + ", symbol=" + symbol + ", amount=" + amount;
        result += ", price=" + price + ", timestamp=" + timestamp + "]";
        return result;
    }
}
