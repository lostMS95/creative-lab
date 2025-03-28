package com.clab.backend.util;


import org.springframework.stereotype.Component;


import java.io.IOException;

@Component
public class ExcelUtil {/*

    *//**
     * 엑셀 파일을 로드합니다.
     * @param excelFile 엑셀 파일
     * @return Workbook 객체
     * @throws IOException 파일 로드 중 오류 발생 시
     *//*
    public Workbook loadExcelFile(File excelFile) throws IOException {
        try (FileInputStream fis = new FileInputStream(excelFile)) {
            return new XSSFWorkbook(fis);
        }
    }

    *//**
     * 엑셀 파일을 저장합니다.
     * @param workbook 수정된 Workbook 객체
     * @param filePath 저장할 파일 경로
     * @throws IOException 파일 저장 중 오류 발생 시
     *//*
    public void saveExcelFile(Workbook workbook, String filePath) throws IOException {
        try (FileOutputStream fos = new FileOutputStream(filePath)) {
            workbook.write(fos);
        }
    }

    *//**
     * 마지막 행을 기준으로 새로운 행을 추가하고 데이터를 입력합니다.
     * @param sheet 엑셀 시트
     * @param newRowData 추가할 행 데이터
     *//*
    public void addRowWithData(Sheet sheet, Object[] newRowData) {
        int lastRowNum = findLastRowInColumn(sheet, 0);
        Row lastRow = sheet.getRow(lastRowNum);
        Row newRow = sheet.createRow(lastRowNum + 1);

        copyRowStyles(lastRow, newRow);

        for (int i = 0; i < newRowData.length; i++) {
            Cell newCell = newRow.getCell(i);
            if (newRowData[i] instanceof String) {
                newCell.setCellValue((String) newRowData[i]);
            } else if (newRowData[i] instanceof Number) {
                newCell.setCellValue(((Number) newRowData[i]).doubleValue());
            } else if (newRowData[i] instanceof Boolean) {
                newCell.setCellValue((Boolean) newRowData[i]);
            } else {
                newCell.setCellValue(newRowData[i].toString());
            }
        }
    }

    *//**
     * 마지막 데이터를 가진 행의 인덱스를 찾습니다.
     * @param sheet 엑셀 시트
     * @param columnIndex 열 인덱스
     * @return 마지막 데이터 행 인덱스
     *//*
    public int findLastRowInColumn(Sheet sheet, int columnIndex) {
        int lastRow = sheet.getLastRowNum();
        for (int i = lastRow; i >= 0; i--) {
            Row row = sheet.getRow(i);
            if (row != null) {
                Cell cell = row.getCell(columnIndex);
                if (cell != null && cell.getCellType() != CellType.BLANK) {
                    return i;
                }
            }
        }
        return 0;
    }

    *//**
     * 행의 스타일을 복사합니다.
     * @param sourceRow 원본 행
     * @param targetRow 대상 행
     *//*
    private void copyRowStyles(Row sourceRow, Row targetRow) {
        if (sourceRow == null) return;

        for (int i = 0; i < sourceRow.getLastCellNum(); i++) {
            Cell sourceCell = sourceRow.getCell(i);
            if (sourceCell == null) continue;

            Cell targetCell = targetRow.createCell(i);
            CellStyle newCellStyle = targetCell.getSheet().getWorkbook().createCellStyle();
            newCellStyle.cloneStyleFrom(sourceCell.getCellStyle());
            targetCell.setCellStyle(newCellStyle);

            if (sourceCell.getCellType() == CellType.STRING) {
                targetCell.setCellValue(sourceCell.getStringCellValue());
            } else if (sourceCell.getCellType() == CellType.NUMERIC) {
                targetCell.setCellValue(sourceCell.getNumericCellValue());
            } else if (sourceCell.getCellType() == CellType.BOOLEAN) {
                targetCell.setCellValue(sourceCell.getBooleanCellValue());
            } else if (sourceCell.getCellType() == CellType.FORMULA) {
                targetCell.setCellFormula(sourceCell.getCellFormula());
            }
        }
    }*/
}