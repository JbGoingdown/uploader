package cn.zm1001.upload.parse.img;

import cn.zm1001.upload.config.UploaderConfig;
import cn.zm1001.upload.exception.UploaderException;
import cn.zm1001.upload.utils.ImageUtils;
import cn.zm1001.util.common.StringUtils;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.fileupload.FileItem;

import java.awt.image.BufferedImage;
import java.util.Map;

/**
 * @author Dongd_Zhou
 * @desc 用户图像上传
 */
public class UserHeaderUploader extends AbstractImgUploader {
    @Override
    public String saveImg(UploaderConfig config, Map<String, String> params, Map<String, FileItem> files) {
        final FileItem fileItem = getDefaultFile(files);
        BufferedImage image = ImageUtils.loadImg(fileItem);
        if (null == image) {
            throw new UploaderException("未能读取图片信息");
        }
        boolean useRealName = true;
        String fileName = params.get("fileName");
        if (StringUtils.isEmpty(fileName)) {
            useRealName = false;
            fileName = fileItem.getName();
        }
        final String savePath = getSavePath(params);
        // 支持图片截取，设置截取后起始坐标及图片宽度、高度
        int x = MapUtils.getIntValue(params, "x", 0);
        int y = MapUtils.getIntValue(params, "y", 0);
        int w = MapUtils.getIntValue(params, "w", 0);
        int h = MapUtils.getIntValue(params, "h", 0);
        image = ImageUtils.thumbnail(image, 400, 400);
        int imgW = image.getWidth();
        int imgH = image.getHeight();
        imgW = x + w > imgW ? imgW - x : w;
        imgH = y + h > imgH ? imgH - y : h;
        image = image.getSubimage(x, y, imgW, imgH);
        image = ImageUtils.resizeImage(image, 100, 100);
        return write(config, image, savePath, fileName, useRealName);
    }
}
