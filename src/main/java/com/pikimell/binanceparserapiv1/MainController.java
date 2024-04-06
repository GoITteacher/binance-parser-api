package com.pikimell.binanceparserapiv1;

import com.pikimell.binanceparserapiv1.model.SymbolItem;
import com.pikimell.binanceparserapiv1.service.SymbolsParser;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@Controller
public class MainController {


    @GetMapping("/symbol")
    @ResponseBody
    String getWelcome(){
        try{
            SymbolsParser symbolsParser = new SymbolsParser();

            return "{\"message\": \"Hello world\"}";
        }catch (Exception e){
            System.out.println(e);
            return "{\"error_message\": \"Something went wrong\"}";
        }
    }

    @PostMapping("/symbol")
    public String saveSymbol(@RequestBody SymbolItem symbol) {
        System.out.println(symbol);

        if (symbol != null) {
            System.out.println(symbol.toString());
            return "Received: " + symbol.toString();
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Request body is empty");
        }
    }
}
