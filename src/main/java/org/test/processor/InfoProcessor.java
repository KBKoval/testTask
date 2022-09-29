package org.test.processor;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;
import org.test.models.FileInfoDto;


public class InfoProcessor implements ItemProcessor<FileInfoDto, FileInfoDto>{
    @Override
    public FileInfoDto process(FileInfoDto fileInfo){
        return fileInfo;
    }
}
