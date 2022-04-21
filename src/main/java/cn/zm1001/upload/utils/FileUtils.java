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
    private static final String FILENAME_PATTERN = "[a-zA-Z0-9_\\-\\|\\.\\u4e00-\\u9fa5]+";

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

    /**
     * 获取文件类型
     *
     * @param photoByte 文件字节码
     * @return 后缀（不含".")
     */
    public static String getImageExtension(byte[] photoByte) {
        String extendName = "jpg";
        if ((photoByte[0] == 71) && (photoByte[1] == 73) && (photoByte[2] == 70) && (photoByte[3] == 56)
                && ((photoByte[4] == 55) || (photoByte[4] == 57)) && (photoByte[5] == 97)) {
            extendName = "gif";
        } else if ((photoByte[6] == 74) && (photoByte[7] == 70) && (photoByte[8] == 73) && (photoByte[9] == 70)) {
            extendName = "jpg";
        } else if ((photoByte[0] == 66) && (photoByte[1] == 77)) {
            extendName = "bmp";
        } else if ((photoByte[1] == 80) && (photoByte[2] == 78) && (photoByte[3] == 71)) {
            extendName = "png";
        }
        return extendName;
    }

    /**
     * 文件名称验证
     *
     * @param filename 文件名称
     * @return true 正常 false 非法
     */
    public static boolean isValidFilename(String filename) {
        return filename.matches(FILENAME_PATTERN) && !filename.contains("..");
    }

    /**
     * 读取指定文件的byte数组
     *
     * @param fileName 文件全路径名称
     * @param os       输出流
     */
    public static void readBytes(String fileName, OutputStream os) throws IOException {
        FileInputStream fis = null;
        try {
            File file = new File(fileName);
            if (!file.exists()) {
                throw new FileNotFoundException(fileName);
            }
            fis = new FileInputStream(file);
            byte[] b = new byte[4098];
            int length;
            while ((length = fis.read(b)) > 0) {
                os.write(b, 0, length);
            }
        } finally {
            IOUtils.close(os);
            IOUtils.close(fis);
        }
    }

    /**
     * 写数据到文件中
     *
     * @param data     数据
     * @param fileName 目标文件
     * @throws IOException IO异常
     */
    public static void writeBytes(byte[] data, String fileName) throws IOException {
        writeBytes(data, new File(fileName));
    }

    /**
     * 写数据到文件中
     *
     * @param data 数据
     * @param file 目标文件
     * @throws IOException IO异常
     */
    public static void writeBytes(byte[] data, File file) throws IOException {
        FileOutputStream fos = null;
        try {
            if (!file.exists()) {
                if (!file.getParentFile().exists()) {
                    file.getParentFile().mkdirs();
                }
            }
            fos = new FileOutputStream(file);
            fos.write(data);
        } finally {
            IOUtils.close(fos);
        }
    }

    /**
     * 删除文件
     *
     * @param filePath 文件
     * @return 是否删除成功
     */
    public static boolean deleteFile(String filePath) {
        File file = new File(filePath);
        // 路径为文件且不为空则进行删除
        if (file.isFile() && file.exists()) {
            return file.delete();
        }
        return false;
    }

    /**
     * 下载文件名重新编码
     *
     * @param request  请求对象
     * @param fileName 文件名
     * @return 编码后的文件名
     */
    public static String encodeFileName(HttpServletRequest request, String fileName) {
        final String agent = request.getHeader("USER-AGENT");
        try {
            if (agent.contains("MSIE")) {
                // IE浏览器
                fileName = URLEncoder.encode(fileName, StandardCharsets.UTF_8.name()).replace("+", " ");
            } else if (agent.contains("Firefox")) {
                // 火狐浏览器
                fileName = new String(fileName.getBytes(), StandardCharsets.ISO_8859_1);
            } else if (agent.contains("Chrome")) {
                // google浏览器
                fileName = URLEncoder.encode(fileName, StandardCharsets.UTF_8.name());
            } else {
                // 其它浏览器
                fileName = URLEncoder.encode(fileName, StandardCharsets.UTF_8.name());
            }
        } catch (UnsupportedEncodingException ignored) {

        }
        return fileName;
    }

    /**
     * 设置响应头
     *
     * @param response 响应对象
     * @param fileName 真实文件名
     * @param fileSize 文件大小
     */
    public static void setResponseHeader(HttpServletRequest request, HttpServletResponse response, String fileName, int fileSize) {
        String encodedFileName = encodeFileName(request, fileName);

        String type = new MimetypesFileTypeMap().getContentType(fileName);
        // 设置ContentType，即告诉客户端所发送的数据属于什么类型
        response.setContentType(type);
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        response.setContentLength(fileSize);
        response.addHeader("Access-Control-Allow-Origin", "*");
        response.addHeader("Access-Control-Expose-Headers", "Content-Disposition,download-filename");
        response.setHeader("Content-Disposition", "attachment;filename=" + encodedFileName);
        response.setHeader("download-filename", encodedFileName);
    }
}
