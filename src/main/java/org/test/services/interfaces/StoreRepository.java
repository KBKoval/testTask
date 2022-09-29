package org.test.services.interfaces;

import org.test.models.FileInfoDto;

public interface StoreRepository {
    public void save(FileInfoDto fileInfo);
}
