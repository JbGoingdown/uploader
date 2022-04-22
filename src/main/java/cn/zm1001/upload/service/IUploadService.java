package cn.zm1001.upload.service;

import cn.zm1001.upload.config.UploaderConfig;
import org.apache.commons.fileupload.FileItem;

import java.util.Map;

/**
 * @author Dongd_Zhou
 * @desc 文件上传服务接口
 */
public interface IUploadService {
    /**
     * 文件上传服务
     *
     * @param config 配置信息
     * @param params 请求参数
     * @param files  上传文件
     * @return 访问地址
     */
    String uploader(UploaderConfig config, Map<String, String> params, Map<String, FileItem> files);
}
