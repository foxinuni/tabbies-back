package dev.downloadablefox.tabbies.webserver.services.medicine;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import dev.downloadablefox.tabbies.webserver.entities.Medicine;

@Service
public class MedicineExcelService {
    public List<Medicine> loadMedicinesFromResource() throws Exception {
        List<Medicine> medicines = new ArrayList<>();

        ClassPathResource resource = new ClassPathResource("MEDICAMENTOS_VETERINARIA.xlsx");

        try (InputStream inputStream = resource.getInputStream();
             Workbook workbook = new XSSFWorkbook(inputStream)) {

            Sheet sheet = workbook.getSheetAt(0);

            for (Row row : sheet) {
                if (row.getRowNum() == 0) continue; // Saltar cabecera

                String name = getCellStringValue(row.getCell(0));
                Double sellPrice = getCellDoubleValue(row.getCell(1));
                Double buyPrice = getCellDoubleValue(row.getCell(2));
                Integer stock = getCellIntegerValue(row.getCell(3));
                Integer sold = getCellIntegerValue(row.getCell(4));

                if (name == null || sellPrice == null || buyPrice == null || stock == null || sold == null) {
                    System.out.println("Fila inválida en Excel. Saltando fila " + (row.getRowNum() + 1));
                    continue;
                }

                Medicine medicine = new Medicine(name, buyPrice, sellPrice, stock, sold);
                medicines.add(medicine);
            }
        }

        return medicines;
    }

    private String getCellStringValue(Cell cell) {
        if (cell == null) return null;

        if (cell.getCellType() == CellType.STRING) {
            return cell.getStringCellValue();
        } else if (cell.getCellType() == CellType.NUMERIC) {
            return String.valueOf(cell.getNumericCellValue());
        } else if (cell.getCellType() == CellType.FORMULA) {
            return cell.getRichStringCellValue().getString();
        }

        return null;
    }

    private Double getCellDoubleValue(Cell cell) {
        if (cell == null) return null;

        if (cell.getCellType() == CellType.NUMERIC) {
            return cell.getNumericCellValue();
        } else if (cell.getCellType() == CellType.STRING) {
            try {
                String value = cell.getStringCellValue().replace("$", "").replace(",", "").trim();
                return Double.parseDouble(value);
            } catch (NumberFormatException e) {
                System.err.println("No se pudo convertir a Double: " + cell.getStringCellValue());
                return null;
            }
        }

        return null;
    }

    private Integer getCellIntegerValue(Cell cell) {
        Double value = getCellDoubleValue(cell);
        return value != null ? value.intValue() : null;
    }
}
