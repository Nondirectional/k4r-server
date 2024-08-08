package com.non.k4r.core.service.impl;

import com.mybatisflex.spring.service.impl.ServiceImpl;
import com.non.k4r.core.entity.Files;
import com.non.k4r.core.mapper.FilesMapper;
import com.non.k4r.core.service.FilesService;
import com.non.k4r.framework.constant.CustomException;
import com.non.k4r.framework.constant.ErrorCodes;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
public class FilesServiceImpl extends ServiceImpl<FilesMapper, Files> implements FilesService {
    private static final String FILE_SAVE_ROOT_PATH = "./data/files/";

    @Override
    public Files uploadFile(MultipartFile multipartFile) {
        if (!StringUtils.hasText(multipartFile.getOriginalFilename())) {
            throw new CustomException(ErrorCodes.UNKNOWN_ERROR, "Invalid file name");
        }
        File file = new File(STR."\{FILE_SAVE_ROOT_PATH}\{System.currentTimeMillis()}\{multipartFile.getOriginalFilename()}");
        if (file.mkdirs()) {
            try {
                multipartFile.transferTo(file);
                Files po = Files
                        .create()
                        .setFilename(multipartFile.getOriginalFilename())
                        .setExtension(multipartFile.getOriginalFilename().substring(multipartFile.getOriginalFilename().lastIndexOf(".") + 1))
                        .setSavePath(file.getAbsolutePath());
                if (po.save()) {
                    return po;
                } else {
                    throw new CustomException(ErrorCodes.UNKNOWN_ERROR, "Failed to save file record to database");
                }
            } catch (IOException e) {
                throw new CustomException(ErrorCodes.UNKNOWN_ERROR, "Failed to save file to disk");
            }
        } else {
            throw new CustomException(ErrorCodes.UNKNOWN_ERROR, "Failed to create directory for file upload");
        }
    }
}
