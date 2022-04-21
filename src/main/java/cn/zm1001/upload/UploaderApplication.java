package cn.zm1001.upload;

import cn.zm1001.upload.config.UploaderConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * @author Dongd_Zhou
 * @desc 启动类
 */
@EnableConfigurationProperties(UploaderConfig.class)
@SpringBootApplication
public class UploaderApplication {
    public static void main(String[] args) {
        SpringApplication.run(UploaderApplication.class, args);
    }
}
