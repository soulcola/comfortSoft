package com.petrtitov.comfortSoft.util;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ExcelUtils {

    public static List<Integer> readNumbersFromXlsx(String filePath) throws IOException {
        List<Integer> numbers = new ArrayList<>();
        try (FileInputStream fis = new FileInputStream(filePath);
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheetAt(0);
            for (Row row : sheet) {
                Cell cell = row.getCell(0);
                if (cell != null) {
                    if (cell.getCellType() == CellType.NUMERIC) {
                        numbers.add((int) cell.getNumericCellValue());
                    } else if (cell.getCellType() == CellType.STRING) {
                        try {
                            numbers.add(Integer.parseInt(cell.getStringCellValue()));
                        } catch (NumberFormatException e) {
                            throw new IllegalArgumentException("Can't convert value \"" +
                                    cell.getStringCellValue() + "\" to Integer.");
                        }
                    }
                }
            }
        }
        return numbers;
    }
}
