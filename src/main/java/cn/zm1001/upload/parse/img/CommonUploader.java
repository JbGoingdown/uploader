package cn.zm1001.upload.parse.img;

import cn.zm1001.upload.config.UploaderConfig;
import cn.zm1001.upload.exception.UploaderException;
import cn.zm1001.upload.utils.ImageUtils;
import cn.zm1001.util.common.StringUtils;
import org.apache.commons.fileupload.FileItem;

import java.awt.image.BufferedImage;
import java.util.Map;

/**
 * @author Dongd_Zhou
 * @desc 通用上传
 */
public class CommonUploader extends AbstractImgUploader {
    @Override
    public String saveImg(UploaderConfig config, Map<String, String> params, Map<String, FileItem> files) {
        final FileItem fileItem = getDefaultFile(files);
        final BufferedImage image = ImageUtils.loadImg(fileItem);
        if (null == image) {
            throw new UploaderException("未能读取图片信息");
        }
        final String fileName = StringUtils.firstNonBlank(params.get("fileName"), fileItem.getName());
        final String savePath = getSavePath(params);
        return write(config, image, savePath, fileName, false);
    }
}
