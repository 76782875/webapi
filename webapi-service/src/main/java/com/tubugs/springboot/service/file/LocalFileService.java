package com.tubugs.springboot.service.file;

import com.tubugs.springboot.utils.UUIDUtil;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

/**
 * Created by xuzhang on 2017/9/1.
 */
public class LocalFileService implements FileService {
    @Value("file.localfile.root")
    private String root;

    @Override
    public String savePicture(String tag, MultipartFile file) throws IOException {
        String path = String.format("/%s/%s.png", tag, UUIDUtil.generate());
        File folder = new File(root + path);
        if (!folder.getParentFile().exists()) {
            folder.getParentFile().mkdirs();
        }
        file.transferTo(folder);
        return path;
    }

    @Override
    public String savePicture(String tag, String link) throws IOException {
        String path = String.format("/%s/%s.png", tag, UUIDUtil.generate());
        HttpClient httpclient = new DefaultHttpClient();
        HttpGet http = new HttpGet(link);
        HttpResponse response = httpclient.execute(http);
        HttpEntity entity = response.getEntity();
        InputStream inputStream = entity.getContent();
        File file = new File(root + path);
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        //写文件
        byte[] bs = new byte[1024];
        OutputStream os = new FileOutputStream(file.getPath());
        int len;
        while ((len = inputStream.read(bs)) != -1) {
            os.write(bs, 0, len);
        }
        return path;
    }
}
