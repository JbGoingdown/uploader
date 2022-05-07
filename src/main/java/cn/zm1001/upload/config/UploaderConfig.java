package cn.zm1001.upload.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author Dongd_Zhou
 * @desc 配置文件
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "uploader.file")
public class UploaderConfig {
    /** 文件域名 */
    private String domain;
    /** 文件存放路径 */
    private String path;
}
