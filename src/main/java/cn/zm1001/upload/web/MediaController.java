package cn.zm1001.upload.web;

import cn.zm1001.upload.service.MediaServiceImpl;
import cn.zm1001.upload.web.base.BaseController;
import cn.zm1001.util.common.JacksonUtils;
import cn.zm1001.util.common.response.R;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Dongd_Zhou
 * @desc 图片上传
 */
@Slf4j
@RestController
public class MediaController extends BaseController {

    private MediaServiceImpl mediaService;

    @Autowired
    public void setMediaService(MediaServiceImpl mediaService) {
        this.mediaService = mediaService;
    }

    @PostMapping("/api/media")
    public R uploadMedia(HttpServletRequest req, HttpServletResponse rsp) {
        // 是否上传请求
        boolean isMultipart = ServletFileUpload.isMultipartContent(req);
        if (!isMultipart) {
            return R.error("非上传请求");
        }
        // 请求参数
        Map<String, String> params = new HashMap<>();
        // 上传文件
        Map<String, FileItem> files = new HashMap<>();

        try {
            uploadFile(req, params, files);
        } catch (FileUploadException e) {
            log.error("#img# ## ## {}", JacksonUtils.toJson(params), e);
            return R.error("上传失败");
        }
        // 媒体文件地址
        String url = mediaService.uploader(uploaderConfig, params, files);
        return R.success(url);
    }
}
