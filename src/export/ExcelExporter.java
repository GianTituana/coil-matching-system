package com.ejemplo.coilmatching.export;

import com.ejemplo.coilmatching.model.Curso;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@Component
public class ExcelExporter {

    public byte[] exportarMatchingResultados(List<Curso> cursos) throws IOException {
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Matching Results");

            // Create header row
            Row headerRow = sheet.createRow(0);
            CellStyle headerStyle = createHeaderStyle(workbook);
            
            String[] headers = {"Código", "Nombre", "Idioma", "Temática", "Universidad", "País"};
            for (int i = 0; i < headers.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(headers[i]);
                cell.setCellStyle(headerStyle);
            }

            // Fill data rows
            int rowNum = 1;
            for (Curso curso : cursos) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(curso.getCodigo());
                row.createCell(1).setCellValue(curso.getNombre());
                row.createCell(2).setCellValue(curso.getIdioma());
                row.createCell(3).setCellValue(curso.getTematica());
                row.createCell(4).setCellValue(curso.getUniversidad() != null ? 
                    curso.getUniversidad().getNombre() : "");
                row.createCell(5).setCellValue(curso.getUniversidad() != null ? 
                    curso.getUniversidad().getPais() : "");
            }

            // Auto-size columns
            for (int i = 0; i < headers.length; i++) {
                sheet.autoSizeColumn(i);
            }

            // Write to byte array
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            workbook.write(outputStream);
            return outputStream.toByteArray();
        }
    }

    private CellStyle createHeaderStyle(Workbook workbook) {
        CellStyle style = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setBold(true);
        style.setFont(font);
        style.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        return style;
    }
}
