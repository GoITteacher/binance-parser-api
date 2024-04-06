package com.pikimell.binanceparserapiv1.service;

import com.pikimell.binanceparserapiv1.model.SymbolItem;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

public class ExcelService {
    static public byte[] createExcelFile(List<SymbolItem> symbols) throws IOException {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Symbols");

        Row headerRow = sheet.createRow(0);
        String[] headers = {"ID", "Symbol", "Price", "Change"};
        CellStyle headerCellStyle = workbook.createCellStyle();
        Font headerFont = workbook.createFont();
        headerFont.setBold(true);
        headerCellStyle.setFont(headerFont);

        for (int i = 0; i < headers.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(headers[i]);
            cell.setCellStyle(headerCellStyle);
        }

        int rowNum = 1;
        for (SymbolItem symbol : symbols) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(symbol.getId());
            row.createCell(1).setCellValue(symbol.getSymbol());
            row.createCell(2).setCellValue(symbol.getPrice());
            row.createCell(3).setCellValue(symbol.getChange());
        }

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        workbook.write(outputStream);
        workbook.close();

        return outputStream.toByteArray();
    }
}
