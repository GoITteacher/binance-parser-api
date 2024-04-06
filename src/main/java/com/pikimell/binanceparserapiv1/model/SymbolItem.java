package com.pikimell.binanceparserapiv1.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.UUID;


@Entity
public class SymbolItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;

    private String symbol;

    private String price;

    private String change;

    public SymbolItem(){

    }

    public SymbolItem(String symbol, String price, String change) {
        this.id = UUID.randomUUID().toString();
        this.symbol = symbol;
        this.price = price;
        this.change = change;
    }

    public String getId() {
        return id;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getChange() {
        return change;
    }

    public void setChange(String change) {
        this.change = change;
    }

    @Override
    public String toString() {
        String result = "==========================\n";
        result += "Symbol: " + symbol + "\n";
        result += "Price: " + price + "\n";
        result += "Change: " + change + "\n";
        result += "==========================\n";
        return result;
    }
}
