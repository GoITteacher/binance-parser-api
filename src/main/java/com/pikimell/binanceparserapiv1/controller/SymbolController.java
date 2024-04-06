package com.pikimell.binanceparserapiv1.controller;

import com.pikimell.binanceparserapiv1.model.SymbolItem;
import com.pikimell.binanceparserapiv1.service.SymbolsParser;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/symbols")
@ResponseBody
public class SymbolController {
    @GetMapping("/hot-coins")
    public ResponseEntity<List<SymbolItem>> getHotCoins() throws IOException, ParseException {
        try{
            SymbolsParser symbolsParser = new SymbolsParser();
            List<SymbolItem> symbols = symbolsParser.getHotCoins();
            return ResponseEntity.ok(symbols);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return ResponseEntity.internalServerError().build();
        }

    }
}
