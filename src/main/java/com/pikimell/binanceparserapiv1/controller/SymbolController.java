package com.pikimell.binanceparserapiv1.controller;

import com.pikimell.binanceparserapiv1.model.SymbolItem;
import com.pikimell.binanceparserapiv1.service.ExcelService;
import com.pikimell.binanceparserapiv1.service.SymbolsParser;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/symbols")
@ResponseBody
@CrossOrigin(origins = "http://localhost:5173/")
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

    @GetMapping("/top-gainers")
    public ResponseEntity<List<SymbolItem>> getTopGainers() throws IOException, ParseException {
        try{
            SymbolsParser symbolsParser = new SymbolsParser();
            List<SymbolItem> symbols = symbolsParser.getTopGainers();
            return ResponseEntity.ok(symbols);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return ResponseEntity.internalServerError().build();
        }

    }

    @GetMapping("/top-losers")
    public ResponseEntity<List<SymbolItem>> getTopLosers() throws IOException, ParseException {
        try{
            SymbolsParser symbolsParser = new SymbolsParser();
            List<SymbolItem> symbols = symbolsParser.getTopLosers();
            return ResponseEntity.ok(symbols);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return ResponseEntity.internalServerError().build();
        }

    }

    @GetMapping("/top-volume")
    public ResponseEntity<List<SymbolItem>> getTopVolume() throws IOException, ParseException {
        try{
            SymbolsParser symbolsParser = new SymbolsParser();
            List<SymbolItem> symbols = symbolsParser.getTopVolume();
            return ResponseEntity.ok(symbols);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/save")
    public ResponseEntity<byte[]> test(@RequestParam(required = false) String type) throws IOException, ParseException {
        try{
            SymbolsParser symbolsParser = new SymbolsParser();
            List<SymbolItem> symbols = switch (type) {
                case "TOP_GAINERS" -> symbolsParser.getTopGainers();
                case "TOP_LOSERS" -> symbolsParser.getTopLosers();
                case "TOP_VOLUME" -> symbolsParser.getTopVolume();
                default -> symbolsParser.getHotCoins();
            };

            byte[] createdFile = ExcelService.createExcelFile(symbols);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            headers.setContentDispositionFormData("attachment", "wishlist.xlsx");
            return ResponseEntity.ok().headers(headers).body(createdFile);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }
}
