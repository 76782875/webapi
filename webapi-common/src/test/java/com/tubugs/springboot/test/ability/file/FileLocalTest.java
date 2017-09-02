package com.tubugs.springboot.test.ability.file;

import com.tubugs.springboot.ability.file.FileAbility;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

/**
 * Created by xuzhang on 2017/9/2.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Import(FileLocalConfig.class)
public class FileLocalTest {

    @Autowired
    private FileAbility fileAbility;

    @Test
    public void savePicture() throws IOException {
        fileAbility.savePicture("download", "https://www.baidu.com/img/bd_logo1.png");
    }
}
