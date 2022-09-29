package org.test.services.implemets;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.test.models.ClassesDto;
import org.test.models.FileInfoDto;
import org.test.models.SectionsDto;
import org.test.services.interfaces.ExcellImportExportService;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class ExcellImportExportServiceImpl implements ExcellImportExportService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ExcellImportExportServiceImpl.class);
    
    public FileInfoDto readExcell(String pathLoadedFile) throws IOException {
        Path file = Paths.get(pathLoadedFile).toAbsolutePath().normalize();//.toFile();
        String excelFileName = StringUtils.cleanPath(file.toString());
        long size =  Files.size(file);
        FileInputStream fileInputStream = new FileInputStream(file.toFile());
        Workbook workbook = getWorkbook(fileInputStream,excelFileName);
        Sheet firstSheet = workbook.getSheetAt(0);
        Iterator<Row> iterator = firstSheet.iterator();
        List<SectionsDto> sections = new ArrayList<>();
        while (iterator.hasNext()) {
            Row nextRow = iterator.next();
            Iterator<Cell> cellIterator = nextRow.cellIterator();
            SectionsDto section = new SectionsDto();
            List<ClassesDto> classes = new ArrayList();
            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();
                String value = getCellValue(cell);
                int columnIndex = cell.getColumnIndex();
                if (value != null) {
                    ClassesDto classDto = new ClassesDto();
                    if (columnIndex == 1) {
                        section.setSectionName(value);
                    } else if (columnIndex % 2 == 0) {
                        classDto.setClassName(value);
                    } else classDto.setCode(value);
                    classes.add(classDto);
                }
                section.setClassesDto(classes);
            }

            LOGGER.info(section.getSectionName());
        }

        workbook.close();
        fileInputStream.close();
        FileInfoDto infoFile = new FileInfoDto();
        infoFile.setAuthor("anonymus");


        byte[] buffer = new byte[fileInputStream.available()];
        fileInputStream.read(buffer);
        infoFile.setContext(buffer);
        infoFile.setFileName(excelFileName);
        infoFile.setSize(size);

        infoFile.setSections(sections);
        return infoFile;
    }

    private String getCellValue(Cell cell) {
        if (cell.getCellType() == CellType.STRING) {
            System.out.print(cell.getStringCellValue() + "--");
            return cell.getStringCellValue();
        } else if (cell.getCellType() == CellType.NUMERIC) {
            System.out.print(cell.getNumericCellValue() + "--");
            return Double.toString(cell.getNumericCellValue());
        } else if (cell.getCellType() == CellType.BOOLEAN) {
            System.out.print(cell.getBooleanCellValue() + "--");
            return Boolean.toString(cell.getBooleanCellValue());
        }
        return null;
    }

    private Workbook getWorkbook(FileInputStream inputStream, String excelFileName) throws IOException {
        Workbook workbook = null;
        if (excelFileName.endsWith("xlsx")) {
            workbook = new XSSFWorkbook(inputStream);
        } else if (excelFileName.endsWith("xls")) {
            workbook = new HSSFWorkbook(inputStream);
        } else {
            throw new IllegalArgumentException("The specified file is not Excel file");
        }
        return workbook;
    }
}
