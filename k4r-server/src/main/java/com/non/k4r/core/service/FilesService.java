package com.non.k4r.core.service;

import com.mybatisflex.core.service.IService;
import com.non.k4r.core.entity.Files;
import org.springframework.web.multipart.MultipartFile;

public interface FilesService extends IService<Files> {

    Files uploadFile(MultipartFile file);
}