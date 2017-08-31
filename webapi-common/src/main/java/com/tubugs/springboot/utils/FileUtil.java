package com.tubugs.springboot.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

/**
 * Created by xuzhang on 2017/8/23.
 */
public class FileUtil {
    private static final Logger logger = LoggerFactory.getLogger(FileUtil.class);

    public static String readTxt(String filePath, String encoding) {
        try {
            StringBuilder sb = new StringBuilder();
            File file = new File(filePath);
            if (file.isFile() && file.exists()) {
                InputStreamReader read = new InputStreamReader(new FileInputStream(file), encoding);
                BufferedReader bufferedReader = new BufferedReader(read);
                String line = null;
                while ((line = bufferedReader.readLine()) != null) {
                    sb.append(line);
                }
                read.close();
            } else {
                logger.error("找不到指定的文件:" + filePath);
            }
            return sb.toString();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return null;
        }
    }
}
