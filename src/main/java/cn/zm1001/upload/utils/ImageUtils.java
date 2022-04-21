package cn.zm1001.upload.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.fileupload.FileItem;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author Dongd_Zhou
 * @desc 图片工具类
 */
@Slf4j
public class ImageUtils {
    /**
     * 解析图片
     *
     * @param file 文件包
     * @return 图片
     */
    public static BufferedImage loadImg(FileItem file) {
        try (InputStream is = new ByteArrayInputStream(file.get())) {
            return ImageIO.read(is);
        } catch (IOException e) {
            log.error("#loadImg# ## ## {}", file.getName(), e);
        }
        return null;
    }

    /**
     * 等比压缩图片
     *
     * @param img    图片信息
     * @param width  高度
     * @param height 宽度
     * @return 压缩后的图片
     */
    public static BufferedImage thumbnail(BufferedImage img, int width, int height) {
        int imgW = img.getWidth();
        int imgH = img.getHeight();
        double ratio;
        if (imgW != width || imgH != height) {
            if (imgW >= imgH) {
                ratio = width / (double) imgW;
            } else {
                ratio = height / (double) imgH;
            }
            imgW = (int) (imgW * ratio);
            imgH = (int) (imgH * ratio);
        }
        BufferedImage canvas = new BufferedImage(imgW, imgH, img.getTransparency());
        Graphics2D g2 = (Graphics2D) canvas.getGraphics();
        try {
            g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.drawImage(img.getScaledInstance(imgW, imgH, Image.SCALE_SMOOTH), 0, 0, null);
        } finally {
            if (null != g2) {
                g2.dispose();
            }
        }
        return canvas;
    }
}
