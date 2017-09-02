package com.tubugs.springboot.ability.file;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * Created by xuzhang on 2017/9/1.
 */
public interface FileAbility {
    String savePicture(String tag, MultipartFile file) throws IOException;

    String savePicture(String tag, String link) throws IOException;
}

