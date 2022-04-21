package cn.zm1001.upload.parse.img;

import cn.zm1001.upload.config.UploaderConfig;
import cn.zm1001.upload.exception.UploaderException;
import cn.zm1001.upload.parse.BaseUploader;
import cn.zm1001.upload.utils.ImageUtils;
import org.apache.commons.fileupload.FileItem;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Map;

/**
 * @author Dongd_Zhou
 * @desc 图片上传
 */
public class ImgUploader extends BaseUploader {


    protected String write(UploaderConfig conf, Map<String, String> params, Map<String, FileItem> files) throws IOException {
        String path = getSavePath(params);
        FileItem fileItem = getDefaultFile(files);
        final BufferedImage image = ImageUtils.loadImg(fileItem);
        if (null == image) {
            throw new UploaderException("读取图片失败");
        }

        return null;
    }
}
