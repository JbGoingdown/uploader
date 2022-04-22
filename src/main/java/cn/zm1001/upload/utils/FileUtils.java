package cn.zm1001.upload.utils;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

import javax.activation.MimetypesFileTypeMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

/**
 * @author Dongd_Zhou
 * @desc 文件工具类
 */
public class FileUtils {
    private static final String FILEPATH_PATTERN = "(/[a-zA-Z0-9_-]+)+";
    private static final String FILENAME_PATTERN = "[a-zA-Z0-9_\\-\\|\\.\\u4e00-\\u9fa5]+";

    /**
     * 文件路径称验证
     *
     * @param filePath 文件路径
     * @return true 正常 false 非法
     */
    public static boolean isValidFilePath(String filePath) {
        return filePath.matches(FILEPATH_PATTERN);
    }

    /**
     * 文件名称验证
     *
     * @param filename 文件名称
     * @return true 正常 false 非法
     */
    public static boolean isValidFilename(String filename) {
        return filename.matches(FILENAME_PATTERN);
    }

    /**
     * 获取文件类型
     * 例如: readme.txt, 返回: txt
     *
     * @param file 文件
     * @return 后缀（不含".")
     */
    public static String getFileType(File file) {
        if (null == file) {
            return StringUtils.EMPTY;
        }
        return getFileType(file.getName());
    }

    /**
     * 获取文件类型
     * 例如: readme.txt, 返回: txt
     *
     * @param fileName 文件名
     * @return 后缀（不含".")
     */
    public static String getFileType(String fileName) {
        int separatorIndex = fileName.lastIndexOf(".");
        if (separatorIndex < 0) {
            return StringUtils.EMPTY;
        }
        return fileName.substring(separatorIndex + 1).toLowerCase();
    }

}
