package com.clinic_project.clinic_system.infra_layer.external.excel_api;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;

public class ExcelReader {
    static XSSFWorkbook workbook;

    public static void main(String[] args){
        try {
            workbook = new XSSFWorkbook("C:\\Users\\Sherif\\Downloads\\تعاقدات فرع محرم بيه.xlsx");
           // sheet =workbook.getSheet("Inaya Egypt");
            for (Sheet sheet : workbook) {
                System.out.println("Processing sheet: " + sheet.getSheetName());
                int rowcount = sheet.getPhysicalNumberOfRows();
                for(int rowNumber =1; rowNumber<rowcount; rowNumber++){
                    Row row = sheet.getRow(rowNumber);

                    if(row != null && row.getCell(3) != null){
                    String name = getCellValueAsString(row.getCell(0));
                    String mobileNumber = getCellValueAsString(row.getCell(3));
                    String insurance = getCellValueAsString(row.getCell(2));
                    String branch = getCellValueAsString(row.getCell(1));
                    System.out.println(name+ ","+mobileNumber+","+insurance+","+branch);
            }}}
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            // Close workbook in finally block
            if (workbook != null) {
                try {
                    workbook.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
            
    }
}

    private static String getCellValueAsString(Cell cell) {
        if (cell == null) {
            return null;
        }

        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue().trim();
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    return cell.getDateCellValue().toString();
                } else {
                    // Format numeric value properly
                    double numericValue = cell.getNumericCellValue();
                    // Check if it's an integer value
                    if (numericValue == Math.floor(numericValue)) {
                        return String.valueOf((long) numericValue);
                    } else {
                        return String.valueOf(numericValue);
                    }
                }
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            case FORMULA:
                try {
                    return cell.getStringCellValue();
                } catch (Exception e) {
                    try {
                        return String.valueOf(cell.getNumericCellValue());
                    } catch (Exception ex) {
                        return cell.getCellFormula();
                    }
                }
            case BLANK:
            default:
                return null;
        }
    }
    }


