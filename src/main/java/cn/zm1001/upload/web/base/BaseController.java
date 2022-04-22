package cn.zm1001.upload.web.base;

import cn.zm1001.upload.config.UploaderConfig;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

/**
 * @author Dongd_Zhou
 * @desc 公共处理
 */
public class BaseController {
    // 超过1MB将存放在临时文件中，否则存放在内存
    private static final int SIZE_THRESHOLD = 1048576;

    protected UploaderConfig uploaderConfig;

    @Autowired
    public void setUploaderConfig(UploaderConfig uploaderConfig) {
        this.uploaderConfig = uploaderConfig;
    }

    /**
     * 提取上传信息
     *
     * @param req    文件上传请求
     * @param params 提取出的请求参数
     * @param files  提取出的请求文件
     * @throws FileUploadException 读取上传请求失败
     */
    protected void uploadFile(HttpServletRequest req, Map<String, String> params, Map<String, FileItem> files) throws FileUploadException {
        DiskFileItemFactory factory = new DiskFileItemFactory();
        factory.setSizeThreshold(SIZE_THRESHOLD);
        List<FileItem> items = new ServletFileUpload(factory).parseRequest(req);
        String key;
        for (FileItem item : items) {
            key = item.getFieldName();
            if (item.isFormField()) {
                try {
                    params.put(key, item.getString("UTF-8"));
                } catch (UnsupportedEncodingException ignored) {
                }
            } else {
                files.put(key, item);
            }
        }
    }
}
