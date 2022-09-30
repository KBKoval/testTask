package org.test.services.implemets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.test.components.StorageProperties;
import org.test.exeptions.StorageException;
import org.test.services.interfaces.StorageService;


import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@Service
public class StorageServiceImpl implements StorageService {
    private static final Logger LOGGER = LoggerFactory.getLogger(StorageServiceImpl.class);
    private final StorageProperties properties;
    private final Path storeLocation;
    @Qualifier("asycJobLauncher")
    private final JobLauncher jobLauncher;

    private final Job processJob;

    @Autowired
    public StorageServiceImpl(JobLauncher jobLauncher, Job processJob, StorageProperties properties) {
        this.jobLauncher = jobLauncher;
        this.processJob = processJob;
        this.properties = properties;
        this.storeLocation = Paths.get(properties.getLocation());
    }

    // TODO ЗДЕСЬ КОСТЫЛЬ. Сначала сохраняем multipartFile во временной директории,  в JobParamerts указываем к нем путь
    // Не нашел метода передать  в job MultipartFile
    @Async("asyncExecutor")
    public CompletableFuture<Long> store(MultipartFile multipartFile) {
        try {
            String excelFileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
            Path uploadDir = getUploadDirLocation();//.resolve("section.xlcx");
            LOGGER.info("" + Files.exists(uploadDir));
            if (!Files.exists(uploadDir)) Files.createDirectories(uploadDir);
            Path load = uploadDir.resolve(excelFileName);
            try (InputStream inputStream = multipartFile.getInputStream()) {
                Files.copy(inputStream, load, StandardCopyOption.REPLACE_EXISTING);
            }
            LOGGER.info(load.toString());
            Map<String, JobParameter> parameters = new HashMap<>();
            JobParameter time = new JobParameter(System.currentTimeMillis());
            JobParameter fileParsePath = new JobParameter(load.toString());
            parameters.put("time", time);
            parameters.put("path", fileParsePath);
            JobParameters jobParameters = new JobParameters(parameters);
            JobExecution jobExecution = jobLauncher.run(processJob, jobParameters);
            LOGGER.info("job id " + jobExecution.getJobInstance().getInstanceId());
            return CompletableFuture.completedFuture( jobExecution.getJobId() );//jobExecution.getStatus()
        } catch (IOException e) {
            throw new StorageException("Failed to store file.", e);
        } catch (Exception ex){
            throw new StorageException("Failed to store file.", ex);
        }
    }

    private Path getUploadDirLocation() {
        return Paths.get(this.storeLocation.toString()).toAbsolutePath().normalize();
    }
}
