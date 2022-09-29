package org.test.services.interfaces;

import org.springframework.web.multipart.MultipartFile;
import org.test.models.FileInfoDto;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;

public interface StorageService {
    public CompletableFuture<Long> store(MultipartFile file);
}
