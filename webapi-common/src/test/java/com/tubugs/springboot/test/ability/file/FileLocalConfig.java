package com.tubugs.springboot.test.ability.file;

import com.tubugs.springboot.ability.file.FileAbility;
import com.tubugs.springboot.ability.file.FileAbilityLocalImpl;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

/**
 * Created by xuzhang on 2017/9/2.
 */
@TestConfiguration
public class FileLocalConfig {
    @Bean
    public FileAbility push() {
        return new FileAbilityLocalImpl();
    }
}
