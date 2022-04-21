package cn.zm1001.upload.parse;

import cn.zm1001.upload.exception.UploaderException;
import cn.zm1001.upload.utils.FileUtils;
import cn.zm1001.util.common.StringUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.Map;

/**
 * @author Dongd_Zhou
 * @desc 文件处理
 */
public abstract class BaseUploader {
    private final static char[] chars = "0123456789abcdef".toCharArray();

    /**
     * 获取文件存放目录
     *
     * @param param 参数
     * @return 文件信息
     */
    protected String getSavePath(Map<String, String> param){
        String path = param.get("path");
        if (StringUtils.isEmpty(path)) {
            throw new UploaderException("未指定文件存放路径");
        }
        if (!FileUtils.isValidFilename(path)) {
            throw new UploaderException("非法文件路径");
        }
        return path;
    }

    /**
     * 获取默认文件
     *
     * @param files 文件映射
     * @return 文件信息
     */
    protected FileItem getDefaultFile(Map<String, FileItem> files){
        return getFile(files, "file");
    }

    /**
     * 获取文件
     *
     * @param files 文件映射
     * @param fileName 文件名
     * @return 文件信息
     */
    protected FileItem getFile(Map<String, FileItem> files, String fileName){
        FileItem fileItem = files.get(fileName);
        if (null == fileItem)
            throw new UploaderException("文件丢失");
        if (StringUtils.isEmpty(fileItem.getName()))
            throw new UploaderException("文件名丢失");
        return fileItem;
    }

    /**
     * 生成随机路径
     * 规则：a0/f9(字符范围：a~f和0~9)
     *
     * @return 随机路径
     */
    protected String randomPath(){
        String randomString = RandomStringUtils.random(4, chars);
        return randomString.substring(0, 2) + "/" + randomString.substring(2, 4);
    }
}
