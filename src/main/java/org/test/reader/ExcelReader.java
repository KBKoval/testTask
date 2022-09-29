package org.test.reader;


import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.test.configurations.SpringBatchConfig;
import org.test.exeptions.StorageException;
import org.test.models.FileInfoDto;
import org.test.processor.InfoProcessor;
import org.test.services.implemets.ExcellImportExportServiceImpl;
import org.test.services.interfaces.ExcellImportExportService;

import java.io.IOException;

/*
Как и в случае с RowMapper, который принимает низкоуровневую конструкцию, такую ​​как ResultSetи возвращает Object, обработка плоского файла требует той же конструкции для преобразования Stringстроки в Object, как показано в следующем определении интерфейса:

public interface LineMapper<T> {

    T mapLine(String line, int lineNumber) throws Exception;
TODO На будущее. Учесть
}
 */
@Component("excel-reader")
@Scope("step")
public class ExcelReader implements ItemReader<FileInfoDto> {
    private final ExcellImportExportService parser = new ExcellImportExportServiceImpl();
    @Value("#{jobParameters['path']}")
    private String path;

    public ExcelReader() {
        try {
            parser.readExcell(path);
        } catch (IOException ex) {
            throw new StorageException("Failed reader file.", ex);
        }
    }
    public FileInfoDto read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException{
       return parser.readExcell(path);
    }
}
