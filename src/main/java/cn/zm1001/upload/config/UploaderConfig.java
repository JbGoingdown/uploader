package cn.zm1001.upload.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author Dongd_Zhou
 * @desc 配置文件
 */
@Data
@Component
@ConfigurationProperties(prefix = "uploader")
public class UploaderConfig {
    /** 文件域名 */
    private String fileDomain;
    /** 文件存放路径 */
    private String filePath;
}
