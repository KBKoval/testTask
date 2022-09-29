package org.test.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.test.services.interfaces.StorageService;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("files")
public class FilesController {
    private final StorageService storeService;
    private static final String TEMPLATE = "Идентификатор задания %-20s  !";

    @Autowired
    public FilesController(StorageService storeService) {
        this.storeService = storeService;
    }


    @PostMapping("/import")
    public ResponseEntity<String> handleFileUpload(@RequestParam("file") MultipartFile file) throws InterruptedException, ExecutionException {
        if (file.isEmpty()) {
            return new ResponseEntity<>("Пожайлуста, загрузите файл !", HttpStatus.BAD_REQUEST);
        }
        CompletableFuture<Long> idJob = storeService.store(file);
        return new ResponseEntity<>(String.format(TEMPLATE, Long.toString(idJob.get())), HttpStatus.OK);
    }
}
