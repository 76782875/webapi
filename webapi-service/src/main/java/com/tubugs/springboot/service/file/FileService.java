package com.tubugs.springboot.service.file;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * Created by xuzhang on 2017/9/1.
 */
public interface FileService {
    String savePicture(String tag, MultipartFile file) throws IOException;

    String savePicture(String tag, String link) throws IOException;
}

