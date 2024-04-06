package com.pikimell.binanceparserapiv1.service;

import com.pikimell.binanceparserapiv1.model.SymbolItem;
import jakarta.persistence.Convert;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class SymbolsParser {

    public Document getDocument() throws IOException {
        String BASE_URL = "https://www.binance.com/ru-UA/markets/trading_data/rankings";
        String userAgent = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/122.0.0.0 Safari/537.36 OPR/108.0.0.0";
        Document doc = Jsoup.connect(BASE_URL).userAgent(userAgent).get();
        return doc;
    }

    public Element getBaseContainer() throws IOException {
        Document doc = getDocument();
        String base_selector = ".css-4jws3m > .css-yyvsvt > .css-7q72aq";
        Element baseContainer = doc.selectFirst(base_selector);
        assert baseContainer != null;
        return baseContainer;
    }

    public List<SymbolItem> getHotCoins() throws IOException {
        Element baseContainer = getBaseContainer();
        Element hotCoinsContainer = baseContainer.selectFirst("div>div.rounded-xl:nth-child(1)");
        assert hotCoinsContainer != null;
        List<Element> elements =  hotCoinsContainer.select(".css-1qyk0y6");
        elements.remove(0);
        return parseSymbols(elements);
    }
    public List<SymbolItem> getTopGainers() throws IOException {
        Element baseContainer = getBaseContainer();
        Element topGainers = baseContainer.selectFirst("div>div.rounded-xl:nth-child(2)");
        assert topGainers != null;
        List<Element> elements =  topGainers.select(".css-1qyk0y6");
        elements.remove(0);
        return parseSymbols(elements);
    }
    public List<SymbolItem> getTopLosers() throws IOException {
        Element baseContainer = getBaseContainer();
        Element topLosers = baseContainer.selectFirst("div>div.rounded-xl:nth-child(3)");
        assert topLosers != null;
        List<Element> elements =  topLosers.select(".css-1qyk0y6");
        elements.remove(0);
        return parseSymbols(elements);
    }
    public List<SymbolItem> getTopVolume() throws IOException {
        Element baseContainer = getBaseContainer();
        Element topValues = baseContainer.selectFirst("div>div.rounded-xl:nth-child(4)");
        assert topValues != null;
        List<Element> elements =  topValues.select(".css-1qyk0y6");
        elements.remove(0);
        return parseSymbols(elements);
    }

    SymbolItem parseSymbol(Element symbol){
        String name = symbol.child(1).text();
        String priceStr = symbol.child(2).text();
        String change = symbol.child(3).text();
        return new SymbolItem(name, priceStr, change);
    }

    List<SymbolItem> parseSymbols(List<Element> symbols){
        List<SymbolItem> symbolItems = new ArrayList<>();
        for (Element symbol : symbols) {
            symbolItems.add(parseSymbol(symbol));
        }
        return symbolItems;
    }
}
