package com.non.k4r.module.file;

import com.non.k4r.core.entity.Files;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.FileNameMap;
import java.net.URLConnection;
import java.util.Optional;

@RestController
public class FileController {
    @GetMapping("/file/file/download/{fileId}")
    public void downloadFile(@PathVariable Long fileId, HttpServletResponse response) {
        Optional.ofNullable(
                        Files
                                .create()
                                .setId(fileId)
                                .one())
                .ifPresent(po -> {
                    // 根据扩展名获取Content-Type
                    FileNameMap map = URLConnection.getFileNameMap();
                    response.setHeader("Content-Disposition", STR."attachment; filename=\{po.getFilename()}");
                    response.setHeader("Content-Type", map.getContentTypeFor(po.getFilename()));
                    File file = new File(po.getSavePath());
                    response.setContentLength((int) file.length());
                    try (FileInputStream fis = new FileInputStream(file)) {
                        response.getOutputStream().write(fis.readAllBytes());
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });
    }
}
